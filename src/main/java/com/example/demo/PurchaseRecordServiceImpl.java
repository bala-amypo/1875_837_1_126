package com.example.demo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseRecordServiceImpl implements PurchaseRecordService {
    private final PurchaseRecordRepository repository;
    public PurchaseRecordServiceImpl(PurchaseRecordRepository repository) { this.repository = repository; }
    
    public PurchaseRecord recordPurchase(PurchaseRecord purchase) {
        if (purchase.getAmount() <= 0) throw new IllegalArgumentException("Amount must be positive");
        return repository.save(purchase);
    }
    public List<PurchaseRecord> getPurchasesByCustomer(Long customerId) { return repository.findByCustomerId(customerId); }
    public Optional<PurchaseRecord> getPurchaseById(Long id) { return repository.findById(id); }
    public List<PurchaseRecord> getAllPurchases() { return repository.findAll(); }
}