package javaTimeAll;

import java.time.LocalDateTime;
import java.util.Random;


class Host extends Person {
	
	Random rnd = new Random();
	String nextCode;
	String address;
	String nextVisitorId;
//	String generationDatetime;
	LocalDateTime generationDatetime;
	
	Host(String id, String address) {
		this.id = id;
		this.address = address;
	}
	
	void generateCode(String visitorId) {
		this.nextVisitorId = visitorId;
//		this.nextCode = rnd.nextInt(1000000);
		int[] randomNumArr = new int[6];
		for (int i = 0; i < randomNumArr.length; i++) {
			randomNumArr[i] = rnd.nextInt(10);
		}
		for (int number : randomNumArr) {
		    sb.append(number);
		}
		
		if(sb.length() <= 6) {
			this.nextCode = sb.toString();
		} else {
			this.nextCode = sb.substring(sb.length()-6, sb.length()).toString();
		}
		this.generationDatetime = super.dateTime;
		System.out.println("Generating code...");
		System.out.println("Please send the following 6-digit code to your visitor: " + this.nextCode);
		System.out.println("Generation time: " + this.generationDatetime.format(formatter));
//		LocalDateTime generationDateTime =  LocalDateTime.now();
	}
	
	
	
	boolean cancel(String code) {
		return false;
	}
}