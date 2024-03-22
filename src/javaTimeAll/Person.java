package javaTimeAll;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

abstract class Person {
	String id;
	static Scanner sc = new Scanner(System.in);
	//StringBuilder sb = new StringBuilder();
	LocalDateTime dateTime = LocalDateTime.now();
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	static String input() {
		return sc.nextLine();
	}
}
