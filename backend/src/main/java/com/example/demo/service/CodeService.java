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
		CodeEntity cd = codeRepo.findById(id).orElse(null);
		
		if(cd != null) {
			return new Code(cd.getC_id(), cd.getC_parent_id(), cd.getC_name(), cd.getC_eng_name(), cd.getC_description());
		}
		return null;
	}
	
	public List<Code> findAll() {
		return codeRepo.findAll().stream().map(m -> new Code(m.getC_id(), m.getC_parent_id(), m.getC_name(), m.getC_eng_name(), m.getC_description())).toList();
	}
}
