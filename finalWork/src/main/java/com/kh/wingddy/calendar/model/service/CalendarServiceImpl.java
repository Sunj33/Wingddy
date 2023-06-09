package com.kh.wingddy.calendar.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.wingddy.calendar.model.dao.CalendarDao;
import com.kh.wingddy.calendar.model.vo.Calendar;

@Service
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	private CalendarDao calendarDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public ArrayList<Calendar> selectScheduleList(int memberNo) {
		return calendarDao.selectScheduleList(sqlSession, memberNo);
	}

	@Override
	public ArrayList<Calendar> selectDaySchedule(HashMap map) {
		return calendarDao.selectDaySchedule(sqlSession, map);
	}

	@Override
	public int insertSchedule(Calendar c) {
		return calendarDao.insertSchedule(sqlSession, c);
	}

	@Override
	public int deleteSchedule(int scheduleNo) {
		return calendarDao.deleteSchedule(sqlSession, scheduleNo);
	}
	
	@Override
	public int updateSchedule(Calendar c) {
		return calendarDao.updateSchedule(sqlSession, c);
	}

	@Override
	public ArrayList<Calendar> selectClassScheduleList(Calendar c) {
		return calendarDao.selectClassScheduleList(sqlSession, c);
	}
	
	
	
	
	
	
}
