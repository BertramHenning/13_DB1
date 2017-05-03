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
import daointerfaces01917.ProduktBatchDAO;
import dto01917.ProduktBatchDTO;

public class MySQLProduktBatchDAO implements ProduktBatchDAO {
	private Connector connector = new Connector();

	@Override
	public ProduktBatchDTO getProduktBatch(int pbId) throws DALException {
		ResultSet rs;
		try {
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("getCommands.txt")).get(1));
			stmt.setInt(1, pbId);
			rs = stmt.executeQuery();
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}
	    try {
	    	if (!rs.first()) throw new DALException("Produktbatch " + pbId + " findes ikke");
	    	return new ProduktBatchDTO (rs.getInt("pb_id"), rs.getInt("status"), rs.getInt("recept_id"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ProduktBatchDTO> getProduktBatchList() throws DALException {
		List<ProduktBatchDTO> list = new ArrayList<ProduktBatchDTO>();
		ResultSet rs;
		try {
			rs = connector.doQuery("SELECT * FROM produktbatch;");
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}
		try
		{
			while (rs.next()) 
			{
				list.add(new ProduktBatchDTO (rs.getInt("pb_id"), rs.getInt("status"), rs.getInt("recept_id")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException {
		try {
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("createCommands.txt")).get(1));
			stmt.setInt(1, produktbatch.getPbId());
			stmt.setInt(2, produktbatch.getStatus());
			stmt.setInt(3, produktbatch.getReceptId());
			stmt.executeQuery();
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}
	}

	@Override
	public void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException {
		try {
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("updateCommands.txt")).get(1));
			stmt.setInt(1, produktbatch.getPbId());
			stmt.setInt(2, produktbatch.getStatus());
			stmt.setInt(3, produktbatch.getReceptId());
			stmt.executeQuery();
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}

	}
}
