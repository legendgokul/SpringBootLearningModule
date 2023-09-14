package com.example.FirstApp.student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IStudentRepository extends JpaRepository <Student, UUID>{
}
