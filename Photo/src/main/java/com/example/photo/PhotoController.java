package com.example.photo;

import com.example.photo.reponse.Photo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    @GetMapping("")
    public List<Photo> getAll(){
        Photo photo = Photo.builder()
                .name("anh thien nhie")
                .url("link cua anh")
                .owner("pham minh tan")
                .description("Anh dep lam").build();
        Photo photo1 = Photo.builder()
                .name("dieu linh")
                .url("link fb cua dieu linh")
                .owner("pham minh tan")
                .description("Anh dep lam").build();
        List<Photo> list = Arrays.asList(photo,photo1) ;
        return list;
    }

}
