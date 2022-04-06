package com.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ParticipationDAO;
import com.entities.Participation;
import com.entities.ParticipationPk;

@Service("participationService")
@Transactional
public class ParticipationServiceImpl implements ParticipationService {

	@Autowired
	private ParticipationDAO dao;
	
	@Override
	public Participation findById(ParticipationPk pk) {
		System.out.println("participationservice-findbyid method called");
		return dao.findById(pk);
	}

	@Override
	public void saveParticipation(Participation participation) {
		System.out.println("participationservice-saveparticipation method called");
		dao.saveParticipation(participation);
	}

	@Override
	public void updateParticipation(Participation participation) {
		System.out.println("participationservice-updateparticipation method called");
		Participation entity = dao.findById(participation.getPk());
		if(entity != null) {
			entity.setScore(participation.getScore());
			entity.setLast_attempted(participation.getLast_attempted());
			entity.setTotalQn(participation.getTotalQn());
			entity.setPaymentDone(participation.isPaymentDone());
		}
	}

	@Override
	public void deleteParticipation(Participation participation) {
		System.out.println("participationservice-deleteparticipation method called");
		dao.deleteParticipation(participation);
	}

	@Override
	public List<Participation> findAllParticipation() {
		System.out.println("participationservice-findallparticipation method called");
		return dao.findAllParticipation();
	}

	@Override
	public List<Participation> findParticipationsByUid(int uid) {
		System.out.println("participationservice-findparticipationbyuid method called");	
		return dao.findAllParticipationByUserId(uid);
	}

	@Override
	public List<Participation> findParticipationsByTid(int tid) {
		System.out.println("participationservice-findparticipationbytid method called");
		return dao.findAllParticipationByTestId(tid);
	}

}
