package de.micromata.jira.rest.core.domain;

import com.google.gson.annotations.Expose;


public class CommentBean {

    @Expose
    private UserBean author;
    @Expose
    private String body;
    @Expose
    private String created;
    @Expose
    private String id;
    @Expose
    private String self;
    @Expose
    private UserBean updateAuthor;
    @Expose
    private String updated;

    public UserBean getAuthor() {
        return author;
    }

    public void setAuthor(UserBean author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public UserBean getUpdateAuthor() {
        return updateAuthor;
    }

    public void setUpdateAuthor(UserBean updateAuthor) {
        this.updateAuthor = updateAuthor;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
