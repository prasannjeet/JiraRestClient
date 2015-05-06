/*
 * Copyright 2013 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.micromata.jira.rest.core.util;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import de.micromata.jira.rest.core.domain.ErrorBean;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Christian Schulze
 * @author Vitali Filippow
 */
public class RestException extends Exception {

    private int statusCode;

    private String reasonPhrase;

    private ErrorBean restErrorMessage;


    public RestException(int statusCode, String reasonPhrase, ErrorBean restErrorMessage) {
        super(statusCode + " " + reasonPhrase + " " + restErrorMessage);
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
        this.restErrorMessage = restErrorMessage;
    }

    public RestException(CloseableHttpResponse response) throws IOException {
        StatusLine statusLine = response.getStatusLine();
        this.statusCode = statusLine.getStatusCode();
        this.reasonPhrase = statusLine.getReasonPhrase();
        try {
            HttpEntity entity = response.getEntity();
            InputStream inputStream = entity.getContent();
            if(inputStream != null) {
                InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
                JsonReader jsonReader = new JsonReader(reader);
                jsonReader.setLenient(true);
                Gson gson = new Gson();
                restErrorMessage = gson.fromJson(jsonReader, ErrorBean.class);
            }
        } catch (IOException e) {
            // nothing to say
        } finally {
            response.close();
        }

    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    public ErrorBean getRestErrorMessage() {
        return restErrorMessage;
    }

    public void setRestErrorMessage(ErrorBean restErrorMessage) {
        this.restErrorMessage = restErrorMessage;
    }
}
