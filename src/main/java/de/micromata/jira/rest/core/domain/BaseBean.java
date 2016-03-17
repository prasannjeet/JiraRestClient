package de.micromata.jira.rest.core.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

/**
 * Author: Christian
 * Date: 12.12.2014.
 */
public class BaseBean implements Comparable<BaseBean> {

    @Expose
    private String expand;
    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private String self;

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    @Override
    public int compareTo(BaseBean o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(this);
    }
}
