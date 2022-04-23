package com.silence.elasticsearch;

import com.silence.DO.DiscussPostDO;
import com.silence.NowcoderApplication;
import com.silence.mapper.DiscussPostMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = NowcoderApplication.class)
public class DiscussPostRepositoryTest {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private DiscussPostRepository discussPostRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testSave() {
        discussPostRepository.save(discussPostMapper.getById(241));
        discussPostRepository.save(discussPostMapper.getById(242));
        discussPostRepository.save(discussPostMapper.getById(243));
    }

    @Test
    public void testSaveList() {
        discussPostRepository.saveAll(discussPostMapper.listPage(101, 0, 100));
        discussPostRepository.saveAll(discussPostMapper.listPage(102, 0, 100));
        discussPostRepository.saveAll(discussPostMapper.listPage(103, 0, 100));
        discussPostRepository.saveAll(discussPostMapper.listPage(111, 0, 100));
        discussPostRepository.saveAll(discussPostMapper.listPage(112, 0, 100));
        discussPostRepository.saveAll(discussPostMapper.listPage(131, 0, 100));
        discussPostRepository.saveAll(discussPostMapper.listPage(132, 0, 100));
        discussPostRepository.saveAll(discussPostMapper.listPage(133, 0, 100));
        discussPostRepository.saveAll(discussPostMapper.listPage(134, 0, 100));
        discussPostRepository.saveAll(discussPostMapper.listPage(138, 0, 100));
        discussPostRepository.saveAll(discussPostMapper.listPage(145, 0, 100));
        discussPostRepository.saveAll(discussPostMapper.listPage(146, 0, 100));
        discussPostRepository.saveAll(discussPostMapper.listPage(11, 0, 100));
        discussPostRepository.saveAll(discussPostMapper.listPage(149, 0, 100));
        discussPostRepository.saveAll(discussPostMapper.listPage(161, 0, 100));
    }

    @Test
    public void testUpdate() {
        DiscussPostDO post = discussPostMapper.getById(231);
        post.setContent("看看你的");
        discussPostRepository.save(post);
    }

    @Test
    public void testDelete() {
        discussPostRepository.deleteById(231);
    }

    @Test
    public void testSearchByRepository() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery("互联网寒冬", "title", "content"))
                .withSort(SortBuilders.fieldSort("type").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("score").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC))
                .withPageable(PageRequest.of(0, 10))
                .withHighlightFields(
                        new HighlightBuilder.Field("title").preTags("<em>").postTags("</em>"),
                        new HighlightBuilder.Field("content").preTags("<em>").postTags("</em>")
                ).build();

        Page<DiscussPostDO> page = elasticsearchTemplate.queryForPage(searchQuery, DiscussPostDO.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                SearchHits hits = searchResponse.getHits();
                if (hits.getTotalHits() <= 0) {
                    return null;
                }

                List<DiscussPostDO> list = new ArrayList<>();
                for (SearchHit hit : hits) {
                    DiscussPostDO post = new DiscussPostDO();

                    String id = hit.getSourceAsMap().get("id").toString();
                    post.setId(Integer.valueOf(id));

                    String userId = hit.getSourceAsMap().get("userId").toString();
                    post.setUserId(Integer.valueOf(userId));

                    String title = hit.getSourceAsMap().get("title").toString();
                    post.setTitle(title);

                    String content = hit.getSourceAsMap().get("content").toString();
                    post.setContent(content);

                    String status = hit.getSourceAsMap().get("status").toString();
                    post.setStatus(Integer.valueOf(status));

                    String createTime = hit.getSourceAsMap().get("createTime").toString();
                    post.setCreateTime(new Date(Long.parseLong(createTime)));

                    String commentCount = hit.getSourceAsMap().get("commentCount").toString();
                    post.setCommentCount(Integer.valueOf(commentCount));

                    HighlightField titleField = hit.getHighlightFields().get("title");
                    if (titleField != null) {
                        post.setTitle(titleField.getFragments()[0].toString());
                    }

                    HighlightField contentField = hit.getHighlightFields().get("content");
                    if (contentField != null) {
                        post.setTitle(contentField.getFragments()[0].toString());
                    }

                    list.add(post);
                }

                return new AggregatedPageImpl(list, pageable,
                        hits.getTotalHits(), searchResponse.getAggregations(), searchResponse.getScrollId(), hits.getMaxScore());
            }
        });

        // Page<DiscussPostDO> page = discussPostRepository.search(searchQuery);
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        System.out.println(page.getNumber());
        System.out.println(page.getSize());
        for (var post : page) {
            System.out.println(post);
        }
    }

}
