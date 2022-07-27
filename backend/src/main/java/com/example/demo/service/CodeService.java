package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CodeEntity;
import com.example.demo.model.Code;
import com.example.demo.repository.CodeRepository;

@Service
public class CodeService {
	@Autowired
	private CodeRepository codeRepo;

	public Code fineById(String id) {
		return codeRepo.findById(id).map(this::convertEntityToModel).orElse(null);
	}

	public List<Code> findAll() {
		return codeRepo.findAll().stream().map(this::convertEntityToModel).toList();
	}

	private Code convertEntityToModel(CodeEntity org) {
		return new Code(org.getC_id(), org.getC_parent_id(), org.getC_name(), org.getC_eng_name(),
				org.getC_description());
	}
}
