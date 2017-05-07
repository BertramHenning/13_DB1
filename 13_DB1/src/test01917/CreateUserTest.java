package test01917;

import java.util.Scanner;

import daoimpl01917.MySQLOperatoerDAO;
import daointerfaces01917.DALException;
import daointerfaces01917.OperatoerDAO;
import dto01917.OperatoerDTO;

public class CreateUserTest {

	public static void main(String[] args) {
		OperatoerDAO opr = new MySQLOperatoerDAO();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Lav en ny operator:");

		System.out.println("indtast ID:");
		int id = sc.nextInt();
		sc.nextLine();
		
		System.out.println("indtast fornavn:");
		String fornavn = sc.nextLine();
		
		System.out.println("indtast efternavn:");
		String efternavn = sc.nextLine();
		
		System.out.println("indtast initialer:");
		String ini = sc.nextLine();
		
		System.out.println("indtast cpr nr:");
		String cpr = sc.nextLine();
		
		System.out.println("indtast password:");
		String password = sc.nextLine();

		sc.close();
		
		OperatoerDTO newopr = new OperatoerDTO(id, fornavn, efternavn, ini, cpr, password);
			
		try {
			opr.createOperatoer(newopr);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
