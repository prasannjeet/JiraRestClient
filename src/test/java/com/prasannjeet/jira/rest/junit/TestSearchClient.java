package com.prasannjeet.jira.rest.junit;

import com.prasannjeet.jira.rest.core.domain.JqlSearchResult;
import com.prasannjeet.jira.rest.core.domain.filter.FilterBean;
import com.prasannjeet.jira.rest.core.jql.*;
import com.prasannjeet.jira.rest.core.util.RestException;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * User: Christian Schulze
 * Email: c.schulze@micromata.de
 * Date: 11.08.2014
 */
public class TestSearchClient extends BaseTest {

//    @Test
//    public void testSearchIssues() throws RestException, IOException, ExecutionException, InterruptedException {
//        JqlSearchBean jsb = new JqlSearchBean();
//        JqlBuilder builder = new JqlBuilder();
//        String jql = builder.addCondition(EField.PROJECT, EOperator.EQUALS, PROJECT_TO_SEARCH)
//                .and().addCondition(EField.STATUS, EOperator.EQUALS, STATUS_OPEN)
//                .orderBy(SortOrder.ASC, EField.CREATED);
//        jsb.setJql(jql);
//        jsb.addField(EField.ISSUE_KEY, EField.STATUS, EField.DUE, EField.SUMMARY, EField.ISSUE_TYPE, EField.PRIORITY, EField.UPDATED, EField.TRANSITIONS);
//        jsb.addExpand(EField.TRANSITIONS);
//        Future<JqlSearchResult> future = jiraRestClient.getSearchClient().searchIssues(jsb);
//        JqlSearchResult jqlSearchResult = future.get();
//        Assert.assertNotNull(jqlSearchResult);
//        Assert.assertEquals(jqlSearchResult.getIssues().size(), Math.min(jqlSearchResult.getTotal(), 50));
//        Assert.assertTrue(jqlSearchResult.getTotal() > 0);
//
//
//    }
//
//    @Test
//    public void testSearchIssueWithMultipleValues() throws IOException, RestException, ExecutionException, InterruptedException {
//        JqlSearchBean jsb = new JqlSearchBean();
//        JqlBuilder builder = new JqlBuilder();
//        String jql = builder.addCondition(EField.PROJECT, EOperator.EQUALS, PROJECT_TO_SEARCH)
//                .and().addCondition(EField.STATUS, EOperator.IN, STATUS_OPEN, STATUS_IN_PROGRESS)
//                .orderBy(SortOrder.ASC, EField.CREATED);
//        jsb.setJql(jql);
//        jsb.addField(EField.ISSUE_KEY, EField.STATUS, EField.DUE, EField.SUMMARY, EField.ISSUE_TYPE, EField.PRIORITY, EField.UPDATED, EField.TRANSITIONS);
//        jsb.addExpand(EField.TRANSITIONS);
//        Future<JqlSearchResult> future = jiraRestClient.getSearchClient().searchIssues(jsb);
//        JqlSearchResult jqlSearchResult = future.get();
//        Assert.assertNotNull(jqlSearchResult);
//        Assert.assertEquals(jqlSearchResult.getIssues().size(), Math.min(jqlSearchResult.getTotal(), 50));
//        Assert.assertTrue(jqlSearchResult.getTotal() > 0);
//
//    }
//
//    @Test
//    public void testCountIssues() throws ExecutionException, InterruptedException, IOException, RestException {
//        JqlSearchBean jsb = new JqlSearchBean();
//        JqlBuilder builder = new JqlBuilder();
//        String jql = builder.addCondition(EField.PROJECT, EOperator.EQUALS, PROJECT_TO_SEARCH).build();
//        jsb.setJql(jql);
//        jsb.addField(EField.ISSUE_KEY, EField.STATUS, EField.DUE, EField.ISSUE_TYPE);
//        Future<JqlSearchResult> future = jiraRestClient.getSearchClient().searchIssues(jsb);
//        JqlSearchResult jqlSearchResult = future.get();
//        Assert.assertNotNull(jqlSearchResult);
//        Assert.assertEquals(jqlSearchResult.getIssues().size(), Math.min(jqlSearchResult.getTotal(), 50));
//        Assert.assertTrue(jqlSearchResult.getTotal() > 0);
//    }
//
//
//    @Test
//    public void testCreateFilter() {
//        FilterBean filter = new FilterBean();
//        filter.setName(PROJECT_TO_SEARCH+" Project");
//        filter.setDescription("A Filter for the "+PROJECT_TO_SEARCH+" Project");
//        filter.setFavourite(Boolean.TRUE);
//        filter.setJql("project = "+PROJECT_TO_SEARCH);
//        final Future<FilterBean> future = jiraRestClient.getSearchClient().createSearchFilter(filter);
//        final FilterBean filterBean;
//        try {
//            filterBean = future.get();
//            Assert.assertNotNull(filterBean);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//            final Throwable cause = e.getCause();
//            cause.printStackTrace();
//        }
//
//    }
//
//    @Test
//    public void testGetFilterForLoggedInUser() throws ExecutionException, InterruptedException {
//        final Future<List<FilterBean>> future = jiraRestClient.getSearchClient().getFavoriteFilter();
//        final List<FilterBean> filterBeans = future.get();
//        Assert.assertNotNull(filterBeans);
//        Assert.assertFalse(filterBeans.isEmpty());
//        String filterId = filterBeans.get(0).getId();
//        testGetFilterById(filterId);
//    }
//
//    private void testGetFilterById(String id) throws ExecutionException, InterruptedException {
//        Future<FilterBean> future = jiraRestClient.getSearchClient().getFilterById(id);
//        FilterBean filterBean = future.get();
//        Assert.assertNotNull(filterBean);
//    }

}
