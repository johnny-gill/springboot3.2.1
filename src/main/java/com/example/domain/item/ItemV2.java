package com.example.domain.item;

import com.example.domain.fileupload.FileUpload;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemV2 {

    private Long id;
    private String itemName;
    private FileUpload attachFile;
    private List<FileUpload> imageFiles;
}
