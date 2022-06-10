package com.brunette.codefellowship.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    public String content;
    public LocalDateTime timestamp;

    @ManyToOne
    ApplicationUser applicationUser;

    public Post() {
        // JPA DEFAULT CONSTRUCTOR
    }

    public Post(String content, ApplicationUser applicationUser, java.time.LocalDateTime timestamp)
    {
        this.applicationUser = applicationUser;
        this.content = content;
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp()
    {

        return timestamp;
    }

    public String getContent()
    {

        return content;
    }

    public void setContent(String content)
    {

        this.content = content;
    }

}