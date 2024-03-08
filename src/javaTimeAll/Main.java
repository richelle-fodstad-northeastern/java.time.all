package javaTimeAll;

import java.time.LocalDateTime;
import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		boolean endProgram = false;
		boolean hostExist = false;
		boolean inputRecordMatch = false;
		boolean visitorExist = false;
		boolean doneSearch = false;
		boolean cancelCode = false;
		boolean doneCodeLookUp = false;
		boolean doneVisitorLookUp = false;
		boolean previousCodeFound = false;
		boolean hostHasCode = false; 

		
		System.out.println("Welcome to the Visitor Access Management System!");
		System.out.println();
		while (endProgram == false) {
			System.out.println("Please select your role: ");
			System.out.println("Host: H");
			System.out.println("Visitor: V");
			System.out.println("Admin: A");
			String role = sc.nextLine().toUpperCase();
			
			if (role.equals("H")) {
//				HOST
				System.out.println("Hi, Host.");
				System.out.println("Please enter your ID number: ");
				String hostId = sc.nextLine().toUpperCase();
//				Find host
				while (hostExist == false) {
					for (int i = 0; i < SharedData.hostArrayList.size(); i++) {
						Host hostObject = SharedData.hostArrayList.get(i);
						if (hostId.equals(hostObject.id)) {
							hostExist = true;
							System.out.println("Welcome back!");
						}
					}
//				Create host
					if (hostExist == false) {
//						System.out.println("Please contact Admin to register your host account.");
						System.out.println("Please enter your address (e.g. 12B): ");
						String hostAddress = sc.nextLine().toUpperCase();
						SharedData.addHost(hostId, hostAddress);
						hostExist = true;
//						endProgram = true;
					}
				}
//				Host actions
				System.out.println("What would you like to do?");
				for (int i = 0; i < SharedData.codeArrayList.size(); i++) {
					Code codeObject = SharedData.codeArrayList.get(i);
					if (codeObject.hostId.equals(hostId)) {
						hostHasCode = true;
						break;
					} 
				}
				
				if (SharedData.codeArrayList.size() == 0 || hostHasCode == false ) {
					System.out.println("Generate new code: G");
				} else {
					System.out.println("Generate new code: G");
					System.out.println("Cancel code: C");
				}
				String cancelGenerate = sc.nextLine().toUpperCase();
				if (cancelGenerate.equals("G")) {
					System.out.println("Please enter the visitor's ID number: ");
					String visitorId = sc.nextLine().toUpperCase();
					for (int i = 0; i < SharedData.hostArrayList.size(); i++) {
						Host hostObject = SharedData.hostArrayList.get(i);
						if (hostId.equals(hostObject.id)) {
							while (previousCodeFound == false && SharedData.codeArrayList.size() > 0) {
								for (int j = 0; j < SharedData.codeArrayList.size(); j++) {
									Code codeObject = SharedData.codeArrayList.get(j);
//									invalidate previous code generated for the same visitor
									if (codeObject.visitorId.equals(visitorId) && codeObject.used == false && codeObject.isValid && codeObject.hostAddress.equals(hostObject.address)) {
										codeObject.invalidate();
										previousCodeFound = true;
										break;
									} else if (j == SharedData.codeArrayList.size() - 1) {
										previousCodeFound = true;
										break;
									}
								}
							}
//							Generate code
							hostObject.generateCode(visitorId);
							SharedData.addCode(hostObject.nextCode, hostObject.address, hostObject.nextVisitorId, hostObject.generationDatetime, hostObject.id);
						}
					}
				} else if (cancelGenerate.equals("C") && SharedData.codeArrayList.size() > 0) {
//					Cancel code
					System.out.println("Please enter the code you would like to cancel: ");
					String codeCancel = sc.nextLine();
					while (cancelCode == false && doneCodeLookUp == false) {
						for (int i = 0; i < SharedData.codeArrayList.size(); i++) {
							Code codeObject = SharedData.codeArrayList.get(i);
							if (codeCancel.equals(codeObject.code) && hostId.equals(codeObject.hostId)) {
								codeObject.invalidate();
								cancelCode = true;
								doneCodeLookUp = true;
								break;
							}
							
							if (i == SharedData.codeArrayList.size() - 1 && cancelCode == false) {
								System.out.println("No such code found");
								doneCodeLookUp = true;
								break;
							}
						}
					}

				} else {
					System.out.println("Action unavailable");
				}
			} else if (role.equals("V")) {
//				VISITOR
				System.out.println("Hi, Visitor.");
				System.out.println("Please enter your ID number: ");
				String visitorId = sc.nextLine().toUpperCase();
				System.out.println("Please enter your host's address: ");
				String address = sc.nextLine().toUpperCase();
				System.out.println("Please enter your 6-digit code: ");
				String code = sc.nextLine().toUpperCase();
				LocalDateTime inputTime = LocalDateTime.now();
//				Find match
				while (doneSearch == false) {
					for (int i = 0; i < SharedData.codeArrayList.size(); i++) {
						Code codeObject = SharedData.codeArrayList.get(i);
						if (inputTime.compareTo(codeObject.generationDateTime.plusHours(3)) == -1 && visitorId.equals(codeObject.visitorId) && address.equals(codeObject.hostAddress) && code.equals(codeObject.code) && codeObject.isValid && codeObject.used == false) {
							inputRecordMatch = true;
							codeObject.used = true;
							while (visitorExist == false && inputRecordMatch == true) {
//								Find visitor
								for (int j = 0; j < SharedData.visitorArrayList.size(); j++) {
									Visitor visitorObject = SharedData.visitorArrayList.get(j);
									if (visitorId.equals(visitorObject.id)) {
										System.out.println("Visitor already exists.");
										visitorExist = true;
										doneSearch = true;
										break;
									}
								}
//								Create visitor
								while (visitorExist == false) {
									SharedData.addVisitor(visitorId);
									visitorExist = true;
									doneSearch = true;
									break;
								}
							}
						} else if (i == SharedData.codeArrayList.size() - 1 && inputRecordMatch == false) {
							doneSearch = true;
							doneVisitorLookUp = true;
							System.out.println("Access denied.");
							break;
						}						
					}
				}
				
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
				
				

				
				
			} else if ((role.equals("A"))) {
				System.out.println("Hi, Admin.");
				System.out.println("What would you like to do?");
				System.out.println("Add new Host: ADD");
				if (SharedData.hostArrayList.size() > 0) {
					System.out.println("Remove Host: DEL");
					System.out.println("Show all Hosts: ALLH");
				}
				if (SharedData.visitorArrayList.size() > 0) {
					System.out.println("Show all Visitors: ALLV");
				}
				if (SharedData.accessArrayList.size() > 0) {
					System.out.println("Show all Access: ALLA");
				}
				String adminAction = sc.nextLine().toUpperCase();
				if (adminAction.equals("ADD")) {
					System.out.println("Please enter the host's ID number: ");
					String hostId = sc.nextLine().toUpperCase();
					System.out.println("Please enter the host's address (e.g. 12B): ");
					String hostAddress = sc.nextLine().toUpperCase();
					SharedData.addHost(hostId, hostAddress);
				} else if (adminAction.equals("DEL") && SharedData.hostArrayList.size() > 0) {
					System.out.println("Please enter the host's ID number: ");
					String hostId = sc.nextLine().toUpperCase();
					SharedData.removeHost(hostId);
				} else if (adminAction.equals("ALLH") && SharedData.hostArrayList.size() > 0) {
					SharedData.showAllHost();
				} else if (adminAction.equals("ALLV") && SharedData.codeArrayList.size() > 0) {
					SharedData.showAllVisitor();
				} else if (adminAction.equals("ALLA") && SharedData.accessArrayList.size() > 0) {
					SharedData.showAllAccess();
				} else {
					System.out.println("Action unavailable");
				}
			
			} else {
				System.out.println("Maintenance");
			}
			
			System.out.println("Would you like to close the program?");
			System.out.println("Yes: Y");
			System.out.println("No: N");
			String endOption = sc.nextLine().toUpperCase();
			
			if (endOption.equals("Y")) {
				endProgram = true;
			} else {
				endProgram = false;
			    hostExist = false;
			    inputRecordMatch = false;
			    visitorExist = false;
			    doneSearch = false;
			    cancelCode = false;
			    doneCodeLookUp = false;
			    doneVisitorLookUp = false;
			    previousCodeFound = false;
			    hostHasCode = false;
			}
		}

	}

}


