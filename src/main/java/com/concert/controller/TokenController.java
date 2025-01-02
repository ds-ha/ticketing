package com.concert.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TokenController {
    
    @PostMapping("/queue_tokens")
    public ResponseEntity<Map<String, Object>> createToken(@RequestBody Map<String, Long> request) {
        // Mock 응답
        Map<String, Object> response = new HashMap<>();
        response.put("uuid", UUID.randomUUID().toString());
        response.put("queue_num", 15);
        response.put("expires_at", "300");
        
        return ResponseEntity.ok(response);
    }
} 