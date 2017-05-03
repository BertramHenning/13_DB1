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
import daointerfaces01917.ReceptKompDAO;
import dto01917.ReceptKompDTO;

public class MySQLReceptKompDTO implements ReceptKompDAO {
	private Connector connector = new Connector();
	
	@Override
	public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException {
		ResultSet rs;
		try {
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("getCommands.txt")).get(2));
			stmt.setInt(1, receptId);
			stmt.setInt(2, raavareId);
			rs = stmt.executeQuery();
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}
	    try {
	    	if (!rs.first()) throw new DALException("Receptkomponenten med  " + receptId + " og " + raavareId);
	    	return new ReceptKompDTO (rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance"));
	    }
	    catch (SQLException e) {throw new DALException(e);
	    }
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException {
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		ResultSet rs;
		try {
			rs = connector.doQuery("SELECT * FROM receptkomponent WHERE recept_ id = " + receptId + ";");
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptKompDTO (rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList() throws DALException {
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		ResultSet rs;
		try {
			rs = connector.doQuery("SELECT * FROM receptkomponent;");
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptKompDTO (rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"), rs.getDouble("tolerance")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
		try {
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("createCommands.txt")).get(6));
			stmt.setInt(1, receptkomponent.getReceptId());
			stmt.setInt(2, receptkomponent.getRaavareId());
			stmt.setDouble(3, receptkomponent.getNomNetto());
			stmt.setDouble(4, receptkomponent.getTolerance());
			stmt.executeQuery();
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}

	}

	@Override
	public void updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
		try {
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("updateCommands.txt")).get(6));
			stmt.setInt(1, receptkomponent.getReceptId());
			stmt.setInt(2, receptkomponent.getRaavareId());
			stmt.setDouble(3, receptkomponent.getNomNetto());
			stmt.setDouble(4, receptkomponent.getTolerance());
			stmt.executeQuery();
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}

	}

}
