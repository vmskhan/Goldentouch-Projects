package com.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.entities.Zoom;





@Repository("ZoomDAO")
public class ZoomDAOImpl extends AbstractDAO<Integer, Zoom> implements ZoomDAO {

	@Override
	public Zoom findById(int optid) {
		System.out.println("zoom-findbyid method called");
		return getByKey(optid);
	}

	@Override
	public void saveZoom(Zoom z) {
		System.out.println("zoom-savezoom method called");
		persist(z);
		
	}

	@Override
	public void deleteZoomById(int optid) {
		System.out.println("deletezoombyid method called");
		Criteria criteria =  createEntityCriteria();
	       Zoom z=(Zoom)criteria.add(Restrictions.eq("optid", optid)).uniqueResult();
	        delete(z);
		
	}

	@Override
	public List<Zoom> findAllZoomObj() {
		System.out.println("finallzoom method called");
		Criteria criteria = createEntityCriteria();
        return (List<Zoom>) criteria.list();
	}

	@Override
	public Zoom findZoomBytid(int tid) {
		// TODO Auto-generated method stub
		Criteria criteria=createEntityCriteria();
		return (Zoom) criteria.add(Restrictions.eq("testId",tid)).uniqueResult();
		
	}

	@Override
	public void deleteZoomByTid(int tid) {
		// TODO Auto-generated method stub
		Criteria criteria=createEntityCriteria();
		Zoom z= (Zoom) criteria.add(Restrictions.eq("testId",tid)).uniqueResult();
		if(z!=null)
			delete(z);
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Zoom> findAllZoomObjBykey(String apiKey) {
//		System.out.println("answer-findallanswersbyqid method called");
//		Criteria criteria = createEntityCriteria();
//		return (List<Zoom>) criteria.add(Restrictions.eq("zoom.apiKey", apiKey)).list();
//	}

	
}
