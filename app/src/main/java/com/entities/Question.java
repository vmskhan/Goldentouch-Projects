package com.entities;

import java.sql.Blob;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int qid;
	private String questionText;
	@Lob
	private Blob questionImage;
	private String answerText;
	@Lob
	private Blob answerImage;
	private int mark;
	private int idx;
	private String questionFormat;
	
	@ManyToOne
	@JoinColumn(name="tid")
	private Test test;
	
	@OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
	private List<Answer> options;
	
	
	public String getQuestionFormat() {
		return questionFormat;
	}
	public void setQuestionFormat(String questionFormat) {
		this.questionFormat = questionFormat;
	}
	public Test getTest() {
		return test;
	}			
	public void setTest(Test test) {
		this.test = test;
	}
	
	public List<Answer> getOptions() {
		return options;
	}
	public void setOptions(List<Answer> options) {
		this.options = options;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	
	public Blob getQuestionImage() {
	return questionImage;
}
public void setQuestionImage(Blob questionImage) {
	try {
		this.questionImage = questionImage;
	} catch(NullPointerException e)
	{
		this.questionImage=null;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	

	public String getAnswerText() {
		return answerText;
	}
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	public Blob getAnswerImage() {
		return answerImage;
	}
	public void setAnswerImage(Blob answerImage) {
		this.answerImage = answerImage;
	}

	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "Question [qtext=" + questionText + ", answer=" + answerText + ", mark=" + mark + ", idx=" + idx + "]";
	}
	public Question(String qtext, String answer, int mark) {
		super();
		this.questionText = qtext;
		this.answerText = answer;
		this.mark = mark;
	}
	public Question() {
		
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	
}

@Component
class optionConverter implements Converter<String[], List<Answer>> {

    /**
     * Override the convert method
     * @param 
     * @return
     */
    @Override
    public List<Answer> convert(String[] options) {
		List<Answer> opts=new LinkedList<Answer>();
		for(String s:options)
			{
			Answer a=new Answer();
			a.setAnswerText(s);
			opts.add(a);
			}
    	return opts;
        
    }
}
@Component
class singleOptionConverter implements Converter<String, List<Answer>> {

    /**
     * Override the convert method
     * @param 
     * @return
     */
    @Override
    public List<Answer> convert(String options) {
		List<Answer> opts=new LinkedList<Answer>();
			Answer a=new Answer();
			a.setAnswerText(options);
			opts.add(a);
    	return opts;
    }
}



