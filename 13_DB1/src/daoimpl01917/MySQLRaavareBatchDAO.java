package daoimpl01917;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.RaavareBatchDAO;
import dto01917.RaavareBatchDTO;

public class MySQLRaavareBatchDAO implements RaavareBatchDAO {
	private Connector connector = new Connector();
	
	@Override
	public RaavareBatchDTO getRaavareBatch(int rbId) throws DALException {
		ResultSet rs;
		try {
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("getCommands.txt")).get(3));
			stmt.setInt(1, rbId);
			rs = stmt.executeQuery();
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}
	    try {
	    	if (!rs.first()) throw new DALException("Raavarebatchet " + rbId + " findes ikke");
	    	return new RaavareBatchDTO (rs.getInt("rb_id"), rs.getInt("raavare_id"), rs.getInt("maengde"));
	    }
	    
	    catch (SQLException e) {throw new DALException(e);} 
	}

	@Override
	public List<RaavareBatchDTO> getRaavareBatchList() throws DALException {
		List<RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();
		ResultSet rs;
		try {
			rs = connector.doQuery("SELECT * FROM raavarebatch;");
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}
		try
		{
			while (rs.next()) 
			{
				list.add(new RaavareBatchDTO(rs.getInt("rb_id"), rs.getInt("raavare_id"), rs.getInt("maengde")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public List<RaavareBatchDTO> getRaavareBatchList(int raavareId) throws DALException {
		List<RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();
		ResultSet rs;
		try {
			rs = connector.doQuery("SELECT * FROM raavarebatch WHERE raavare_id = " + raavareId +";");
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}
		try
		{
			while (rs.next()) 
			{
				list.add(new RaavareBatchDTO(rs.getInt("rb_id"), rs.getInt("raavare_id"), rs.getInt("maengde")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
		try {
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("createCommands.txt")).get(3));
			stmt.setInt(1, raavarebatch.getRbId());
			stmt.setInt(2, raavarebatch.getRaavareId());
			stmt.setDouble(3, raavarebatch.getMaengde());
			stmt.executeQuery();
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}
	}

	@Override
	public void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException {
		try {
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("updateCommands.txt")).get(3));
			stmt.setInt(1, raavarebatch.getRbId());
			stmt.setInt(2, raavarebatch.getRaavareId());
			stmt.setDouble(3, raavarebatch.getMaengde());
			stmt.executeQuery();
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}

	}

	@Override
	public double getMaengde(int rbId) throws DALException {
		ResultSet rs;
		try {
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("functions.txt")).get(3));
			stmt.setInt(1, rbId);
			rs = stmt.executeQuery();
			rs.next();
			return rs.getDouble(1); 
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}
	}

}
