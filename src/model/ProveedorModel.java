package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entidad.Proveedor;
import util.MySqlDBConexion;

public class ProveedorModel {

	private static Logger log = Logger.getLogger(ProveedorModel.class.getName());
	
	//Vamos a crear los metodos para el crud
	//---------------INSERTAR----------------------
	public int insertaProveedor(Proveedor obj) {
		int salida = -1;
		Connection conn= null;
		PreparedStatement pstm = null;
		try {
			conn=MySqlDBConexion.getConexion();
			
			String sql = "insert into proveedor values(null,?,?,?,?,?,?,?,curtime())";
			pstm= conn.prepareStatement(sql);
			pstm.setString(1,obj.getNombre());
			pstm.setString(2,obj.getApellido());
			pstm.setString(3,obj.getDni());
			pstm.setString(4,obj.getDireccion());
			pstm.setString(5,obj.getTelefono());
			pstm.setString(6,obj.getCorreo());
			pstm.setString(7,obj.getPais());
		
			
			log.info("sql -->"+pstm);
			salida= pstm.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			try {
				if(pstm!=null)
					pstm.close();
				if(conn!=null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();}
		}
		return salida;
	}
	//-----------------ACTUALIZAR-----------------
	public int actualizarProveedor(Proveedor pProveedor) {
		int salida=-1;
		Connection con=null;
		PreparedStatement pst=null;
		try {
			con=MySqlDBConexion.getConexion();
			
			String sql="update proveedor set nombres=?,apellidos=?,dni=?,direccion=?,telefono=?,correo=?,pais=? where idProveedor=?";
			pst=con.prepareStatement(sql);
			
			pst.setString(1,pProveedor.getNombre());
			pst.setString(2,pProveedor.getApellido());
			pst.setString(3,pProveedor.getDni());
			pst.setString(4,pProveedor.getDireccion());
			pst.setString(5,pProveedor.getTelefono());
			pst.setString(6,pProveedor.getCorreo());
			pst.setString(7,pProveedor.getPais());
			pst.setInt(8,pProveedor.getIdProveedor());
			
			log.info(">>>>"+pst);
			salida=pst.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pst !=null)pst.close();
				if(con !=null)pst.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return salida;
	}
	//--------------------------------------------
	
	//-------------------ELIMINAR-----------------
	public int elimnarProveedor(int idProveedor) {
		int salida=-1;
		Connection con=null;
		PreparedStatement pst=null;
		try {
			//obtenemos la conexion
			con=MySqlDBConexion.getConexion();
			//creamos sentencia SQL
			String sql="delete from proveedor where idProveedor=?";
			pst=con.prepareStatement(sql);
			pst.setInt(1,idProveedor);
			
			log.info(">>>>"+pst);
			//Ejecutamos sentencia SQL
			salida=pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pst !=null)pst.close();
				if(con !=null)con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return salida;
	}
	//--------------------------------------------
	
	//----------------LISTAR----------------------
	public List<Proveedor> listarProveedor(){
		ArrayList<Proveedor>listado = new ArrayList<Proveedor>();
		Connection con = null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			con=MySqlDBConexion.getConexion();
			String sql="select*from Proveedor";
			pst=con.prepareStatement(sql);
			
			rs=pst.executeQuery();
			Proveedor pProveedor=null;
			while(rs.next()) {
				pProveedor=new Proveedor();
				pProveedor.setIdProveedor(rs.getInt(1));
				pProveedor.setNombre(rs.getString(2));
				pProveedor.setApellido(rs.getString(3));
				pProveedor.setDni(rs.getString(4));
				pProveedor.setDireccion(rs.getString(5));
				pProveedor.setTelefono(rs.getString(6));
				pProveedor.setCorreo(rs.getString(7));
				pProveedor.setPais(rs.getString(8));
				pProveedor.setFechaRegistro(rs.getDate(9));
				listado.add(pProveedor);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pst!=null)pst.close();
				if(con!=null)con.close();
				if(rs!=null)rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		
		return listado;
	}
	//--------------------------------------------
	public List<Proveedor> consultaPorFecha(String desde,String hasta){
		ArrayList<Proveedor> data= new ArrayList<Proveedor>();
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs = null;
		try {
			conn=MySqlDBConexion.getConexion();
			String sql = "select*from Proveedor where fechaRegistro between ? and ?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, desde);
			pstm.setString(2,hasta);
			System.out.println("SQL-->"+pstm);
			rs= pstm.executeQuery();
			Proveedor obj = null;
			while(rs.next()) {
				obj= new Proveedor();
				obj.setIdProveedor(rs.getInt("idProveedor"));
				obj.setNombre(rs.getString("nombres"));
				obj.setApellido(rs.getString("apellidos"));
				obj.setDni(rs.getString("dni"));
				obj.setDireccion(rs.getString("direccion"));
				obj.setTelefono(rs.getString("telefono"));
				obj.setCorreo(rs.getString("correo"));
				obj.setPais(rs.getString("pais"));
				obj.setFechaRegistro(rs.getDate("fechaRegistro"));
				data.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
			if(rs!=null)rs.close();
			if(pstm!=null)pstm.close();
			if(conn!=null)conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}return data;
	}
	//--------------------------SEGUNDA CONSULTA----------------------
	public List<Proveedor> consultaValores(String nombres,String apellidos,String dni
			,String direccion,String telefono,String correo,String pais, String desde,String hasta ){
		ArrayList<Proveedor> data= new ArrayList<Proveedor>();
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			conn=MySqlDBConexion.getConexion();
			String sql="Select* from sistema_biblioteca_simple_2022_01.proveedor "
					+"where nombres like ? "
					+"and apellidos like ? "
					+"and(?='' or dni=?) "
					+"and direccion like ? "
					+"and telefono like ? "
					+"and correo like ? "
					+"and (?='' or pais like ? )"
					+"and (?='' or ?='' or " 
					+"fechaRegistro between ? and ?); ";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, "%"+nombres+"%");
			pstm.setString(2,"%"+apellidos+"%");
			pstm.setString(3,dni);
			pstm.setString(4,dni);
			pstm.setString(5, "%"+direccion+"%");
			pstm.setString(6, "%"+telefono+"%");
			pstm.setString(7, "%"+correo+"%");
			pstm.setString(8, pais);
			pstm.setString(9, "%"+pais+"%");
			pstm.setString(10, desde);
			pstm.setString(11, hasta);
			pstm.setString(12, desde);
			pstm.setString(13, hasta);
			System.out.println("SQL.--->"+pstm);
			rs=pstm.executeQuery();
			Proveedor obj = null;
			while(rs.next()) {
				obj=new Proveedor();
				obj.setIdProveedor(rs.getInt("idProveedor"));
				obj.setNombre(rs.getString("nombres"));
				obj.setApellido(rs.getString("apellidos"));
				obj.setDni(rs.getString("dni"));
				obj.setDireccion(rs.getString("direccion"));
				obj.setTelefono(rs.getString("telefono"));
				obj.setCorreo(rs.getString("Correo"));
				obj.setPais(rs.getString("Pais"));
				obj.setFechaRegistro(rs.getDate("fechaRegistro"));
				data.add(obj);
			}
			
		}catch(Exception e) {e.printStackTrace();}finally {
			try {
				if(rs!=null)	rs.close();
				if(pstm!=null)pstm.close();
				if(conn!=null)conn.close();
			} catch (Exception e2) {} 
		}return data;
		
		
	}
	
	
	
	
	
	
	
	
}
























