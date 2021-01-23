package com.github.api.user.client.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.net.URI;

import static org.junit.Assert.assertEquals;

public class GitHubConfigTest {

    private static final String LOGIN = "login";
    private static final String GIT_HUB_URL = "https://api.github.com/users/%s";

    private RestConfig config;

    @BeforeEach
    public void setUp() {
        config = new GitHubConfig(LOGIN);
    }

    @Test
    public void testGetURI() {
        URI expectedURI = URI.create(String.format(GIT_HUB_URL, LOGIN));
        assertEquals("Invalid URI", expectedURI, config.getURI());
    }

    @Test
    public void testGetHttpMethod() {
        assertEquals("Invalid HTTP Method", HttpMethod.GET, config.getHttpMethod());
    }

    @Test
    public void testGetHttpEntity() {
        assertEquals("Invalid HTTP Entity", HttpEntity.EMPTY, config.getHttpEntity());
    }
}