package com.kh.wingddy.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.wingddy.common.model.vo.Attachment;
import com.kh.wingddy.member.model.dao.MemberDao;
import com.kh.wingddy.member.model.vo.Cert;
import com.kh.wingddy.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public Member loginMember(Member m) {
		
		return memberDao.loginMember(sqlSession, m);
	}

	@Override
	public int insertMember(Member m) {
		
		return memberDao.insertMember(sqlSession, m);
	}

	@Override
	public int insertTeacher(Member m, Attachment at) {
		
		int result1 = memberDao.insertMember(sqlSession, m);
		int result2 = memberDao.insertAttach(sqlSession, at);
		
		return result1 * result2;
	}

	@Override
	public int updateMember(Member m, Attachment at) {
		
		int result1 = memberDao.updateMember(sqlSession, m);
		int result2 = memberDao.updateProfile(sqlSession, at);
		
		return result1 * result2;
	}

	@Override
	public Attachment selectProfile(int memberNo) {
		return memberDao.selectProfile(sqlSession, memberNo);
	}

	@Override
	public Member searchId(String email) {
		return memberDao.searchId(sqlSession, email);
	}

	@Override
	public int insertProfile(Attachment at) {
		return memberDao.insertProfile(sqlSession, at);
	}

	@Override
	public Attachment selectEmploy(int memberNo) {
		return memberDao.selectEmploy(sqlSession, memberNo);
	}

	@Override
	public int idCheck(String memberId) {
		return memberDao.idCheck(sqlSession, memberId);
	}

	@Override
	public int insertCert(Cert cert) {
		return memberDao.insertCert(sqlSession, cert);
	}

	@Override
	public Cert checkCode(Cert cert) {
		return memberDao.checkCode(sqlSession, cert);
	}

	@Override
	public int certifyCode(Cert cert) {
		return memberDao.certifyCode(sqlSession, cert);
	}

	@Override
	public int updatePwd(Member m) {
		return memberDao.updatePwd(sqlSession, m);
	}
	/*
	@Override
	public int dropMember(Member m) {
		return memberDao.dropMember(sqlSession, m);
	}

	@Override
	public int dropEmploy(int memberNo) {
		return memberDao.dropEmploy(sqlSession, memberNo);
	}

	@Override
	public int dropClassroom(int memberNo) {
		return memberDao.dropEmploy(sqlSession, memberNo);
	}
	*/
}
