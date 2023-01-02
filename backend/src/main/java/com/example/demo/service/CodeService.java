package com.example.demo.service;

import com.example.demo.annotation.CrossConcern;
import com.example.demo.entity.CodeEntity;
import com.example.demo.model.Code;
import com.example.demo.repository.CodeRepository;
import com.example.demo.utils.AutoCloseableTest;
import com.example.demo.utils.LambdaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
public class CodeService {
	@Autowired
	private CodeRepository codeRepo;

	public Code fineById(String id) {
		return codeRepo.findById(id).map(this::convertEntityToModel).orElse(null);
	}

	public Integer getTotal(List<Integer> list, Predicate<Integer> selector) {
		return list.stream().filter(selector).mapToInt(m -> m).sum();
	}
	
	public Integer getIncrement(Integer num, Function<Integer, Integer> func) {
		return func.apply(num);
	}


	@CrossConcern
	public List<Code> findAll() {
		Function<Integer, Integer> test = value -> value + 1;
		
		List<Integer> list = List.of(1, 4, 2, 5);

		// 테스트 해보자
		System.out.println(this.getTotal(list, n -> n % 2 == 0));
		System.out.println(this.getIncrement(list.get(0), test));
		LambdaTest.test(t -> t.print().print2());
		AutoCloseableTest.use(u -> u.print());
		
		return codeRepo.findAll().stream().map(this::convertEntityToModel).toList();
	}

	private Code convertEntityToModel(CodeEntity org) {
		return new Code(org.getC_id(), org.getC_parent_id(), org.getC_name(), org.getC_eng_name(),
				org.getC_description());
	}
}
