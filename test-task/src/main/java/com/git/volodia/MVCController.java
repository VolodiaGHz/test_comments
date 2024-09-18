package com.git.volodia;

import com.git.volodia.pojo.Comment;
import com.git.volodia.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MVCController {

    final
    CommentsService commentsService;

    public MVCController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/users")
    public String showForm(Model model) throws Exception {
        List<Comment> comments = commentsService.loadAndSave();
        model.addAttribute("comments",comments);
        return "users";
    }
}
