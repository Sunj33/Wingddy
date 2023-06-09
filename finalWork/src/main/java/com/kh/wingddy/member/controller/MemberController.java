package com.kh.wingddy.member.controller;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.wingddy.classroom.model.service.ClassroomService;
import com.kh.wingddy.common.model.vo.Attachment;
import com.kh.wingddy.common.template.GenerateSecret;
import com.kh.wingddy.common.template.RenameFile;
import com.kh.wingddy.member.model.service.MemberService;
import com.kh.wingddy.member.model.vo.Cert;
import com.kh.wingddy.member.model.vo.Member;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ClassroomService classroomService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
	private JavaMailSenderImpl sender;
	
	
	private RenameFile renameFile = new RenameFile();
	
	@RequestMapping("login.me")
	public ModelAndView loginMember(Member m
									,ModelAndView mv
									,HttpSession session
									,HttpServletResponse response
									,String[] remember) {
		
		Member loginUser = memberService.loginMember(m);
		int month = (60 * 60 * 24 * 28);
		if(loginUser != null && bcryptPasswordEncoder.matches(m.getMemberPwd(), loginUser.getMemberPwd())) {
			//System.out.println(bcryptPasswordEncoder.matches(m.getMemberPwd(), loginUser.getMemberPwd()));
			//System.out.println(memberService.selectProfile(loginUser.getMemberNo()));
			//System.out.println(remember[0]);
			if(remember != null) {
				Cookie saveId = new Cookie("saveId", m.getMemberId());
				saveId.setMaxAge(month);
				response.addCookie(saveId);
			}
			session.setAttribute("loginUser", loginUser);
			session.setAttribute("classList", classroomService.selectClassList(loginUser));
			session.setAttribute("profile", memberService.selectProfile(loginUser.getMemberNo()));
			mv.setViewName("redirect:/");
		} else {
			session.setAttribute("alertMsg", "로그인실패");
			mv.setViewName("common/loginForm");
		}
		return mv;
		
	}
	  
	@RequestMapping("loginForm.me")
	public String loginForm() {
		
		
		return "common/loginForm";
	}
	
	@RequestMapping("logout.me")
	public String logout(HttpSession session) {
		session.invalidate();
		return "sideBar/sideBar";
	}
	
	@RequestMapping("enrollForm.me")
	public String enrollForm() {
		return "common/enrollForm";
	}
	
	@PostMapping("enrollMember.me")
	public String insertMember(Member m,
							   MultipartFile upfile,
							   HttpSession session,
							   Model model) {
		
		//System.out.println("평문 : " + m.getMemberPwd());
		
		if(!upfile.getOriginalFilename().equals("")) {
			
			/*
			 * memberType = S, T
			 * t의 경우 multipart 처리하여 insert되게끔 받아야함
			 * 관리자의 승인이 필요하고 기본적으론 s타입으로 insert되야함
			 * s의 경우 member객체만 받아서 회원가입처리
			 */
			String encPwd = bcryptPasswordEncoder.encode(m.getMemberPwd());
			m.setMemberPwd(encPwd);
			m.setMemberType("T");
			
			//System.out.println(encPwd);
			String changeName = renameFile.fileName(upfile, session);
			
			Attachment at = new Attachment();
			at.setOriginName(upfile.getOriginalFilename());
			at.setChangeName(changeName);
			at.setFileLevel(1);
			at.setFilePath("resources/uploadFiles/" + changeName);
			
			//System.out.println("나는 선생" + m);
			//System.out.println("나는 첨부" + at);
			return memberService.insertTeacher(m, at) > 0 ? "sideBar/sideBar":"common/errorPage";
		} else {
			
			System.out.println(m);
			String encPwd = bcryptPasswordEncoder.encode(m.getMemberPwd());
			m.setMemberPwd(encPwd);
			m.setMemberType("S");
			
			//System.out.println("나는 학생" + m);
			return memberService.insertMember(m) > 0 ? "sideBar/sideBar":"common/errorPage";
		}
	}
	
	@RequestMapping("errorPage.me")
	public String error() {
		return "common/errorPage";
	}
	
	@RequestMapping("profile.me")
	public String profile() {
		return "member/profile";
	}
	
	@ResponseBody
	@PostMapping("confirmPass.me")
	public String confirmPass(String memberPwd, HttpSession session) {
	
		//System.out.println(memberPwd);
		String userId = ((Member)session.getAttribute("loginUser")).getMemberId();
		String loginPwd = ((Member)session.getAttribute("loginUser")).getMemberPwd();
		Member m = new Member();
		m.setMemberId(userId);
		m.setMemberPwd(memberPwd);
		String result = null;
		if(m != null && bcryptPasswordEncoder.matches(m.getMemberPwd(), loginPwd)) {
			
			 Member correct = memberService.loginMember(m);
			 if(correct != null) {
				 result = "1";
			 }
		}
		return result;
	}
	
	@RequestMapping("updateForm.me")
	public String updateFormMember() {
		
		return "member/updateForm";
	}
	
	
	@RequestMapping("updateMember.me")
	public String updateMember(Member m, Attachment at, MultipartFile reUpfile, HttpSession session, String update) {
		
		//System.out.println(update);
		int result = 0;
		//System.out.println("기존에 존재하는 프로필 : " + at);
		if(!reUpfile.getOriginalFilename().equals("")) {
			if(at.getOriginName() != null) {
				new File(session.getServletContext().getRealPath(at.getFilePath())).delete();
			} 
			
			String changeName = renameFile.fileName(reUpfile, session);
			
			at.setMemberNo(m.getMemberNo());
			at.setOriginName(reUpfile.getOriginalFilename());
			at.setChangeName(changeName);
			at.setFilePath("resources/uploadFiles/" + changeName);
			at.setFileLevel(0);
			
			//System.out.println("가공된 at객체 : " + at);
			
			if(update.equals("firstUpdate")) {
				result = memberService.insertProfile(at);
			}
			//System.out.println("프로필 insert : " + result);
			//System.out.println(at);
			if(update.equals("update")) {
				result = memberService.updateMember(m, at);
			}
		}
		//System.out.println("프로필 update : " + result);
		if(result > 0) {
			//System.out.println("update성공한 at객체 : " + at);
			session.setAttribute("alertMsg", "수정완료");
			session.removeAttribute("profile");
			session.removeAttribute("loginUser");
			session.setAttribute("loginUser", memberService.loginMember(m));
			session.setAttribute("profile", memberService.selectProfile(m.getMemberNo()));
			return "member/profile";
		} else {
			session.setAttribute("alertMsg", "수정실패");
			return "member/updateForm";
		}
		
	}
	
	@RequestMapping("forgetId.me")
	public String forgetId() {
		return "member/forgetId";
	}
	
	@ResponseBody
	@RequestMapping(value="searchId.me", produces="application/json; charset=UTF-8")
	public String searchId(String email) {
		
		return new Gson().toJson(memberService.searchId(email));
	}
	
	@RequestMapping("updateEmploy.me")
	public ModelAndView employForm(ModelAndView mv, HttpSession session) {
		
		int memberNo = ((Member)session.getAttribute("loginUser")).getMemberNo();
		mv.addObject("employ", memberService.selectEmploy(memberNo));
		mv.setViewName("member/");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("deleteCookie.me")
	public String deleteCookie(String cookie, HttpServletResponse response) {
		
		Cookie ck = new Cookie("saveId", "nope");
		ck.setMaxAge(0);
		response.addCookie(ck);
		
		return cookie != null ? "success" : "fail"; 
	}
	
	@ResponseBody
	@RequestMapping("idCheck.me")
	public int idCheck(String memberId) {
		
		return memberService.idCheck(memberId);
	}
	
	@ResponseBody
	@RequestMapping("forgetPwd.me")
	public String forgetPwdForm(String email, HttpServletRequest request, Model model) throws MessagingException {
		
		Member forgetUser = memberService.searchId(email);
		if(forgetUser.getMemberId() != null) {
			
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			
			GenerateSecret gs = new GenerateSecret();
			String secret = gs.generateSecret();
			String ip = request.getRemoteAddr();
			
			Cert cert = new Cert();
			cert.setMemberWho(ip);
			cert.setSecret(secret);
			
			memberService.insertCert(cert);
			
			helper.setTo(email);
			helper.setSubject("WINGDDY 이메일 인증 번호 보내드립니다!");
			helper.setText("<div>"
						 + "<img src=\"https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Ft0B9e%2Fbtsh9BI8zya%2FUifPbFfhfWwk7NzrRCW6J0%2Fimg.png\" alt=\"메인페이지로고\" width=\"250px\" height=\"50px\"/>"
						 + "<br/><br/>"
						 + "인증번호 : " + secret
						 + "<br/><br/>"
						 + "<form action='http://localhost:8007/wingddy/checkCert.me' method='post'>"
						 + "<input type='hidden' name='email' value='" + email + "'/>"
						 + "<button type='submit'>인증코드 입력하러가기 </button>"
						 + "</form>"
						 + "</div>"
						 ,true);
			
			sender.send(message);
			return email;
			
		}
		return "notExist";
	}
	
	@RequestMapping("checkCert.me")
	public ModelAndView certEmail(String email, ModelAndView mv) {
		
		mv.addObject("email", email);
		mv.setViewName("member/certEmail");
		
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("checkCode.me")
	public String checkCode(String certCode, HttpServletRequest request) {
		
		Cert cert = new Cert();
		cert.setMemberWho(request.getRemoteAddr());
		cert.setSecret(certCode);
		
		Cert result = memberService.checkCode(cert);
		if(result.getMemberWho() != null) {
			memberService.certifyCode(cert);
		}
		return result.getMemberWho() != null ? "success" : "fail";
	}
	
	@ResponseBody
	@RequestMapping("updatePwd.me")
	public int updatePwd(String memberPwd, String email) {
		
		Member m = new Member();
		m.setEmail(email);
		String encPwd = bcryptPasswordEncoder.encode(memberPwd);
		m.setMemberPwd(encPwd);
		
		return memberService.updatePwd(m);
	}
	
	
}
