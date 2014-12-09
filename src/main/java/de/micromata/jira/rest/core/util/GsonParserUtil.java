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

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Christian Schulze
 * @author Vitali Filippow
 */
public class GsonParserUtil {

    private static final JsonParser parser = new JsonParser();

    public static List<JsonObject> parseJsonObjects(InputStream inputStream) throws UnsupportedEncodingException {
        InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
        JsonReader jsonReader = new JsonReader(reader);
        jsonReader.setLenient(true);
        JsonElement parse = parser.parse(jsonReader);
        JsonArray asJsonArray = parse.getAsJsonArray();
        return parseJsonArray(asJsonArray);
    }

    public static JsonObject parseJsonObject(InputStream inputStream) throws UnsupportedEncodingException {
        InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
        JsonReader jsonReader = new JsonReader(reader);
        jsonReader.setLenient(true);
        JsonElement parse = parser.parse(jsonReader);
        return parse.getAsJsonObject();
    }


    public static List<JsonObject> parseJsonArray(JsonArray array) {
        List<JsonObject> retval = new ArrayList<JsonObject>();
        for (JsonElement jsonElement : array) {
            retval.add(jsonElement.getAsJsonObject());
        }
        return retval;
    }

    public static String parseObjectToJson(Object object) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(object);
        return jsonString;
    }

    public static String parseTransitionToJson(int transitionId) {
        JsonObject parent = new JsonObject();
        JsonObject transitionObject = new JsonObject();
        transitionObject.addProperty(JsonConstants.PROP_ID, transitionId);
        parent.add(JsonConstants.ELEM_TRANSITION, transitionObject);
//    	if(assigneeName != null) {
//    		JsonObject fieldsObject = new JsonObject();
//
//    		JsonObject assigneeObject = new JsonObject();
//    		assigneeObject.addProperty(JsonConstants.PROP_NAME, assigneeName);
//    		fieldsObject.add(JsonConstants.ELEM_ASSIGNEE, assigneeObject);
//    	}
        String jsonString = new Gson().toJson(parent);
        return jsonString;
    }

    

}
