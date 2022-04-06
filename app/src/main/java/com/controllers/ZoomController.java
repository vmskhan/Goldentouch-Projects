package com.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.entities.User;
import com.entities.Zoom;
import com.service.TempService;
import com.service.UserService;
import com.service.ZoomService;
import com.zoomComponent.CreateMeeting;
import com.zoomComponent.Token;
import com.zoomComponent.ZoomMeetingObjectDTO;
import com.zoomComponent.Zoomobj;
import com.zoomComponent.ZoomSignatureRetriever;

@Controller
public class ZoomController {
	
	@Autowired
	Environment env;
		
	@Autowired
	TempService tempService;
	
	@Autowired
	ZoomSignatureRetriever c;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ZoomService zoomService;
	
	private RestTemplate restTemplate;
	 
	@RequestMapping(path="/oauther")	 
	 public void  getAccesstoken(HttpServletRequest request,HttpServletResponse resp,ModelAndView mandv,Principal principal) throws Exception {
		 int userId=3;
		 System.out.println("method oauther called");
	        String url = "";
	        String code=request.getParameter("code");
	        // create headers
	        HttpHeaders headers = new HttpHeaders();
	        // set `content-type` header
	        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	        // set `accept` header
	        String ids=Base64.getEncoder().encodeToString((this.env.getProperty("app.zoom.oauth.client-id")+":"+this.env.getProperty("app.zoom.oauth.client-secret")).getBytes());
	        headers.add("Authorization", "Basic "+ids);
      headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	       
	        // create a map for post parameters
	        Map<String, String> map = new HashMap<>();
	        map.put("grant_type","authorization_code");
	        map.put("code", code);
//	        map.put("redirect_uri", "http://localhost:8080/oauther/");
	        String encodedURL = map.keySet().stream().map(key -> key + "=" + encodeValue(map.get(key)))
	        	     .collect(Collectors.joining("&", "https://zoom.us/oauth/token?", ""));
	        encodedURL+="&redirect_uri=http://localhost:8080/oauther/";
	        System.out.println(encodedURL);
	        // build the request
	        HttpEntity<Map<String, String>> entity = new HttpEntity<>(headers);

	        
	        //create rest template
	        this.restTemplate=new RestTemplateBuilder().build();
	        
	        // send POST request
	        ResponseEntity<Token> response = this.restTemplate.exchange(encodedURL, HttpMethod.POST,entity, Token.class);
	        ZoomMeetingObjectDTO zm;
	        // check response status code
	        if (response.getStatusCode() == HttpStatus.OK) {
	            Token newtk=response.getBody();
	            System.out.println(newtk);
	            Zoomobj zo=new Zoomobj();
	            CreateMeeting cm=new CreateMeeting();
	            zm= cm.createMeeting(zo,newtk.getAccess_token());
	            //request.setAttribute("zoomObj", zm);
	            
	            User user=userService.findUserByEmailid(principal.getName());
//		        ZoomSignatureRetriever c=new ZoomSignatureRetriever();
		        String sig=c.sendSignature(1,zm.getId());
		       
		        Zoom z=new Zoom();
		        z.setSdkKey(this.env.getProperty("app.zoom.sdk-key"));
		        z.setSignature(sig);
		        z.setMeetingNumber(zm.getId().toString());
		        z.setPassword(zm.getPassword());
		        z.setUserName(user.getName());
		       z.setTestId(tempService.getTempByUid(user.getUid()).getTempval());
//		        System.err.println(l.get(5));
		        
		        zoomService.saveZoomObj(z);
		        resp.sendRedirect("/admin/meet");
		        
	        } else {
	            System.out.println(response.getStatusCodeValue());
	            
	        }
	        
	        
	 }
	 private String encodeValue(String value)  {
		    try {
				return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			return null;
		}
}
