package com.concert.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ReservationController {

    @PostMapping("/reservations")
    public ResponseEntity<Map<String, Object>> createReservation(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> request) {
        
        Map<String, Object> seatInfo = new HashMap<>();
        seatInfo.put("seat_num", 1);
        seatInfo.put("grade", "VIP");
        seatInfo.put("price", 200000);
        
        Map<String, Object> response = new HashMap<>();
        response.put("reservation_id", "123");
        response.put("seat_info", seatInfo);
        response.put("temp_reserved_at", "2024-03-19T15:30:00");
        response.put("status", "TEMPORARY");
        
        return ResponseEntity.ok(response);
    }
} 