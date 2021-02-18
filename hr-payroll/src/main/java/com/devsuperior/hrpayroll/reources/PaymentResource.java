package com.devsuperior.hrpayroll.reources;

import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {

    private final PaymentService service;

    public PaymentResource(PaymentService service) {
        this.service = service;
    }

    @GetMapping(value = "/{workerId}/days/{days}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long workerId, @PathVariable Integer days) {
        Payment payment = service.getPayment(workerId, days);
        return ResponseEntity.ok(payment);
    }
}
