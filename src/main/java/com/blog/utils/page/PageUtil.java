package com.blog.utils.page;

public class PageUtil {
    private static int page = 0;
    public static int pageSize=10;
    private static int totalPage = 0;

    public static int getStartIndex(int totalSize, int currentPage) {
        page = currentPage ;

        if (page == 0)
            page = 1;
        int totalPage = (int) Math.ceil((totalSize + pageSize - 1) / pageSize);
        if (page > totalPage) {
            if (totalPage > 0)
                page = totalPage;
        }
        if (page == -1) {
            pageSize = -1;
            page = 1;
        }
        return (page - 1) * pageSize;
    }

    /**
     * 计算总页数
     * @param totalSize
     * @return
     */
    public static int getTotalPage(int totalSize){
        return (totalSize + pageSize - 1) / pageSize;
    }

    public static int getPage() {
        return page;
    }

    public static void setPage(int page) {
        PageUtil.page = page;
    }

    public static int getPageSize() {
        return pageSize;
    }

    public static void setPageSize(int pageSize) {
        PageUtil.pageSize = pageSize;
    }

    public static int getTotalPage() {
        return totalPage;
    }

    public static void setTotalPage(int totalPage) {
        PageUtil.totalPage = totalPage;
    }
}
