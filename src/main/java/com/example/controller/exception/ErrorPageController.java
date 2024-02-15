package com.example.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/error-page")
public class ErrorPageController {

    @GetMapping("/404")
    public String errorPage404() {
        log.info("errorPage404");
        return "error-page/404";
    }

    @GetMapping("/500")
    public String errorPage500() {
        log.info("errorPage500");
        return "error-page/500";
    }
}
