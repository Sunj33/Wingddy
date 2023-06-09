package com.kh.wingddy.letter.model.vo;

import java.util.List;

import lombok.Data;

@Data
public class Letter {
	
	private int letterNo;
	private int classNo;
	private String sender;
	private String recipient;
	private String letterContent;
	private String sendDate;
	private String anonymous;
	private String toManitto; // 마니띠가 마니또에게 
	private String gift; 
	private String getGift;
	private String readCheck;
	private String className;
	private String keyword;
	
	
	private int memberNo;
	private List<String> letterList;
	
}
