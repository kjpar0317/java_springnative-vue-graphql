package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import com.example.demo.model.Code;
import com.example.demo.service.CodeService;

import reactor.core.publisher.Flux;

//@TypeHint(types = Code.class, access = {TypeAccess.AUTO_DETECT})
@Secured({"ROLE_ADMIN"})
@Controller
class CodeController {
	@Autowired
	private CodeService codeService;

	@QueryMapping
	Flux<Code> codes() {
		return Flux.fromIterable(codeService.findAll());
	}
}