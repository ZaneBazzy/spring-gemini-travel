package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class AiController {
	private final BackendService backendService;

	public AiController(BackendService backendService) {
		this.backendService = backendService;
	}

	@PostMapping("/ai/generate")
	public String generate(@RequestBody java.util.List<String> chatRequest) {
		String userMessage = String.join("\n",  chatRequest);
		return backendService.getAiResponse(userMessage);
	}

	@PostMapping("/users")
	public ResponseEntity<User> newUser(@RequestBody User user) {
		User savedUser = backendService.saveUser(user);
		return ResponseEntity.ok(savedUser);
	}

	@GetMapping("/users")
	public Iterable<User> getUsers() {
		return backendService.getUsers();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		return backendService.getUserById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}
