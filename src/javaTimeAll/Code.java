package javaTimeAll;


import java.time.LocalDateTime;

class Code {
	String code;
	boolean isValid = true;
	String hostAddress;
	String visitorId;
	LocalDateTime generationDateTime;
	String hostId;
	boolean used = false;
	
	Code(String code, String hostAddress, String visitorId, LocalDateTime generationDateTime, String hostId) {
		this.code = code;
		this.hostAddress = hostAddress;
		this.visitorId = visitorId;
		this.generationDateTime = generationDateTime;
		this.hostId = hostId;
	}
	
	Code(String code, boolean isValid, String hostAddress, String visitorId, LocalDateTime generationDateTime, String hostId, boolean used) {
		this.code = code;
		this.isValid = isValid;
		this.hostAddress = hostAddress;
		this.visitorId = visitorId;
		this.generationDateTime = generationDateTime;
		this.hostId = hostId;
		this.used = used;
	}
	
	
	void invalidate() {
		System.out.println("Cancelling code "+ this.code + " for " + this.visitorId + "...");
		this.isValid = false;   //does not delete, just make it invalidated.
		System.out.println("Code cancelled");
		System.out.println(this.code + " is valid: " + this.isValid);
	}
	
	boolean getIsValid() {
		return this.isValid;
	}	
}
