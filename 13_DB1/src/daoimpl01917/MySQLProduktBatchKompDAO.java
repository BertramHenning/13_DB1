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
import daointerfaces01917.ProduktBatchKompDAO;
import dto01917.ProduktBatchKompDTO;

public class MySQLProduktBatchKompDAO implements ProduktBatchKompDAO {
	private Connector connector = new Connector();
	
	@Override
	public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException {
		ResultSet rs;
		try {
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("getCommands.txt")).get(2));
			stmt.setInt(1, pbId);
			stmt.setInt(2, rbId);
			rs = stmt.executeQuery();
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}
		 try {
		    	if (!rs.first()) throw new DALException("Produktbatchkomponenten med pb_id " + pbId + "og rb_id "+ rbId + " findes ikke");
		    	return new ProduktBatchKompDTO (rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id"));
		    }
		    catch (SQLException e) {throw new DALException(e); }
	}
	


	@Override
	public void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
		try {
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("createCommands.txt")).get(2));
			stmt.setInt(1, produktbatchkomponent.getPbId());
			stmt.setInt(2, produktbatchkomponent.getRbId());
			stmt.setDouble(3, produktbatchkomponent.getTara());
			stmt.setDouble(4, produktbatchkomponent.getNetto());
			stmt.setInt(5, produktbatchkomponent.getOprId());
			stmt.executeQuery();
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}
	}

	@Override
	public void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
		try {
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("updateCommands.txt")).get(2));
			stmt.setInt(1, produktbatchkomponent.getPbId());
			stmt.setInt(2, produktbatchkomponent.getRbId());
			stmt.setDouble(3, produktbatchkomponent.getTara());
			stmt.setDouble(4, produktbatchkomponent.getNetto());
			stmt.setInt(5, produktbatchkomponent.getOprId());
			stmt.executeQuery();
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}

	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException {
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		ResultSet rs;
		try {
			rs = connector.doQuery("SELECT * FROM produktbatchkomponent;");
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}
		try
		{
			while (rs.next()) 
			{
				list.add(new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}



	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException {
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		ResultSet rs;
		try {
			rs = connector.doQuery("SELECT * FROM produktbatchkomponent WHERE pb_id = " + pbId +";");
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}
		try
		{
			while (rs.next()) 
			{
				list.add(new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

}
