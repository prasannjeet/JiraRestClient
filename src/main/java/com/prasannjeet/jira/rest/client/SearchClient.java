package com.prasannjeet.jira.rest.client;

import com.prasannjeet.jira.rest.core.domain.JqlSearchResult;
import com.prasannjeet.jira.rest.core.domain.filter.FilterBean;
import com.prasannjeet.jira.rest.core.jql.JqlSearchBean;

import java.util.List;
import java.util.concurrent.Future;


/**
 * User: Christian Schulze Email: c.schulze@micromata.de Date: 31.07.2014
 */
public interface SearchClient {


    /**
     * Performs an extended search for issues given by the project.
     *
     * @return list of issues
     * @throws com.prasannjeet.jira.rest.core.util.RestException
     */
    Future<JqlSearchResult> searchIssues(JqlSearchBean jsb);

    /**
     * Create a new Search Filter for the logged in User
     *
     * @param filter
     * @return
     */
    Future<FilterBean> createSearchFilter(FilterBean filter);


    /**
     * Get favorite Filter for JqlSearch for the logged in User
     *
     * @return List of FilterBeans
     */
    Future<List<FilterBean>> getFavoriteFilter();

    /**
     * Get Filter by Id
     *
     * @param id the id of the filter
     * @return FilterBean
     */
    Future<FilterBean> getFilterById(String id);



}
