package com.prasannjeet.jira.rest.client;

import com.prasannjeet.jira.rest.core.domain.AttachmentMetaBean;
import com.prasannjeet.jira.rest.core.domain.IssuetypeBean;
import com.prasannjeet.jira.rest.core.domain.PriorityBean;
import com.prasannjeet.jira.rest.core.domain.StatusBean;
import com.prasannjeet.jira.rest.core.domain.field.CreateFieldBean;
import com.prasannjeet.jira.rest.core.domain.field.FieldBean;
import com.prasannjeet.jira.rest.core.domain.system.ConfigurationBean;
import com.prasannjeet.jira.rest.core.util.RestException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

/**
 * The SystemClient provides all Information about the Jira System Configuration
 */
public interface SystemClient {

    /**
     * Return the Configuration of the remote Jira Instanz
     *
     * @return ConfigurationBean
     */
    Future<ConfigurationBean> getConfiguration();

    /**
     * Returns a list of all issue types visible to the connected client.
     *
     * @return list of issue types
     */
    Future<List<IssuetypeBean>> getIssueTypes();

    /**
     * Returns a list of all statuses.
     *
     * @return list of statuses
     */
    Future<List<StatusBean>> getStates();


    /**
     * Returns a List of all Priority Object from the Remote Jira.
     *
     * @return
     */
    Future<List<PriorityBean>> getPriorities();


    /**
     * Return a List of all Field configure in Jira, standard and custom
     *
     * @return a List of FieldBean
     */
    Future<List<FieldBean>> getAllFields();


    /**
     * Return all Custom Field configure in the Jira
     *
     * @return a List of FieldBean
     */
    Future<List<FieldBean>> getAllCustomFields();

    /**
     * Return a Custom Field by Id
     *
     */
    Future<FieldBean> getCustomFieldById(String id);


    /**
     * Get the Attachment Meta Information for the jira instanz
     *
     * @return AttachmentMetaBean
     */
    Future<AttachmentMetaBean> getAttachmentMeta();

    /**
     * Creates a Custom Field
     *
     * @param fieldBean The CreateFieldBean with the create Informations
     * @return The created Field as FieldBean
     */
    Future<FieldBean> createCustomField(CreateFieldBean fieldBean);

}
