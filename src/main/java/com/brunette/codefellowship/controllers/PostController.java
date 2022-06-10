package com.brunette.codefellowship.controllers;


import com.brunette.codefellowship.models.ApplicationUser;
import com.brunette.codefellowship.models.Post;
import com.brunette.codefellowship.repository.ApplicationUserRepository;
import com.brunette.codefellowship.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PostRepository postRepository;

    @PostMapping("/create-post")
    public RedirectView createPost(String username, String body) {
        ApplicationUser newUser = applicationUserRepository.findByUsername(username);
        LocalDateTime timestamp = LocalDateTime.now();
        Post newPost = new Post(body, newUser, timestamp);
        List<Post> posts = newUser.getUserPosts();
        if (posts == null) {
            posts = new ArrayList<>();
        }
        posts.add(newPost);
        applicationUserRepository.save(newUser);
        String route = "/user/" + username;
        return new RedirectView(route);
    }
}
