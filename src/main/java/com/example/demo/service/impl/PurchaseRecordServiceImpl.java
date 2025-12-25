package com.example.demo.service.impl;
import com.example.demo.model.PurchaseRecord;
import com.example.demo.repository.PurchaseRecordRepository;
import com.example.demo.service.PurchaseRecordService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PurchaseRecordServiceImpl implements PurchaseRecordService {
    private final PurchaseRecordRepository repository;
    public PurchaseRecordServiceImpl(PurchaseRecordRepository repository) { this.repository = repository; }
    
    public PurchaseRecord recordPurchase(PurchaseRecord purchase) {
        if (purchase.getAmount() <= 0) throw new IllegalArgumentException("Amount must be positive");
        return repository.save(purchase);
    }
    public List<PurchaseRecord> getPurchasesByCustomer(Long customerId) { return repository.findByCustomerId(customerId); }
    public PurchaseRecord getPurchaseById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Purchase record not found"));
    }
    public List<PurchaseRecord> getAllPurchases() { return repository.findAll(); }
}