package com.silence.util;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class SensitiveFilter {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveFilter.class);

    private static final String REPLACEMENT = "***";

    private TrieNode root = new TrieNode();

    @PostConstruct
    public void init() {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("sensitive-word.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))
        ) {
            String keyword;
            while ((keyword = reader.readLine()) != null) {
                this.addKeyword(keyword);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addKeyword(String keyword) {
        TrieNode p = root;
        for (int i = 0; i < keyword.length(); i++) {
            char ch = keyword.charAt(i);
            if (p.getSubNode(ch) == null) {
                p.addSubNode(ch, new TrieNode());
            }
            p = p.getSubNode(ch);
        }
        p.setKeywordEnd(true);
    }

    public String filter(String text) {
        if (StringUtils.isBlank(text)) {
            return text;
        }

        TrieNode p = root;
        int left = 0;
        int right = 0;
        StringBuilder sb = new StringBuilder();

        while (left < text.length()) {
            if (right == text.length()) {
                sb.append(left++);
                continue;
            }

            char ch = text.charAt(right);

            if (isSymbol(ch)) {
                 if (p == root) {
                     sb.append(ch);
                     left++;
                 }
                 right++;
                 continue;
            }

            p = p.getSubNode(ch);
            if (p == null) {
                sb.append(text.charAt(left));
                right = ++left;
                p = root;
            } else if (p.isKeywordEnd()) {
                sb.append(REPLACEMENT);
                left = ++right;
                p = root;
            } else {
                right++;
            }
        }
        return sb.toString();
    }

    private boolean isSymbol(Character ch) {
        return !CharUtils.isAsciiAlphanumeric(ch) && (ch < 0x2E80 || ch > 0x9FFF);
    }

    public class TrieNode {

        private boolean isKeywordEnd = false;

        private Map<Character, TrieNode> subNodes = new HashMap<>();

        public boolean isKeywordEnd() {
            return isKeywordEnd;
        }

        public void setKeywordEnd(boolean keywordEnd) {
            isKeywordEnd = keywordEnd;
        }

        public void addSubNode(Character c, TrieNode node) {
            subNodes.put(c, node);
        }

        public TrieNode getSubNode(Character c) {
            return subNodes.get(c);
        }

    }

}
