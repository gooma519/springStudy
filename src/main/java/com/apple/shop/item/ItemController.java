package com.apple.shop.item;

import com.apple.shop.Post;
import com.apple.shop.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;
    private final PostRepository postRepository;
    private final ItemService itemService;

    @GetMapping("/list")
    String list(Model model) {
        List<Item> result = itemRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
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
        itemService.saveItem(formData);
        return "redirect:/list";
    };

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model){
        Optional<Item> result = itemService.getItemById(id);
        result.ifPresent(item -> model.addAttribute("item", item));
        return "detail.html";
    }

    @GetMapping("/edit/{id}")
    String editPost(@PathVariable Long id, Model model){
        Optional<Item> result = itemService.getItemById(id);
        result.ifPresent(item -> model.addAttribute("item", item));
        return "edit.html";
    }

    @PostMapping("/update/{id}")
    String updatePost(@PathVariable Long id, @RequestParam Map formData) throws Exception {
        itemService.updateItem(id, formData);
        return "redirect:/list";
    }

//    @GetMapping("/test1")
//    String test1(){
//        System.out.println("실행");
//        return "redirect:/list";
//    }

    @PostMapping("/test")
    String test(@RequestBody Map<String, Object> formData){
        System.out.println(formData);
        return "redirect:/list";
    }

    @DeleteMapping("/delete")
    ResponseEntity<String> delete(@RequestParam Long id){
        itemService.deleteItem(id);
        return ResponseEntity.status(200).body("삭제완료");
    }
}
