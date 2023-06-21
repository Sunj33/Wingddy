package com.kh.wingddy.education.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.kh.wingddy.education.model.service.EducationService;
import com.kh.wingddy.education.model.vo.Edu;
import com.kh.wingddy.education.model.vo.EduProgress;
import com.kh.wingddy.education.model.vo.Quiz;
import com.kh.wingddy.member.model.vo.Member;
import com.kh.wingddy.voca.model.service.VocaService;
import com.kh.wingddy.voca.model.vo.ClassVocaBook;
import com.kh.wingddy.voca.model.vo.Voca;

@Controller
public class EducationController {
	
	@Autowired
	private EducationService educationService;
	
	@Autowired
	private VocaService vocaService;
	
	@RequestMapping("main.edu")
	public ModelAndView eduMainPage(int cno, ModelAndView mv) {
		
		ArrayList<EduProgress> eList = educationService.selectEduList(cno);
		
		mv.
		addObject("eList", eList).
		setViewName("education/eduMain");
		return mv;
	}
	
	@RequestMapping("detail.edu")
	public ModelAndView eduDetailPage(HttpServletRequest request, ModelAndView mv, int eduNo) {
		mv.addObject("qList", educationService.selectQuizList(eduNo))
		.addObject("mList", educationService.selectCompleteStudent(eduNo))
		.setViewName("education/eduDetail");
		return mv;
	}
	
	@PostMapping("insertForm.edu")
	public ModelAndView insertEduForm(HttpSession session, ModelAndView mv, int cno) {
		
		mv.addObject("vcList", vocaService.selectVocaBookList(((Member)session.getAttribute("loginUser")).getMemberNo()))
		.setViewName("education/insertEduForm");
		return mv;
	}
	
	@PostMapping("insert.edu")
	public ModelAndView insertEdu(HttpSession session, ModelAndView mv, Edu e, String content, int cno) {
		JsonArray jArr = new JsonParser().parse(content).getAsJsonArray();
		ArrayList<Quiz> qList = new ArrayList();
		
		if(!e.getEduType().equals("W")) {
			for(int i = 0; i < jArr.size(); i++) {
				Quiz q = new Quiz();
				q.setQuizContent(jArr.get(i).getAsJsonObject().get("content").getAsString());
				q.setCorrectContent(jArr.get(i).getAsJsonObject().get("correct").getAsString());
				qList.add(q);
			}
		} else {
			int bookNo = jArr.get(0).getAsJsonObject().get("bookNo").getAsInt();
			ArrayList<Voca> vList = vocaService.selectVocaList(bookNo);
			ClassVocaBook cv = new ClassVocaBook().builder()
											      .classNo(cno)
										      	  .bookNo(bookNo)
											      .build();
			if(vocaService.checkClassBook(cv) == 0) {
				ArrayList<ClassVocaBook> cvList = new ArrayList();
				cvList.add(cv);
				vocaService.insertClassBookOne(cvList);
			}
			for(Voca v : vList) {
				Quiz q = new Quiz();
				
				String quiz;
				String correct;
				if((int)(Math.random() * 2) > 0) {
					quiz = v.getVocaEnglish();
					correct = v.getVocaKorean();
				} else {
					quiz = v.getVocaKorean();
					correct = v.getVocaEnglish();
				}
				q.setQuizContent(quiz);
				q.setCorrectContent(correct);
				qList.add(q);
			}
		}
		e.setClassNo(cno);
		
		mv.addObject("cno", cno);
		if(educationService.insertEducation(e, qList) > 0) {
			mv.setViewName("redirect:main.edu");
		} else {
			session.setAttribute("alertMsg", "과제 등록 실패!");
			mv.setViewName("redirect:insertForm.edu");
		}
		return mv;
	}
	
	@RequestMapping("deadLine.edu")
	public String updateEndTime(HttpSession session, Model m, int cno, int eduNo) {
		
		if(educationService.updateEndTime(eduNo) > 0) {
			session.setAttribute("alertMsg", "마감시간이 변경 되었습니다");
		} else {
			session.setAttribute("alertMsg", "변경에 실패하였습니다");
		}
		
		m.addAttribute("cno", cno);
		
		return "redirect:main.edu";
	}
	
	@RequestMapping("exam.edu")
	public ModelAndView eduExamPage(HttpServletRequest request, ModelAndView mv, int eduNo, String eduType, int cno) {
		
		String view = "";
		switch(eduType) {
		case "W" : view = "education/examWord"; break;
		case "S" : view = "education/examSentence"; break;
		case "O" : view = "education/examOX"; break;
		default :  view = "redirect:main.edu";
		}
		mv.addObject("cno", cno).setViewName(view);
		return mv;
	}
	
	@RequestMapping("result.edu")
	public String eduResultPage(HttpServletRequest request, int eduNo) {
		
		return "education/eduResult";
	}
}
