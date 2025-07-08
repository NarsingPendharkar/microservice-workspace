package com.task.tasks.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.tasks.entity.Tasks;
import com.task.tasks.service.TaskService;
import com.task.tasks.util.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Tasks>>> getAllTasks(HttpServletRequest request) {
        List<Tasks> tasks = taskService.getAllTasks();
        ApiResponse<List<Tasks>> response = ApiResponse.success(
                tasks,
                "Tasks fetched successfully",
                request.getRequestURI()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Tasks>> getTaskById(@PathVariable Long id, HttpServletRequest request) {
        Tasks Tasks = taskService.getTaskById(id);
        if (Tasks == null) {
            ApiResponse<Tasks> errorResponse = ApiResponse.error(
                    "Tasks not found",
                    request.getRequestURI(),
                    "Not Found",
                    HttpStatus.NOT_FOUND.value()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        ApiResponse<Tasks> response = ApiResponse.success(
                Tasks,
                "Tasks fetched successfully",
                request.getRequestURI()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Tasks>> createTask(@Valid @RequestBody Tasks Tasks, HttpServletRequest request) {
        if (Tasks == null || Tasks.getTitle() == null || Tasks.getTitle().isEmpty()) {
            ApiResponse<Tasks> errorResponse = ApiResponse.error(
                    "Tasks name cannot be null or empty",
                    request.getRequestURI(),
                    "Bad Request",
                    HttpStatus.BAD_REQUEST.value()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        Tasks created = taskService.createTask(Tasks);
        ApiResponse<Tasks> response = ApiResponse.success(
                created,
                "Tasks created successfully",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Tasks>> updateTask(@PathVariable Long id, @Valid @RequestBody Tasks Tasks, HttpServletRequest request) {
        Tasks updated = taskService.updateTask(id, Tasks);
        if (updated == null) {
            ApiResponse<Tasks> errorResponse = ApiResponse.error(
                    "Tasks not found",
                    request.getRequestURI(),
                    "Not Found",
                    HttpStatus.NOT_FOUND.value()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        ApiResponse<Tasks> response = ApiResponse.success(
                updated,
                "Tasks updated successfully",
                request.getRequestURI()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTask(@PathVariable Long id, HttpServletRequest request) {
        boolean deleted = taskService.deleteTask(id);
        if (!deleted) {
            ApiResponse<Void> errorResponse = ApiResponse.error(
                    "Tasks not found",
                    request.getRequestURI(),
                    "Not Found",
                    HttpStatus.NOT_FOUND.value()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        ApiResponse<Void> response = ApiResponse.success(
                null,
                "Tasks deleted successfully",
                request.getRequestURI()
        );
        return ResponseEntity.ok(response);
    }
}
