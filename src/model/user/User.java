package model.user;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;
import java.util.Scanner;

public class User {
	private String usrName, pwd, name, email, address;
	private String fileName = "./Data/users.txt";
	private String tmpFile= "./Data/temp.txt";
	Path usrPath = Paths.get("./Data/users.txt");
	Path tmpPath = Paths.get("./Data/temp.txt");

	// constructor when creating a new user
	public User(String usrName, String name, String email, String address) {
		this.usrName = generateUser();
		this.pwd = generatePwd();
		this.name = name;
		this.email = email;
		this.address = address;

	}
	
	private String generateUser(){
		int index = generateUserIndex();
		return "pent"+(index+1);
	}
	
	//get next generated username index
	private int generateUserIndex(){
		int n=1;
		int index=0;
		String last = getLastUsrInFile();
		try{
			while(true){
				index =Integer.parseInt(last.substring(last.length()-n, last.length()));
				n++;
			}
		}catch( NumberFormatException e){
			return index;	
		}
	}
	
	// returns true if finds user in file or false otherwise
		public String getLastUsrInFile() {
			String[] tokens = new String[5];
			String usr="";
			try (Scanner sc = new Scanner(new File(fileName));) {
				while (sc.hasNext()) {
					String line = sc.nextLine();
					tokens = line.split(":", 5);
					usr = tokens[0];
				}
				return usr;
			} catch (FileNotFoundException f) {
				System.out.println("File not found");
			}
			return "";
		}
	// constructor for existing user
	public User(String usrName, char[] pwd) {
		this.usrName = usrName;
		this.pwd = String.valueOf(pwd);
	}

	// generate first password
	private String generatePwd() {
		String pwd = "";
		Random r = new Random();
		String chars = "qwertyuiopasdfghjklzxcvbnm!@#$%&";
		for (int i = 0; i < 6; i++) {
			pwd += chars.charAt(r.nextInt(chars.length()));
		}
		return pwd;
	}

	// if the user does not exists, insert it into users file
	public boolean addUsr() {
		if (!isUsrInFile()){
			appendUsr();
			return true;
		}
		else
			return false;
	}

	// returns true if finds user in file or false otherwise
	public boolean isUsrInFile() {
		String[] tokens = new String[5];
		try (Scanner sc = new Scanner(new File(fileName));) {
			while (sc.hasNext()) {
				String line = sc.nextLine();
				tokens = line.split(":", 5);
				if (tokens[0].equals(usrName))
					return true;
			}
		} catch (FileNotFoundException f) {
			System.out.println("File not found");
		}
		return false;
	}

	// appends a new user to file users
	private void appendUsr() {
		try (PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter(
				fileName,true)));) {
			p.println(this.toString());
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}
	}

	// returns true if finds user in file or false otherwise
	public boolean isPwdCorrect() {
		String[] tokens = new String[5];
		try (Scanner sc = new Scanner(new File(fileName));) {
			while (sc.hasNext()) {
				String line = sc.nextLine();
				tokens = line.split(":", 5);
				if (tokens[0].equals(usrName) && tokens[1].equals(pwd)){
					this.name=tokens[2];
					this.email=tokens[3];
					this.address=tokens[4];
					return true;
				}
					
			}
		} catch (FileNotFoundException f) {
			System.out.println("File not found");
		}
		return false;
	}

	// update the password
	public boolean updatePwd( char[] newPwd) {
		this.pwd = String.valueOf(newPwd);
		try {
			removeUsr();
			appendUsr();
			return true;
		} catch (IOException e) {
			return false;
		}
		
	}
	
	//creates a copy of user file in a temp file skipping the line
	// of the user in question
	// and calls a method to overwrite user file with the temp file 
	private void removeUsr() throws IOException{
		String[] tokens = new String[5];
		try (Scanner sc = new Scanner(new File(fileName));
				PrintWriter p = new PrintWriter(new BufferedWriter(
						new FileWriter(tmpFile)));) {
			while (sc.hasNext()) {
				String line = sc.nextLine();
				tokens = line.split(":", 5);
				if (!tokens[0].equals(usrName))
					p.println(line);
				else{
					this.name=tokens[2];
					this.email=tokens[3];
					this.address=tokens[4];
				}
					
			}	
		} catch (FileNotFoundException f) {
			System.out.println("File not found");
		}
		overWriteFile();
	}

	// overwrites the user files with the temp file 
	private void overWriteFile() throws IOException{
		Files.move(tmpPath, usrPath, StandardCopyOption.REPLACE_EXISTING);
	}
	
	// returns instance variables in a compound string
	public String toString() {
		return usrName + ":" + pwd + ":" + name + ":" + email + ":" + address;
	}
	
	//accessors
	public String getUsrName(){
		return usrName;
	}
	public String getPwd(){
		return pwd;
	}
	public String getName(){
		return name;
	}
}
