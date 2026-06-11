package com.monapp.api.controller;

import com.monapp.api.dto.PaymentRequest;
import com.monapp.api.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@Tag(name = "Payments", description = "Gestion des paiements Stripe")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-payment-intent")
    @Operation(summary = "Créer un PaymentIntent Stripe et retourner le clientSecret")
    public ResponseEntity<Map<String, String>> createPaymentIntent(
            @Valid @RequestBody PaymentRequest request) {

        String clientSecret = paymentService.createPaymentIntent(
                request.getAmount(),
                request.getCurrency(),
                request.getDescription()
        );
        return ResponseEntity.ok(Map.of("clientSecret", clientSecret));
    }
}
