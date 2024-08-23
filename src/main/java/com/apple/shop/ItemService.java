package com.apple.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public void saveItem(Map formData){
        Item item = new Item();
        item.setTitle((String) formData.get("title"));
        item.setPrice(Integer.parseInt((String) formData.get("price")));
        itemRepository.save(item);
    }
}
