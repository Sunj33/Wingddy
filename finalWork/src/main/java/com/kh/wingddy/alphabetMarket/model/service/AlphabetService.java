package com.kh.wingddy.alphabetMarket.model.service;

import java.util.ArrayList;

import com.kh.wingddy.alphabetMarket.model.vo.Alphabet;
import com.kh.wingddy.alphabetMarket.model.vo.AlphabetMarket;
import com.kh.wingddy.alphabetMarket.model.vo.ChangeAlphabet;
import com.kh.wingddy.alphabetMarket.model.vo.MarketReply;
import com.kh.wingddy.alphabetMarket.model.vo.MyCount;
import com.kh.wingddy.alphabetMarket.model.vo.Words;
import com.kh.wingddy.common.model.vo.PageInfo;

public interface AlphabetService {
	
	// 알파벳 마켓 메인화면 리스트
	ArrayList<AlphabetMarket> ajaxSelectMarketList(AlphabetMarket am);

	
	// 알파벳 마켓 디테일
	AlphabetMarket marketDetail(int bno);
	
	// 댓글 총 개수
	public int replyCount(int bno); 

	// 마켓 댓글 리스트조회
	ArrayList<MarketReply> ajaxReplyList(PageInfo pi, int bno);
	
	
	// 알파벳 마켓 작성시 자기 알파벳 셀렉트
	ArrayList<AlphabetMarket> selectCategory(Alphabet ap);
	
	
	// 알파벳 마켓 글 작성
	int insertMarket(AlphabetMarket am);

	
	// 마켓 댓글 작성
	int ajaxInsertReply(MarketReply mr);
	
	// 마켓 댓글 수정
	int ajaxUpdateReply(MarketReply mr);
	
	
	// 마켓 알파벳 체인지
	String ajaxChangeAlphabet(ChangeAlphabet ca);
	
	
	// 내 알파벳 마이 카운트조회
	MyCount ajaxMyCount(MyCount mc);
	
	// 작성자의 마지막 게시글 번호 조회
	int writerLastMarket(AlphabetMarket am);
	
	// 알파벳 가챠
	String ajaxGachaAlphabet(Alphabet ap);
	
	
	// 단어 > 쿠폰 교환
	public String insertWords(Words wd);
	
}
