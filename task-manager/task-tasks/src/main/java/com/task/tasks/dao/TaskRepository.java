package com.task.tasks.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.tasks.entity.TaskStatus;
import com.task.tasks.entity.Tasks;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Long> {

	// Additional query methods can be defined here if needed
	// For example, to find tasks by status or assigned user
	 List<Tasks> findByStatus(TaskStatus status);
	List<Tasks> findByAssignedTo(Long assignedTo);

}
