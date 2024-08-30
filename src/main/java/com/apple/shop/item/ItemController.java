package com.apple.shop.item;

import com.apple.shop.comment.Comment;
import com.apple.shop.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final S3Service s3Service;
    private final CommentService commentService;

    @GetMapping("/list")
    String list(Model model) {
        List<Item> result = itemRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("items", result);
        return "redirect:/list/page/1";
    }

    @GetMapping("/write")
    String write(Authentication auth) {
        if(auth != null) {
            return "write.html";
        }
        return "redirect:/signin";
    }

    @PostMapping("/add")
    String addPost(@RequestParam Map formData, Authentication auth){
        itemService.saveItem(formData, auth.getName());
        return "redirect:/list";
    };

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model){
        Optional<Item> result = itemService.getItemById(id);
        List<Comment> comment = commentService.getCommentByParentId(id);
        result.ifPresent(item -> model.addAttribute("item", item));
        model.addAttribute("comment", comment);
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

    @GetMapping("/list/page/{page}")
    String getListPage(@PathVariable Integer page,Model model) {
        Page<Item> result = itemRepository.findPageBy(PageRequest.of(page - 1,5, Sort.by(Sort.Direction.ASC, "id")));
        model.addAttribute("totalPage", result.getTotalPages());
        model.addAttribute("items", result);
        return "list.html";
    }

    @GetMapping("/presigned-url")
    @ResponseBody
    String getUrl(@RequestParam String filename){
        var result = s3Service.createPresignedUrl("test/" + filename);
        return  result;
    }

}
