package com.sevenspan.patient.controller;

import com.sevenspan.patient.dto.responsedto.PostsResponse;
import com.sevenspan.patient.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostsService postService;

    @GetMapping("/")
    public List<PostsResponse> getPosts() { return postService.getPosts(); }

    @GetMapping("/{postId}")
    public PostsResponse getPostById(@PathVariable Long postId) { return postService.getPostById(postId); }
}
