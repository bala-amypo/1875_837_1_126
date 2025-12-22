package com.example.demo.controller;

import com.example.demo.model.TierUpgradeRule;
import com.example.demo.service.TierUpgradeRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tier-rules")
public class TierUpgradeRuleController {

private final TierUpgradeRuleService tierUpgradeRuleService;

// Constructor injection
public TierUpgradeRuleController(TierUpgradeRuleService tierUpgradeRuleService) {
this.tierUpgradeRuleService = tierUpgradeRuleService;
}

//  POST -Create a new rule
@PostMapping
public ResponseEntity<TierUpgradeRule> createRule(@RequestBody TierUpgradeRule rule) {
TierUpgradeRule createdRule = tierUpgradeRuleService.createRule(rule);
return ResponseEntity.ok(createdRule);
}

//  PUT - Update an existing rule
@PutMapping("/{id}")
public ResponseEntity<TierUpgradeRule> updateRule(
@PathVariable Long id, @RequestBody TierUpgradeRule rule) {
TierUpgradeRule updatedRule = tierUpgradeRuleService.updateRule(id, rule);
return ResponseEntity.ok(updatedRule);
 }

//  GET - Get all active rules
@GetMapping("/active")
public ResponseEntity<List<TierUpgradeRule>> getActiveRules() {
 List<TierUpgradeRule> activeRules = tierUpgradeRuleService.getActiveRules();
 return ResponseEntity.ok(activeRules);
 }
 //  GET - Get all rules
@GetMapping
public ResponseEntity<List<TierUpgradeRule>> getAllRules() {
List<TierUpgradeRule> allRules = tierUpgradeRuleService.getAllRules();
return ResponseEntity.ok(allRules);
}

// GET - Get specific rule
@GetMapping("/lookup")
public ResponseEntity<TierUpgradeRule> getRule(@RequestParam String fromTier,@RequestParam String toTier) {
TierUpgradeRule rule = tierUpgradeRuleService.getRule(fromTier, toTier);
return ResponseEntity.ok(rule);
}
}
