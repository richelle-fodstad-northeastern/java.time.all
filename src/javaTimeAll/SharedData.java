package javaTimeAll;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;




public class SharedData {
	public static ArrayList<Host> hostArrayList = new ArrayList<>(); 
	public static ArrayList<Visitor> visitorArrayList = new ArrayList<>(); 
	public static ArrayList<Code> codeArrayList = new ArrayList<>(); 
	public static ArrayList<Access> accessArrayList = new ArrayList<>();
	
	
	//initialize hostArrayList,visitorArrayList,codeArrayList,accessArrayList
	static void initializeHost() {
		String hostId;
		String address;
		ArrayList<String> hostArrayListStrings = readFromFile("files/hostFile.txt");
		for (int i=0;i<hostArrayListStrings.size();i++) {
			hostId = hostArrayListStrings.get(i).split(",")[0].split("::")[1];
			address = hostArrayListStrings.get(i).split(",")[1].split("::")[1];    
			addHost(hostId,address);
		}
	}
	
	
	static void initializeVisitor() {
		String visitorId;
		ArrayList<String> hostArrayListStrings = readFromFile("files/visitorFile.txt");
		for (int i=0;i<hostArrayListStrings.size();i++) {
			visitorId = hostArrayListStrings.get(i).split(",")[0].split("::")[1];
			addVisitor(visitorId);
		}
	}
	
	static void initializeCode() {
		String code;
		boolean isValid;
		String hostAddress;
		String visitorId;
		LocalDateTime generationDateTime;
		String hostId;
		boolean used;
		ArrayList<String> hostArrayListStrings = readFromFile("files/codeFile.txt");
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
		for (int i=0;i<hostArrayListStrings.size();i++) {
			code = hostArrayListStrings.get(i).split(",")[0].split("::")[1];
			isValid = Boolean.parseBoolean(hostArrayListStrings.get(i).split(",")[1].split("::")[1]);
			hostAddress = hostArrayListStrings.get(i).split(",")[2].split("::")[1];  
			visitorId = hostArrayListStrings.get(i).split(",")[3].split("::")[1];  
			generationDateTime =LocalDateTime.parse(hostArrayListStrings.get(i).split(",")[4].split("::")[1],dateTimeFormatter);
			hostId = hostArrayListStrings.get(i).split(",")[5].split("::")[1];
			used = Boolean.parseBoolean(hostArrayListStrings.get(i).split(",")[6].split("::")[1]);
			addCode(code,isValid,hostAddress,visitorId,generationDateTime,hostId,used);
		}
	}
	
    private static ArrayList<String> readFromFile(String filePath) {
			   ArrayList<String> newArrayList = new ArrayList<>();
			   try {	    
					//FileNotFoundException，need try		  
				File file = new File(filePath);
				Scanner fileScanner = new Scanner(file);
				System.out.println("Reading contents from the file:" + filePath);

				while (fileScanner.hasNextLine()) {
					newArrayList.add(fileScanner.nextLine());
				    }
				fileScanner.close();
				
			   } catch (FileNotFoundException c) {
				    System.err.println("File not found: " + c.getMessage());
			   }
			   return newArrayList;
    }

	
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
	
	static void addCode(String code, boolean isValid, String hostAddress, String visitorId, LocalDateTime generationDateTime, String hostId, boolean used) {
		codeArrayList.add(new Code(code, isValid, hostAddress, visitorId, generationDateTime, hostId, used));
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
	
	
	
	static void saveHostFile() {
		ArrayList<String> hostArrayListStrings = new ArrayList<String>();
		for (int i=0;i<hostArrayList.size();i++) {
			hostArrayListStrings.add("hostId::" + hostArrayList.get(i).id + 
					",address::" + hostArrayList.get(i).address);    
		}
		writeToFile(hostArrayListStrings, "files/hostFile.txt");
	}
	
	
	static void saveVisitorFile() {
		ArrayList<String> visitorArrayListStrings = new ArrayList<String>();
		for (int i=0;i<visitorArrayList.size();i++) {
			visitorArrayListStrings.add("visitorId::" + visitorArrayList.get(i).id);    
		}
		writeToFile(visitorArrayListStrings, "files/visitorFile.txt");
	}


	static void saveCodeFile() {
		ArrayList<String> codeArrayListStrings = new ArrayList<String>();
		for (int i=0;i<codeArrayList.size();i++) {
			codeArrayListStrings.add("code::" + codeArrayList.get(i).code +
					",isValid::" + codeArrayList.get(i).isValid +
					",hostAddress::" + codeArrayList.get(i).hostAddress +
					",visitorId::" + codeArrayList.get(i).visitorId +
					",generationDateTime::" + codeArrayList.get(i).generationDateTime +
					",hostId::" + codeArrayList.get(i).hostId +
					",used::" + codeArrayList.get(i).used);    
		}
		writeToFile(codeArrayListStrings, "files/codeFile.txt");
		
	}
	
	
	public static void writeToFile(ArrayList<String> arrayList, String filePath) {	
		try {
		    PrintWriter writer = new PrintWriter(filePath);
		    for (int i = 0; i <= arrayList.size()-1; i++) {
		        writer.write(arrayList.get(i) + "\n");
		    }
		    writer.close();
		    System.out.println("have been written to " + filePath);
		} catch (IOException e) {
		    System.err.println("Error writing to " + filePath + "： " + e.getMessage());
		}
	}
    

	
	
}


