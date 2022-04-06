package com.dao;

import java.util.List;

import com.entities.temp;

public interface tempDAO {
 
    void saveTemp(temp t);
     
    void deleteTemp();
     
    List<temp> findAllTemp();

	temp getTempByUid(int uid);
    
 
}
