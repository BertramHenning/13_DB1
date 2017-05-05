package daoimpl01917;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.TransactionsDAO;


public class MySQLTransactionsDAO implements TransactionsDAO {
	private Connector connector = new Connector();

	@Override
	public void BatchMaengdeExchange1(int maengde, int pbIDfra, int pbIDtil) throws DALException {
		try {
			System.out.println(Files.readAllLines(Paths.get("transactionCommands.txt")).get(0));
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("transactionCommands.txt")).get(0));
			stmt.setInt(1, maengde);
			stmt.setInt(2, pbIDfra);
			stmt.setInt(3, pbIDtil);
	
			stmt.executeQuery();
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}

	}

}
