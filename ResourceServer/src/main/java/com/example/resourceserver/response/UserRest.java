package com.example.resourceserver.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRest {
    private String firstName;
    private String lastName;
    private String userId;
}
