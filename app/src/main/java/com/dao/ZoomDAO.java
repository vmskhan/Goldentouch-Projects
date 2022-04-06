package com.dao;

import java.util.List;

import com.entities.Answer;
import com.entities.Zoom;



public interface ZoomDAO {
	
	public Zoom findById(int optid);
	public void saveZoom(Zoom z);
	public void deleteZoomById(int optid);
	public List<Zoom> findAllZoomObj();
	//public List<Zoom> findAllZoomObjBykey(String apiKey) ;
	public Zoom findZoomBytid(int tid);
	public void deleteZoomByTid(int tid);
}
