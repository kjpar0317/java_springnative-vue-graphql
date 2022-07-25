package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CodeEntity;

@Repository
public interface CodeRepository extends JpaRepository<CodeEntity, String> {
}
