package com.entities;

import java.io.IOException;
import java.sql.Blob;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.Hibernate;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
@Transactional
@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int oid;
	private String answerText;
	@Lob
	private Blob answerImage;
	@ManyToOne()
	@JoinColumn(name="qid")
	private Question question;
	
	public Blob getAnswerImage() {
		return answerImage;
	}
	public void setAnswerImage(Blob answerImage)
	{
		this.answerImage=answerImage;
	}
	
	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}


	
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Answer()
	{
		
	}

	public String getAnswerText() {
		return answerText;
	}

	@Override
	public String toString() {
		return "Answer [oid=" + oid + ", answer=" + answerText + ", question=" + question + "]";
	}

	public void setAnswerText(String answer) {
		this.answerText = answer;
	}
}

@Component
class MPFileToBlobConverter implements Converter<MultipartFile, Blob> {

    /**
     * Override the convert method
     * @param 
     * @return
     */
    @Override
    public Blob convert(MultipartFile file) {
		try {
			return BlobProxy.generateProxy(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
}
