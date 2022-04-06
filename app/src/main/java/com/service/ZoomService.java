package com.service;

import java.util.List;

import com.entities.Zoom;



public interface ZoomService {
	void saveZoomObj(Zoom z);
	public Zoom findById(int optid) ;
	public List<Zoom> findAllZoom();
	public Zoom findZoomBytid(int tid);
	void deleteZoomByTid(int tid);
}
