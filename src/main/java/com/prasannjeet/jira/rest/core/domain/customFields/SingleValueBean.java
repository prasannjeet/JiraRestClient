package com.prasannjeet.jira.rest.core.domain.customFields;

/**
 * Created by cschulc on 22.02.16.
 */
public class SingleValueBean extends CustomFieldBaseBean {

    private ValueBean value;

    public ValueBean getValue() {
        return value;
    }

    public void setValue(ValueBean value) {
        this.value = value;
    }
}
