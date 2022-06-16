package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


import entidad.Opcion;

import entidad.Usuario;
import util.MySqlDBConexion;

public class UsuarioModel {

	private static final Logger log = Logger.getLogger(UsuarioModel.class.getName());
	
	public Usuario valida(String login, String clave) {
		Usuario bean = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "select * from usuario where login=? and password =?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, login);
			pstm.setString(2, clave);
			log.info(">>>" + pstm);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				bean = new Usuario();
				bean.setIdUsuario(rs.getInt(1));
				bean.setNombre(rs.getString("nombre"));
				bean.setApellido(rs.getString("apellido"));
				bean.setDni(rs.getString("dni"));
				bean.setLogin(rs.getString("login"));
				bean.setPassword(rs.getString("password"));
			
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				conn.close();
				pstm.close();
			} catch (SQLException e) {
			}
		}
		return bean;
	}
	//
	public int insertaUsuario(Usuario obj) {
		int salida = -1;
		Connection conn= null;
		PreparedStatement pstm = null;
		try {
			conn=MySqlDBConexion.getConexion();
			
 ///? esto es para ver cuantos atributos de nombres vas a poner x ejemplo ? = gpstm.setString(1,obj.getNombre());
			String sql = "insert into usuario values(null,?,?,?,?,?,?,curtime(),?,?,?)";
			pstm= conn.prepareStatement(sql);
			pstm.setString(1,obj.getNombre());
			pstm.setString(2,obj.getApellido());
			pstm.setString(3,obj.getDni());
			pstm.setString(4,obj.getLogin());
			pstm.setString(5,obj.getPassword());
			pstm.setString(6,obj.getCorreo());
			pstm.setDate(7,obj.getFechaNacimiento());
			pstm.setString(8,obj.getDireccion());
			pstm.setString(9,obj.getPais());
			
			log.info("sql -->"+pstm);
			salida= pstm.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			try {
				if(pstm!=null)
					pstm.close();
				if(conn!=null)
					conn.close();
			} catch (Exception e2) {}
		}
		return salida;
	}

	public List<Opcion> obtieneOpciones(int idUsuario) {
		ArrayList<Opcion> data = new ArrayList<Opcion>();
		Opcion bean = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "select p.idOpcion, p.nombre  from opcion p inner join rol_has_opcion r on p.idOpcion = r.idOpcion inner join rol c on r.idrol = c.idrol inner join usuario_has_rol h on c.idrol = h.idRol where idUsuario = ? order by 2;";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idUsuario);
			log.info(">>>" + pstm);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				bean = new Opcion();
				bean.setIdOpcion(rs.getInt("idopcion"));
				bean.setNombre(rs.getString("nombre"));
				data.add(bean);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				conn.close();
				pstm.close();
			} catch (SQLException e) {
			}
		}
		return data;
	}
////////consulta
	public List<Usuario> listaUsuarioNombreLike(String filtro){
		ArrayList<Usuario> salida =new ArrayList<Usuario>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//recepciona data
		ResultSet rs = null;
		
		try {
			//1 se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 se prepara el sql 
			String sql = "SELECT * FROM usuario where nombre like ?; ";
		 pstm = conn.prepareStatement(sql);
		 pstm.setString(1, filtro +"%");
		 
		 //siem mandar log
		 log.info(">>> " + pstm);
		 
		 //se ejecuta el sql en la base de datos
		 rs = pstm.executeQuery();
		 Usuario obj = null;
		 
		 while(rs.next()) {
			 obj = new Usuario();
			 obj.setIdUsuario(rs.getInt(1));
			 obj.setNombre(rs.getString(2));
			 obj.setApellido(rs.getString(3));
			 obj.setDni(rs.getString(4));
			 obj.setLogin(rs.getString(5));
			 obj.setPassword(rs.getString(6));
			 obj.setCorreo(rs.getString(7));
			 obj.setFechaNacimiento(rs.getDate(8));
			 obj.setDireccion(rs.getString(9));
			 obj.setPais(rs.getString(10));
			 salida.add(obj);
			 
		 }
		 
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if (rs !=null) rs.close();
				if (pstm !=null) pstm.close();
				if (conn !=null) conn.close();
			} catch (Exception e2) {    }	
		}
		return salida;
		}
/////////////////////////////////cruddddddd
	public int actualizaUsuario(Usuario obj) {
		int actualizados = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "update usuario set nombre=?, apellido=?, dni=?, login=?, password=?, correo=?, fechaNacimiento=?, direccion=?, pais =? where idUsuario=?"; 
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getApellido());
			pstm.setString(3, obj.getDni());
			pstm.setString(4, obj.getLogin());
			pstm.setString(5, obj.getPassword());
			pstm.setString(6, obj.getCorreo());
			pstm.setDate(7, obj.getFechaNacimiento());
			pstm.setString(8, obj.getDireccion());
			pstm.setString(9, obj.getPais());
			pstm.setInt(10, obj.getIdUsuario());
			
			log.info(">>> " + pstm);
			
			actualizados = pstm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return actualizados;
	}
	public int eliminaUsuario(int idUsuario) {
		int eliminados = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Se crea la conexión
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "delete from usuario where idUsuario = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idUsuario);
			
			log.info(">>> " + pstm);
			//3 Ejecutamos a la base de datos
			//Retorna la cantidad de registrados en salida
			eliminados = pstm.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return eliminados;
	}
///lista todos
	public List<Usuario> listaUsuario(){
		ArrayList<Usuario> data = new ArrayList<Usuario>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//recepciona data
		ResultSet rs = null;
		
		try {
			//1 se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 se prepara el sql 
			String sql = "select * from usuario";
		 pstm = conn.prepareStatement(sql);
		 
		 //siem mandar log
		 log.info(">>> " + pstm);
		 
		 //se ejecuta el sql en la base de datos
		 rs = pstm.executeQuery();
		 
		 Usuario obj = null;
		 
		 while(rs.next()) {
			 obj = new Usuario();
			 obj.setIdUsuario(rs.getInt(1));
			 obj.setNombre(rs.getString(2));
			 obj.setApellido(rs.getString(3));
			 obj.setDni(rs.getString(4));
			 obj.setLogin(rs.getString(5));
			 obj.setPassword(rs.getString(6));
			 obj.setCorreo(rs.getString(7));
			 obj.setFechaNacimiento(rs.getDate(9));
			 obj.setDireccion(rs.getString(10));
			 obj.setPais(rs.getString(11));
			 System.out.println("********* usuario" + obj);
			 data.add(obj);
		 }
		 
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if (rs !=null) rs.close();
				if (pstm !=null) pstm.close();
				if (conn !=null) conn.close();
			} catch (SQLException e) {  
				e.printStackTrace();
			}	
		}
		System.out.println("*********** data list -> " + data);
		return data;
		}
	
	public List<Usuario> consultaPorNombreDNIFecha(String nombre,String apellido,String dni , String desde, String hasta){
		ArrayList<Usuario> salida =new ArrayList<Usuario>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//recepciona data
		ResultSet rs = null;
		
		try {
			//1 se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 se prepara el sql 
			String sql = "SELECT * FROM sistema_biblioteca_simple_2022_01.usuario "
					+ " WHERE ( nombre LIKE ?) and ( apellido LIKE ?) and "
					+ " (? =  '' or dni = ?) "
					+ "and (? = '' or ? = '' or "
					+ "fechaNacimiento between ? and ? ); ";
		 pstm = conn.prepareStatement(sql);
		 pstm.setString(1,  "%"+nombre+"%");
		 pstm.setString(2,  "%"+apellido+"%");
		 pstm.setString(3, dni);
		 pstm.setString(4, dni);
		 pstm.setString(5, desde);
		 pstm.setString(6, hasta);
		 pstm.setString(7, desde);
		 pstm.setString(8, hasta);
		 
			System.out.println("SQL --> " + pstm);
		 
		 //se ejecuta el sql en la base de datos
		 rs = pstm.executeQuery();
		 Usuario obj = null;
		 
		 while(rs.next()) {
			 obj = new Usuario();
			 obj = new Usuario();
			 obj.setIdUsuario(rs.getInt(1));
			 obj.setNombre(rs.getString(2));
			 obj.setApellido(rs.getString(3));
			 obj.setDni(rs.getString(4));
			 obj.setLogin(rs.getString(5));
			 obj.setPassword(rs.getString(6));
			 obj.setCorreo(rs.getString(7));
			 obj.setFechaNacimiento(rs.getDate(9));
			 obj.setDireccion(rs.getString(10));
			 obj.setPais(rs.getString(11));
			 salida.add(obj);
			 
		 }
		 
		 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if (rs !=null) rs.close();
				if (pstm !=null) pstm.close();
				if (conn !=null) conn.close();
			} catch (Exception e2) {    }	
		}
		return salida;
		}
	
}
