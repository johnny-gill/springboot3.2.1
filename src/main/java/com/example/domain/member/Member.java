package com.example.domain.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Member {

    private Long id;

    @NotBlank
    private String loginId;

    @NotEmpty
    private String name;

    @NotBlank
    private String password;

}
