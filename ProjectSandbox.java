package info5100_ProjectSandbox;

import java.util.Scanner;
import java.time.LocalDateTime;

public class ProjectSandbox {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Scanner sc = new Scanner(System.in);
		LocalDateTime dateTime = LocalDateTime.now();
		System.out.println(dateTime.plusHours(3).format(Person.formatter));
		System.out.println(LocalDateTime.now().plusHours(5).compareTo(LocalDateTime.now().plusHours(3)));
//		System.out.println(SharedData.hostArrayList.size());
//		System.out.println(SharedData.visitorArrayList.size());
//		System.out.println(SharedData.codeArrayList.size());
//
////		SharedData.addHost("HOST1", "1A");
//		SharedData.clearAll();
		
		
		
//		System.out.println(SharedData.hostArrayList.getFirst());
		// TODO Auto-generated method stub
////		PROTOTYPE 2
////		Ask if need to create host
//		System.out.println("Hi, host.");
////		Ask host if need to generate code
//		System.out.println("Would you like to generate a code?");
//		String genCodeBoolean = sc.nextLine();
//		if (genCodeBoolean.equals("Y")) {
//			System.out.println("Please input your id: ");
//			String hostId = sc.nextLine();
//			boolean hostExist = false;
//			boolean doneCreation = false;
//			// creating the first host
//			if(SharedData.hostArrayList.size() == 0) {
//				System.out.println("Please input your address (e.g. 12B): ");
//				String hostAddress = sc.nextLine();
//				SharedData.hostArrayList.add(new Host(hostId, hostAddress));
//				System.out.println(SharedData.hostArrayList.getLast().toString());
//				hostExist = true;
//			} else {
//				for (int i = 0; i < SharedData.hostArrayList.size(); i++) {
//					System.out.println(SharedData.hostArrayList.get(i).toString());
//					Host hostObject = SharedData.hostArrayList.get(i);
//					if(hostId.equals(hostObject.id)) {
//						hostExist = true;
//					} else {
//						// creating new host
//						System.out.println("Please input your address (e.g. 12B): ");
//						String hostAddress = sc.nextLine();
//						SharedData.hostArrayList.add(new Host(hostId, hostAddress));
//						System.out.println(SharedData.hostArrayList.getLast().toString());
//						System.out.println(SharedData.hostArrayList.get(i).toString());
//						hostExist = true;
//					} 
//				}
//			}
//			
//			while (hostExist == true && doneCreation == false) {
//				for(int i = 0; i < SharedData.hostArrayList.size(); i++) {
//					Host hostObject = SharedData.hostArrayList.get(i);
//					if(hostId.equals(hostObject.id)) {
//						System.out.println("Awating Host inputs...");
//						hostObject.generateCode();
//						if (SharedData.visitorArrayList.size() == 0) {
//							System.out.println("Creating visitor...");
//							SharedData.visitorArrayList.add(new Visitor(hostObject.nextVisitorId));
//							System.out.println(SharedData.visitorArrayList.getLast().toString());
//							SharedData.codeArrayList.add(new Code(hostObject.nextCode, hostObject.address, hostObject.nextVisitorId, hostObject.generationDatetime));
//							System.out.println(SharedData.codeArrayList.getLast().toString());
//							doneCreation = true;
//						} else {
//							for (int j = 0; j < SharedData.visitorArrayList.size(); j++) {
//								Visitor visitorObject = SharedData.visitorArrayList.get(j);
//								if(visitorObject.id == hostObject.nextVisitorId) {
//									System.out.println(SharedData.visitorArrayList.get(i).toString());
//									System.out.println("Visitor already registered.");
//									System.out.println("Creating code...");
//									SharedData.codeArrayList.add(new Code(hostObject.nextCode, hostObject.address, hostObject.nextVisitorId, hostObject.generationDatetime));
//									System.out.println(SharedData.codeArrayList.getLast().toString());
//								}
//							}
//							doneCreation = true;
//						}
//					}
//				}
//			}
//		}
////		Prompt for visitor code input
//		System.out.println("Hi, visitor.");
//		System.out.println("Please input your id: ");
//		String inputId = sc.nextLine();
//		System.out.println("Please input the address of your host: ");
//		String address = sc.nextLine();
//		System.out.println("Please input your 6-digit code: ");
//		String code = sc.nextLine();
//		
//		
////		Grant or reject access
//		for (int k = 0; k < SharedData.codeArrayList.size(); k++) {
//			System.out.println(SharedData.codeArrayList.get(k).toString());
//			Code codeObject = SharedData.codeArrayList.get(k);
//			if(inputId.equals(codeObject.visitorId) && address.equals(codeObject.hostAddress) && code.equals(codeObject.code) && codeObject.getIsValid()) {
//				for (int l = 0; l < SharedData.visitorArrayList.size(); l++) {
//					Visitor visitorObject = SharedData.visitorArrayList.get(l);
//					visitorObject.grantAccess();
//					System.out.println("Welcome! Access granted");
//				}
//			} else {
//				System.out.println("Access denied");
//			}
//		}
		
		
//		accessArrayList.add(new Access(codeMaryJohn.code, mary.id, codeMaryJohn.hostAddress, codeMaryJohn.visitorId, codeMaryJohn.generationDateTime, john.accessDateTime));
//		System.out.println(accessArrayList.getFirst().toString());

	}

}


