package javaTimeAll;

public class Admin extends Person {
	
	
	static void handleAdminActions() {
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
	}

}
