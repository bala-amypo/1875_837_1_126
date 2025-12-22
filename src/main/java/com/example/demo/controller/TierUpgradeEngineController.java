package com.example.demo.controller;

import com.example.demo.model.TierHistoryRecord;
import com.example.demo.service.TierUpgradeEngineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tier-engine")
public class TierUpgradeEngineController {

    private final TierUpgradeEngineService tierUpgradeEngineService;

    public TierUpgradeEngineController(TierUpgradeEngineService tierUpgradeEngineService) {
        this.tierUpgradeEngineService = tierUpgradeEngineService;
    }

   
    @PostMapping("/evaluate/{customerId}")
    public ResponseEntity<?> evaluateTier(@PathVariable Long customerId) {

        TierHistoryRecord historyRecord =
                tierUpgradeEngineService.evaluateAndUpgradeTier(customerId);

        if (historyRecord == null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("No tier upgrade criteria met");
        }

        return ResponseEntity.ok(historyRecord);
    }

    
    @GetMapping("/history/{customerId}")
    public ResponseEntity<List<TierHistoryRecord>> getHistoryByCustomer(
            @PathVariable Long customerId
    ) {
        return ResponseEntity.ok(
                tierUpgradeEngineService.getHistoryByCustomer(customerId)
        );
    }

    
    @GetMapping
    public ResponseEntity<List<TierHistoryRecord>> getAllHistory() {
        return ResponseEntity.ok(
                tierUpgradeEngineService.getAllHistory()
        );
    }
}
