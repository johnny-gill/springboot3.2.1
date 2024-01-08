package com.example.web.validation;

import com.example.domain.item.ItemRepository;
import com.example.domain.item.ItemSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

    private final ItemRepository itemRepository;

    @PostMapping("/add")
    public Object add(@Validated @RequestBody ItemSaveForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("error={}", bindingResult);
            return bindingResult.getAllErrors();
        }

        return form;
    }
}
