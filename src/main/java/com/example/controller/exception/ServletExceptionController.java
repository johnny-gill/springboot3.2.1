package com.example.controller.exception;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;

@Slf4j
@Controller
public class ServletExceptionController {

    @GetMapping("/errorEx")
    public void errorEx() {
        throw new RuntimeException("예외 발생!");
    }
    
    @GetMapping("/error404")
    public void erorr404(HttpServletResponse response) throws IOException {
        response.sendError(404, "404 오류");
    }

    @GetMapping("/error500")
    public void error500(HttpServletResponse response) throws IOException {
        response.sendError(500, "500 오류");
    }

}
