package com.kh.wingddy.alphabetMarket.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.wingddy.alphabetMarket.model.vo.AlphabetMarket;

@Repository
public class AlphabetDao {
	
	public void ajaxSelectMarketList(SqlSessionTemplate sqlSession, AlphabetMarket am){
		//return sqlSession.selectList(am);
	}

}
