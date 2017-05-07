package daoimpl01917;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import connector01917.Connector;

import daointerfaces01917.DALException;
import daointerfaces01917.OperatoerDAO;
import dto01917.OperatoerDTO;

public class MySQLOperatoerDAO implements OperatoerDAO{
	private Connector connector = new Connector();
	
	public OperatoerDTO getOperatoer(int oprId) throws DALException {
		ResultSet rs = null;
		try {
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("getCommands.txt")).get(0));
			stmt.setInt(1, oprId);
			rs = stmt.executeQuery();
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}
	    try {
	    	if (!rs.first()) throw new DALException("Operatoeren " + oprId + " findes ikke");
	    	return new OperatoerDTO (rs.getInt("opr_id"), rs.getString("opr_fornavn"), rs.getString("opr_efternavn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"));
	    }
	    catch (SQLException e) {
	    	throw new DALException(e); 
	    }
		
	}
	
	public void createOperatoer(OperatoerDTO opr) throws DALException {		
			try {
				PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("createCommands.txt")).get(0));
				stmt.setInt(1, opr.getOprId());
				stmt.setString(2, opr.getOprFornavn());
				stmt.setString(3, opr.getOprEfternavn());
				stmt.setString(4, opr.getIni());
				stmt.setString(5, opr.getCpr());
				stmt.setString(6, opr.getPassword());
				stmt.executeQuery();
			} catch (Exception e) {
				throw new DALException(e.getMessage());
			}
	}
	
	public void updateOperatoer(OperatoerDTO opr) throws DALException {
		
		try {
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("updateCommands.txt")).get(0));
			stmt.setInt(1, opr.getOprId());
			stmt.setString(2, opr.getOprFornavn());
			stmt.setString(3, opr.getOprEfternavn());
			stmt.setString(4, opr.getIni());
			stmt.setString(5, opr.getCpr());
			stmt.setString(6, opr.getPassword());
			stmt.executeQuery();
		} catch (Exception e) {
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

	@Override
	public String getCPR(int oprdId) throws DALException {
		
		return null;
	}

	
	
}
	
