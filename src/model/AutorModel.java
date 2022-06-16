package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entidad.Autor;
import util.MySqlDBConexion;

public class AutorModel {
	private static final Logger log = Logger.getLogger(AutorModel.class.getName());

	public int insertarAutor(Autor oAutor) {
		int salida = -1;
		Connection con = null;
		PreparedStatement pst = null;

		try {
			// Obtención de la conexión
			con = MySqlDBConexion.getConexion();

			// Creación de sentencia SQL
			String sql = "insert into autor values(null,?,?,?,curtime(),?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, oAutor.getNombres());
			pst.setString(2, oAutor.getApellidos());
			pst.setDate(3, oAutor.getFechaNacimiento());
			pst.setString(4, oAutor.getPais());
			pst.setString(5, oAutor.getGrado());

			log.info(">>>" + pst);

			// Ejecución de la sentencia SQL
			salida = pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return salida;
	}

	public int eliminarAutor(int idAutor) {
		int salida = -1;
		Connection con = null;
		PreparedStatement pst = null;

		try {
			// Obtención de la conexión
			con = MySqlDBConexion.getConexion();

			// Creación de sentencia SQL
			String sql = "delete from autor where idAutor = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, idAutor);

			log.info(">>>" + pst);

			// Ejecución de la sentencia SQL
			salida = pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return salida;
	}

	public int actualizarAutor(Autor oAutor) {
		int salida = -1;
		Connection con = null;
		PreparedStatement pst = null;

		try {
			// Obtención de la conexión
			con = MySqlDBConexion.getConexion();

			// Creación de sentencia SQL
			String sql = "update autor set nombres=?, apellidos=?, fechaNacimiento=?, pais=?, grado=? where idAutor=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, oAutor.getNombres());
			pst.setString(2, oAutor.getApellidos());
			pst.setDate(3, oAutor.getFechaNacimiento());
			pst.setString(4, oAutor.getPais());
			pst.setString(5, oAutor.getGrado());
			pst.setInt(6, oAutor.getIdAutor());

			log.info(">>>" + pst);

			// Ejecución de la sentencia SQL
			salida = pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return salida;
	}

	public List<Autor> listarAutor() {
		ArrayList<Autor> listado = new ArrayList<Autor>();

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			// Obtención de la conexión
			con = MySqlDBConexion.getConexion();

			// Creación de la sentencia SQL
			String sql = "select * from Autor";
			pst = con.prepareStatement(sql);

			// Ejecución de sentencia SQL en BD
			rs = pst.executeQuery();
			Autor oAutor = null;
			while (rs.next()) {
				oAutor = new Autor();
				oAutor.setIdAutor(rs.getInt(1));
				oAutor.setNombres(rs.getString(2));
				oAutor.setApellidos(rs.getString(3));
				oAutor.setFechaNacimiento(rs.getDate(4));
				oAutor.setFechaRegistro(rs.getDate(5));
				oAutor.setPais(rs.getString(6));
				oAutor.setGrado(rs.getString(7));
				listado.add(oAutor);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
				if (rs != null)
					rs.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return listado;
	}
	
	public List<Autor> ConsultaReporteAutor(String nombres, String apellidos, String fechaDesde, String fechaHasta, String pais, String grado){
		ArrayList<Autor> listado = new ArrayList<Autor>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			// Obtención de la conexión
			con = MySqlDBConexion.getConexion();

			// Creación de la sentencia SQL
			String sql = "SELECT * FROM autor WHERE "
					+ "(nombres LIKE ?) AND "
					+ "(apellidos LIKE ?) AND "
					+ "(?='' OR ?='' OR fechaNacimiento BETWEEN ? AND ?) AND "
					+ "(? = '' OR pais=?) AND "
					+ "(? = '' OR grado=?);";
			pst = con.prepareStatement(sql);
			pst.setString(1, "%" + nombres + "%");
			pst.setString(2, "%" + apellidos + "%");
			pst.setString(3, fechaDesde);
			pst.setString(4, fechaHasta);
			pst.setString(5, fechaDesde);
			pst.setString(6, fechaHasta);
			pst.setString(7, pais);
			pst.setString(8, pais);
			pst.setString(9, grado);
			pst.setString(10, grado);
			
			System.out.println("SQL -->" + pst);
			
			//Ejecución del QUery
			rs = pst.executeQuery();
			
			
			// Ejecución de sentencia SQL en BD
		
			Autor oAutor = null;
			while (rs.next()) {
				oAutor = new Autor();
				oAutor.setIdAutor(rs.getInt(1));
				oAutor.setNombres(rs.getString(2));
				oAutor.setApellidos(rs.getString(3));
				oAutor.setFechaNacimiento(rs.getDate(4));
				oAutor.setFechaRegistro(rs.getDate(5));
				oAutor.setPais(rs.getString(6));
				oAutor.setGrado(rs.getString(7));
				listado.add(oAutor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(con !=null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return listado;
	}
}
