package com.example.client_type.controller;

import com.example.client_type.Album;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class AlbumController {

    @GetMapping("album")
    public String getListAlbum(Model model){
        Album album = Album.builder()
                .id("id 1 ")
                .userId("Do thi dieu linh")
                .description("Mo ta ngan ve album")
                .albumTitle("Title of album 1 ")
                .build();
        Album album1 = Album.builder()
                .id("id 1 ")
                .userId("Pham Minh Tan")
                .description("Tren thong thien van duoi tuong dia ly")
                .albumTitle("Title of album ")
                .build();
        model.addAttribute("albums",Arrays.asList(album,album1));
        model.addAttribute("mess", "Do thi dieu linh");
        return "album";
    }

    private boolean check(List<Album> data){
        for (Album al : data){
            for (String a: al.getUrls()){
                if(a.equals("123"))
                    throw new ExceptionTest();
            }
            return false;
        }
        return false;
    }
    public static class ExceptionTest extends RuntimeException{

    }
}
