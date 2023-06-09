package com.kh.wingddy.classroom.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.wingddy.classroom.model.service.ClassroomService;
import com.kh.wingddy.classroom.model.vo.ClassMember;
import com.kh.wingddy.classroom.model.vo.Classroom;
import com.kh.wingddy.common.template.GenerateSecret;
import com.kh.wingddy.education.model.service.EducationService;
import com.kh.wingddy.education.model.vo.EduProgress;
import com.kh.wingddy.education.model.vo.Incorrect;
import com.kh.wingddy.member.model.service.MemberServiceImpl;
import com.kh.wingddy.member.model.vo.Member;

@Controller
public class ClassroomController {
	@Autowired
	private ClassroomService classroomService;
	
	@Autowired
	private EducationService educationService;
	
	@RequestMapping("classMain.cl")
	public ModelAndView ClassMainView(ModelAndView mv, HttpSession session, int cno) {
		
		//ArrayList<ClassMember> cm = classroomService.selectPassStudent(cno);
		//System.out.println(cm);
		Member m = (Member)session.getAttribute("loginUser");
		String memberType = m.getMemberType();
		//System.out.println(memberType);
		if(memberType.equals("T")) {
			//session.setAttribute("classroom", new Classroom(cno, "임시세션", "임시세션","임시코드"));
			mv.addObject("passMember", classroomService.selectPassStudent(cno));
			mv.addObject("myCount", classroomService.selectClassRanking(cno));
			mv.setViewName("classroom/classTeacherMain");
		} else {
			HashMap<String, Object> map = new HashMap();
			map.put("classNo", cno);
			map.put("memberNo", m.getMemberNo());
			map.put("memberType", memberType);
			
			ArrayList<EduProgress> eList = educationService.selectEduList(map);
			
			mv.
			addObject("eList", eList).
			addObject("myCount", classroomService.selectClassRanking(cno)).
			setViewName("classroom/classStudentMain");
		}
		return mv;
	}

	
	@ResponseBody
	@RequestMapping("passStudent.cl")
	public String passStudent(ClassMember cm) {
		
		//System.out.println(cm);
		int result = classroomService.passStudent(cm);
		
		
		return result > 0 ? "pass" : "nope";
	}
	
	@RequestMapping("enrollClassForm.cl")
	public String enrollClassForm() {
		return "classroom/enrollClassForm";
	}
	
	@RequestMapping("enrollClass.cl")
	public ModelAndView enrollClassForm(ModelAndView mv, String enterCode, HttpSession session) {
		
		Classroom cr = classroomService.joinClass(enterCode);
		
		if(cr != null) {
			//session.setAttribute("alertMsg", "입장신청되었습니닷!");
			mv.addObject("classroom", cr);
			mv.setViewName("classroom/enrollClassForm");
		} else {
			session.setAttribute("alertMsg", "존재하지 않는 클래스입니닷!");
			mv.setViewName("classroom/enrollClassForm");
		}
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("insertPass.cl")
	public int insertPass(ClassMember cm) {
		
		return classroomService.insertPass(cm);
	}
	
	@RequestMapping("addClassForm.cl")
	public String addClassForm() {
		return "classroom/addClassForm";
	}
	
	@RequestMapping("addClass.cl")
	public ModelAndView addClass(Classroom cr, HttpSession session, ModelAndView mv) {
		
		
		if(cr.getTeacherName() != null) {
			
			GenerateSecret gs = new GenerateSecret();
			String secret = gs.generateSecret();
			
			cr.setEnterCode(secret);
		}
		// 새로고침해줘야함 바로 반영이 안댐
		if(classroomService.createClassroom(cr) > 0) {
			session.setAttribute("classList", classroomService.selectClassList((Member)session.getAttribute("loginUser")));
			mv.setViewName("redirect:/");
		} else {
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping("classManagement.cl")
	public ModelAndView studentManagement(ModelAndView mv, int cno) {
		
		// 관리페이지 내 select문에서 가져올 데이터
		//System.out.println(cno);
		mv.addObject("classProgress", classroomService.selectProgressRate(cno));
		mv.setViewName("classroom/studentManagement");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("kickoutStudent.cl")
	public int kickoutStudent(@RequestParam(value="studentNoArr[]")int[] studentNoArr, int classNo) {
		
		ArrayList<ClassMember> cmList = new ArrayList();
		for(int i = 0; i < studentNoArr.length; i++) {
			ClassMember cm = new ClassMember();     
			cm.setMemberNo(studentNoArr[i]);
			cm.setClassNo(classNo);
			
			cmList.add(cm);
		}
		
		//System.out.println(memberNoList.get(0));
		System.out.println("cmList" + cmList);
		int result = classroomService.kickoutStudent(cmList);
		//System.out.println(result);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="progressStudent.cl", produces="application/json; charset=UTF-8")
	public String progressStudent(ClassMember cm) {
		//System.out.println(cm);
		ArrayList<Incorrect> icList = classroomService.selectProgressStudent(cm);
		Incorrect incorrect = icList.get(0);
		System.out.println(incorrect);
		
		//System.out.println(icList);
		return new Gson().toJson(classroomService.selectProgressStudent(cm));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 클래스 학생관리 > 학생 뽑기 횟수 부여 - 세희
	@ResponseBody
	@RequestMapping(value="giveGachaCount", produces="html/text; charset=UTF-8")
	public String giveGachaCount(ClassMember cm) {
		
		if(classroomService.giveGachaCount(cm) > 0) {
			return "success";
		}else {
			return "fail";
		}
		
	}
	

	
}
