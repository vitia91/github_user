package com.github.api.user.model;


import lombok.Builder;
import lombok.Data;

import java.net.URL;
import java.time.ZonedDateTime;

@Builder
@Data
public class User {

    private Integer id;
    private String login;
    private String name;
    private String type;
    private URL avatarUrl;
    private ZonedDateTime createdAt;
    private Double calculations;

}
