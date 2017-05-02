package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector2;
import daointerfaces01917.DALException;
import daointerfaces01917.ProduktBatchDAO;
import dto01917.OperatoerDTO;
import dto01917.ProduktBatchDTO;

public class MySQLProduktBatchDAO implements ProduktBatchDAO {
	private Connector2 connector = new Connector2();

	@Override
	public ProduktBatchDTO getProduktBatch(int pbId) throws DALException {
		ResultSet rs;
		try {
			rs = connector.doQuery("SELECT * FROM operatoer WHERE pb_id = " + pbId + ";");
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
			connector.doUpdate(
					"INSERT INTO produktbatch(pb_id, status, recept_id) VALUES " +
					"(" + produktbatch.getPbId() + ", '" + produktbatch.getStatus() + ", '" + produktbatch.getReceptId() + "');"
				);
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}

	}

	@Override
	public void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException {
		try {
			connector.doUpdate(
					"UPDATE produktbatch SET  status = '" + produktbatch.getStatus() + "', recept_id =  '" + produktbatch.getReceptId() + 
					"' WHERE pb_id = " + produktbatch.getPbId() + ";"
			);
		} catch (Exception e) {
			throw new DALException(e.getMessage());
		}

	}

}
