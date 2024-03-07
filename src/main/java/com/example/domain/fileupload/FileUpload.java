package com.example.domain.fileupload;

import lombok.Data;

@Data
public class FileUpload {
    private String uploadFileName;
    private String storeFileName;

    public FileUpload(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
