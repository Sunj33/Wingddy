package com.kh.wingddy.calendar.model.service;

import java.sql.Date;
import java.util.ArrayList;

import com.kh.wingddy.calendar.model.vo.Calendar;
import com.kh.wingddy.member.model.vo.Member;

public interface CalendarService {
	public ArrayList<Calendar> selectSchedule(Member m);
	
	public Calendar selectDaySchedule(Member m, Date day);
	
	public int insertSchedule(Calendar c);
	
	public int updateSchedule(int scheduleNo);
	
	public int deleteSchedule(int scheduleNo);
}
