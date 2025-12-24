package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.TierHistoryRecord;
import com.example.demo.service.TierUpgradeEngineService;

@RestController
@RequestMapping("/api/tier-engine")
public class TierUpgradeEngineController {

    private final TierUpgradeEngineService engineService;

    public TierUpgradeEngineController(TierUpgradeEngineService engineService) {
        this.engineService = engineService;
    }

    @PostMapping("/evaluate/{customerId}")
    public ResponseEntity<TierHistoryRecord> evaluateTier(@PathVariable Long customerId) {
        return ResponseEntity.ok(engineService.evaluateAndUpgradeTier(customerId));
    }

    @GetMapping("/history/{customerId}")
    public ResponseEntity<List<TierHistoryRecord>> getHistoryByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(engineService.getHistoryByCustomer(customerId));
    }

    @GetMapping
    public ResponseEntity<List<TierHistoryRecord>> getAllHistory() {
        return ResponseEntity.ok(engineService.getAllHistory());
    }
}
