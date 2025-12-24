package com.example.demo.controller;

import com.example.demo.entity.TierUpgradeRule;
import com.example.demo.service.TierUpgradeRuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tier-rules")
@Tag(name = "Tier Upgrade Rules")
public class TierUpgradeRuleController {
    private final TierUpgradeRuleService ruleService;

    public TierUpgradeRuleController(TierUpgradeRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public TierUpgradeRule createRule(@RequestBody TierUpgradeRule rule) {
        return ruleService.createRule(rule);
    }

    @PutMapping("/{id}")
    public TierUpgradeRule updateRule(@PathVariable Long id, @RequestBody TierUpgradeRule rule) {
        return ruleService.updateRule(id, rule);
    }

    @GetMapping("/active")
    public List<TierUpgradeRule> getActiveRules() {
        return ruleService.getActiveRules();
    }

    @GetMapping
    public List<TierUpgradeRule> getAllRules() {
        return ruleService.getAllRules();
    }

    @GetMapping("/lookup")
    public TierUpgradeRule getRule(@RequestParam String fromTier, @RequestParam String toTier) {
        return ruleService.getRule(fromTier, toTier);
    }
}