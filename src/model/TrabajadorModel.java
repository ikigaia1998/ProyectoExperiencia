package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import entidad.Trabajador;
import util.MySqlDBConexion;

public class TrabajadorModel {
	
	private static Logger log = Logger.getLogger(TrabajadorModel.class.getName());
	
	public int insertarTrabajador (Trabajador tra) {
		
		log.info(">> Inicio >> InsertarTrabajador()");
		int salida = -1;
		
		Connection cn = null;
		PreparedStatement prep = null;
		
		try {
			cn = MySqlDBConexion.getConexion();
			
			String sql = "insert into trabajador values(null,?,?,?,?,?,curtime())";
			prep = cn.prepareStatement(sql);
			prep.setString(1, tra.getNombre());
			prep.setString(2, tra.getApellido());
			prep.setString(3, tra.getDni());
			prep.setString(4, tra.getCorreo());
			prep.setString(5, tra.getTelefono());
			
			log.info(">> SQL >>" + prep);
			
			salida = prep.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				if (cn != null) cn.close();
				if (prep != null) prep.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		log.info(">> Fin >> InsertarTrabajador()");
		return salida;
	}

	public int eliminateTrabajador (int idTrabajador) {
		
		log.info(">> Inicio >> eliminateTrabajador()");
		int salida = -1;
		
		Connection cn = null;
		PreparedStatement prep = null;
		
		try {
			cn = MySqlDBConexion.getConexion();
			
			String sql = "delete from trabajador where id_tra = ?";
			prep = cn.prepareStatement(sql);
			prep.setInt(1, idTrabajador);
			
			log.info(">> SQL >>" + prep);
			
			salida = prep.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				if (cn != null) cn.close();
				if (prep != null) prep.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		log.info(">> Fin >> eliminateTrabajador()");
		return salida;
	}
	
	
	public int ModifyTrabajador (Trabajador tra) {
		
		log.info(">> Inicio >> ModifyTrabajador()");
		int salida = -1;
		
		Connection cn = null;
		PreparedStatement prep = null;
		
		try {
			cn = MySqlDBConexion.getConexion();
			
			String sql = "update trabajador set nombre=?, apellido=?, dni=?, correo=?, telefono=? where id_tra=?";
			prep = cn.prepareStatement(sql);
			prep.setString(1, tra.getNombre());
			prep.setString(2, tra.getApellido());
			prep.setString(3, tra.getDni());
			prep.setString(4, tra.getCorreo());
			prep.setString(5, tra.getTelefono());
			prep.setInt(6,tra.getId_tra());
			
			log.info(">> SQL >>" + prep);
			
			salida = prep.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				if (cn != null) cn.close();
				if (prep != null) prep.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		log.info(">> Fin >> ModifyTrabajador()");
		return salida;
	}
	
	public List <Trabajador> listaTrabajador(){
		ArrayList <Trabajador> salida = new ArrayList <Trabajador>();
		
		Connection cn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		
		try {
			cn = MySqlDBConexion.getConexion();
			
			String sql = "select * from trabajador";
			prep = cn.prepareStatement(sql);
			
			log.info(">> SQL >>" + prep);
			
			rs = prep.executeQuery();
			Trabajador tra = null;
			while(rs.next()) {
			tra= new Trabajador();
			tra.setId_tra(rs.getInt(1));
			tra.setNombre(rs.getString(2));
			tra.setApellido(rs.getString(3));
			tra.setDni(rs.getString(4));
			tra.setCorreo(rs.getString(5));
			tra.setTelefono(rs.getString(6));
			tra.setFecha(rs.getString(7));
			salida.add(tra);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
	} finally {
		
		try {
			if (cn != null) cn.close();
			if (prep != null) prep.close();
			if (rs != null) rs.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
		return salida;
	}
		
	}
