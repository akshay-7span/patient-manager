package com.sevenspan.patient.restclient;

import com.sevenspan.patient.dto.responsedto.PostsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

//Using publicly available apis
@FeignClient(name = "postsClient", url = "https://jsonplaceholder.typicode.com/")
public interface PostsClient {

    @RequestMapping(method = RequestMethod.GET, value = "/posts")
    List<PostsResponse> getPosts();

    @RequestMapping(method = RequestMethod.GET, value = "/posts/{postId}", produces = "application/json")
    PostsResponse getPostById(@PathVariable("postId") Long postId);
}
