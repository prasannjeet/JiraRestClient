package com.prasannjeet.jira.rest.client;

import com.prasannjeet.jira.rest.core.domain.ComponentBean;
import com.prasannjeet.jira.rest.core.domain.ProjectBean;
import com.prasannjeet.jira.rest.core.domain.VersionBean;
import com.prasannjeet.jira.rest.core.domain.meta.MetaBean;
import com.prasannjeet.jira.rest.core.util.RestException;

import java.util.List;
import java.util.concurrent.Future;

/**
 * The IssueClient provides all Informations for Jira Issues
 *
 * User: Christian Schulze c.schulze@micromata.de
 */
public interface ProjectClient {


    /**
     * Returns a list of all projects the logged in User can see..
     *
     * @return list of projects
     */
    Future<List<ProjectBean>> getAllProjects();

    /**
     * Returns a full representation of the project for the given key.
     *
     * @param projectKey = the project key
     * @return all informations for the project
     */
    Future<ProjectBean> getProjectByKey(final String projectKey);

    /**
     * Returns a list of all versions for a project.
     *
     * @param projectKey = the project key
     * @return list of versions
     */
    Future<List<VersionBean>> getProjectVersions(final String projectKey);


    /**
     * Returns a list of all components for a project.
     *
     * @param projectKey = the project key
     * @return list of components
     */
    Future<List<ComponentBean>> getProjectComponents(final String projectKey);


    /**
     * Return the Meta Data for the IssueTypes of a Project. This includes all possible IssueTypes and the Fields including the AllowedValues
     *
     * @param projectKey
     * @return
     */
    Future<MetaBean> getIssueTypesMetaForProject(final String projectKey);

}
