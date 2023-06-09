package com.kh.wingddy.couponProduct.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.wingddy.alphabetMarket.model.service.AlphabetService;
import com.kh.wingddy.alphabetMarket.model.vo.MyCount;
import com.kh.wingddy.common.model.vo.PageInfo;
import com.kh.wingddy.common.template.Pageination;
import com.kh.wingddy.common.template.RenameFile;
import com.kh.wingddy.couponProduct.model.service.CouponProductServiceImpl;
import com.kh.wingddy.couponProduct.model.vo.CouponProduct;

@Controller
public class CouponProductController {
	
	@Autowired
	private CouponProductServiceImpl cpService;
	
	@Autowired
	private AlphabetService alphabetService;
	
	private RenameFile rename = new RenameFile();
	
	@RequestMapping(value="couponStore")
	public String selectCouponProductList(@RequestParam(value = "cPage", defaultValue = "1") int currentPage, 
										  @RequestParam(value = "orderBy", defaultValue ="coupon_price asc") String orderBy, 
										  int cno, 
										  Model model){
		
		PageInfo pi = Pageination.getPageInfo(cpService.selectListCount(cno), currentPage, 8, 5);
		model.addAttribute("pi", pi);
		//System.out.println(pi);
		
		CouponProduct cp = new CouponProduct();
		cp.setClassNo(cno);
		cp.setOrderBy(orderBy);
		
		model.addAttribute("cplist", cpService.selectCouponProductList(pi, cp));
		//System.out.println(cpService.selectCouponProductList(pi, cp));
		
		model.addAttribute("orderBy", orderBy);
		return "coupon/couponProductList";
	}
	
	/**
	 * 교사가 구매한 상품 목록 조회해서 클래스 상품 등록하는 페이지로 이동
	 * @param cno
	 */
	@RequestMapping("enrollForm.cp")
	public String enrollCpForm(Model model, int cno, int mno) {
		model.addAttribute("cplist", cpService.selectClassCplist(mno));
		//System.out.println(model.getAttribute("cplist"));
		return "coupon/enrollCouponProductForm"; 
	}
	
	@RequestMapping("myList.cp")
	public String myCouponList(CouponProduct cp, int cno, Model model) {
		cp.setClassNo(cno);
		model.addAttribute("myCplist", cpService.myCouponList(cp));
		System.out.println(cpService.myCouponList(cp));
		return "coupon/myCouponProduct";
	}
	
	@ResponseBody
	@RequestMapping(value="couponCount.cp", produces="application/json; charset=UTF-8")
	public String myCount(MyCount mc) {
		return new Gson().toJson(alphabetService.ajaxMyCount(mc));
	}
	
	@RequestMapping("buy.cp")
	public String buyCouponProduct(CouponProduct cp, Model model, HttpServletRequest request) {
		//System.out.println(cp);
		
		ArrayList<CouponProduct> cpList = new ArrayList(); 
		
		for(int i = 0; i < cp.getAmount(); i++) {
			cpList.add(cp);
		}
		//System.out.println(cp.getClassNo());
		if(cpService.buyCouponProduct(cpList, cp) > 0 ) {
			request.setAttribute("couponCno", cp.getClassNo());
			return "sideBar/sideBar";
		} else {
			model.addAttribute("errorMsg", "쿠폰 교환 실패");
			return "common/errorPage";
		}
	}
	
	
	/** 클래스 쿠폰 상품 등록
	 * @param cp
	 * @return
	 */
	@RequestMapping("enroll.cp")
	public String insertCouponProduct(CouponProduct cp, Model model, HttpServletRequest request){

		if(cpService.insertCouponProduct(cp) > 0) {
			request.setAttribute("couponCno", cp.getClassNo());
			return "sideBar/sideBar";
		} else {
			model.addAttribute("errorMsg", "상품 등록 실패");
			return "common/errorPage";
		}
		
	}
	
	@RequestMapping("selectSlist.cp")
	public String selectStudentCplist(CouponProduct cp, int cno, Model model) {
		cp.setClassNo(cno);
		//System.out.println(cpService.selectStudentCplist(cp));
		model.addAttribute("sclist", cpService.selectStudentCplist(cp));
		
		return "coupon/studentCpList";
	}
	
	@RequestMapping("use.cp")
	public String useCoupon(CouponProduct cp, int cno, Model model, HttpServletRequest request, HttpSession session) {
		
		cp.setClassNo(cno);
		System.out.println(cp);
		if(cpService.useCoupon(cp) > 0) {
			request.setAttribute("useCpCno", cp.getClassNo());
			session.setAttribute("alertMsg", "학생의 쿠폰이 성공적으로 사용되었습니다.");
			return "sideBar/sideBar";
		} else {
			model.addAttribute("errorMsg", "학생의 쿠폰상품 사용에 실패했습니다.");
			return "common/errorPage";
		}
	}
	
	@RequestMapping("useSearch.cp")
	public String useSearchCoupon(CouponProduct cp, int cno, Model model, HttpServletRequest request, HttpSession session) {
		//System.out.println(cp);
		if(cpService.useCoupon(cp) > 0) {
			request.setAttribute("useCpCno", cno);
			session.setAttribute("alertMsg", "학생의 쿠폰이 성공적으로 사용되었습니다.");
			return "sideBar/sideBar";
		} else {
			model.addAttribute("errorMsg", "학생의 쿠폰상품 사용에 실패했습니다.");
			return "common/errorPage";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="searchStudent.cp", produces="application/json; charset=UTF-8")
	public String ajaxSearchStudentCp(String searchOption, String keyword, int cno) {
		HashMap map = new HashMap();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		map.put("cno", cno);
		
		//System.out.println(map);
		
		return new Gson().toJson(cpService.searchStudentCp(map));
	}
	
	
	
	
}
