package com.sevenspan.patient.service;

import com.sevenspan.patient.dto.responsedto.PostsResponse;

import java.util.List;

public interface PostsService {

    List<PostsResponse> getPosts();

    PostsResponse getPostById(Long postId);
}
