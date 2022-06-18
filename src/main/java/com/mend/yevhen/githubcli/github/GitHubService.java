package com.mend.yevhen.githubcli.github;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mend.yevhen.githubcli.model.Release;
import com.mend.yevhen.githubcli.model.Stat;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GitHubService {

    private final RestClient client;

    public GitHubService(RestClient client) {
        this.client = client;
    }

    public List<Stat> stats(String repo) {
        JsonNode stats = client.get(repo, "");
        JsonNode contributors = client.get(repo, "contributors");
        List<Stat> retVal = new LinkedList<>();
        retVal.add(new Stat("Stars", String.valueOf(stats.at("/stargazers_count").asInt())));
        retVal.add(new Stat("Forks", String.valueOf(stats.at("/forks").asInt())));
        retVal.add(new Stat("Contributors", String.valueOf(contributors.size())));
        retVal.add(new Stat("Language", stats.at("/language").asText()));
        return retVal;
    }

    public List<Release> downloads(String repo) {
        ArrayNode releases = (ArrayNode) client.get(repo, "releases");
        List<Release> retVal = new ArrayList<>();
        for (JsonNode release : releases) {
            StreamSupport.stream(release.get("assets").spliterator(), false)
                    .map(a -> new Release(
                            release.at("/name").asText(),
                            a.at("/name").asText(),
                            a.at("/download_count").asInt()))
                    .forEach(retVal::add);
        }
        return retVal;
    }
}
