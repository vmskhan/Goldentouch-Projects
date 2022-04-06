package com.service;

import java.util.List;

import org.apache.bcel.generic.ReturnaddressType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ZoomDAO;
import com.entities.Zoom;



@Service("zoomService")
@Transactional
public class ZoomServiceImpl implements ZoomService {

		@Autowired
		private ZoomDAO dao;
		
		@Override
		public Zoom findById(int optid) {
			System.out.println("answerservice-findbyid method called");
			return dao.findById(optid);
		}

		@Override
		public List<Zoom> findAllZoom() {
			System.out.println("answerservice-findallanswers method called");
			return dao.findAllZoomObj();
		}

//		@Override
//		public List<Zoom> findAllZoomBykey(String apikey) {
//			System.out.println("answerservice-findallanswersbyqid method called");
//			return dao.findAllZoomObjBykey(apikey);
//		}

		@Override
		public void saveZoomObj(Zoom z) {
			// TODO Auto-generated method stub
			dao.saveZoom(z);
		}

		@Override
		public Zoom findZoomBytid(int tid) {
			// TODO Auto-generated method stub
			return dao.findZoomBytid(tid);
		}

		@Override
		public void deleteZoomByTid(int tid) {
			// TODO Auto-generated method stub
			dao.deleteZoomByTid(tid);
		}

	}


