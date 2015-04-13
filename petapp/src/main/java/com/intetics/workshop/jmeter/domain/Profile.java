package com.intetics.workshop.jmeter.domain;

import java.io.Serializable;

public class Profile
    implements Serializable {

    private Long id;
    private String title;
    private String content;
    private String location;

    public Profile(Long id, String title, String content, String location) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getLocation() {
        return location;
    }
}
