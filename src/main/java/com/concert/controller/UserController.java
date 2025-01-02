package com.concert.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping("/balance")
    public ResponseEntity<Map<String, Object>> chargeBalance(
            @RequestBody Map<String, Integer> request) {
        
        Map<String, Object> response = new HashMap<>();
        response.put("user_id", 123);
        response.put("updated_balance", 500000);
        response.put("last_charge_amount", request.get("amount"));
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/balance")
    public ResponseEntity<Map<String, Object>> getBalance(
            @RequestHeader("Authorization") String token) {
        
        Map<String, Object> response = new HashMap<>();
        response.put("user_id", 123);
        response.put("balance", 500000);
        
        return ResponseEntity.ok(response);
    }
} 