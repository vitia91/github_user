package com.github.api.user.client.config;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.net.URI;

public interface RestConfig {

    /**
     * @return src URI
     */
    URI getURI();

    /**
     * @return HttpMethod
     */
    HttpMethod getHttpMethod();

    /**
     * @return HttpEntity
     */
    HttpEntity getHttpEntity();

}
