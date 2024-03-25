package javaTimeAll;

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
	
	
    static void handleVisitorActions() {
    	        boolean doneSearch = false;
          	boolean doneVisitorLookUp = false;
          	boolean inputRecordMatch = false;
          	boolean visitorExist = false;
			System.out.println("Hi, Visitor.");
			System.out.println("Please enter your ID number: ");
			String visitorId = sc.nextLine().toUpperCase();
			System.out.println("Please enter your host's address: ");
			String address = sc.nextLine().toUpperCase();
			System.out.println("Please enter your 6-digit code: ");
			String code = sc.nextLine().toUpperCase();
			LocalDateTime inputTime = LocalDateTime.now();
			
//			Find match
			while (doneSearch == false) {
				for (int i = 0; i < SharedData.codeArrayList.size(); i++) {
					Code codeObject = SharedData.codeArrayList.get(i);
//					Only when all of the following conditions are met:
//					Condition #1: input time within 3 hours after codeObject.generationDateTime
//					Condition #2: visitorId input matches codeObject.visitorId
//					Condition #3: address input matches codeObject.hostAddress
//					Condition #4: code input matches codeObject.code
//					Condition #5: codeObject.isValid == true
//					Condition #6: codeObject.used == false
					if (inputTime.compareTo(codeObject.generationDateTime.plusHours(3)) == -1 && 
                            visitorId.equals(codeObject.visitorId) && address.equals(codeObject.hostAddress) &&
                             code.equals(codeObject.code) && codeObject.isValid && codeObject.used == false) {
						inputRecordMatch = true;
//						Set codeObject.used to true (Grant access)
						codeObject.used = true;
						while (visitorExist == false && inputRecordMatch == true) {
//							Find visitor
							for (int j = 0; j < SharedData.visitorArrayList.size(); j++) {
								Visitor visitorObject = SharedData.visitorArrayList.get(j);
								if (visitorId.equals(visitorObject.id)) {
									System.out.println("Visitor already exists.");
									visitorExist = true;
									doneSearch = true;
									break;
								}
							}
//							If not found, create visitor
							while (visitorExist == false) {
								SharedData.addVisitor(visitorId);
								visitorExist = true;
								doneSearch = true;
								break;
							}
						}
					} 
//					If any of the above condition is not met, deny access
					else if (i == SharedData.codeArrayList.size() - 1 && inputRecordMatch == false) {
						doneSearch = true;
						doneVisitorLookUp = true;
						System.out.println("Access denied.");
						break;
					}						
				}
			}
//			Create access
			while (doneVisitorLookUp == false) {
				for (int j = 0; j < SharedData.visitorArrayList.size(); j++) {
					Visitor visitorObject = SharedData.visitorArrayList.get(j);
					if (visitorId.equals(visitorObject.id)) {
						visitorObject.setExpectedAddress(address);
						visitorObject.setExpectedCode(code);
						visitorObject.grantAccess(inputTime);
						SharedData.addAccess(visitorObject.expectedAddress, visitorObject.id, visitorObject.expectedCode, visitorObject.accessDateTime);
						doneVisitorLookUp = true;
						break;
					}
				}
			}	
    	
    }
	
	
}