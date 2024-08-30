package com.apple.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public void saveItem(Map formData, String username){
        Item item = new Item();
        item.setTitle((String) formData.get("title"));
        item.setPrice(Integer.parseInt((String) formData.get("price")));
        item.setUsername(username);
        item.setImgUrl((String) formData.get("img_url"));
        itemRepository.save(item);
    }

    public Optional<Item> getItemById(Long id){
        return itemRepository.findById(id);
    }

    public void updateItem(Long id, Map formData) throws Exception{
        String title = (String) formData.get("title");
        if(title.length() < 100 ){
            Item item = new Item();
            item.setId(id);
            item.setTitle(title);
            item.setPrice(Integer.parseInt((String) formData.get("price")));
            itemRepository.save(item);
        }else {
            throw new Exception("Title length exceeds 100 characters");
        }
    }

    public void deleteItem(Long id){
        itemRepository.deleteById(id);
    }
}
