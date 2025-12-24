package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.TierUpgradeRule;
import com.example.demo.service.TierUpgradeRuleService;

@RestController
@RequestMapping("/api/tier-rules")
public class TierUpgradeRuleController {

    private final TierUpgradeRuleService ruleService;

    public TierUpgradeRuleController(TierUpgradeRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<TierUpgradeRule> createRule(@RequestBody TierUpgradeRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TierUpgradeRule> updateRule(@PathVariable Long id, @RequestBody TierUpgradeRule rule) {
        return ResponseEntity.ok(ruleService.updateRule(id, rule));
    }

    @GetMapping
    public ResponseEntity<List<TierUpgradeRule>> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }

    @GetMapping("/lookup")
    public ResponseEntity<TierUpgradeRule> getRule(@RequestParam String fromTier) {
        return ResponseEntity.ok(ruleService.getRuleByFromTier(fromTier));
    }
}
