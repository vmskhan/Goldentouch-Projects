package com.zoomComponent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ZoomSignatureRetriever {
	
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}


	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}


	private RestTemplate restTemplate;


public String sendSignature(int role,Long meetingNumber) {	
	//signature request
	 String url = "http://localhost:4000";
	 HttpHeaders headers = new HttpHeaders();
    // set `content-type` header
    headers.setContentType(MediaType.APPLICATION_JSON);
    // set `accept` header

    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
   
    // create a map for post parameters
    Map<String, Object> map = new HashMap<>();
    map.put("meetingNumber",meetingNumber.toString());
    map.put("role", role);
  HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
  //create resttemplate
  this.restTemplate=new RestTemplateBuilder().build();
  // send POST request
  ResponseEntity<Signature> response = this.restTemplate.postForEntity(url, entity, Signature.class);

  // check response status code
  if (response.getStatusCode() == HttpStatus.OK) {
      System.out.println(response.getBody());
      System.out.println("sig obj:"+response.getBody().getSignature());
  } else {
      System.out.println("null"+response.getBody());
  }
	
	String sig=response.getBody().getSignature();
	System.out.println("adminsiganture method called");
	return sig;
}

}

