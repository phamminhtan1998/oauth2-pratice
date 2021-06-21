package com.example.album;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Album {
    private String id;
    private String userId;
    private String albumTitle;
    private String description;
    private String url;
}
