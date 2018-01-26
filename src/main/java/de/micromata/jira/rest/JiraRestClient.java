package de.micromata.jira.rest;

import de.micromata.jira.rest.client.*;
import de.micromata.jira.rest.core.*;
import de.micromata.jira.rest.core.domain.field.FieldBean;
import de.micromata.jira.rest.core.misc.RestParamConstants;
import de.micromata.jira.rest.core.misc.RestPathConstants;
import de.micromata.jira.rest.core.util.HttpMethodFactory;
import de.micromata.jira.rest.core.util.URIHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * User: Christian Schulze
 * Email: c.schulze@micromata.de
 * Date: 22.08.2014
 */
public class JiraRestClient implements RestParamConstants, RestPathConstants {


    private static final String HTTP = "http";
    private static final String HTTPS = "https";

    protected final ExecutorService executorService;

    private URI baseUri;
    private String username = StringUtils.EMPTY;
    private CloseableHttpClient httpclient;
    private HttpHost proxyHost;
    private CookieStore cookieStore = new BasicCookieStore();
    private HttpClientContext clientContext;

    private static Map<String, FieldBean> customfields;

    private static RequestConfig requestConfig;

    private IssueClient issueClient;

    private UserClient userClient;

    private SearchClient searchClient;

    private ProjectClient projectClient;

    private SystemClient systemClient;

    public JiraRestClient(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public int connect(URI uri, String username, String password) throws IOException, URISyntaxException, ExecutionException, InterruptedException {
        return connect(uri, username, password, null);
    }

    /**
     * Builds and configures a new client connection to JIRA.
     *
     * @param uri      = the login mask URI where JIRA is running
     * @param username = login name
     * @param password = login password
     * @return 200 succees, 401 for wrong credentials and 403 for captcha is needed, you have to login at the jira website
     * @throws de.micromata.jira.rest.core.util.RestException
     */
    public int connect(URI uri, String username, String password, HttpHost proxyHost) throws IOException, URISyntaxException, ExecutionException, InterruptedException {
        this.username = username;
        String host = uri.getHost();
        int port = getPort(uri.toURL());
        String scheme = HTTP;
        if (port == 443) scheme = HTTPS;
        HttpHost target = new HttpHost(host, port, scheme);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(target.getHostName(), target.getPort()),
                new UsernamePasswordCredentials(username, password));
        httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build();
        // Create AuthCache instance
        AuthCache authCache = new BasicAuthCache();
        // Generate BASIC scheme object and add it to the local
        // auth cache
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(target, basicAuth);
        // Add AuthCache to the execution context
        clientContext = HttpClientContext.create();
        clientContext.setAuthCache(authCache);
        this.baseUri = buildBaseURI(uri);

        // setzen des Proxies
        if(proxyHost != null){
            this.proxyHost = proxyHost;
            requestConfig = RequestConfig.custom().setProxy(proxyHost).build();
        }

        URIBuilder uriBuilder = URIHelper.buildPath(baseUri, USER);
        uriBuilder.addParameter(USERNAME, username);
        HttpGet method = HttpMethodFactory.createGetMethod(uriBuilder.build());
        CloseableHttpResponse response = httpclient.execute(method, clientContext);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            // Get the Cache for the CustomFields, need to deserialize the customFields in Issue Json
            Future<List<FieldBean>> allCustomFields = getSystemClient().getAllCustomFields();
            List<FieldBean> fieldBeen = allCustomFields.get();
            customfields = new HashMap<>();
            for (FieldBean fieldBean : fieldBeen) {
                customfields.put(fieldBean.getId(), fieldBean);
            }
        }
        response.close();
        return statusCode;
    }

    public static Map<String, FieldBean> getCustomfields() {
        return customfields;
    }

    public static RequestConfig getRequestConfig() {
        return requestConfig;
    }

    /**
     * Extract port from URL
     *
     * @param endpointUrl
     * @return
     */
    private int getPort(URL endpointUrl) {
        int port = (endpointUrl.getPort() != -1 ? endpointUrl.getPort() : endpointUrl.getDefaultPort());
        if (port != -1) {
            return port;
        }
        if (HTTPS.equals(endpointUrl.getProtocol())) {
            return 443;
        }
        return 80;
    }

    private URI buildBaseURI(URI uri) throws URISyntaxException {
        String path = uri.getPath();
        if (path.isEmpty() == false) {
            if (path.endsWith("/")) {
                path = path.substring(0, path.length() - 1);
            }
            path = path.concat(RestPathConstants.BASE_REST_PATH);
        } else {
            path = RestPathConstants.BASE_REST_PATH;
        }
        return new URIBuilder(uri).setPath(path).build();
    }


    public IssueClient getIssueClient() {
        if (issueClient == null) {
            issueClient = new IssueClientImpl(this, executorService);
        }
        return issueClient;
    }

    public ProjectClient getProjectClient() {
        if (projectClient == null) {
            projectClient = new ProjectClientImpl(this, executorService);
        }
        return projectClient;
    }

    public SearchClient getSearchClient() {
        if (searchClient == null) {
            searchClient = new SearchClientImpl(this, executorService);
        }
        return searchClient;
    }

    public SystemClient getSystemClient() {
        if (systemClient == null) {
            systemClient = new SystemClientImpl(this, executorService);
        }
        return systemClient;
    }

    public UserClient getUserClient() {
        if (userClient == null) {
            userClient = new UserClientImpl(this, executorService);
        }
        return userClient;
    }

    public CloseableHttpClient getClient() {
        return httpclient;
    }

    public HttpClientContext getClientContext() {
        return clientContext;
    }

    /**
     * Gets the base URI.
     *
     * @return the base URI
     */
    public URI getBaseUri() {
        return baseUri;
    }

    public String getUsername() {
        return username;
    }

    public HttpHost getProxy() {
        return proxyHost;
    }
}
