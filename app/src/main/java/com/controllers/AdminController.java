package com.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
import com.service.ParticipationService;
import com.service.QuestionService;
import com.service.SubmissionService;
import com.service.TempService;
import com.service.TestService;
import com.service.UserService;
import com.service.ZoomService;
import com.util.UserPart;
import com.view.ExcelExport;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired 
	TempService tempService;
	
	@Autowired
	ZoomService zoomService;
	
	@Autowired
	private TestService testService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private SubmissionService submissionService;
	
	@Autowired
	private ParticipationService participationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AnswerService answerService;
	
	@RequestMapping(value="/dashboard",method = RequestMethod.GET)
	public ModelAndView createTest(ModelAndView mandv,@RequestParam(required = false) String error,Principal principal) {
		System.out.println("createtest method called");
		mandv.addObject("msg","");
		if(error != null) {
			mandv.addObject("msg","Test with the name already exists");
		}
		
		List<Test> tests = testService.findAllTestsByUid(userService.findUserByEmailid(principal.getName()).getUid());
		List<User> participants=userService.findAllParticiapnts("user");
		List<Test> currentTest=new ArrayList<Test>();
		Iterator<Test> itr = tests.iterator();
		while(itr.hasNext()) {
			Test temp = itr.next();
			if(temp.getState().equals("start") || temp.getState().equals("edit") ) {
				currentTest.add(temp); 
			}
		}
		mandv.addObject("tests", currentTest);
		mandv.addObject("participants",participants);
		mandv.addObject("test",new Test());
		mandv.addObject("user", new User());
		mandv.setViewName("AdminDashboard");
		return mandv;
	
	}
	
	
	@RequestMapping(value="/dashboard",method=RequestMethod.POST)
	public ModelAndView processTest(Test test,ModelAndView mandv,Principal principal) {
		System.out.println("processtest method called");
		Test temp = testService.findTestByTestName(test.getTname());
		if(temp == null) {
			if(test.getAmount() == 0) {
				test.setNeedPayment(false);
			}else {
				test.setNeedPayment(true);
			}
			test.setUser(userService.findUserByEmailid(principal.getName()));
			testService.saveTest(test);
			return new ModelAndView("redirect:/admin/dashboard");
		}else {
			return new ModelAndView("redirect:/admin/dashboard?error");
		}
	
	}
	
	@RequestMapping(value = "/history",method = RequestMethod.GET)
	public ModelAndView History(ModelAndView mandv,Principal principal) {
		System.out.println("history method called");
		List<Test> tests = testService.findAllTestsByUid(userService.findUserByEmailid(principal.getName()).getUid());
		List<Test> completedTest=new ArrayList<Test>();
		Iterator<Test> itr = tests.iterator();
		while(itr.hasNext()) {
			Test temp = itr.next();
			if(temp.getState().equals("end")) {
				completedTest.add(temp); 
			}
		}
		mandv.addObject("completedtests",completedTest);
		mandv.setViewName("AdminHistory");
		return mandv;
	}
	
	@RequestMapping(value = "/evaluation",method = RequestMethod.GET)
	public ModelAndView PendingEvaluaion(ModelAndView mandv,Principal principal) {
		System.out.println("PendingEvaluation method called");
		List<Test> tests = testService.findAllTestsByUid(userService.findUserByEmailid(principal.getName()).getUid());
		List<Test> pendingTests=new ArrayList<Test>();
		Iterator<Test> itr = tests.iterator();
		while(itr.hasNext()) {
			Test temp = itr.next();
			if(temp.getState().equals("pending")) {
				pendingTests.add(temp); 
			}
		}
		mandv.addObject("pendingtests",pendingTests);
		mandv.setViewName("AdminEvaluation");
		return mandv;
	}
	
	@RequestMapping(value="/deleteTestFromHistory/{tid}",method=RequestMethod.GET)
	public String deleteTest(ModelAndView mandv,@PathVariable int tid)
	{
		System.out.println("deletetest-method called");
		File fileToDelete;
		List<Question> qs=questionService.findQuestionsByTid(tid);
		
			for(Question q:qs)
			{
			List<Answer> as=answerService.findAllAnswerByQid(q.getQid());
			try {
		    
			    for(Answer a:as)
			    {
			    	try {
			    	fileToDelete =	new File("src/main/resources/static/img/os"+a.getOid()+".jpg"); 
				    FileUtils.forceDelete(fileToDelete);
			    	}
			    	catch(FileNotFoundException e)
					{
						;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			    fileToDelete =	new File("src/main/resources/static/img/qs"+q.getQid()+".jpg"); 
			    FileUtils.forceDelete(fileToDelete);
			}
			catch(FileNotFoundException e)
			{
				;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		zoomService.deleteZoomByTid(tid);
		testService.deleteTestById(tid);
		return "redirect:/admin/history";
	}
	
	@RequestMapping(value="/test/{id}",method = RequestMethod.GET)
	public ModelAndView test(ModelAndView mandv, @PathVariable int id) {
		System.out.println("test method called");
		Test test = testService.findById(id);
		
			temp t=new temp();
			t.setUid(test.getUser().getUid());
			t.setTempval(id);
			tempService.updateTemp(t);
		
		Question question = new Question();
		List<Question> questions = questionService.findQuestionsByTid(id);
		List<Answer> options = new ArrayList<>();
		for(int i = 0 ; i < 4 ; i++) {
			options.add(new Answer());
		}
		question.setOptions(options);
		for(Question q:questions)
		{
			if(q.getQuestionImage()!=null)
			{
			ByteArrayInputStream bis;
			try {
				bis = new ByteArrayInputStream(q.getQuestionImage().getBytes(1, (int) q.getQuestionImage().length()));
				BufferedImage bm = ImageIO.read(bis);
				String fileName="qs"+String.valueOf(q.getQid())+".jpg";
				String fileLocation = new File("src\\main\\resources\\static\\img").getAbsolutePath() + "\\" + fileName;
				FileOutputStream fout=new FileOutputStream(fileLocation);
				ImageIO.write(bm,"jpg",fout);
				fout.close();
				mandv.addObject("qimg-"+q.getQid(),"/qs"+q.getQid()+".jpg");
				System.out.println("---------------question-id="+q.getQid());
			} catch(Exception e) {;}
			}
			else
				System.out.println("---------------------------------------qqqq "+(q.getQuestionImage()!=null)+" "+q.getQid());
//			if(q.getQuestionFormat().equals("obj-b"))
//			{
//				
//				q.setQuestionText(q.getQuestionText().substring(q.getQuestionText().indexOf("$ ")+2));
//				//System.out.println("--------------------------------\n\nType-b-question is present: "+q.getQuestionText()+"\n\n-------------------------------------");
//			}
		}
		
		mandv.addObject("question",question);
		mandv.addObject("test",test);
		mandv.addObject("questions",questions);
		mandv.setViewName("AdminQuestion");
		return mandv;
	
	}
	
	
	@RequestMapping(value="/test/{tid}/qn",method = RequestMethod.POST)
	public String postQuestion(Question question,@PathVariable int tid,@RequestParam(name="images")List<MultipartFile> imgs) throws Exception {		//,BindingResult errors) {
		System.out.println("postquestion method called");
		Test test = testService.findById(tid);
		System.out.println(question.toString()+question.getOptions());
		question.setTest(test);
		if(!imgs.get(0).isEmpty())
			try {
				question.setQuestionImage(BlobProxy.generateProxy(imgs.get(0).getBytes()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			question.setQuestionImage(null);
//		-------------------------------------------------------------------------------
		if(question.getQuestionFormat().equals("obj-a"))
		{
			question.setAnswerImage(question.getOptions().get(Integer.parseInt(question.getAnswerText())).getAnswerImage());
			question.setAnswerText(question.getOptions().get(Integer.parseInt(question.getAnswerText())).getAnswerText());
			
			
			Iterator<Answer> options = question.getOptions().iterator();
			Answer opt;
			int i=1;
			while(options.hasNext()) {
				opt=options.next();
				opt.setQuestion(question);
				opt.setAnswerImage(null);
				if(i<imgs.size())
					if(!imgs.get(i).isEmpty())
						try {
							opt.setAnswerImage(BlobProxy.generateProxy(imgs.get(i).getBytes()));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				//System.out.println(question.getQuestionImage()==null);
				i++;
			}
		}
		else if(question.getQuestionFormat().equals("obj-b"))
		{
			question.setTest(test);
			question.setAnswerImage(null);
			//question.setQuestionText(question.getQuestionText());
			

			Iterator<Answer> options = question.getOptions().iterator();
			Answer opt;
			int i=1;
			while(options.hasNext()) {
				opt=options.next();
				opt.setQuestion(question);
				opt.setAnswerImage(null);
				if(i<imgs.size())
					if(!imgs.get(i).isEmpty())
						try {
							opt.setAnswerImage(BlobProxy.generateProxy(imgs.get(i).getBytes()));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
				i++;
			}

		}
		else if(question.getQuestionFormat().equals("sub-a"))
		{
			if(!imgs.get(1).isEmpty())
				try {
					question.setAnswerImage(BlobProxy.generateProxy(imgs.get(1).getBytes()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else
				question.setAnswerImage(null);
			question.setOptions(new LinkedList<Answer>());
		
		}
//		--------------------------------------------------------------------------------------------
		questionService.saveQuestion(question);
		return "redirect:/admin/test/"+tid;
	
	}
	
	
	@RequestMapping(value="/test/{tid}/qn/{qid}/d",method = RequestMethod.GET)
	public String deleteQuestion(@PathVariable int tid,@PathVariable int qid) {
		System.out.println("dletequestion method called");
		questionService.deleteQuestionById(qid);
	    File fileToDelete =	new File("src/main/resources/static/img/qs"+qid+".jpg"); 
	    
	   try {
		FileUtils.forceDelete(fileToDelete);
	} catch (IOException e) {
		
		e.printStackTrace();
	}
		return "redirect:/admin/test/"+tid;
	
	}
	
	
	@RequestMapping(value="/result/{tid}",method = RequestMethod.GET)
	public ModelAndView result(ModelAndView mandv,@PathVariable int tid) {
		System.out.println("result method called");
		ParticipationPk pk=new ParticipationPk();
		pk.setTid(tid);
		Test test = testService.findById(tid);
		List<Participation> participations=participationService.findParticipationsByTid(tid);
		ArrayList<UserPart> userPart=new ArrayList<UserPart>();
		Iterator<Participation> itr=participations.iterator();
		while(itr.hasNext()) {
			Participation temp=itr.next();
			int uid=temp.getPk().getUid();
			User user=userService.findById(uid);
			UserPart up=new UserPart(user,temp);
			userPart.add(up);
		}
		Collections.sort(userPart,Collections.reverseOrder());
		Iterator<UserPart> itr1=userPart.iterator();
		int rank=1;
		while(itr1.hasNext()) {
			UserPart s=itr1.next();
			s.setRank(rank++);
		}
		mandv.addObject("test",test);
		mandv.addObject("users",userPart);
		mandv.setViewName("AdminResult");
		return mandv;
	
	}
	
	
	@RequestMapping(value="/test/{tid}/start",method = RequestMethod.GET)
	public String startTest(@PathVariable int tid) {
		System.out.println("startTest method called");
		Test test = testService.findById(tid);
		test.setState("start");
		int idx = 1;
		Iterator<Question> questions = questionService.findQuestionsByTid(tid).iterator();
		int totalMark = 0;
		while(questions.hasNext()) {
			Question qn = questions.next();
			qn.setIdx(idx++);
			totalMark += qn.getMark();
			System.out.println(qn.toString());
			questionService.updateQuestion(qn);
		}
		test.setTotalMarks(totalMark);
		testService.updateTest(test);
		
		
		return "redirect:/admin/test/"+tid;
	
	}
	
	@Transactional
	@RequestMapping(value="/test/{tid}/end",method = RequestMethod.GET)
	public String endTest(@PathVariable int tid) {
		System.out.println("endtest method called");
		Test test = testService.findById(tid);
		List<Question> lq=test.getQuestions();
		boolean isEvalRequired=false;
		for(Question q:lq)
		{
			if(q.getQuestionFormat().equals("sub-a"))
			{
				isEvalRequired=true;
				break;
			}
		}
		if(isEvalRequired)
			test.setState("pending");
		else
			test.setState("end");
		testService.updateTest(test);
		return "redirect:/admin/dashboard";
	
	}
	
	@RequestMapping(value="/test/{tid}/finishEvaluation",method = RequestMethod.GET)
	public String finishEvaluation(@PathVariable int tid) {
		System.out.println("finishEvaluation method called");
		Test test = testService.findById(tid);
		test.setState("end");
		testService.updateTest(test);
		return "redirect:/admin/dashboard";
	
	}
	
	@Transactional
	@RequestMapping(value="/testeval/{id}",method = RequestMethod.GET)
	public ModelAndView getTestForEvaluation(ModelAndView mandv, @PathVariable int id) {
		System.out.println("gettestforevaluation method called");
		Test test = testService.findById(id);
		User user=userService.findById(test.getPid());
		temp t_exists=tempService.getTempByUid(test.getUser().getUid());
		if(t_exists==null)
		{
			temp t=new temp();
			t.setUid(test.getUser().getUid());
			t.setTempval(id);
			tempService.saveTemp(t);	
		}
		else
		{
			t_exists.setTempval(id);
//			tempService.saveTemp(t_exists);
		}
		
		Question question = new Question();
		List<Question> questions = questionService.findQuestionsByTid(id);
		for(Question q:questions)
		{
//			no session error while loading jsp- Solution: other class objects have to be retrieved and set before going to the view
			q.setOptions(answerService.findAllAnswerByQid(q.getQid()));
		}
		List<Submission> submission=submissionService.findSubmissionByTid(id);
		List<Answer> options = new ArrayList<>();
		for(int i = 0 ; i < 4 ; i++) {
			options.add(new Answer());
		}
		question.setOptions(options);
		Answer option=new Answer();
		for(Question q:questions)
		{
			if(q.getAnswerImage()!=null)
			{
			ByteArrayInputStream bis;
			try {
				bis = new ByteArrayInputStream(q.getAnswerImage().getBytes(1, (int) q.getAnswerImage().length()));
				BufferedImage bm = ImageIO.read(bis);
				String fileName="as"+String.valueOf(q.getQid())+".jpg";
				String fileLocation = new File("src\\main\\resources\\static\\img").getAbsolutePath() + "\\" + fileName;
				FileOutputStream fout=new FileOutputStream(fileLocation);
				ImageIO.write(bm,"jpg",fout);
				fout.close();
				mandv.addObject("aimg-"+q.getQid(),"/as"+q.getQid()+".jpg");
				//System.out.println("---------------question-id="+q.getQid());
			} catch(Exception e) {;}
			}
			else
				System.out.println("---------------------------------------qqqq "+(q.getQuestionImage()!=null)+" "+q.getQid());
		}

		
		mandv.addObject("question",question);
		mandv.addObject("test",test);
		mandv.addObject("option",option);
		mandv.addObject("Questions",questions);
		mandv.addObject("submission",submission);
		mandv.addObject("participant_name",user.getName());
		mandv.setViewName("AdminEvaluateAnswer");
		return mandv;
	
	}
	
	@RequestMapping(value="/testevalsubmit/{tid}/{index}/{state}",method = RequestMethod.GET)
	@ResponseBody
	public String changeStateOfSubmission(@PathVariable int tid,@PathVariable int index,@PathVariable String state) {
		System.out.println("changestateofsubmission method called");
		Test t=testService.findById(tid);

		List<Submission> sb=submissionService.findSubmissionByTidAndUid(tid, t.getPid());
		Submission s=sb.get(index);
		ParticipationPk pk=new ParticipationPk();
		pk.setTid(tid);
		pk.setUid(s.getCompkey().getUid());
		Participation p=participationService.findById(pk);
		
		if(state.equals("correct") && !s.getState().equals("correct"))
		{
		s.setMark(questionService.findById(s.getCompkey().getQid()).getMark());
		p.setScore(p.getScore()+s.getMark());
		}
		else if(state.equals("wrong") && !s.getState().equals("wrong"))
		{
			p.setScore(p.getScore()-s.getMark());
			s.setMark(0);
		}
		s.setState(state);
		submissionService.updateSubmission(s);
		participationService.updateParticipation(p);
		return "succeeded";
	}
	 
	@RequestMapping(value="/excelExport/{tid}",method = RequestMethod.GET)
	public ModelAndView exportToExcel(ModelAndView mandv,@PathVariable int tid) {
		System.out.println("exporttoexcel method called");
		ParticipationPk pk=new ParticipationPk();
		pk.setTid(tid);
		List<Participation> participations=participationService.findParticipationsByTid(tid);
		ArrayList<UserPart> userPart=new ArrayList<UserPart>();
		Iterator<Participation> itr=participations.iterator();
		while(itr.hasNext()) {
			Participation temp=itr.next();
			int uid=temp.getPk().getUid();
			User user=userService.findById(uid);
			UserPart up=new UserPart(user,temp);
			userPart.add(up);
		}
		Collections.sort(userPart,Collections.reverseOrder());
		Iterator<UserPart> itr1=userPart.iterator();
		int rank=1;
		while(itr1.hasNext()) {
			UserPart s=itr1.next();
			s.setRank(rank++);
		}
		mandv.setView(new ExcelExport());
		mandv.addObject("list",userPart);
		return mandv;
	
	}
	
	//Zoom meeting functions
	@RequestMapping(path="/startMeet") 
	 public String startAdminMeeting(Principal principal,HttpServletRequest request,HttpServletResponse response) throws Exception {
		 
		 System.out.println("method startAdminmeeting called");
		 Zoom z=zoomService.findZoomBytid(tempService.getTempByUid(userService.findUserByEmailid(principal.getName()).getUid()).getTempval());
		 if(z!=null)
		 {
			 return "AdminMeet";
		 }
		 return "redirect:https://zoom.us/oauth/authorize?response_type=code&client_id=YT3ThexPRZCNcg9IDQ3MFA&redirect_uri=http://localhost:8080/oauther/";
			  
	 }
	
	 
	 
	 @RequestMapping(value="/meet",method = RequestMethod.GET)
		public ModelAndView showMeet(ModelAndView mandv) {
			System.out.println("showMeet method called");
			mandv.setViewName("AdminMeet");
			return mandv;
		}
	 

	 @RequestMapping(value="/deleteMeet",method = RequestMethod.GET)
		public void deleteMeet(Principal principal) {
			System.out.println("deleteMeet method called");
			zoomService.deleteZoomByTid(tempService.getTempByUid(userService.findUserByEmailid(principal.getName()).getUid()).getTempval());
		}
	 
	 
	 @RequestMapping(path="/getMeetingDetails", produces="application/json", method=RequestMethod.POST)
	 @ResponseBody
	 public Map<String,String> meetDetails(Principal principal){ //@PathVariable(name="apikey")String apikey) {
		 System.out.println("getMeetingDetails ADmin- method called");
	 	Zoom z=zoomService.findZoomBytid(tempService.getTempByUid(userService.findUserByEmailid(principal.getName()).getUid()).getTempval());
	 	//Zoom z=ls.get(ls.size()-1);
	 	Map<String,String> m=new LinkedHashMap<String,String>();
	 	m.put("sdkKey", z.getSdkKey());
	 	m.put("meetingNumber", z.getMeetingNumber());
	 	m.put("password", z.getPassword());
	 	m.put("signature", z.getSignature());
	 	m.put("username", z.getUserName());
	 	
	 	 return m;
	 }
	 
	
	 
	
}
