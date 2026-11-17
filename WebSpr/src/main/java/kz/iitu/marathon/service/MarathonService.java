package kz.iitu.marathon.service;

import kz.iitu.marathon.model.Runner;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MarathonService {
    private final List<Runner> runners = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    public MarathonService() {
        runners.add(new Runner(counter.getAndIncrement(), "Alice", "USA"));
        runners.add(new Runner(counter.getAndIncrement(), "Bob", "UK"));
        runners.add(new Runner(counter.getAndIncrement(), "Charlie", "Canada"));
    }

    public List<Runner> getAllRunners(String nameQuery) {
        if (nameQuery == null || nameQuery.isBlank()) return runners;
        return runners.stream()
                .filter(r -> r.getName().toLowerCase().contains(nameQuery.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Runner getRunnerById(Long id) {
        return runners.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
    }

    public Runner createRunner(Runner runner) {
        if (runner.getName() == null || runner.getName().isBlank() ||
                runner.getCountry() == null || runner.getCountry().isBlank()) {
            return null;
        }
        runner.setId(counter.getAndIncrement());
        runners.add(runner);
        return runner;
    }

    public Runner updateRunner(Long id, Runner updatedData) {
        if (updatedData.getName() == null || updatedData.getName().isBlank() ||
                updatedData.getCountry() == null || updatedData.getCountry().isBlank()) {
            return null;
        }
        Runner runner = getRunnerById(id);
        if (runner != null) {
            runner.setName(updatedData.getName());
            runner.setCountry(updatedData.getCountry());
        }
        return runner;
    }

    public boolean deleteRunner(Long id) {
        return runners.removeIf(r -> r.getId().equals(id));
    }
}