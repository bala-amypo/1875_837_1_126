package com.example.demo.controller;

import com.example.demo.model.TierHistoryRecord;
import com.example.demo.service.TierUpgradeEngineService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tier-engine")
@Tag(name = "Tier Upgrade Engine")
public class TierUpgradeEngineController {
    private final TierUpgradeEngineService service;
    public TierUpgradeEngineController(TierUpgradeEngineService service) { this.service = service; }

    @PostMapping("/evaluate/{customerId}")
    public TierHistoryRecord evaluate(@PathVariable Long customerId) { return service.evaluateAndUpgradeTier(customerId); }
    @GetMapping("/history/{customerId}")
    public List<TierHistoryRecord> getHistory(@PathVariable Long customerId) { return service.getHistoryByCustomer(customerId); }
    @GetMapping
    public List<TierHistoryRecord> getAllHistory() { return service.getAllHistory(); }
}