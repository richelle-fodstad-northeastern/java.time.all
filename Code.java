package info5100_ProjectSandbox;

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
	
	void invalidate() {
		System.out.println("Cancelling code "+ this.code + " for " + this.visitorId + "...");
		this.isValid = false;
		System.out.println("Code cancelled");
		System.out.println(this.code + " is valid: " + this.isValid);
	}
	
	boolean getIsValid() {
		return this.isValid;
	}	
}
