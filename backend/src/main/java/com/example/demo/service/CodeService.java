package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Code;
import com.example.demo.repository.CodeRepository;

@Service
public class CodeService {
	@Autowired
	private CodeRepository codeRepo;
	
	public Code fineById(String id) {
		return codeRepo.findById(id)
				.map(m -> new Code(m.getC_id(), m.getC_parent_id(), m.getC_name(), m.getC_eng_name(), m.getC_description()))
				.orElse(null);
	}
	
	public List<Code> findAll() {
		return codeRepo.findAll().stream()
				.map(m -> new Code(m.getC_id(), m.getC_parent_id(), m.getC_name(), m.getC_eng_name(), m.getC_description()))
				.toList();
	}
}
