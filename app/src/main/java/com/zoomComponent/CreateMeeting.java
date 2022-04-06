package com.zoomComponent;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.web.bind.annotation.RequestMapping;
//import io.jsonwebtoken.security.Keys;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class CreateMeeting {
	

	public ZoomMeetingObjectDTO createMeeting(Zoomobj zoomobj,String acctoken) {
	        System.out.println("Request to create a Zoom meeting");
	       // replace zoomUserId with your user ID
	        String zoomUserId="khanvms9@gmail.com";
	        
	        String apiUrl = "https://api.zoom.us/v2/users/" + zoomUserId + "/meetings";
	        
	      // replace with your password or method
	        zoomobj.setPassword("5498102");
	        zoomobj.setType(1);
	      // replace email with your email
	        //zoomobj.setHost_email("khanvms9@gmail.com");

	      // Optional Settings for host and participant related options
	        ZoomMeetingSettingsDTO settingsDTO = new ZoomMeetingSettingsDTO();
	        settingsDTO.setJoin_before_host(false);
	        settingsDTO.setParticipant_video(true);
	        settingsDTO.setHost_video(false);
	        settingsDTO.setAuto_recording("cloud");
	        settingsDTO.setMute_upon_entry(true);
	        zoomobj.setSettings(settingsDTO);

	        RestTemplate restTemplate = new RestTemplate();
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Authorization", "Bearer " + acctoken);
	        headers.add("content-type", "application/json");
	        HttpEntity<Zoomobj> httpEntity = new HttpEntity<Zoomobj>(zoomobj, headers);
	        ResponseEntity<ZoomMeetingObjectDTO> zEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, ZoomMeetingObjectDTO.class);
	        if(zEntity.getStatusCodeValue() == 201) {
	            System.out.println("Zooom meeeting response "+zEntity);
	            return zEntity.getBody();
	        } else {
	            System.out.println("Error while creating zoom meeting "+ zEntity.getStatusCode());
	        }
	        return null;
	    }

}
	    /**
	     * Generate JWT token for Zoom using api credentials
	     * 
	     * @return JWT Token String
	     */
//	    private String generateZoomJWTTOken() {
//	        String id = UUID.randomUUID().toString().replace("-", "");
//	        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//
//	        Date creation = new Date(System.currentTimeMillis());
//	        Date tokenExpiry = new Date(System.currentTimeMillis() + (1000 * 60));
//
//	        Key key = Keys
//	            .hmacShaKeyFor(zoomApiSecret.getBytes());
//	        return Jwts.builder()
//	            .setId(id)
//	            .setIssuer(zoomApiKey)
//	            .setIssuedAt(creation)
//	            .setSubject("")
//	            .setExpiration(tokenExpiry)
//	            .signWith(key, signatureAlgorithm)
//	            .compact();
//	    }
//}
