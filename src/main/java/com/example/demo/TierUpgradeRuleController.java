package com.example.demo;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController @RequestMapping("/api/tier-rules") @Tag(name="Tier Upgrade Rules")
public class TierUpgradeRuleController {
    private final TierUpgradeRuleService service;
    public TierUpgradeRuleController(TierUpgradeRuleService service) { this.service = service; }
    @PostMapping public TierUpgradeRule create(@RequestBody TierUpgradeRule r) { return service.createRule(r); }
    @PutMapping("/{id}") public TierUpgradeRule update(@PathVariable Long id, @RequestBody TierUpgradeRule r) { return service.updateRule(id, r); }
    @GetMapping("/active") public List<TierUpgradeRule> getActive() { return service.getActiveRules(); }
    @GetMapping public List<TierUpgradeRule> getAll() { return service.getAllRules(); }
    // Unwrap Optional
    @GetMapping("/lookup") public TierUpgradeRule getRule(@RequestParam String fromTier, @RequestParam String toTier) { 
        return service.getRule(fromTier, toTier).orElseThrow(() -> new NoSuchElementException("Rule not found")); 
    }
}