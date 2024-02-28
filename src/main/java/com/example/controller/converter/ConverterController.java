package com.example.controller.converter;

import com.example.domain.type.IpPort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ConverterController {

    @GetMapping("/converter-v1")
    public String converterV1(HttpServletRequest request) {
        String data = request.getParameter("data");
        Integer intValue = Integer.valueOf(data);
        log.info("intValue = {}", intValue);
        return "ok";
    }

    @GetMapping("/ip-port")
    public String ipPort(@RequestParam IpPort ipPort) {
        log.info("ipPort.getIp()={}", ipPort.getIp());
        log.info("ipPort.getPort()={}", ipPort.getPort());
        return "ok";
    }
}
