package info5100_ProjectSandbox;

import java.time.LocalDateTime;

class Visitor extends Person {
	String expectedCode;
	String expectedAddress;
	LocalDateTime accessDateTime;
	
	Visitor(String id) {
		this.id = id;
	}
	
	void setExpectedCode(String expectedCode) {
		this.expectedCode = expectedCode;
	}
	
	void setExpectedAddress(String expectedAddress) {
		this.expectedAddress = expectedAddress;
	}
	
	LocalDateTime grantAccess(LocalDateTime inputTime) {
		System.out.println("Welcome! Access granted at " + inputTime.format(formatter));
		return this.accessDateTime = inputTime;
	}
	
	
}