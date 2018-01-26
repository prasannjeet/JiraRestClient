package de.micromata.jira.rest.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import de.micromata.jira.rest.JiraRestClient;
import de.micromata.jira.rest.core.custom.IssueBeanDeserializer;
import de.micromata.jira.rest.core.custom.MetaBeanDeserializer;
import de.micromata.jira.rest.core.domain.IssueBean;
import de.micromata.jira.rest.core.domain.meta.MetaBean;
import de.micromata.jira.rest.core.util.URIHelper;
import org.apache.commons.lang3.Validate;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;

/**
 * Author: Christian Date: 09.12.2014.
 */
public abstract class BaseClient {

    protected final JiraRestClient jiraRestClient ;
    protected final CloseableHttpClient client;
    protected final HttpClientContext clientContext;
    protected final URI baseUri;
    protected ExecutorService executorService;

	protected final Gson gson	= new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .registerTypeAdapter(IssueBean.class, new IssueBeanDeserializer())
            .registerTypeAdapter(MetaBean.class, new MetaBeanDeserializer())
            .create();

    public BaseClient(JiraRestClient jiraRestClient) {
        this.baseUri = jiraRestClient.getBaseUri();
        this.clientContext = jiraRestClient.getClientContext();
        this.jiraRestClient = jiraRestClient;
        this.client = jiraRestClient.getClient();
    }

    protected JsonReader toJsonReader(InputStream inputStream)
			throws UnsupportedEncodingException {

		Validate.notNull(inputStream);
		InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
		JsonReader jsonReader = new JsonReader(reader);
		jsonReader.setLenient(true);
		return jsonReader;
	}

	protected URIBuilder buildPath(String... paths) throws URISyntaxException {
        return URIHelper.buildPath(baseUri, paths);
	}

    protected JsonReader getJsonReader(CloseableHttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        InputStream inputStream = entity.getContent();
        return toJsonReader(inputStream);
    }
}
