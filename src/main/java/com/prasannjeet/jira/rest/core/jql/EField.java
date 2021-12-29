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

package com.prasannjeet.jira.rest.core.jql;

/**
 * Searchable issue fields in JIRA.
 *
 * @author Christian Schulze
 * @author Vitali Filippow
 */
public enum EField {

    ALL("*all", null),

    NAVIGABLE("*naviagble", null),

    /**
     * Issues that are assigned to a particular Affects Version(s).
     *
     * <p>Examples:
     * <ol>
     * <li>affectedVersion = "3.14"
     * <li>affectedVersion = "Big Ted"
     * <li>affectedVersion = 10350
     * </ol>
     */
    AFFECTED_VERSION("affectedVersion", EFieldType.VERSION),

    /**
     * Issues that are assigned to a particular user.
     * <p>Examples:
     * <ol>
     * <li>assignee = "John Smith"
     * <li>assignee = jsmith
     * <li>assignee WAS "John Smith"
     * <li>assignee WAS jsmith
     * <li>assignee = "bob@mycompany.com"
     * </ol>
     */
    ASSIGNEE("assignee", EFieldType.USER),

    /**
     * Issues that belong to projects in a particular Category.
     * <p>Examples:
     * <ol>
     * <li>category = "Alphabet Projects"
     * </ol>
     */
    CATEGORY("category", EFieldType.CATEGORY),

    /**
     * Issues that have a Comment which contains particular text.
     * <p>Examples:
     * <ol>
     * <li>comment ~ "My PC is quite old"
     * <li>comment ~ "\"My PC is quite old\""
     * </ol>
     */
    COMMENT("comment", EFieldType.TEXT),

    /**
     * Issues that belong to a particular component(s) of a project.
     * <p>Examples:
     * <ol>
     * <li>component in (Comp1, Comp2)
     * <li>component in (Comp1) and component in (Comp2)
     * <li>component = Comp1 and component = Comp2
     * <li>component = 20500
     * </ol>
     */
    COMPONENT("component", EFieldType.COMPONENT),

    /**
     * Issues that were created on, before or after a particular date (or date range).
     * <p>Use one of the following formats:
     * <ol>
     * <li>"yyyy/MM/dd HH:mm"</li>
     * <li>"yyyy-MM-dd HH:mm"</li>
     * <li>"yyyy/MM/dd"</li>
     * <li>"yyyy-MM-dd"</li>
     * </ol>
     * <p>Alias <b>createdDate</b>.
     * <p>Examples:
     * <ol>
     * <li>created &lt; "2010/12/12"
     * <li>created &lt;= "2010/12/13"
     * <li>created &gt; "2010/12/12" and created &lt; "2010/12/12 14:00"
     * <li>created &gt; "-1d"
     * <li>created &gt; "2011/01/01" and created &lt; "2011/02/01"
     * <li>created &gt; "2011/01/15" and created &lt; "2011/01/16"
     * </ol>
     */
    CREATED("created", EFieldType.DATE),

//	CUSTOM_FIELD("CustomFieldName", EFieldType.CUSTOM_TYPE),

    /**
     * Issues where the Description contains particular text.
     * <p>Examples:
     * <ol>
     * <li>description ~ "Please see screenshot"
     * <li>description ~ "\"Please see screenshot\""
     * </ol>
     */
    DESCRIPTION("description", EFieldType.TEXT),

    /**
     * Issues that were due on, before or after a particular date (or date range).
     * <p>Use one of the following formats:
     * <ol>
     * <li>"yyyy/MM/dd"</li>
     * <li>"yyyy-MM-dd"</li>
     * </ol>
     * <p>Alias <b>dueDate</b>.
     * <p>Examples:
     * <ol>
     * <li>due &lt; "2010/12/31"
     * <li>due &lt;= "2011/01/01"
     * <li>due = "1d"
     * <li>due &gt;= "2011/01/01" and due &lt;= "2011/01/31"
     * <li>due = "2011/01/15"
     * </ol>
     */
    DUE("due", EFieldType.DATE),

    /**
     * Issues where the Environment contains particular text.
     * <p>Examples:
     * <ol>
     * <li>environment ~ "Third floor"
     * <li>environment ~ "\"Third floor\""
     * </ol>
     */
    ENVIRONMENT("environment", EFieldType.TEXT),

    /**
     * <i>Only available if you are using GreenHopper 6.1.2 or later.</i>
     * <p>Issues that belong to a particular epic in GreenHopper.
     * <p>Examples:
     * <ol>
     * <li>"epic link" = ANERDS-317
     * <li>"epic link" = Jupiter
     * </ol>
     */
    EPIC_LINKS("epic link", EFieldType.CUSTOM_TYPE),

    /**
     * You can use a saved filter to narrow your search. You can search by filter name or
     * filter ID (i.e. the number that JIRA automatically allocates to a saved filter).
     * <p>Alias <b>request</b>, <b>savedFilter</b>, <b>searchRequest</b>.
     * <p>Examples:
     * <ol>
     * <li>filter = "My Saved Filter" and assignee = jsmith
     * <li>filter = 12000 and assignee = jsmith
     * </ol>
     */
    FILTER("filter", EFieldType.FILTER),

    /**
     * Issues that are assigned to a particular Fix Version.
     * <p>Examples:
     * <ol>
     * <li>fixVersion in ("3.14", "4.2")
     * <li>fixVersion = "Little Ted"
     * <li>fixVersion = 10001
     * </ol>
     */
    FIX_VERSION("fixVersion", EFieldType.VERSION),

    /**
     * Issues with a particular Issue Key or Issue ID (i.e. the number that JIRA automatically allocates to an Issue).
     * <p>Alias <b>id</b>, <b>issue</b>, <b>key</b>.
     * <p>Examples:
     * <ol>
     * <li>issueKey = ABC-123
     * </ol>
     */
    ISSUE_KEY("issueKey", EFieldType.ISSUE),

    /**
     * Issues that were last viewed on, before or after a particular date (or date range).
     * <p>Use one of the following formats:
     * <ol>
     * <li>"yyyy/MM/dd HH:mm"</li>
     * <li>"yyyy-MM-dd HH:mm"</li>
     * <li>"yyyy/MM/dd"</li>
     * <li>"yyyy-MM-dd"</li>
     * </ol>
     * <p>Examples:
     * <ol>
     * <li>lastViewed &lt; "2010/12/12"
     * <li>lastViewed &lt;= "2010/12/13"
     * <li>lastViewed &gt; "2010/12/12" and created &lt; "2010/12/12 14:00"
     * <li>lastViewed &gt; "-1d"
     * <li>lastViewed &gt; "2011/01/01" and created &lt; "2011/02/01"
     * <li>lastViewed &gt; "2011/01/15" and created &lt; "2011/01/16"
     * </ol>
     */
    LAST_VIEWED("lastViewed", EFieldType.DATE),

    /**
     * <i>Only available if Issue Level Security has been enabled by your JIRA administrator.</i>
     * <p>Issues with a particular Security Level.
     * <p>Examples:
     * <ol>
     * <li>level in ("Really High", level1)
     * <li>level = 123
     * </ol>
     */
    LEVEL("level", EFieldType.SECURITY_LEVEL),

    /**
     * <i>Only available if time-tracking has been enabled by your JIRA administrator.</i>
     * <p>Search for issues where the Original Estimate is set to a particular value (i.e. a number, not a date or date range).
     * <p>Alias <b>timeOriginalEstimate</b>
     * <p>Examples:
     * <ol>
     * <li>originalEstimate = 1h
     * <li>originalEstimate &gt; 2d
     * </ol>
     */
    ORIGINAL_ESTIMATE("originalEstimate", EFieldType.DURATION),

    /**
     * <i>Only available if sub-tasks have been enabled by your JIRA administrator.</i>
     * <p>Search for all sub-tasks of a particular issue.
     * <p>Examples:
     * <ol>
     * <li>parent = TEST-1234
     * </ol>
     */
    PARENT("parent", EFieldType.ISSUE),

    /**
     * Issues with a particular Priority.
     * <p>Examples:
     * <ol>
     * <li>priority = High
     * <li>priority = 10000
     * </ol>
     */
    PRIORITY("priority", EFieldType.PRIORITY),

    /**
     * Issues that belong to a particular Project.
     * <p>Examples:
     * <ol>
     * <li>project = "ABC Project"
     * <li>project = "ABC"
     * <li>project = 1234
     * </ol>
     */
    PROJECT("project", EFieldType.PROJECT),

    /**
     * <i>Only available if time-tracking has been enabled by your JIRA administrator.</i>
     * <p>Search for issues where the Remaining Estimate is set to a particular value (i.e. a number, not a date or date range).
     * <p>Alias <b>timeEstimate</b>
     * <p>Examples:
     * <ol>
     * <li>remainingEstimate &gt; 4h
     * </ol>
     */
    REMAINING_ESTIMATE("remainingEstimate", EFieldType.DURATION),

    /**
     * Issues that were reported by (i.e. created by) a particular user.
     * <p>Examples:
     * <ol>
     * <li>reporter = "Jill Jones"
     * <li>reporter = jjones
     * <li>reporter = "bob@mycompany.com"
     * </ol>
     */
    REPORTER("reporter", EFieldType.USER),

    /**
     * Issues that have a particular Resolution.
     * <p>Examples:
     * <ol>
     * <li>resolution in ("Cannot Reproduce", "Won't Fix")
     * <li>resolution = 5
     * <li>resolution = unresolved
     * </ol>
     */
    RESOLUTION("resolution", EFieldType.RESOLUTION),

    /**
     * Issues that were resolved on, before or after a particular date (or date range).
     * <p>Use one of the following formats:
     * <ol>
     * <li>"yyyy/MM/dd HH:mm"</li>
     * <li>"yyyy-MM-dd HH:mm"</li>
     * <li>"yyyy/MM/dd"</li>
     * <li>"yyyy-MM-dd"</li>
     * </ol>
     * <p>Alias <b>resolutionDate</b>
     * <p>Examples:
     * <ol>
     * <li>resolved &lt;= "2010/12/31"
     * <li>resolved &lt; "2010/12/31 14:00"
     * <li>resolved &lt;= "2011/01/01"
     * <li>resolved &gt; "2011/01/01" and resolved &lt; "2011/02/01"
     * <li>resolved &gt; "2011/01/15" and resolved &lt; "2011/01/16"
     * <li>resolved &gt; -1h
     * </ol>
     */
    RESOLVED("resolved", EFieldType.DATE),

    /**
     * <i>Only available if you are using GreenHopper.</i>
     * <p>Search for issues that are assigned to a particular sprint in GreenHopper.
     * <p>Examples:
     * <ol>
     * <li>sprint = 999
     * <li>sprint = "February 1"
     * <li>sprint in ("February 1","February 2","February 3")
     * <li>sprint is not empty
     * </ol>
     */
    SPRINT("sprint", EFieldType.NUMBER),

    /**
     * Issues that have a particular Status.
     * <p>Examples:
     * <ol>
     * <li>status = Open
     * <li>status = 1
     * <li>status WAS Open
     * </ol>
     */
    STATUS("status", EFieldType.STATUS),

    /**
     * Issues where the Summary contains particular text.
     * <p>Examples:
     * <ol>
     * <li>summary ~ "Error saving file"
     * <li>summary ~ "\"Error saving file\""
     * </ol>
     */
    SUMMARY("summary", EFieldType.TEXT),

    /**
     * This is a "master-field" that allows you to search all text fields, i.e.:
     * <ol>
     * <li>Summary</li>
     * <li>Description</li>
     * <li>Environment</li>
     * <li>Comments</li>
     * </ol>
     * <p>Examples:
     * <ol>
     * <li>text ~ "Fred"
     * <li>text ~ Fred
     * <li>text ~ "\"full screen\""
     * </ol>
     */
    TEXT("text", EFieldType.TEXT_MASTER),

    /**
     * Issues that have a particular Issue Type.
     * <p>Alias <b>issueType</b>
     * <p>Examples:
     * <ol>
     * <li>type = Bug
     * <li>issueType in (Bug,Improvement)
     * <li>issueType = 2
     * </ol>
     */
    ISSUE_TYPE("issuetype", EFieldType.ISSUE_TYPE),

    /**
     * <i>Only available if time-tracking has been enabled by your JIRA administrator.</i>
     * <p>Search for issues where the Time Spent is set to a particular value (i.e. a number, not a date or date range).
     * <p>Examples:
     * <ol>
     * <li>timeSpent &gt; 5d
     * </ol>
     */
    TIME_SPENT("timeSpent", EFieldType.DURATION),

    /**
     * Issues that were updated on, before or after a particular date (or date range).
     * <p>Use one of the following formats:
     * <ol>
     * <li>"yyyy/MM/dd HH:mm"</li>
     * <li>"yyyy-MM-dd HH:mm"</li>
     * <li>"yyyy/MM/dd"</li>
     * <li>"yyyy-MM-dd"</li>
     * </ol>
     * <p>Alias <b>updatedDate</b>
     * <p>Examples:
     * <ol>
     * <li>updated &lt; "2010/12/12"
     * <li>updated &lt; "2010/12/13"
     * <li>updated &lt; "2010/12/31 14:00"
     * <li>updated &lt; "-2w"
     * <li>updated &gt; "2011/01/15" and updated &lt; "2011/01/16"
     * <li>updated &gt; "20011/01/01" and updated &lt; "2011/02/01"
     * </ol>
     */
    UPDATED("updated", EFieldType.DATE),

    /**
     * Issues for which a particular user has voted.
     * <p>Examples:
     * <ol>
     * <li>voter = "jsmith"
     * </ol>
     */
    VOTER("voter", EFieldType.USER),

    /**
     * Issues with a specified number of votes.
     * <p>Examples:
     * <ol>
     * <li>votes &gt;= 12
     * </ol>
     */
    VOTES("votes", EFieldType.NUMBER),

    /**
     * Issues that a particular user is watching.
     * <p>Examples:
     * <ol>
     * <li>watcher = "jsmith"
     * </ol>
     */
    WATCHER("watcher", EFieldType.USER),

    /**
     * Issues with a specified number of watchers.
     * <p>Examples:
     * <ol>
     * <li>watchers &gt; 3
     * </ol>
     */
    WATCHERS("watchers", EFieldType.NUMBER),

    /**
     * <i>Only available if time-tracking has been enabled by your JIRA administrator.</i>
     * <p>Issues where the Work Ratio has a particular value.</p>
     * Work Ratio is calculated as follows: workRatio = timeSpent / originalEstimate) x 100
     * <p>Examples:
     * <ol>
     * <li>workRatio &gt; 75
     * </ol>
     */
    WORK_RATIO("workRatio", EFieldType.NUMBER),

    /** <i>Avaiable Transitions for the Issue</i>
     *  <p>an Issue has several Transition to which the status can change.</p>
     */
    TRANSITIONS("transitions", EFieldType.CUSTOM_TYPE),
    
    
    /** <i>Avaiable changelog for an Issue</i>
     */
    CHANGELOG("changelog", EFieldType.CUSTOM_TYPE),

    /** <i>Renders the Description Markup to HTML</i>
     */
    RENDEREDFIELDS("renderedFields", EFieldType.CUSTOM_TYPE),

    /**
     * <i>Attachment Informations</i>
     */
    ATTACHMENT("attachment", EFieldType.CUSTOM_TYPE);

    /**
     * The name of the field.
     */
    private final String field;

    /**
     * The type of the field.
     */
    private final EFieldType type;

    /**
     * Instantiates a new field.
     *
     * @param field = name of the field
     * @param type  = type of the field
     */
    EField(String field, EFieldType type) {
        this.field = field;
        this.type = type;
    }

    /**
     * Gets the name of the field.
     *
     * @return the field name
     */
    public String getField() {
        return field;
    }

    /**
     * Gets the type of the field.
     *
     * @return the field type
     */
    public EFieldType getType() {
        return type;
    }

    @Override
    public String toString() {
        return getField();
    }
}
