package com.concert.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PaymentController {

    @PostMapping("/payments")
    public ResponseEntity<Map<String, Object>> processPayment(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, String> request) {
        
        Map<String, Object> seatInfo = new HashMap<>();
        seatInfo.put("concert_date", "2024-03-20");
        seatInfo.put("seat_num", 1);
        seatInfo.put("grade", "VIP");
        
        Map<String, Object> paymentDetails = new HashMap<>();
        paymentDetails.put("before_balance", 500000);
        paymentDetails.put("after_balance", 300000);
        paymentDetails.put("payment_method", "BALANCE");
        
        Map<String, Object> response = new HashMap<>();
        response.put("payment_id", "456");
        response.put("reservation_id", request.get("reservation_id"));
        response.put("paid_amount", 200000);
        response.put("status", "SUCCESS");
        response.put("completed_at", "2024-03-19T15:28:00");
        response.put("seat_info", seatInfo);
        response.put("payment_details", paymentDetails);
        
        return ResponseEntity.ok(response);
    }
} 