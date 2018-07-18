package com.example.a74099.wanandroid.bean;

import java.util.List;

public class NavigationBean {
    private List<Articles> articles;

    private int cid;

    private String name;

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }

    public List<Articles> getArticles() {
        return this.articles;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getCid() {
        return this.cid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public class Articles {
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
            return "Articles{" +
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
        return "NavigationBean{" +
                "articles=" + articles +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                '}';
    }
}
