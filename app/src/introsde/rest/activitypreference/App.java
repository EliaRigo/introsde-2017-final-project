package introsde.rest.activitypreference;

import java.util.Scanner;

public class App {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		while(true) {
			welcome();
			int i = sc.nextInt();
			switch(i) {
				case 1: login(); break;
				case 2: registration(); break;
				case 0: exit(); break;
				default: welcome(); break;
			}
			System.out.println(i);
		}
	}
	
	public static void exit() {
		sc.close();
		System.exit(0);
	}
	
	public static void welcome() {
		System.out.println("WELCOME TO TRENTO ACTIVITY PREFERENCE");
		System.out.println();
		System.out.println("Select your choose: ");
		System.out.println();
		System.out.println("1. Login");
		System.out.println("2. Registration");
		System.out.println("0. Exit");
		System.out.println();
		System.out.print("> ");
	}
	
	public static void login() {
		System.out.println();
		System.out.println("LOGIN ");
		System.out.println();
	}
	
	public static void registration() {
		System.out.println();
		System.out.println("REGISTRATION ");
		System.out.println();
	}
}
