package com.example.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
public class ApiExceptionController {


    @GetMapping("/api/members/{id}")
    public MemberDto getMember(@PathVariable String id) {

        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        }

        return new MemberDto(id, UUID.randomUUID().toString());
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String id;
        private String password;
    }

}
