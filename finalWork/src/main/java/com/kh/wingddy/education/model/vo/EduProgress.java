package com.kh.wingddy.education.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString(callSuper = true) @Builder
public class EduProgress extends Edu{

	private int totalCount;
	private int completeCount;
	private int progressRate;
}
