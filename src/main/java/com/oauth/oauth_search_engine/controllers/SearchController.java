package com.oauth.oauth_search_engine.controllers;

import com.google.api.services.customsearch.v1.model.Result;
import com.oauth.oauth_search_engine.services.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins={"http://localhost:8084"}, maxAge = 3600)
@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Operation(summary = "Get the results using GET method")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found results",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Object.class)) })})
    @GetMapping("/search/{sQuery}")
    public ResponseEntity<Object> findByStringGetMethod(@PathVariable String sQuery){
        try{
            List<Result> resultList=searchService.getResults(sQuery);
            return ResponseEntity.status(HttpStatus.OK).body(resultList);
        }catch (Exception exc){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"msg\":\""+exc.toString()+"recordDeleted\"}");
        }
    }

    @Operation(summary = "Get the results using POST method")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found results",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Object.class)) })})
    @PostMapping("/search/{sQuery}")
    public ResponseEntity<Object> findByStringPostMethod(@PathVariable String sQuery){
        try{
            List<Result> resultList=searchService.getResults(sQuery);
            return ResponseEntity.status(HttpStatus.OK).body(resultList);
        }catch (Exception exc){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"msg\":\""+exc.toString()+"recordDeleted\"}");
        }
    }

}