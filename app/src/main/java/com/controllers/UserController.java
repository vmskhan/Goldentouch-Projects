package com.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entities.Answer;
import com.entities.Participation;
import com.entities.ParticipationPk;
import com.entities.Question;
import com.entities.Submission;
import com.entities.SubmissionPk;
import com.entities.Test;
import com.entities.User;
import com.entities.Zoom;
import com.entities.temp;
import com.service.AnswerService;
import com.service.MailService;
import com.service.ParticipationService;
import com.service.QuestionService;
import com.service.SubmissionService;
import com.service.TempService;
import com.service.TestService;
import com.service.UserService;
import com.service.ZoomService;
import com.util.Mail;
import com.util.Report;
import com.util.TestPart;
import com.view.ExportPdf;
import com.zoomComponent.ZoomSignatureRetriever;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	ZoomSignatureRetriever c;
	
	@Autowired
	TempService tempService;
	
	@Autowired
	ZoomService zoomService;
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ParticipationService participationService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private SubmissionService submissionService;
	
	@Autowired
	private MailService mailService;
	
	
	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public ModelAndView processLoginPage(ModelAndView mandv,Principal principal) {
		System.out.println("processLoginPage method called");
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		User user = userService.findUserByEmailid(principal.getName());
		List<Test> tests = testService.findAllTestsByPid(user.getUid());
		List<Test> liveTest = new ArrayList<Test>();
		List<Test> endTest = new ArrayList<Test>();
		List<Participation> participations = participationService.findParticipationsByUid(user.getUid());
		List<TestPart> currentTest = new ArrayList<TestPart>();
		List<TestPart> completedTest = new ArrayList<TestPart>();
		Iterator<Test> itr = tests.iterator();
		while(itr.hasNext()) {
			Test temp = itr.next();
			if(temp.getState().equals("start")) {
				liveTest.add(temp); 
			}
		}
		int i,j;
		for(i = 0 ; i < liveTest.size() ; i++) {
			int flag = 0;
			for(j = 0 ; j < participations.size() ; j++) {
				if(liveTest.get(i).getTid() == participations.get(j).getPk().getTid()) {
					flag = 1;
					if(participations.get(j).getLast_attempted() == participations.get(j).getTotalQn()) {
						// completed
						Test t = liveTest.get(i);
						Participation p = participations.get(j);
						TestPart tp = new TestPart(t,p);
						completedTest.add(tp);
					}else {
						// not completed
						Test t = liveTest.get(i);
						Participation p = participations.get(j);
						TestPart tp = new TestPart(t,p);
						currentTest.add(tp);
					}
				}
			}
			if(flag == 0) {
				Test t = liveTest.get(i);
				Participation p = new Participation();
				TestPart tp = new TestPart(t,p);
				
				currentTest.add(tp);
			}
		}
		itr =tests.iterator();
		while(itr.hasNext()) {
			Test temp = itr.next();
			if(temp.getState().equals("end")) {
				endTest.add(temp); 
			}
		}
		for(i = 0 ; i < endTest.size() ; i++) {
			int flag = 0;
			for(j = 0 ; j < participations.size() ; j++) {
				if(endTest.get(i).getTid() == participations.get(j).getPk().getTid()) {
					flag = 1;
					if(participations.get(j).getLast_attempted() == participations.get(j).getTotalQn()) {
						Test t = endTest.get(i);
						Participation p = participations.get(j);
						TestPart tp = new TestPart(t,p);
						completedTest.add(tp);
					}
				}
			}
		}

		mandv.addObject("tests",tests);
		mandv.addObject("user",user);
		mandv.addObject("currentTest",currentTest);
		mandv.addObject("completedTest",completedTest);
		mandv.setViewName("StudentDashboard");
		return mandv;
	}
	
	
	@RequestMapping(value = "/history",method = RequestMethod.GET)
	public ModelAndView History(ModelAndView mandv,Principal principal) {
		System.out.println("history method called");
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		User user = userService.findUserByEmailid(principal.getName());
		List<Test> tests = testService.findAllTestsByPid(user.getUid());
		List<Test> liveTest = new ArrayList<Test>();
		List<Test> endTest = new ArrayList<Test>();
		List<Participation> participations = participationService.findParticipationsByUid(user.getUid());
		List<TestPart> currentTest = new ArrayList<TestPart>();
		List<TestPart> completedTest = new ArrayList<TestPart>();
		Iterator<Test> itr = tests.iterator();
		while(itr.hasNext()) {
			Test temp = itr.next();
			if(temp.getState().equals("start")) {
				liveTest.add(temp); 
			}
		}
		int i,j;
		for(i = 0 ; i < liveTest.size() ; i++) {
			int flag = 0;
			for(j = 0 ; j < participations.size() ; j++) {
				if(liveTest.get(i).getTid() == participations.get(j).getPk().getTid()) {
					flag = 1;
					if(participations.get(j).getLast_attempted() == participations.get(j).getTotalQn()) {
						// completed
						Test t = liveTest.get(i);
						Participation p = participations.get(j);
						TestPart tp = new TestPart(t,p);
						completedTest.add(tp);
					}else {
						// not completed
						Test t = liveTest.get(i);
						Participation p = participations.get(j);
						TestPart tp = new TestPart(t,p);
						currentTest.add(tp);
					}
				}
			}
			if(flag == 0) {
				Test t = liveTest.get(i);
				Participation p = new Participation();
				TestPart tp = new TestPart(t,p);
				
				currentTest.add(tp);
			}
		}
		itr =tests.iterator();
		while(itr.hasNext()) {
			Test temp = itr.next();
			if(temp.getState().equals("end")) {
				endTest.add(temp); 
			}
		}
		for(i = 0 ; i < endTest.size() ; i++) {
			int flag = 0;
			for(j = 0 ; j < participations.size() ; j++) {
				if(endTest.get(i).getTid() == participations.get(j).getPk().getTid()) {
					flag = 1;
					if(participations.get(j).getLast_attempted() == participations.get(j).getTotalQn()) {
						Test t = endTest.get(i);
						Participation p = participations.get(j);
						TestPart tp = new TestPart(t,p);
						completedTest.add(tp);
					}
				}
			}
		}
		mandv.addObject("completedTest", completedTest);
		mandv.setViewName("StudentHistory");
		return mandv;
	}
	@Transactional
	@RequestMapping(value="/test/{tid}/start",method = RequestMethod.GET)
	public String start(@PathVariable int tid, Principal principal) {
		System.out.println("start method called");
		// checking if the user id authorised
		if(principal == null) {
			return "redirect:/login";
		}
		
		String username = principal.getName();
		User user = userService.findUserByEmailid(username);
		
		
			temp t=new temp();
			t.setUid(user.getUid());
			t.setTempval(tid);
			tempService.updateTemp(t);	
		
		
		// generating primary key
		ParticipationPk pk = new ParticipationPk();
		pk.setTid(tid);
		pk.setUid(user.getUid());
		
		// getting total number of questions
		int totalQuestions = questionService.findQuestionsByTid(tid).size();

		// checking the participation exists
		Participation check = participationService.findById(pk);
		if(check != null) {
			return "redirect:/user/test/"+tid+"/qn";
		}
		
		// creating new participation
		Participation participation = new Participation();
		participation.setPk(pk);
		participation.setScore(0);
		participation.setLast_attempted(0);
		participation.setTotalQn(totalQuestions);
		participationService.saveParticipation(participation);
		
		return "redirect:/user/test/"+tid+"/qn";
	}	
	
	@Transactional
	@RequestMapping(value = "/test/{tid}/qn",method = RequestMethod.GET)
	public ModelAndView getQuestion(ModelAndView mandv,@PathVariable int tid, Principal principal) {
		// checking if the user id authorised
		
		String qformat;
		System.out.println("getQuestion method called");
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		
		String username = principal.getName();
		User user = userService.findUserByEmailid(username);

		// generating primary key
		ParticipationPk pk = new ParticipationPk();
		pk.setTid(tid);
		pk.setUid(user.getUid());

		// checking the participation exists
		Participation participation = participationService.findById(pk);
		if(participation == null || participation.getLast_attempted() == participation.getTotalQn()) {
			return new ModelAndView("redirect:/user/dashboard");
		}
		
		Question question = questionService.findQuestionByTidAndIdx(tid, participation.getLast_attempted()+1);
		List<Answer> options = answerService.findAllAnswerByQid(question.getQid());
		
		
			if(question.getQuestionImage()!=null)
			{
				System.out.println("yes question image is not null");
			
			try {
				ByteArrayInputStream bis = new ByteArrayInputStream(question.getQuestionImage().getBytes(1, (int) question.getQuestionImage().length()));
				BufferedImage bm = ImageIO.read(bis);
				String fileName="qs"+String.valueOf(question.getQid())+".jpg";
				String fileLocation = new File("src\\main\\resources\\static\\img").getAbsolutePath() + "\\" + fileName;
				FileOutputStream fout=new FileOutputStream(fileLocation);
				ImageIO.write(bm,"jpg",fout);
				fout.close();
				//mandv.addObject("qimg-"+q.getQid(),"/qs"+question.getQid()+".jpg");
				//System.out.println("---------------question-id="+question.getQid());
			}
			catch(Exception e) {;}
			}
			else
				System.out.println("NO question image is null"+ question.getQid());
		
	
			for(Answer opt: ListUtils.emptyIfNull(options))
			{
				if(opt.getAnswerImage()!=null)
				{
				ByteArrayInputStream bis;
				try {
					
					bis = new ByteArrayInputStream(opt.getAnswerImage().getBytes(1, (int) opt.getAnswerImage().length()));
					BufferedImage bm = ImageIO.read(bis);
					String fileName="os"+String.valueOf(opt.getOid())+".jpg";
					String fileLocation = new File("src\\main\\resources\\static\\img").getAbsolutePath() + "\\" + fileName;
					FileOutputStream fout=new FileOutputStream(fileLocation);
					ImageIO.write(bm,"jpg",fout);
					fout.close();
					//System.out.println("---------------question-id="+opt.getOid());
				}
				catch(Exception e) {;}
				}
			}
		
		System.out.println("------------------------------\n\nqformat:"+question.getQuestionFormat()+" for qid"+question.getQuestionText()+"\n\n--------------------------------------");
		mandv.addObject("question",question);
		mandv.addObject("participation",participation);
		mandv.addObject("options",options);
		mandv.addObject("tid",tid);
		mandv.addObject("opt",new Answer());
		//mandv.addObject("qformat", question.get);
		mandv.setViewName("TestPage");
		return mandv;
	}
	@Transactional
	@RequestMapping(value = "/test/{tid}/qn",method = RequestMethod.POST)
	public String submitQuestion(Answer answer,ModelAndView mandv,@PathVariable int tid, Principal principal) {
		// checking if the user id authorised
		System.out.println("submitquestion method called");
		if(principal == null) {
			return "redirect:/login";
		}
		
		String username = principal.getName();
		User user = userService.findUserByEmailid(username);

		// generating primary key
		ParticipationPk pk = new ParticipationPk();
		pk.setTid(tid);
		pk.setUid(user.getUid());

		// checking the participation exists
		Participation participation = participationService.findById(pk);
		if(participation == null) {
			return "redirect:/user/dashboard";
		}
		System.out.println(participation);
		Question question = questionService.findQuestionByTidAndIdx(tid, participation.getLast_attempted()+1);

		SubmissionPk subPk = new SubmissionPk();
		subPk.setQid(question.getQid());
		subPk.setTid(tid);
		subPk.setUid(user.getUid());
		
		Submission sub = new Submission();
		sub.setCompkey(subPk);
		// check if the answer is right
		sub.setChoice(answer.getAnswerText());
		sub.setRightanswer(question.getAnswerText());
		if(question.getQuestionFormat().equals("obj-b"))
		{
			String ans="";
			StringTokenizer st=new StringTokenizer(question.getAnswerText(),",");
			ans+=question.getOptions().get(Integer.parseInt(st.nextToken())).getAnswerText();
			while(st.hasMoreElements())
			{
				ans+=","+question.getOptions().get(Integer.parseInt(st.nextToken())).getAnswerText();
			}
			sub.setRightanswer(ans);
		}
		if(question.getQuestionFormat().equals("sub-a"))
		{
			sub.setState("pending");
			sub.setMark(0);
		}
		else {
			if(sub.getRightanswer().equals(sub.getChoice())) {
				// right answer
				participation.setScore(participation.getScore()+question.getMark());
				sub.setState("correct");
				sub.setMark(question.getMark());
			}else {
				// wrong answer
				sub.setState("wrong");
				sub.setMark(0);
			}
		}
		System.out.println(sub);
		submissionService.saveSubmission(sub);
		participation.setLast_attempted(participation.getLast_attempted()+1);
		System.out.println(participation);
		participationService.updateParticipation(participation);
		if(participation.getLast_attempted() == participation.getTotalQn()) {
			return "redirect:/user/dashboard";
		}
		return "redirect:/user/test/"+tid+"/qn";
	}
	
	@RequestMapping(value = "/test/{tid}/payment",method = RequestMethod.GET)
	public ModelAndView payment(ModelAndView mandv,@PathVariable int tid,Principal principal) {
		System.out.println("payment method called");
		if(principal == null) {
			return new ModelAndView("redirect:/login");
		}
		Test test = testService.findById(tid);
		if(!test.isNeedPayment()) {
			return new ModelAndView("redirect:/user/dashboard");
		}
		mandv.addObject("test",test);
		mandv.setViewName("Payment");
		return mandv;
	}
	

	@RequestMapping(value = "/exportpdf/{tid}/{uid}",produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> employeeReports(HttpServletResponse response,@PathVariable int tid,@PathVariable int uid) throws IOException {
		System.out.println("employeereports method called");
		SubmissionPk sk=new SubmissionPk();
		List<Report> reports=new ArrayList<Report>();
		List<Question> questions=questionService.findQuestionsByTid(tid);
		Iterator<Question> itr=questions.iterator();
		while(itr.hasNext()) {
			Question temp=itr.next();
			sk.setTid(tid);
			sk.setUid(uid);
			sk.setQid(temp.getQid());
			Submission sub=submissionService.findById(sk);
			Report report=new Report(sub,temp);
			reports.add(report);
		}

		ByteArrayInputStream bis = ExportPdf.reportsReport(reports);

		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Disposition", "attachment;filename=testreport.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
	
	@RequestMapping(value = "/test/{tid}/s",method = RequestMethod.GET)
	public ModelAndView startTestUi(ModelAndView mandv,@PathVariable int tid) {
		Test test = testService.findById(tid);
		System.out.println("starttestui method called");
		mandv.addObject("test",test);
		mandv.setViewName("StartTest");
		return mandv;
	}
	@Transactional
	@RequestMapping(value = "/studentresult/{tid}/{uid}")
	public ModelAndView StudentResultPage(ModelAndView mandv,@PathVariable int tid,@PathVariable int uid)
	{
		System.out.println("studentresultpage method called");
		Test test=testService.findById(tid);
		String tname=test.getTname();
		SubmissionPk sk=new SubmissionPk();
		List<Report> reports=new ArrayList<Report>();
		List<Question> questions=questionService.findQuestionsByTid(tid);
		Iterator<Question> itr=questions.iterator();
		while(itr.hasNext()) {
			
			Question temp=itr.next();
			String tempstr=temp.getAnswerText();
			sk.setTid(tid);
			sk.setUid(uid);
			sk.setQid(temp.getQid());

			Submission sub=submissionService.findById(sk);
			Report report=new Report(sub,temp);
			reports.add(report);
			temp.setAnswerText(tempstr);
		}
		mandv.addObject("reports",reports);
		mandv.addObject("tid",tid);
		mandv.addObject("uid",uid);
		mandv.addObject("tname",tname);
		mandv.setViewName("StudentResult");
		return mandv;
		
	}
	
	@RequestMapping(value = "/sendMail/{tid}/{uid}")
	public String submissionEmail(HttpServletResponse response,@PathVariable int tid,@PathVariable int uid, Principal principal) throws IOException {
		System.out.println("submissionemail method called");
		if(principal == null) {
			return "redirect:/login";
		}
		SubmissionPk sk=new SubmissionPk();
		List<Report> reports=new ArrayList<Report>();
		List<Question> questions=questionService.findQuestionsByTid(tid);
		Iterator<Question> itr=questions.iterator();
		while(itr.hasNext()) {
			Question temp=itr.next();
			sk.setTid(tid);
			sk.setUid(uid);
			sk.setQid(temp.getQid());
			Submission sub=submissionService.findById(sk);
			Report report=new Report(sub,temp);
			reports.add(report);
		}

		ByteArrayInputStream bis = ExportPdf.reportsReport(reports);
		Mail mail = new Mail();
        mail.setMailFrom("khanvms9@gmail.com");
        mail.setMailTo(principal.getName());
        mail.setMailSubject("Onview - Your Submission");
        mail.setMailContent("Attached the PDF wherein you can find your submissions");
        mailService.sendEmail(mail,reports);
		return "redirect:/user/history";
		
		
	}
	//zoom meetings
	 
	 @RequestMapping(path="/getMeetingDetails", produces="application/json", method=RequestMethod.POST)
	 @ResponseBody
	 public Map<String,String> meetDetails(Principal principal){ //@PathVariable(name="apikey")String apikey) {
		 System.out.println("getMeetingDetails ADmin- method called");
		 User user=userService.findUserByEmailid(principal.getName());
	 	Zoom z=zoomService.findZoomBytid(tempService.getTempByUid(user.getUid()).getTempval());
	 	String sig=c.sendSignature(0,Long.parseLong(z.getMeetingNumber()));
	 	//Zoom z=ls.get(ls.size()-1);
	 	Map<String,String> m=new LinkedHashMap<String,String>();
	 	m.put("sdkKey", z.getSdkKey());
	 	m.put("meetingNumber", z.getMeetingNumber());
	 	m.put("password", z.getPassword());
	 	m.put("signature", sig);
	 	m.put("username", user.getName());
	 	
	 	 return m;
	 }
}
