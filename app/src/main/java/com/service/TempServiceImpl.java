package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.AnswerDAO;
import com.dao.tempDAO;
import com.entities.Answer;
import com.entities.Participation;
import com.entities.temp;

@Service("TempService")
@Transactional
public class TempServiceImpl implements TempService {

	@Autowired
	private tempDAO dao;
	
	@Override
	public temp getTemp() {
		System.out.println("tempservice-getTemp method called");
		return dao.findAllTemp().get(0);
	}

	@Override
	public List<temp> findAllTemp() {
		System.out.println("tempservice-findalltemp method called");
		return dao.findAllTemp();
	}

	@Override
	public void deleteTemp() {
		// TODO Auto-generated method stub
		System.out.println("tempservice-deletetemp method called");
		dao.deleteTemp();
	}

	@Override
	public void saveTemp(temp t) {
		// TODO Auto-generated method stub
		System.out.println("tempservice-savetemp method called");
		dao.saveTemp(t);
		
	}

	@Override
	public temp getTempByUid(int uid) {
		
		return dao.getTempByUid(uid);
	}

	@Override
	public void updateTemp(temp t) {
		//System.out.println("participationservice-updateparticipation method called");
		temp entity = dao.getTempByUid(t.getUid());
		if(entity != null) {
			entity.setTempval(t.getTempval());
		}
		else
			dao.saveTemp(t);
	}
	

}
