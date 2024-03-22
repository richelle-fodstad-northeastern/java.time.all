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
		this.nextCode =""+rnd.nextInt(1000000);
/**		int[] randomNumArr = new int[6];
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
*/
		this.generationDatetime = super.dateTime;
		System.out.println("Generating code...");
		System.out.println("Please send the following 6-digit code to your visitor: " + this.nextCode);
		System.out.println("Generation time: " + this.generationDatetime.format(formatter));
//		LocalDateTime generationDateTime =  LocalDateTime.now();
	}
	
	
	
	boolean cancel(String code) {
		return false;
	}
	
	
	static void handleHostActions() {
		
		//boolean hostHasCode = false;
		boolean previousCodeFound = false;
		boolean cancelCode = false;
		boolean doneCodeLookUp = false;
	
		System.out.println("Hi, Host.");
		System.out.println("Please enter your ID number: ");
		String hostId = sc.nextLine().toUpperCase();
		
//		Find host	
//		Create host when isHostExist(hostId) == false
		if (isHostExist(hostId) == false) {
//				System.out.println("Please contact Admin to register your host account.");
				System.out.println("Please enter your address (e.g. 12B): ");
				String hostAddress = sc.nextLine().toUpperCase();
				SharedData.addHost(hostId, hostAddress);
//				hostExist = true;
//				endProgram = true;
		}
		
		System.out.println("Welcome back!");

//		Host actions
		System.out.println("What would you like to do?");

		
		
		if (SharedData.codeArrayList.size() == 0 || isHostHasCode(hostId) == false ) {
			System.out.println("Generate new code: G");
		} else {
			System.out.println("Generate new code: G");
			System.out.println("Cancel code: C");
		}
		String cancelOrGenerate = sc.nextLine().toUpperCase();
		if (cancelOrGenerate.equals("G")) 
			generateCode2(hostId,previousCodeFound);	
		 else if (cancelOrGenerate.equals("C") && SharedData.codeArrayList.size() > 0) 
//			Cancel code
			 cancelCode(hostId,cancelCode,doneCodeLookUp);
	     else {
			System.out.println("Action unavailable");
		}	
		
	}
    
    
     private static boolean isHostExist(String hostId) {
			for (int i = 0; i < SharedData.hostArrayList.size(); i++) {
				Host hostObject = SharedData.hostArrayList.get(i);
				if (hostId.equals(hostObject.id)) 
					return true;
			}
			return false; 	
    }
     
     
     private static boolean isHostHasCode(String hostId) {
		for (int i = 0; i < SharedData.codeArrayList.size(); i++) {
			Code codeObject = SharedData.codeArrayList.get(i);
			if (codeObject.hostId.equals(hostId)) {
				return true;
			} 
		}
		return false;
     }
    	
     private static void generateCode2(String hostId, boolean previousCodeFound) {
     System.out.println("Please enter the visitor's ID number: ");
		String visitorId = sc.nextLine().toUpperCase();
		for (int i = 0; i < SharedData.hostArrayList.size(); i++) {
			Host hostObject = SharedData.hostArrayList.get(i);
			if (hostId.equals(hostObject.id)) {
				while (previousCodeFound == false && SharedData.codeArrayList.size() > 0) {
					for (int j = 0; j < SharedData.codeArrayList.size(); j++) {
						Code codeObject = SharedData.codeArrayList.get(j);
//						invalidate previous code generated for the same visitor
						if (codeObject.visitorId.equals(visitorId) && codeObject.used == false && 											codeObject.isValid && codeObject.hostAddress.equals(hostObject.address)) {
							codeObject.invalidate();
							previousCodeFound = true;
							break;
						} else if (j == SharedData.codeArrayList.size() - 1) {
							previousCodeFound = true;
							break;
						}
					}
				}
//				Generate code
				hostObject.generateCode(visitorId);
				SharedData.addCode(hostObject.nextCode, hostObject.address, hostObject.nextVisitorId,     												hostObject.generationDatetime, hostObject.id);
			}
		}
     }

     private static void cancelCode(String hostId, boolean cancelCode, boolean doneCodeLookUp) {
 		System.out.println("Please enter the code you would like to cancel: ");
		String codeCancel = sc.nextLine();
		while (cancelCode == false && doneCodeLookUp == false) {
			for (int i = 0; i < SharedData.codeArrayList.size(); i++) {
				Code codeObject = SharedData.codeArrayList.get(i);
				if (codeCancel.equals(codeObject.code) && hostId.equals(codeObject.hostId) 
						&& codeObject.isValid == true) {
					codeObject.invalidate();
					cancelCode = true;
					doneCodeLookUp = true;
					break;
				}
				if (i == SharedData.codeArrayList.size() - 1 && cancelCode == false) {
					System.out.println("No such code found");
					doneCodeLookUp = true;
					break;
				} //should here be "i <= SharedData.codeArrayList.size() - 1" instead of "i == SharedData.codeArrayList.size() - 1" ?
			}
		}

}
}

	