package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TB_CODE_M")
@Data
@NoArgsConstructor
public class CodeEntity {
	@Id
	private String c_id;
	private String c_parent_id;
	private String c_name;
	private String c_eng_name;
	private String c_description;
}