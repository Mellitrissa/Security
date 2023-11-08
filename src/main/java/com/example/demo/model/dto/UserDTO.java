package com.example.demo.model.dto;

import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String password;
    private List<String> roleNames;
}
