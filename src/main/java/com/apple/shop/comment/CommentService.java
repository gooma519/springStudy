package com.apple.shop.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<Comment> getCommentByParentId(Long id) {
        return commentRepository.findByParentId(id);
    }

    public void saveComment(Map formdata, Long parentId, String username) {
        Comment comment = new Comment();
        comment.setParentId(parentId);
        comment.setUsername(username);
        comment.setContent((String) formdata.get("content"));
        commentRepository.save(comment);
    }
}
