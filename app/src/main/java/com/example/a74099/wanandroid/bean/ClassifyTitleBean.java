package com.example.a74099.wanandroid.bean;

import java.util.List;

/**
 * Created by 74099 on 2018/8/22.
 */

public class ClassifyTitleBean {
    private List<Children> children;

    private String courseId;

    private String id;

    private String name;

    private String order;

    private String parentChapterId;

    private String visible;

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    public List<Children> getChildren() {
        return this.children;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return this.courseId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrder() {
        return this.order;
    }

    public void setParentChapterId(String parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public String getParentChapterId() {
        return this.parentChapterId;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getVisible() {
        return this.visible;
    }

    public class Children {

    }
}
