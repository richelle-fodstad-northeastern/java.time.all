package javaTimeAll;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class SharedData {
	public static ArrayList<Host> hostArrayList = new ArrayList<>(); 
	public static ArrayList<Visitor> visitorArrayList = new ArrayList<>(); 
	public static ArrayList<Code> codeArrayList = new ArrayList<>(); 
	public static ArrayList<Access> accessArrayList = new ArrayList<>();
	

	static void removeHost(String hostId) {
		for (int i = 0; i < hostArrayList.size(); i++) {
			Host hostObject = hostArrayList.get(i);
			if (hostId.equals(hostObject.id)) {
				System.out.println("Removing Host...");
				hostArrayList.remove(i);
				System.out.println("Host removed");
			} else {
				System.out.println(hostId + " not found");
			}
		}
	}
	
	static void addHost(String hostId, String hostAddress) {
		System.out.println("Creating Host...");
		hostArrayList.add(new Host(hostId, hostAddress));
		System.out.println("Host created");
	}
	
	static void addVisitor(String visitorId) {
		System.out.println("Creating visitor...");
		visitorArrayList.add(new Visitor(visitorId));
		System.out.println("Visitor created");
	}
	
	static void addCode(String code, String hostAddress, String visitorId, LocalDateTime generationDateTime, String hostId) {
		codeArrayList.add(new Code(code, hostAddress, visitorId, generationDateTime, hostId));
	}
	
	static void addAccess(String expectedAddress, String visitorId, String code, LocalDateTime accessDateTime) {
		accessArrayList.add(new Access(expectedAddress, visitorId, code, accessDateTime));
	}
	
	
	static void clearHost() {
		hostArrayList.clear();
	}
	
	static void clearVisitor() {
		visitorArrayList.clear();
	}
	
	static void clearCode() {
		codeArrayList.clear();
	}
	
	static void clearAccess() {
		accessArrayList.clear();
	}
	
	static void clearAll() {
		hostArrayList.clear();
		visitorArrayList.clear();
		codeArrayList.clear();
		accessArrayList.clear();
	}
	
	static void showAllHost() {
		for (int i = 0; i <  hostArrayList.size(); i++) {
			Host hostObject = hostArrayList.get(i);
			System.out.println("Host " + (i + 1) );
			System.out.println("id: " + hostObject.id);
			System.out.println("Address:  " + hostObject.address);
		}
	}
	
	static void showAllVisitor() {
		for (int i = 0; i <  visitorArrayList.size(); i++) {
			Visitor visitorObject = visitorArrayList.get(i);
			System.out.println("Visitor " + (i + 1));
			System.out.println("id: " + visitorObject.id);
			System.out.println("Expected Code: " + visitorObject.expectedCode);
			System.out.println("Expected Address: " + visitorObject.expectedAddress);
			System.out.println("Last access datetime: " + visitorObject.accessDateTime);
		}
	}
	
	static void showAllCode() {
		for (int i = 0; i <  codeArrayList.size(); i++) {
			Code codeObject = codeArrayList.get(i);
			System.out.println("Code " + (i + 1));
			System.out.println("Code: " + codeObject.code);
			System.out.println("Visitor id: " + codeObject.visitorId);
			System.out.println("Generation Datetime: " + codeObject.generationDateTime);
			System.out.println("Host Address: " + codeObject.hostAddress);
			System.out.println("Is Valid? " + codeObject.isValid);
			System.out.println("Is Used? " + codeObject.used);
		}
	}
	
	static void showAllAccess() {
		for (int i = 0; i <  accessArrayList.size(); i++) {
			Access accessObject = accessArrayList.get(i);
			System.out.println("Access " + (i + 1));
			System.out.println("Visitor id: " + accessObject.visitorId);
			System.out.println("Host Address: " +accessObject.hostAddress);
			System.out.println("Code: " + accessObject.code);
			System.out.println("Access Datetime: " + accessObject.accessDateTime);
		}
	}
	
	
	
	

}

