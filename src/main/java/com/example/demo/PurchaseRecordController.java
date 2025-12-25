package com.example.demo;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController @RequestMapping("/api/purchases") @Tag(name="Purchase Records")
public class PurchaseRecordController {
    private final PurchaseRecordService service;
    public PurchaseRecordController(PurchaseRecordService service) { this.service = service; }
    @PostMapping public PurchaseRecord record(@RequestBody PurchaseRecord p) { return service.recordPurchase(p); }
    @GetMapping("/customer/{customerId}") public List<PurchaseRecord> getByCustomer(@PathVariable Long customerId) { return service.getPurchasesByCustomer(customerId); }
    // FIXED: Service returns Entity
    @GetMapping("/{id}") public PurchaseRecord getById(@PathVariable Long id) { return service.getPurchaseById(id); }
    @GetMapping public List<PurchaseRecord> getAll() { return service.getAllPurchases(); }
}