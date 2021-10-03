package com.oauth.oauth_search_engine.services;

import com.google.api.services.customsearch.v1.model.Result;

import java.util.List;

public interface SearchService {
    List<Result> getResults(String sQuery);
}
