package com.example.studentapi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class StudentController {
    private final List<Student> students = new ArrayList<>(List.of(
            new Student(1L, "Asha", "Java"),
            new Student(2L, "Rahul", "Spring Boot"),
            new Student(3L, "Meera", "Cloud Computing")
    ));
    private final AtomicLong nextId = new AtomicLong(4);

    @GetMapping("/")
    public Welcome welcome() {
        return new Welcome(
                "Welcome to the Student Management API!",
                "Try GET /api/students or GET /health"
        );
    }

    @GetMapping("/health")
    public Health health() {
        return new Health("UP");
    }

    @GetMapping("/api/students")
    public List<Student> getAllStudents() {
        return List.copyOf(students);
    }

    @GetMapping("/api/students/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return students.stream()
                .filter(student -> student.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student not found: " + id));
    }

    @PostMapping("/api/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@RequestBody CreateStudentRequest request) {
        if (request.name() == null || request.name().isBlank()
                || request.course() == null || request.course().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Both name and course are required");
        }

        Student student = new Student(
                nextId.getAndIncrement(),
                request.name().trim(),
                request.course().trim()
        );
        students.add(student);
        return student;
    }

    public record Welcome(String message, String instructions) {}
    public record Health(String status) {}
    public record CreateStudentRequest(String name, String course) {}
}
