package com.example.client_type.controller;

import com.example.client_type.Album;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Controller
public class AlbumController {
    @Autowired
    OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
    @Autowired
    RestTemplate restTemplate;


    @GetMapping("album")
    public String getListAlbum(Model model, @AuthenticationPrincipal OidcUser principal){
        OidcIdToken idToken = principal.getIdToken();
        String idTokenValue = idToken.getTokenValue();
        System.out.println("Principal :"+principal);
        System.out.println("id token :"+ idToken.getTokenValue());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient oauth2Client = oAuth2AuthorizedClientService.loadAuthorizedClient(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(),
                oAuth2AuthenticationToken.getName());
        String token = oauth2Client.getAccessToken().getTokenValue();
        System.out.println("Access token : "+token);
        String url = "http://localhost:8082/album";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","Bearer "+token);
        HttpEntity entity = new HttpEntity(httpHeaders);
        ResponseEntity<List<Album>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<List<Album>>() {
                }

        );
        List<Album> body = responseEntity.getBody();

//        Album album = Album.builder()
//                .id("id 1 ")
//                .userId("Do thi dieu linh")
//                .description("Mo ta ngan ve album")
//                .albumTitle("Title of album 1 ")
//                .build();
//        Album album1 = Album.builder()
//                .id("id 1 ")
//                .userId("Pham Minh Tan")
//                .description("Tren thong thien van duoi tuong dia ly")
//                .albumTitle("Title of album ")
//                .build();
        model.addAttribute("albums",body);
        model.addAttribute("mess", "Do thi dieu linh");
        return "album";
    }


}
