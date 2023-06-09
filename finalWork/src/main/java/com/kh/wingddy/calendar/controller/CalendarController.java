package com.kh.wingddy.calendar.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.wingddy.calendar.model.service.CalendarServiceImpl;
import com.kh.wingddy.calendar.model.vo.Calendar;

@Controller
public class CalendarController {
	
	@Autowired
	private CalendarServiceImpl calendarService;
	
	@RequestMapping("calendar")
	public String selectCalendar() {
		return "calendar/calendar";
	}
	
	@ResponseBody
	@RequestMapping(value="selectScheduleList", produces="application/json; charset=UTF-8")
	public String ajaxScheduleList(int memberNo) {
		return new Gson().toJson(calendarService.selectScheduleList(memberNo));
	}
	
	@RequestMapping("insertSchedule")
	public String insertSchedule(Calendar c, Model model) {
		
		if(calendarService.insertSchedule(c) > 0) {
			return "redirect:/calendar"; 
		} else {
			model.addAttribute("errorMsg", "일정 추가 실패.");
			return "common/errorPage";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="daySchedule", produces="application/json; charset=UTF-8")
	public String selectDaySchedule(Calendar c, String date) {
		HashMap map = new HashMap();
		map.put("c", c);
		map.put("date", date);
		
		return new Gson().toJson(calendarService.selectDaySchedule(map));
	}
	
	@RequestMapping("deleteSchedule")
	public String deleteSchedule(int scheduleNo, Model model) {
		if(calendarService.deleteSchedule(scheduleNo) > 0) {
			return "redirect:/calendar";
		} else {
			model.addAttribute("errorMsg", "일정 삭제 실패. 다시 시도해주세요.");
			return "common/errorPage";
		}
	}
	
	@RequestMapping("updateSchedule")
	public String updateSchedule(Calendar c, Model model) {
		//System.out.println(c);
		if(calendarService.updateSchedule(c) > 0) {
			return "redirect:/calendar";
		} else {
			model.addAttribute("errorMsg", "일정 수정 실패");
			return "common/errorPage";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="selectClassScheduleList", produces="application/json; charset=UTF-8")
	public String selectClassScheduleList(Calendar c) {
		return new Gson().toJson(calendarService.selectClassScheduleList(c));
	}
	
	
}
