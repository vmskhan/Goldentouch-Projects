package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Participation;
import com.entities.ParticipationPk;
import com.entities.Question;
import com.entities.Result;
import com.entities.User;

@Repository("ParticipationDAO")
@Transactional
public class ParticipationDAOImpl extends AbstractDAO<ParticipationPk, Participation> implements ParticipationDAO {

	@Override
	public Participation findById(ParticipationPk pk) {
		System.out.println("participation-findbyid method called");
		return getByKey(pk);
	}

	@Override
	public void saveParticipation(Participation participation) {
		System.out.println("participation-savepartiipation method called");
		persist(participation);
	}

	@Override
	public void deleteParticipation(Participation participation) {
		System.out.println("participation-deleteparticipation method called");
		delete(participation);
	}

	@Override
	public List<Participation> findAllParticipation() {
		System.out.println("participation-findallparticipation method called");
		Criteria criteria = createEntityCriteria();
        return (List<Participation>) criteria.list();
	}

	@Override
	public List<Participation> findAllParticipationByUserId(int uid) {
		System.out.println("participation-findallparticipationbyuserid method called");
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("pk.uid", uid));  
		return (List<Participation>) criteria.add(Restrictions.eq("pk.uid", uid)).list();
	}

	@Override
	public List<Participation> findAllParticipationByTestId(int tid) {
		System.out.println("participation-findallparticipationbytestid method called");
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("pk.tid", tid));  
		return (List<Participation>) criteria.add(Restrictions.eq("pk.tid", tid)).list();
	}

}
