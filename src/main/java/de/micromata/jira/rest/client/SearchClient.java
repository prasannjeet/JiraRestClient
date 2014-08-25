package de.micromata.jira.rest.client;

import de.micromata.jira.rest.core.domain.JqlSearchResultBean;
import de.micromata.jira.rest.core.jql.JqlSearchBean;
import de.micromata.jira.rest.core.util.RestException;

import java.io.IOException;

/**
 * Author: Christian Schulze
 * Email: c.schulze@micromata.de
 * Date: 31.07.2014
 */
public interface SearchClient {

    /**
     * Performs an extended search for issues given by the project.
     *
     * @return list of issues
     * @throws de.micromata.jira.rest.core.util.RestException
     */
    public JqlSearchResultBean searchIssues(JqlSearchBean jsb) throws RestException, IOException;
}
