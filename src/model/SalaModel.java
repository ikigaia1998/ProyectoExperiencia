package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entidad.Sala;
import util.MySqlDBConexion;

public class SalaModel {

	private static Logger log = Logger.getLogger(SalaModel.class.getName());

	public int insertaSala(Sala obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();

			String sql = "insert into sala values(null,?,?,?,?,1,curtime(),?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNumero());
			pstm.setInt(2, obj.getPiso());
			pstm.setInt(3, obj.getNumAlumnos());
			pstm.setString(4, obj.getRecursos());
			pstm.setString(5, obj.getSede());

			log.info("sql --> " + pstm);

			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}
	
	public int eliminaSala(int idSala) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//Se crea la conexión
			conn = MySqlDBConexion.getConexion();
			
			//Se prepara el SQL
			String sql = "delete from sala where idSala = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idSala);
			
			log.info(">>> "+pstm);
			
			//Ejecutamos la BD
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		return salida;
	}
	
	public int actualizaSala(Sala oSala) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//Se crea la conexión
			conn = MySqlDBConexion.getConexion();
			
			//Se prepara el SQL
			String sql = "update sala set numero=?, piso=?, numAlumnos=?, recursos=?, estado=?, sede=? where idSala=?";
			pstm = conn.prepareCall(sql);
			pstm.setString(1, oSala.getNumero());
			pstm.setInt(2, oSala.getPiso());
			pstm.setInt(3, oSala.getNumAlumnos());
			pstm.setString(4, oSala.getRecursos());
			pstm.setInt(5, oSala.getestado());
			pstm.setString(6, oSala.getSede());
			pstm.setInt(7, oSala.getIdSala());
			
			log.info(">>> " + pstm);
			
			//Ejecutamos a la base de datos
			salida = pstm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		return salida;
	}
	
	public List<Sala> listaSala(){
		ArrayList<Sala> listado = new ArrayList<Sala>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conn = MySqlDBConexion.getConexion();
			
			String sql = "select * from Sala";
			pstm = conn.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			Sala oSala = null;
			while (rs.next()) {
				oSala = new Sala();
				oSala.setIdSala(rs.getInt(1));
				oSala.setNumero(rs.getString(2));
				oSala.setPiso(rs.getInt(3));
				oSala.setNumAlumnos(rs.getInt(4));
				oSala.setRecursos(rs.getString(5));
				oSala.setestado(rs.getInt(6));
				oSala.setFechaRegistro(rs.getDate(7));
				oSala.setSede(rs.getString(8));
				listado.add(oSala);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		return listado;
	}
}