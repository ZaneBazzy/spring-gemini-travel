package com.example.demo;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;

@Controller
public class AiController {
    private final BackendService backendService;

    public AiController(BackendService backendService) {
        this.backendService = backendService;
    }

    @GetMapping("/")
    public String index(HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/user.html";
        }
        return "index.html";
    }

    @PostMapping("/ai/generate")
    @ResponseBody
    public String generate(@RequestBody java.util.List<String> chatRequest) {
        String userMessage = String.join("\n", chatRequest);
        return backendService.getAiResponse(userMessage);
    }

    @PostMapping("/users")
    @ResponseBody
    public ResponseEntity<User> newUser(@RequestBody User user, HttpSession session) {
        User savedUser = backendService.loginOrRegisterUser(user);
        session.setAttribute("userId", savedUser.getId());
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/users")
    @ResponseBody
    public Iterable<User> getUsers() {
        return backendService.getUsers();
    }

    @GetMapping("/users/{id}")
    @ResponseBody
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return backendService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user.html";
    }
}
