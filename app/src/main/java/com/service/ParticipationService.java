package com.service;

import java.util.List;

import com.entities.Participation;
import com.entities.ParticipationPk;

public interface ParticipationService {

	Participation findById(ParticipationPk pk);
	
	void saveParticipation(Participation participation);
	
	void updateParticipation(Participation participation);
	
	void deleteParticipation(Participation participation);
	
	List<Participation> findAllParticipation();
	
	List<Participation> findParticipationsByUid(int uid);
	List<Participation> findParticipationsByTid(int tid);
}
