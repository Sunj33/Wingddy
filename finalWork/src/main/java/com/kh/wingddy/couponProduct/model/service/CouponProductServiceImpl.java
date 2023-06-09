package com.kh.wingddy.couponProduct.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.wingddy.common.model.vo.Attachment;
import com.kh.wingddy.common.model.vo.PageInfo;
import com.kh.wingddy.couponProduct.model.dao.CouponProductDao;
import com.kh.wingddy.couponProduct.model.vo.CouponProduct;

@Service
public class CouponProductServiceImpl implements CouponProductService {
	
	@Autowired
	private CouponProductDao cpDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int selectListCount(int classNo) {
		return cpDao.selectListCount(sqlSession, classNo);
	}

	@Override
	public ArrayList<CouponProduct> selectCouponProductList(PageInfo pi, CouponProduct cp) {
		return cpDao.selectCouponProductList(sqlSession, pi, cp);
	}
	
	@Override
	public int insertCouponProduct(CouponProduct cp) {
		return cpDao.insertCouponProduct(sqlSession, cp) > 0 ? 1 : 0;
	}


	@Override
	public int useCoupon(CouponProduct cp) {
		return cpDao.useCoupon(sqlSession, cp) > 0 ? 1 : 0 ; 
	}
	
	@Override
	public ArrayList<CouponProduct> myCouponList(CouponProduct cp) {
		return cpDao.myCouponList(sqlSession, cp);
	}

	@Override
	public int buyCouponProduct(ArrayList<CouponProduct> cpList, CouponProduct cp) {
		//(학생 보유 상품 insert, 상품 목록 수량 update, 학생 보유 쿠폰 update)
		
		
		if(cpDao.insertMyCp(sqlSession, cpList) * cpDao.updateCp(sqlSession, cp) * cpDao.updateCoupon(sqlSession, cp) > 0) {
			return 1; 
		} else {
			return 0;
		}
	}

	@Override
	public ArrayList<CouponProduct> selectClassCplist(int cno) {
		return cpDao.selectClassCplist(sqlSession, cno);
	}

	@Override
	public ArrayList<CouponProduct> selectStudentCplist(CouponProduct cp) {
		return cpDao.selectStudentCplist(sqlSession, cp);
	}

	@Override
	public ArrayList<CouponProduct> searchStudentCp(HashMap map) {
		return cpDao.searchStudentCp(sqlSession, map);
	}

	
	
	
	
	
	
	
}
