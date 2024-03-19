package com.Assignment.Internship.Assignment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Date dueDate;
    private String status;

    @Column(name = "user_id") // Specify the column name in the database
    private Long userId; // User ID associated with the task
}
