package com.github.api.user.counters.model;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "USER_REQUEST")
public class UserRequestCounter {
    @Id
    @Column(name = "LOGIN")
    private String id;
    @Setter
    @Column(name = "REQUEST_COUNT")
    @Builder.Default
    private Integer requestCount = 0;

    public UserRequestCounter(String id) {
        this.id = id;
    }

}
