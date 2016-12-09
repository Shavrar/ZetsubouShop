package com.supernet.filters;

/**
 * Base class for search
 */
public abstract class SearchFilter {

    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
