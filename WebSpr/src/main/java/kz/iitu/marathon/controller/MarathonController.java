package kz.iitu.marathon.controller;

import kz.iitu.marathon.model.Runner;
import kz.iitu.marathon.service.MarathonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/runners")
public class MarathonController {
    private final MarathonService marathonService;

    public MarathonController(MarathonService marathonService) {
        this.marathonService = marathonService;
    }

    @GetMapping
    public List<Runner> getAllRunners(@RequestParam(required = false) String name) {
        return marathonService.getAllRunners(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Runner> getRunnerById(@PathVariable Long id) {
        Runner runner = marathonService.getRunnerById(id);
        if (runner == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(runner);
    }

    @PostMapping
    public ResponseEntity<Runner> createRunner(@RequestBody Runner runner) {
        Runner created = marathonService.createRunner(runner);
        if (created == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Runner> updateRunner(@PathVariable Long id, @RequestBody Runner runner) {
        if (marathonService.getRunnerById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Runner updated = marathonService.updateRunner(id, runner);
        if (updated == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRunner(@PathVariable Long id) {
        boolean deleted = marathonService.deleteRunner(id);
        if (!deleted) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}