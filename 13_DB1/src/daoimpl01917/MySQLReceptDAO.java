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
import daointerfaces01917.ReceptDAO;
import dto01917.ReceptDTO;

public class MySQLReceptDAO implements ReceptDAO {
	private Connector connector = new Connector();
	
	@Override
	public ReceptDTO getRecept(int receptId) throws DALException {
				ResultSet rs;
				try {
					PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("getCommands.txt")).get(5));
					stmt.setInt(1, receptId);
					rs = stmt.executeQuery();
				} catch (Exception e) {
					throw new DALException(e.getMessage());
				}
			    try {
			    	if (!rs.first()) throw new DALException("recepten " + receptId + " findes ikke");
			    	return new ReceptDTO (rs.getInt("recept_id"), rs.getString("recept_navn"));
			    }
			    catch (SQLException e) {throw new DALException(e);
			    }
	}

	@Override
	public List<ReceptDTO> getReceptList() throws DALException {
		List<ReceptDTO> list = new ArrayList<ReceptDTO>();
		ResultSet rs;
		try {
			rs = connector.doQuery("SELECT * FROM recept;");
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}
		try
		{
			while (rs.next()) 
			{
				list.add(new ReceptDTO(rs.getInt("recept_id"), rs.getString("recept_navn")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createRecept(ReceptDTO recept) throws DALException {
		try {
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("createCommands.txt")).get(5));
			stmt.setInt(1, recept.getReceptId());
			stmt.setString(2, recept.getReceptNavn());
			stmt.executeQuery();
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}

	}

	@Override
	public void updateRecept(ReceptDTO recept) throws DALException {
		try {
			PreparedStatement stmt = connector.getConnection().prepareStatement(Files.readAllLines(Paths.get("updateCommands.txt")).get(5));
			stmt.setInt(1, recept.getReceptId());
			stmt.setString(2, recept.getReceptNavn());
			stmt.executeQuery();
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}

	}

}
