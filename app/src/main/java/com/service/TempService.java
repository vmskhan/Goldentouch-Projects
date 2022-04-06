package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entities.temp;

public interface TempService {
	temp getTemp();
	List<temp> findAllTemp();
	void deleteTemp();
	void saveTemp(temp t);
	temp getTempByUid(int uid);
	void updateTemp(temp t);
}
