package de.micromata.jira.rest.junit;

import de.micromata.jira.rest.core.domain.*;
import de.micromata.jira.rest.core.domain.update.FieldOperation;
import de.micromata.jira.rest.core.domain.update.IssueUpdate;
import de.micromata.jira.rest.core.domain.update.Operation;
import de.micromata.jira.rest.core.jql.EField;
import de.micromata.jira.rest.core.misc.JsonConstants;
import de.micromata.jira.rest.core.util.RestException;
import de.micromata.jira.rest.core.util.URIParser;
import junit.framework.Assert;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


/**
 * User: Christian Schulze
 * Email: c.schulze@micromata.de
 * Date: 11.08.2014
 */
public class TestIssueClient extends BaseTest {

    private static final String ISSUE_KEY = "DEMO-2";
    private static final String NEW_LINE = System.getProperty("line.separator");


    @Test
    public void testGetIssueByKey() throws RestException, IOException, ExecutionException, InterruptedException {
        Future<IssueBean> future = jiraRestClient.getIssueClient().getIssueByKey(ISSUE_KEY);
        while (future.isDone()) {
            final IssueBean issueBean = future.get();
            Assert.assertNotNull(issueBean);
            Assert.assertEquals(ISSUE_KEY, issueBean.getKey());
        }

    }


    @Test
    public void testGetIssueKeyWithFields() throws RestException, IOException, ExecutionException, InterruptedException {
        List<String> field = new ArrayList<String>();
        field.add(EField.SUMMARY.getField());
        field.add(EField.DESCRIPTION.getField());
        List<String> expand = new ArrayList<String>();
        expand.add(EField.RENDEREDFIELDS.getField());
        expand.add(EField.TRANSITIONS.getField());
        final Future<IssueBean> future = jiraRestClient.getIssueClient().getIssueByKey(ISSUE_KEY, field, expand);
        while (future.isDone()) {
            IssueBean issue = future.get();
            Assert.assertNotNull(issue);
            Assert.assertNotNull(issue.getFields().getSummary());
            Assert.assertNotNull(issue.getFields().getDescription());
            Assert.assertNotNull(issue.getRenderedFields().getDescription());
        }
    }


    @Test
    public void testGetAttachment() throws IOException, RestException, ExecutionException, InterruptedException {
        final Future<IssueBean> future = jiraRestClient.getIssueClient().getIssueByKey(ISSUE_KEY);
        while(future.isDone()){
            final IssueBean issue = future.get();
            List<AttachmentBean> attachments = issue.getFields().getAttachment();
            Assert.assertNotNull(attachments);
            Assert.assertFalse(attachments.isEmpty());
            AttachmentBean attachment = attachments.get(0);
            String fileName = attachment.getFilename();
            String contentURI = attachment.getContent();
            URI uri = URIParser.parseStringToURI(contentURI);
            final Future<Byte[]> future1 = jiraRestClient.getIssueClient().getAttachment(uri);
            while(future1.isDone()){
                final Byte[] attachmentBytes = future1.get();
                Assert.assertNotNull(attachmentBytes);
                OutputStream output = new FileOutputStream(fileName);
                final byte[] bytes = ArrayUtils.toPrimitive(attachmentBytes);
                output.write(bytes);
                output.flush();
                output.close();
            }

        }


    }


    @Test
    public void testCreateIssue() throws RestException, IOException, ExecutionException, InterruptedException {
        IssueBean issue = new IssueBean();
        FieldsBean fields = new FieldsBean();
        fields.setDescription("Test Description");
        fields.setSummary("Test Title");
        ProjectBean project = new ProjectBean();
        project.setKey("REMOTE");
        fields.setProject(project);
        IssuetypeBean issueType = new IssuetypeBean();
        issueType.setName("Bug");
        fields.setIssuetype(issueType);
        PriorityBean priority = new PriorityBean();
        priority.setName(JsonConstants.PRIORITY_MAJOR);
        fields.setPriority(priority);
        fields.setDuedate("2015-08-01");

//        ComponentBean componentBean1 = new ComponentBean();
//        componentBean1.setName("Backend");
//        issue.getComponents().add(componentBean1);
//        ComponentBean componentBean2 = new ComponentBean();
//        componentBean2.setName("Frontend");
//        issue.getComponents().add(componentBean2);
//
//        VersionBean versionBean1 = new VersionBean();
//        versionBean1.setName("1.1");
//        VersionBean versionBean2 = new VersionBean();
//        versionBean2.setName("1.0");
//        issue.getVersions().add(versionBean1);
//        issue.getVersions().add(versionBean2);
//        issue.getFixVersions().add(versionBean1);
//        issue.getFixVersions().add(versionBean2);

        UserBean userBean = new UserBean();
        userBean.setName("admin");
        fields.setAssignee(userBean);
        List<String> labels = new ArrayList<String>();
        labels.add("foobar");
        labels.add("inubit");
        fields.setLabels(labels);
        issue.setFields(fields);

        final Future<IssueResponse> future = jiraRestClient.getIssueClient().createIssue(issue);
        while (future.isDone()){
            final IssueResponse issueResponse = future.get();
            if (issueResponse != null) {
                String issueKey = issueResponse.getKey();
                if (issueKey != null) {
                    System.out.println(issueKey);
                } else {
                    System.out.println(issueResponse.getError());
                }
            }
        }

    }

    @Test
    public void testSetLinkInEviroment() throws IOException, RestException, ExecutionException, InterruptedException {
        final Future<IssueBean> future = jiraRestClient.getIssueClient().getIssueByKey(ISSUE_KEY);
        while (future.isDone()){
            final IssueBean issue = future.get();
            Assert.assertNotNull(issue);
            Assert.assertEquals(ISSUE_KEY, issue.getKey());
            String environment = issue.getFields().getEnvironment();
            StringBuilder sb = new StringBuilder();
            sb.append(environment);
            sb.append(NEW_LINE).append(NEW_LINE);
            sb.append(issue.getSelf());
            String newEnviroment = sb.toString();
            IssueUpdate issueUpdate = new IssueUpdate();
            Map<String, List<FieldOperation>> update = issueUpdate.getUpdate();
            List<FieldOperation> operations = new ArrayList<FieldOperation>();
            operations.add(new FieldOperation(Operation.SET.getName(), newEnviroment));
            update.put(JsonConstants.PROP_ENVIRONMENT, operations);
            final Future<IssueBean> updateFuture = jiraRestClient.getIssueClient().updateIssue(ISSUE_KEY, issueUpdate);
            while (updateFuture.isDone()){
                final IssueBean issueBean = updateFuture.get();
                String updateIssueEnvironment = issueBean.getFields().getEnvironment();
                Assert.assertEquals(newEnviroment, updateIssueEnvironment);
            }
        }
    }

}
