package com.task.tasks.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.task.tasks.dao.TaskRepository;
import com.task.tasks.entity.Tasks;

import jakarta.validation.Valid;

@Service
public class TaskService {

	private final TaskRepository taskRepository;
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	// Add methods to handle task operations like create, update, delete, and fetch tasks
	public Tasks createTask(@Valid Tasks task) {
		return taskRepository.save(task);
	}
	public Tasks updateTask(Long taskId, @Valid Tasks task) {
		if (taskRepository.existsById(taskId)) {
			task.setId(taskId);
			return taskRepository.save(task);
		}
		return null; // or throw an exception
	}
	public boolean deleteTask(Long taskId) {
		if (taskRepository.existsById(taskId)) {
			taskRepository.deleteById(taskId);	
			return true;
		}else {
			return false; 
		}
	}
	public List<Tasks> getAllTasks() {
		return taskRepository.findAll();
	}

	public Tasks getTaskById(Long id) {
		return taskRepository.findById(id).orElse(null);
	}
}
