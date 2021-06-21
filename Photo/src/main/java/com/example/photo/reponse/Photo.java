package com.example.photo.reponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Photo {
    private String name;
    private String url;
    private String description;
    private String owner;
}
