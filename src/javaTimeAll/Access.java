package javaTimeAll;

import java.time.LocalDateTime;

class Access {
	String hostAddress;
	String visitorId;
	String code;
	LocalDateTime accessDateTime;
	
	Access(String hostAddress, String visitorId, String code, LocalDateTime accessDateTime) {
		this.hostAddress = hostAddress;
		this.visitorId = visitorId;
		this.code = code;
		this.accessDateTime = accessDateTime;
	}

}