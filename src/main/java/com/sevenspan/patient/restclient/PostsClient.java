package com.sevenspan.patient.restclient;

import com.sevenspan.patient.dto.responsedto.PostsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//Using publicly available apis
@FeignClient(name = "postsClient", url = "https://jsonplaceholder.typicode.com/")
public interface PostsClient {

    @GetMapping(value = "/posts")
    List<PostsResponse> getPosts();

    @GetMapping(value = "/posts/{postId}", produces = "application/json")
    PostsResponse getPostById(@PathVariable("postId") Long postId);
}
