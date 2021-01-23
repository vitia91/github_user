package com.github.api.user.client;

import com.github.api.user.client.config.RestConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * REST client to send request
 */
@Service
public class RestClient {

    /**
     * Sends a request to the provided URI and returns result
     *
     * @param config
     * @return
     */
    public ResponseEntity<? extends Map> call(RestConfig config) {
        RestTemplate rest = new RestTemplate();
        return rest.exchange(
                config.getURI(),
                config.getHttpMethod(),
                config.getHttpEntity(),
                Map.class);
    }
}
