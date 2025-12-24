package com.example.demo.controller;

import com.example.demo.service.TierUpgradeEngineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tier-engine")
@Tag(name = "Tier Upgrade Engine")
public class TierUpgradeEngineController {
    private final TierUpgradeEngineService service;

    public TierUpgradeEngineController(TierUpgradeEngineService service) { this.service = service; }

    @PostMapping("/evaluate/{customerId}")
    @Operation(summary = "Run evaluation", description = "Evaluate and upgrade customer tier")
    public Object evaluate(@PathVariable Long customerId) {
        return service.evaluateAndUpgradeTier(customerId);
    }

    @GetMapping("/history/{customerId}")
    public Object getHistory(@PathVariable Long customerId) {
        return service.getHistoryByCustomer(customerId);
    }

    @GetMapping
    public Object getAllHistory() { return service.getAllHistory(); }
}