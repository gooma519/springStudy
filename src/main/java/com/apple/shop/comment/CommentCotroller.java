package com.apple.shop.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CommentCotroller {

    private final CommentService commentService;

    @PostMapping("/comment/{parentId}")
    String addComment(@PathVariable Long parentId, @RequestParam Map formData, Authentication auth) {
        if(auth == null) {
            return "redirect:/signin";
        }
        commentService.saveComment(formData, parentId, auth.getName());
        return "redirect:/detail/" + parentId;
    }
}
