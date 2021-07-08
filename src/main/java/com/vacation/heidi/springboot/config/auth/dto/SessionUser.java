package com.vacation.heidi.springboot.config.auth.dto;

import com.vacation.heidi.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private long uid;
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.uid = user.getUid();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
