package test01917;

import daoimpl01917.MySQLOperatoerDAO;
import daoimpl01917.MySQLRaavareBatchDAO;
import daoimpl01917.MySQLTransactionsDAO;
import daointerfaces01917.DALException;
import dto01917.OperatoerDTO;


public class Main {
	public static void main(String[] args) {
		
		System.out.println("transactions");
		MySQLRaavareBatchDAO raavare = new MySQLRaavareBatchDAO();
		try {
			System.out.println("råvare 1: " + raavare.getRaavareBatch(1));
			System.out.println("råvare 1: " + raavare.getRaavareBatch(8));
		} catch (DALException e1) {
			e1.printStackTrace();
		}
		
		MySQLTransactionsDAO trans = new MySQLTransactionsDAO();
		try {
			trans.BatchMaengdeExchange1(100, 1, 8);
		} catch (DALException e2) {
			e2.printStackTrace();
		}
		
		try {
			System.out.println("råvare 1: " + raavare.getRaavareBatch(1));
			System.out.println("råvare 1: " + raavare.getRaavareBatch(8));
		} catch (DALException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("Operatoer nummer 3:");
		MySQLOperatoerDAO opr = new MySQLOperatoerDAO();
		try { System.out.println(opr.getOperatoer(3)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Indsaettelse af ny operatoer med opr_id =  11");
		OperatoerDTO oprDTO = new OperatoerDTO(11,"Hej", "Juan","DJ","000000-0000","iloveyou");
		try { opr.createOperatoer(oprDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }	
		
		System.out.println("Operatoer nummer 4:");
		try { System.out.println(opr.getOperatoer(4)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Opdatering af initialer for operatoer nummer 4");
		oprDTO.setIni("DoJu");
		try { opr.updateOperatoer(oprDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Operatoer nummer 4:");
		try { System.out.println(opr.getOperatoer(4)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Alle operatoerer:");
		try { System.out.println(opr.getOperatoerList()); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Operatoer nummer 5:");
		try { System.out.println(opr.getOperatoer(5)); }
		catch (DALException e) { System.out.println(e.getMessage()); }		
		
	}
}
