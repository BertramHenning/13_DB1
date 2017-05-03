package daoimpl01917;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import connector01917.Connector2;

import daointerfaces01917.DALException;
import daointerfaces01917.OperatoerDAO;
import dto01917.OperatoerDTO;

public class MySQLOperatoerDAO implements OperatoerDAO{
	private Connector2 connector = new Connector2();
	
	public OperatoerDTO getOperatoer(int oprId) throws DALException {
		ResultSet rs = null;
		try {
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("commands.txt")).get(0));
			stmt.setInt(1, oprId);
			System.out.println(stmt);
			rs = stmt.executeQuery();
//			rs = Connector.doQuery(Files.readAllLines(Paths.get("commands.txt")).get(0));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
	    	if (!rs.first()) throw new DALException("Operatoeren " + oprId + " findes ikke");
	    	return new OperatoerDTO (rs.getInt("opr_id"), rs.getString("opr_fornavn"), rs.getString("opr_efternavn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"));
	    }
	    catch (SQLException e) {throw new DALException(e); 
	    }
		
	}
	
	public void createOperatoer(OperatoerDTO opr) throws DALException {		
			try {
				connector.doUpdate(Files.readAllLines(Paths.get("commands.txt")).get(1));
			} catch (Exception e) {
				throw new DALException(e.getMessage());
			}
	}
	
	public void updateOperatoer(OperatoerDTO opr) throws DALException {
		try {
			connector.doUpdate(
					"UPDATE operatoer SET  opr_fornavn = '" + opr.getOprFornavn() + "', opr_efternavn = '" + opr.getOprEfternavn() + "', ini =  '" + opr.getIni() + 
					"', cpr = '" + opr.getCpr() + "', password = '" + opr.getPassword() + "' WHERE opr_id = " +
					opr.getOprId() + ";"
			);
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	}
	
	public List<OperatoerDTO> getOperatoerList() throws DALException {
		List<OperatoerDTO> list = new ArrayList<OperatoerDTO>();
		ResultSet rs;
		try {
			rs = connector.doQuery("SELECT * FROM operatoer;");
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
		try
		{
			while (rs.next()) 
			{
				list.add(new OperatoerDTO(rs.getInt("opr_id"), rs.getString("opr_fornavn"), rs.getString("opr_efternavn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}
		
		
}
	
