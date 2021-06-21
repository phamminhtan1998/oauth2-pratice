package com.example.client_type;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Album {
    private String id;
    private String userId;
    private String albumTitle;
    private String description;
    private String url;
    private List<String> urls;
}
