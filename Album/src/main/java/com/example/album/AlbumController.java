package com.example.album;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {


    @GetMapping
    public List<Album> getAll(){
        Album album=Album.builder()
                .id("album1")
                .userId("1")
                .albumTitle("you are my fire")
                .description("My favorite song is belong to back street boy ")
                .build();
        Album album1=Album.builder()
                .id("album2")
                .userId("12")
                .albumTitle("Tocs nhu tuyet")
                .description("My second favorite song is belong to jay chou ")
                .build();
        List<Album> albums = Arrays.asList(album,album1);
        return albums;
    }
}
