package com.kh.wingddy.voca.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.wingddy.member.model.vo.Member;
import com.kh.wingddy.voca.model.dao.VocaMapper;
import com.kh.wingddy.voca.model.vo.ClassVocaBook;
import com.kh.wingddy.voca.model.vo.Voca;
import com.kh.wingddy.voca.model.vo.VocaBook;

@Service
public class VocaServiceImpl implements VocaService {
	
	@Autowired
	private VocaMapper vocaMapper;
	
	@Override
	public ArrayList<VocaBook> selectVocaBookList(int memNo) {
		return vocaMapper.selectVocaBookList(memNo);
	}

	@Override
	public ArrayList<Voca> selectVocaList(int bookNo) {
		return vocaMapper.selectVocaList(bookNo);
	}

	@Override
	public ArrayList<ClassVocaBook> selectClassVocaBookList(Member m) {
		return vocaMapper.selectClassVocaBookList(m);
	}

	@Override
	@Transactional
	public int deleteVocaBook(int bookNo) {
		return vocaMapper.deleteVocaList(bookNo)
			 * vocaMapper.deleteVocabook(bookNo);
	}

	@Override
	@Transactional
	public int insertVocaBook(VocaBook vb, ArrayList<Voca> vlist) {
		return vocaMapper.insertVocaBook(vb) 
			 * vocaMapper.insertVocaList(vlist);
	}

	@Override
	public ArrayList<Voca> searchVoca(String text) {
		return vocaMapper.searchVoca(text);
	}

	@Override
	@Transactional
	public int updateVocaBook(HashMap<String, Object> vb) {
		return vocaMapper.deleteVocaList((Integer)(vb.get("bookNo"))) 
			 * vocaMapper.insertVocaMap(vb);
	}

	@Override
	public int insertClassBook(ArrayList<ClassVocaBook> cvList) {
		return vocaMapper.deleteClassBook(cvList.get(0).getBookNo())
			 * vocaMapper.insertClassBook(cvList);
	}
	
	@Override
	public int deleteClassBook(int bookNo) {
		return vocaMapper.deleteClassBook(bookNo);
	}

	@Override
	public ArrayList<ClassVocaBook> selectBookClassList(int bookNo) {
		return vocaMapper.selectBookClassList(bookNo);
	}

	@Override
	public int checkClassBook(ClassVocaBook cv) {
		return vocaMapper.checkClassBook(cv);
	}

	@Override
	public int insertClassBookOne(ArrayList<ClassVocaBook> cvList) {
		return vocaMapper.insertClassBook(cvList);
	}

}
