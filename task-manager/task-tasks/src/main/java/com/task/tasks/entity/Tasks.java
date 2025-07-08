package com.task.tasks.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_task")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Title is required")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @NotNull(message = "Description is required")
    @Size(min = 1, max = 1000, message = "Description must be between 1 and 1000 characters")
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status is required")
    private TaskStatus status;

    @Temporal(TemporalType.DATE)
    @FutureOrPresent(message = "Due date must be today or in the future")
    @NotNull(message = "Due date is required")
    private Date dueDate;

    @NotNull(message = "Created by is required")
    @Column(name = "created_by")
    private Long createdBy;

    @NotNull(message = "Assigned to is required")
    @Column(name = "assigned_to")
    private Long assignedTo;

    @Temporal(TemporalType.TIMESTAMP)
    @PastOrPresent(message = "Created at must be in the past or present")
    @NotNull(message = "Created at is required")
    private Date createdAt;
}