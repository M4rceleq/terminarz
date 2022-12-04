package pl.marcel.plan_elektronik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.marcel.plan_elektronik.model.Deadline;
import pl.marcel.plan_elektronik.repository.DeadlineRepository;

import java.util.List;

@RestController
@RequestMapping("/deadlines")
public class DeadlineController {

    @Autowired
    DeadlineRepository deadlineRepository;

    @GetMapping("")
    public List<Deadline> getAll() {
        return deadlineRepository.getAll();
    }

    @GetMapping("/{id}")
    public Deadline getById(@PathVariable("id") int id) {
        return deadlineRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Deadline> deadlines) {
        return deadlineRepository.save(deadlines);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return deadlineRepository.delete(id);
    }
}