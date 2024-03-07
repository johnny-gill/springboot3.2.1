package com.example.controller.item;

import com.example.component.FileStore;
import com.example.domain.fileupload.FileUpload;
import com.example.domain.item.ItemForm;
import com.example.domain.item.ItemV2;
import com.example.repository.item.ItemRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemControllerV2 {

    private final FileStore fileStore;
    private final ItemRepositoryV2 itemRepositoryV2;

    @GetMapping("/items/v2/add")
    public String addForm() {
        return "items/v2/item-form";
    }

    @PostMapping("/items/v2/add")
    public String add(@ModelAttribute ItemForm itemForm, RedirectAttributes redirectAttributes) throws IOException {
        FileUpload attachFile = fileStore.storeFile(itemForm.getAttachFile());
        List<FileUpload> imageFiles = fileStore.storeFiles(itemForm.getImageFiles());

        ItemV2 item = new ItemV2();
        item.setItemName(itemForm.getItemName());
        item.setAttachFile(attachFile);
        item.setImageFiles(imageFiles);
        itemRepositoryV2.save(item);

        redirectAttributes.addAttribute("itemId", item.getId());

        return "redirect:/items/v2/{itemId}";
    }

    @GetMapping("/items/v2/{id}")
    public String items(@PathVariable Long id, Model model) {
        ItemV2 item = itemRepositoryV2.findById(id);
        model.addAttribute("item", item);
        return "items/v2/item-view";
    }

    @ResponseBody
    @GetMapping("/images/{fileName}")
    public Resource downloadImage(@PathVariable String fileName) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(fileName));
    }

    @GetMapping("/attach/{itemId}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long itemId) throws MalformedURLException {
        ItemV2 item = itemRepositoryV2.findById(itemId);
        String storeFileName = item.getAttachFile().getStoreFileName();
        String uploadFileName = item.getAttachFile().getUploadFileName();

        UrlResource urlResource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));
        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        log.info("uploadFileName={}, encodedUploadFileName={}", uploadFileName, encodedUploadFileName);

        String contentDisposition = "attachment; filename=" + encodedUploadFileName; // 다운로드시 원래 파일명으로

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(urlResource);

    }
}
