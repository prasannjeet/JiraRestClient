package com.prasannjeet.jira.rest.junit;

import com.prasannjeet.jira.rest.JiraRestClient;
import com.prasannjeet.jira.rest.core.jql.JqlConstants;
import com.prasannjeet.jira.rest.core.misc.RestPathConstants;
import org.junit.Before;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Junit-Test for JiraRestClient.
 * You need a running Jira-Instance with the TEST_SYSTEM_URL.
 * Best use is the atlassian-plugin-sdk
 * <p>
 * User: Christian Schulze
 * Email: c.schulze@micromata.de
 * Date: 09.08.2014
 */
class BaseTest implements JqlConstants, RestPathConstants {

    static final String CONFIG_FILE_NAME = "config.properties";
    static final String FIELD_FILE_NAME = "fields.json";
    static final String CUSTOM_FILE_NAME = "customfields.json";

    static final String URL_PARAM = "url";
    static final String LOGIN_PARAM = "login";
    static final String PASSWORD_PARAM = "password";

    static final String USERNAME = "username";
    static final String ISSUE = "issue";
    static final String PROJECT = "project";



    String testSystemUrl = "http://localhost:2990/jira";
    String login = "admin";
    String password = "admin";

    String USERNAME_TO_SEARCH = "admin";
    String ISSUEKEY_TO_SEARCH = "DEMO-1";
    String PROJECT_TO_SEARCH = "DEMO";

    boolean HAS_CREATE_PERMISSION = false;

    JiraRestClient jiraRestClient;

    public BaseTest() {
        try {
            loadConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void connect() throws URISyntaxException, IOException, ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
//        ProxyHost proxy = new ProxyHost("proxy", 3128);
        URI uri = new URI(testSystemUrl);
        jiraRestClient = new JiraRestClient(executorService);
        jiraRestClient.connect(uri, login, password);
    }

    private void loadConfig() throws IOException {
        String path = new File(System.getProperty("user.dir")) + File.separator;
        Properties config = new Properties();
        config.load(new FileInputStream(path + CONFIG_FILE_NAME));
        testSystemUrl = config.getProperty(URL_PARAM);
        login = config.getProperty(LOGIN_PARAM);
        password = config.getProperty(PASSWORD_PARAM);

        USERNAME_TO_SEARCH = config.getProperty(USERNAME);
        ISSUEKEY_TO_SEARCH = config.getProperty(ISSUE);
        PROJECT_TO_SEARCH = config.getProperty(PROJECT);

        HAS_CREATE_PERMISSION = Boolean.parseBoolean(config.getProperty("createPermission"));

    }
}
