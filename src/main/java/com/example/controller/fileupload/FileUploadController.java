package com.example.controller.fileupload;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/file-upload")
public class FileUploadController {

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/v1/upload")
    public String v1UploadForm() {
        return "file-upload/upload-form";
    }

    @PostMapping("/v1/upload")
    public String v1Upload(HttpServletRequest request) throws ServletException, IOException {
        log.info("request={}", request);

        String itemName = request.getParameter("itemName");
        log.info("itemName={}", itemName);

        Collection<Part> parts = request.getParts();
        log.info("parts={}", parts);

        return "file-upload/upload-form";
    }

    @GetMapping("/v2/upload")
    public String v2UploadForm() {
        return "file-upload/upload-form";
    }

    @PostMapping("/v2/upload")
    public String v2Upload(HttpServletRequest request) throws IOException, ServletException {
        log.info("request={}", request);

        String itemName = request.getParameter("itemName");
        log.info("itemName={}", itemName);

        Collection<Part> parts = request.getParts();
        log.info("parts={}", parts);

        for (Part part : parts) {
            log.info("===== PART =====");
            log.info("part.getName()={}", part.getName());

            for (String headerName : part.getHeaderNames()) {
                log.info("headerName={}, header={}", headerName, part.getHeader(headerName));
            }

            log.info("part.getSubmittedFileName()={}", part.getSubmittedFileName());
            log.info("part.getSize()={}", part.getSize());

            InputStream inputStream = part.getInputStream();
            String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
//            log.info("body={}", body);

            if (StringUtils.hasText(part.getSubmittedFileName())) {
                String fullPath = fileDir + part.getSubmittedFileName();
                log.info("fullPath={}", fullPath);
                part.write(fullPath);
            }
        }

        return "file-upload/upload-form";
    }

}
