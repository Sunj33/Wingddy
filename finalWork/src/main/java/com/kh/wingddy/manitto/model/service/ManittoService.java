package com.kh.wingddy.manitto.model.service;

import java.util.ArrayList;

import com.kh.wingddy.manitto.model.vo.Manitto;

public interface ManittoService {
	
	// 마니또 매칭시키기
	String manittoMatching(Manitto mt); 
	
	// 마니또 리스트 불러오기
	ArrayList<Manitto> selectManittoList(int classNo);
	
	// 마니또 종료
	int deleteManitto(int classNo);
	
	// 내 마니띠 보기
	String selectMyManitti(Manitto mt);
}
