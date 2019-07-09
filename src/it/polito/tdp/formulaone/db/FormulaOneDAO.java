package it.polito.tdp.formulaone.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.formulaone.model.Season;

public class FormulaOneDAO {

	public List<Season> getAllSeasons() {
		String sql = "SELECT year, url FROM seasons ORDER BY year";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			List<Season> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Season(rs.getInt("year"), rs.getString("url")));
			}
			conn.close();
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Integer> getGareYear(Integer anno){
		String sql = "select distinct r.raceId as id from races r where r.year = ? ";
		List<Integer> result = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();
		
			while (rs.next()) {
				result.add(rs.getInt("id"));
			}
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Integer getPeso(Integer id1, Integer id2) {
		String sql = "select count(distinct(d1.driverId)) as peso " + 
				"from driverstandings d1, driverstandings d2, results r1, results r2 " + 
				"where d1.raceId = ? and d2.raceId = ? " + 
				"and d1.raceId = r1.raceId and d2.raceId = r2.raceId " + 
				"and r1.statusid = 1 and r2.statusid = 1 " + 
				"and d1.driverId = d2.driverId " ;
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id1);
			st.setInt(2, id2);
			ResultSet rs = st.executeQuery();
		
			if (rs.next()) {
				Integer peso = rs.getInt("peso");
				conn.close();
				return peso;
			}
			conn.close();
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
