package com.apple.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;
    private final PostRepository postRepository;

    @GetMapping("/list")
    String list(Model model) {
        List<Item> result = itemRepository.findAll();
        List<Post> posts = postRepository.findAll();
        model.addAttribute("items", result);
        model.addAttribute("posts", posts);
        return "list.html";
    }

    @GetMapping("/write")
    String write(Model model) {
        return "write.html";
    }

    @PostMapping("/add")
    String addPost(@RequestParam Map formData){
        Item item = new Item();
        item.setTitle((String) formData.get("title"));
        item.setPrice(Integer.parseInt((String) formData.get("price")));
        itemRepository.save(item);
        return "redirect:/list";
    };

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model){
        Optional<Item> result = itemRepository.findById(id);
        if(result.isPresent()){
            System.out.println(result.get());
            model.addAttribute("item", result.get());
        }
        return "detail.html";
    }
}
