package com.concert.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/concerts")
public class ConcertController {

    @GetMapping("/available_dates")
    public ResponseEntity<Map<String, Object>> getAvailableDates(
            @RequestHeader("Authorization") String token) {
        
        List<Map<String, Object>> dates = new ArrayList<>();
        Map<String, Object> date1 = new HashMap<>();
        date1.put("date", "2024-03-20");
        date1.put("available_seat_count", 30);
        dates.add(date1);
        
        Map<String, Object> response = new HashMap<>();
        response.put("available_dates", dates);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{date}/seats")
    public ResponseEntity<Map<String, Object>> getAvailableSeats(
            @PathVariable String date,
            @RequestHeader("Authorization") String token) {
        
        List<Map<String, Object>> seats = new ArrayList<>();
        Map<String, Object> seat1 = new HashMap<>();
        seat1.put("seat_num", 1);
        seat1.put("grade", "VIP");
        seat1.put("price", 200000);
        seat1.put("status", "AVAILABLE");
        seats.add(seat1);
        
        Map<String, Object> response = new HashMap<>();
        response.put("seats", seats);
        
        return ResponseEntity.ok(response);
    }
} 