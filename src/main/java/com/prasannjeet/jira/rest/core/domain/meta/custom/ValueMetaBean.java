package com.prasannjeet.jira.rest.core.domain.meta.custom;

import com.google.gson.annotations.Expose;
import com.prasannjeet.jira.rest.core.domain.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cschulc on 17.03.16.
 */
public class ValueMetaBean extends BaseBean {

    @Expose
    private String value;

    @Expose
    private List<ValueMetaBean> children = new ArrayList<>();

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ValueMetaBean> getChildren() {
        return children;
    }

    public void setChildren(List<ValueMetaBean> children) {
        this.children = children;
    }
}
