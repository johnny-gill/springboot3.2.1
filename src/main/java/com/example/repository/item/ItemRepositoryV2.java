package com.example.repository.item;

import com.example.domain.item.Item;
import com.example.domain.item.ItemV2;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepositoryV2 {

    private static final Map<Long, ItemV2> store = new HashMap<>();
    private static long sequence = 0L;

    public ItemV2 save(ItemV2 item) {
        item.setId(sequence++);
        store.put(item.getId(), item);
        return item;
    }

    public ItemV2 findById(Long id) {
        return store.get(id);
    }

}
