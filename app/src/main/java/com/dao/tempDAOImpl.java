package com.dao;

import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Answer;
import com.entities.Question;
import com.entities.temp;

@Repository("tempDAO")
public class tempDAOImpl extends AbstractDAO<Integer, temp> implements tempDAO{

	

	@Override
	public void saveTemp(temp t) {
		System.out.println("temp-savetemp method called");
		persist(t);
		
	}

	@Override
	public void deleteTemp() {
		System.out.println("deletetemp method called");
		Criteria criteria =  createEntityCriteria();
	       temp t=(temp)criteria.uniqueResult();
	        delete(t);
		
	}

	@Override
	public List<temp> findAllTemp() {
		System.out.println("finalltemp method called");
		Criteria criteria = createEntityCriteria();
        return (List<temp>) criteria.list();
	}

	@Override
	public temp getTempByUid(int uid) {
		Criteria criteria = createEntityCriteria();
        return (temp) criteria.add(Restrictions.eq("uid",uid)).uniqueResult();
	}


}
