package com.sevenspan.patient.service;

import com.sevenspan.patient.dto.responsedto.PostsResponse;
import com.sevenspan.patient.restclient.PostsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    PostsClient postsClient;

    @Override
    public List<PostsResponse> getPosts() {
        return postsClient.getPosts();
    }

    @Override
    public PostsResponse getPostById(Long postId) {
        return postsClient.getPostById(postId);
    }
}
