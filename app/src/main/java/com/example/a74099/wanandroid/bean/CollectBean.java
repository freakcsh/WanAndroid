package com.example.a74099.wanandroid.bean;

import java.util.List;

/**
 * Created by 74099 on 2018/8/29.
 */

public class CollectBean {
    private String curPage;

    private List<Datas> datas;

    private String offset;

    private boolean over;

    private String pageCount;

    private String size;

    private String total;

    public void setCurPage(String curPage) {
        this.curPage = curPage;
    }

    public String getCurPage() {
        return this.curPage;
    }

    public void setDatas(List<Datas> datas) {
        this.datas = datas;
    }

    public List<Datas> getDatas() {
        return this.datas;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getOffset() {
        return this.offset;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public boolean getOver() {
        return this.over;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getPageCount() {
        return this.pageCount;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return this.size;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return this.total;
    }

    public class Datas {
        private String author;

        private String chapterId;

        private String chapterName;

        private String courseId;

        private String desc;

        private String envelopePic;

        private int id;

        private String link;

        private String niceDate;

        private String origin;

        private int originId;

        private String publishTime;

        private String title;

        private String userId;

        private String visible;

        private String zan;

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthor() {
            return this.author;
        }

        public void setChapterId(String chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterId() {
            return this.chapterId;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public String getChapterName() {
            return this.chapterName;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public String getCourseId() {
            return this.courseId;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return this.desc;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public String getEnvelopePic() {
            return this.envelopePic;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getLink() {
            return this.link;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getNiceDate() {
            return this.niceDate;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getOrigin() {
            return this.origin;
        }

        public void setOriginId(int originId) {
            this.originId = originId;
        }

        public int getOriginId() {
            return this.originId;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getPublishTime() {
            return this.publishTime;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setVisible(String visible) {
            this.visible = visible;
        }

        public String getVisible() {
            return this.visible;
        }

        public void setZan(String zan) {
            this.zan = zan;
        }

        public String getZan() {
            return this.zan;
        }

        @Override
        public String toString() {
            return "Datas{" +
                    "author='" + author + '\'' +
                    ", chapterId=" + chapterId +
                    ", chapterName='" + chapterName + '\'' +
                    ", courseId=" + courseId +
                    ", desc='" + desc + '\'' +
                    ", envelopePic='" + envelopePic + '\'' +
                    ", id=" + id +
                    ", link='" + link + '\'' +
                    ", niceDate='" + niceDate + '\'' +
                    ", origin='" + origin + '\'' +
                    ", originId=" + originId +
                    ", publishTime=" + publishTime +
                    ", title='" + title + '\'' +
                    ", userId=" + userId +
                    ", visible=" + visible +
                    ", zan=" + zan +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CollectBean{" +
                "curPage=" + curPage +
                ", datas=" + datas +
                ", offset=" + offset +
                ", over=" + over +
                ", pageCount=" + pageCount +
                ", size=" + size +
                ", total=" + total +
                '}';
    }
}
