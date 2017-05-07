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
import daointerfaces01917.RaavareDAO;
import dto01917.RaavareDTO;

public class MySQLRaavareDAO implements RaavareDAO {
	private Connector connector = new Connector();
	
	@Override
	public RaavareDTO getRaavare(int raavareId) throws DALException {
		ResultSet rs;
		try {
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("getCommands.txt")).get(4));
			stmt.setInt(1, raavareId);
			rs = stmt.executeQuery();
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}
		 try {
		    	if (!rs.first()) throw new DALException("Raavaren med RaavareId " + raavareId + " findes ikke");
		    	return new RaavareDTO (rs.getInt("raavare_id"), rs.getString("raavare_navn"), rs.getString("leverandoer"));
		    }
		    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<RaavareDTO> getRaavareList() throws DALException {
		List<RaavareDTO> list = new ArrayList<RaavareDTO>();
		ResultSet rs;
		try {
			rs = connector.doQuery("SELECT * FROM raavare;");
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}
		try
		{
			while (rs.next()) 
			{
				list.add(new RaavareDTO(rs.getInt("raavare_id"), rs.getString("raavare_navn"), rs.getString("leverandoer")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createRaavare(RaavareDTO raavare) throws DALException {
		try {
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("createCommands.txt")).get(4));
			stmt.setInt(1, raavare.getRaavareId());
			stmt.setString(2, raavare.getRaavareNavn());
			stmt.setString(3, raavare.getLeverandoer());
			stmt.executeQuery();
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}

	}

	@Override
	public void updateRaavare(RaavareDTO raavare) throws DALException {
		try {
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("updateCommands.txt")).get(4));
			stmt.setInt(1, raavare.getRaavareId());
			stmt.setString(2, raavare.getRaavareNavn());
			stmt.setString(3, raavare.getLeverandoer());
			stmt.executeQuery();
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}

	}

	@Override
	public String getRaavareNavn(int raavareId) throws DALException {
		ResultSet rs;
		try {
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("functions.txt")).get(2));
			stmt.setInt(1, raavareId);
			rs = stmt.executeQuery();
			rs.next();
			return rs.getString(1); 
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}
	}

}
