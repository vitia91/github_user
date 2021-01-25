package com.github.api.user.client.config;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.net.URI;

/**
 * Configuration for GitHub
 */
public final class GitHubConfig implements RestConfig {
    private static final String GIT_HUB_URL = "https://api.github.com/users/%s";

    private final URI uri;
    private final HttpMethod httpMethod = HttpMethod.GET;
    private final HttpEntity httpEntity = HttpEntity.EMPTY;

    public GitHubConfig(String login) {
        this.uri = URI.create(String.format(GIT_HUB_URL, login));
    }

    @Override
    public URI getURI() {
        return uri;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    @Override
    public HttpEntity getHttpEntity() {
        return httpEntity;
    }
}
