package com.example.a74099.wanandroid.bean;

import java.util.List;

public class ArticleListBean {
    private int curPage;

    private List<Datas> datas ;

    private int offset;

    private boolean over;

    private int pageCount;

    private int size;

    private int total;

    public void setCurPage(int curPage){
        this.curPage = curPage;
    }
    public int getCurPage(){
        return this.curPage;
    }
    public void setDatas(List<Datas> datas){
        this.datas = datas;
    }
    public List<Datas> getDatas(){
        return this.datas;
    }
    public void setOffset(int offset){
        this.offset = offset;
    }
    public int getOffset(){
        return this.offset;
    }
    public void setOver(boolean over){
        this.over = over;
    }
    public boolean getOver(){
        return this.over;
    }
    public void setPageCount(int pageCount){
        this.pageCount = pageCount;
    }
    public int getPageCount(){
        return this.pageCount;
    }
    public void setSize(int size){
        this.size = size;
    }
    public int getSize(){
        return this.size;
    }
    public void setTotal(int total){
        this.total = total;
    }
    public int getTotal(){
        return this.total;
    }
    public class Datas {
        private String apkLink;

        private String author;

        private int chapterId;

        private String chapterName;

        private boolean collect;

        private int courseId;

        private String desc;

        private String envelopePic;

        private boolean fresh;

        private int id;

        private String link;

        private String niceDate;

        private String origin;

        private String projectLink;

        private String publishTime;

        private int superChapterId;

        private String superChapterName;

        private List<Tags> tags;

        private String title;

        private int type;

        private int userId;

        private int visible;

        private int zan;

        public void setApkLink(String apkLink) {
            this.apkLink = apkLink;
        }

        public String getApkLink() {
            return this.apkLink;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthor() {
            return this.author;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public int getChapterId() {
            return this.chapterId;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public String getChapterName() {
            return this.chapterName;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        public boolean getCollect() {
            return this.collect;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getCourseId() {
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

        public void setFresh(boolean fresh) {
            this.fresh = fresh;
        }

        public boolean getFresh() {
            return this.fresh;
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

        public void setProjectLink(String projectLink) {
            this.projectLink = projectLink;
        }

        public String getProjectLink() {
            return this.projectLink;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getPublishTime() {
            return this.publishTime;
        }

        public void setSuperChapterId(int superChapterId) {
            this.superChapterId = superChapterId;
        }

        public int getSuperChapterId() {
            return this.superChapterId;
        }

        public void setSuperChapterName(String superChapterName) {
            this.superChapterName = superChapterName;
        }

        public String getSuperChapterName() {
            return this.superChapterName;
        }

        public void setTags(List<Tags> tags) {
            this.tags = tags;
        }

        public List<Tags> getTags() {
            return this.tags;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return this.type;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getUserId() {
            return this.userId;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getVisible() {
            return this.visible;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }

        public int getZan() {
            return this.zan;
        }

        public class Tags {
            private String name;

            private String url;

            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return this.name;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getUrl() {
                return this.url;
            }

            @Override
            public String toString() {
                return "Tags{" +
                        "name='" + name + '\'' +
                        ", url='" + url + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Datas{" +
                    "apkLink='" + apkLink + '\'' +
                    ", author='" + author + '\'' +
                    ", chapterId=" + chapterId +
                    ", chapterName='" + chapterName + '\'' +
                    ", collect=" + collect +
                    ", courseId=" + courseId +
                    ", desc='" + desc + '\'' +
                    ", envelopePic='" + envelopePic + '\'' +
                    ", fresh=" + fresh +
                    ", id=" + id +
                    ", link='" + link + '\'' +
                    ", niceDate='" + niceDate + '\'' +
                    ", origin='" + origin + '\'' +
                    ", projectLink='" + projectLink + '\'' +
                    ", publishTime=" + publishTime +
                    ", superChapterId=" + superChapterId +
                    ", superChapterName='" + superChapterName + '\'' +
                    ", tags=" + tags +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", userId=" + userId +
                    ", visible=" + visible +
                    ", zan=" + zan +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ArticleListBean{" +
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
