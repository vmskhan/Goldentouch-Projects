package com.dao;

import java.util.List;

import com.entities.Participation;
import com.entities.ParticipationPk;

public interface ParticipationDAO {
	
	Participation findById(ParticipationPk pk);
	
	void saveParticipation(Participation participation);
	
	void deleteParticipation(Participation participation);
	
	List<Participation> findAllParticipation();
	
	List<Participation> findAllParticipationByUserId(int uid);
	List<Participation> findAllParticipationByTestId(int tid);
	
}
