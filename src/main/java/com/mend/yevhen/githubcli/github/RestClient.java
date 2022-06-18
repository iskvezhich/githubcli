package com.mend.yevhen.githubcli.github;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestClient {

    private static final String server = "https://api.github.com/repos/";
    private final RestTemplate rest;
    private final HttpHeaders headers;
    private HttpStatus status;

    public RestClient() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
    }

    public JsonNode get(String uri, String suffix) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        String url = server + uri + (suffix.isEmpty() ? "" : "/" + suffix);
        JsonNode retVal;
        try {
            ResponseEntity<JsonNode> responseEntity = rest.exchange(url, HttpMethod.GET, requestEntity, JsonNode.class);
            this.setStatus(responseEntity.getStatusCode());
            retVal = responseEntity.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("An error occurred during execution GET request on " + url, e);
        }
        return retVal;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}