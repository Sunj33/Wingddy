package com.kh.wingddy.alphabetMarket.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.wingddy.alphabetMarket.model.dao.AlphabetDao;
import com.kh.wingddy.alphabetMarket.model.vo.AlphabetMarket;

@Service
public class AlphabetServiceImpl implements AlphabetService {
	
	@Autowired
	private AlphabetDao alphabetDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public ArrayList<AlphabetMarket> ajaxSelectMarketList(AlphabetMarket am) {
		
		//return alphabetDao.ajaxSelectMarketList(sqlSession, am);
		return null;
	
	}

	@Override
	public ArrayList<AlphabetMarket> ajaxSelectMarketListFilter(AlphabetMarket am) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlphabetMarket marketDetail(int marketBno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int marketInsert(AlphabetMarket am) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int marketInsert(int marketBno) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int alphabet(AlphabetMarket am) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int ajaxSelectReply(int marketBno) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int ajaxInsertReply(int marketBno) {
		// TODO Auto-generated method stub
		return 0;
	}



}
