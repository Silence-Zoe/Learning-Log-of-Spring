package com.silence.VO;

public class DiscussPostPageVO {
    private int pageNum = 1;
    private int pageSize = 10;
    private int rows;
    private String path;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        if (pageNum < 1) {
            pageNum = 1;
        }
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows >= 0) {
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getOffSet() {
        return (pageNum - 1) * pageSize;
    }

    public int getTotal() {
        return rows / pageSize + ((rows % pageSize == 0) ? 0 : 1);
    }

    public int getFrom() {
        if (pageNum >= getTotal() - 2) {
            return getTotal() - 4;
        }
        int from = pageNum - 2;
        return Math.max(from, 1);
    }

    public int getTo() {
        if (pageNum <= 3) {
            return 5;
        }
        int to = pageNum + 2;
        return Math.min(to, getTotal());
    }
}