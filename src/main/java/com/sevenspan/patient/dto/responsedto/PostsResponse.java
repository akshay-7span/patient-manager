package com.sevenspan.patient.dto.responsedto;

import lombok.Data;

@Data
public class PostsResponse {

    private Long userId;
    private Long id;
    private String title;
    private String body;
}
