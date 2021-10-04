package com.oauth.oauth_search_engine.services;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.customsearch.v1.CustomSearchAPI;
import com.google.api.services.customsearch.v1.model.Result;
import com.google.api.services.customsearch.v1.model.Search;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service("searchService")
public class SearchServiceImpl implements SearchService{

    public static final String GOOGLE_URL = "https://www.google.com/search";

    @Override
    public List<Result> getResults(String sQuery) {
        try {
            String cx = "searchEngineID"; //My search engine
            HttpRequestInitializer httpRequestInitializer = request -> {
            };
            NetHttpTransport netHttpTransport = new NetHttpTransport();
            netHttpTransport.createRequestFactory().getInitializer();
            //Instance Customsearch GoogleNetHttpTransport
            CustomSearchAPI cs = new CustomSearchAPI.Builder(netHttpTransport, GsonFactory.getDefaultInstance(), httpRequestInitializer).build();
            //Set search parameter
            CustomSearchAPI.Cse.List list = cs.cse().list();
            list.setCx(cx);
            list.setQ(sQuery);
            list.setKey("googleAuthKey");
            list.setNum(10);

            //Execute search
            Search resultList = list.execute();
            //The method getItems() returns a list of the "Result" object whit the results
            //https://developers.google.com/resources/api-libraries/documentation/customsearch/v1/java/latest/com/google/api/services/customsearch/model/Result.html#getCacheId--
            return resultList.getItems();
        }catch(IOException exc){
            System.out.println(exc.getStackTrace());
        }catch (Exception exc){
            System.out.println(exc.getStackTrace());
        }
        return null;
    }
}
