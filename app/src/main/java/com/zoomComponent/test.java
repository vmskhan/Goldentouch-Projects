package com.zoomComponent;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.security.InvalidKeyException;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;

@Component
public class test {
	public static String generateSignature(String apiKey, String apiSecret, String meetingNumber, Integer role) {
		try {
			Mac hasher = Mac.getInstance("HmacSHA256");
			String ts = Long.toString(System.currentTimeMillis() - 30000);
			String msg = String.format("%s%s%s%d", apiKey, meetingNumber, ts, role);
			hasher.init(new SecretKeySpec(apiSecret.getBytes(), "HmacSHA256"));
			String message = Base64.getEncoder().encodeToString(msg.getBytes());
			byte[] hash = hasher.doFinal(message.getBytes());
			String hashBase64Str = DatatypeConverter.printBase64Binary(hash);
			String tmpString = String.format("%s.%s.%s.%d.%s", apiKey, meetingNumber, ts, role, hashBase64Str);
			String encodedString = Base64.getEncoder().encodeToString(tmpString.getBytes());
			return encodedString.replaceAll("\\=+$", "");
	  	}
	  	catch (NoSuchAlgorithmException e) {}
	  	catch (InvalidKeyException e) {}
		return "";
	}
	public String getSig(String meetingNumber,int role) {
		String signature = test.generateSignature("I_ahU1t7SI-kq5MIWW-8BA", "2wxBkcgmWsOsT1vxohEVxR9c7ogfecigcmr0", meetingNumber, role);
		System.out.println(signature);
		return(signature);
	}
}