package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import entidad.Articulo;
import util.MySqlDBConexion;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ArticuloModel {
	private static Logger log = Logger.getLogger(ProveedorModel.class.getName());
	
	
	public int insertaArticulo(Articulo obj) {
		int salida = -1;
		Connection conn= null;
		PreparedStatement pstm = null;
		try {
			conn=MySqlDBConexion.getConexion();
			
			String sql = "insert into articulo values(null,'stock.png',?,?,?,?,?,curtime())";
			pstm= conn.prepareStatement(sql);
			pstm.setString(1,obj.getNombre());
			pstm.setString(2,obj.getStock());
			pstm.setString(3,obj.getDetalle());
			pstm.setInt(4,obj.getIdCategoria());
			pstm.setInt(5,obj.getIdProveedor());
		
			
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
	public int actualizarProveedor(Articulo pArticulo) {
		int salida=-1;
		Connection con=null;
		PreparedStatement pst=null;
		try {
			con=MySqlDBConexion.getConexion();
			
			String sql="update proveedor set nomar=?,stock=?,detalle=?,id_cat=?,id_prove=? where id_art=?";
			pst=con.prepareStatement(sql);
			
			pst.setString(1,pArticulo.getNombre());
			pst.setString(2,pArticulo.getStock());
			pst.setString(3,pArticulo.getDetalle());
			pst.setInt(4,pArticulo.getIdCategoria());
			pst.setInt(5,pArticulo.getIdProveedor());
			pst.setInt(6,pArticulo.getIdArticulo());
			
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
	public int elimnarProveedor(int idArticulo) {
		int salida=-1;
		Connection con=null;
		PreparedStatement pst=null;
		try {
			//obtenemos la conexion
			con=MySqlDBConexion.getConexion();
			//creamos sentencia SQL
			String sql="delete from articulo where id_art=?";
			pst=con.prepareStatement(sql);
			pst.setInt(1,idArticulo);
			
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
		public List<Articulo> listarArticulo(){
			
			ArrayList<Articulo>listado = new ArrayList<Articulo>();
			Connection con = null;
			PreparedStatement pst=null;
			ResultSet rs=null;
			
			try {
				con=MySqlDBConexion.getConexion();
				String sql="select*from articulo";
				pst=con.prepareStatement(sql);
				
				rs=pst.executeQuery();
				Articulo lArticulo=null;
				while(rs.next()) {
					lArticulo=new Articulo();
					lArticulo.setIdArticulo(rs.getInt(1));
					lArticulo.setNombre(rs.getString(3));
					lArticulo.setStock(rs.getString(4));
					lArticulo.setDetalle(rs.getString(5));
					lArticulo.setIdCategoria(rs.getInt(6));
					lArticulo.setIdProveedor(rs.getInt(7));		
					lArticulo.setFechaRegistro(rs.getDate(8));
					listado.add(lArticulo);
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
		
		public List<Articulo> consultaValores(String nombre,String stock,String detalle
				,int idCategoria,int idProveedor,String desde,String hasta ){
			ArrayList<Articulo> data= new ArrayList<Articulo>();
			Connection conn=null;
			PreparedStatement pstm=null;
			ResultSet rs=null;
			try {
				conn=MySqlDBConexion.getConexion();
				String sql="Select* from articulo "
						+"where nomar like ? "
						+"and stock like ? "
						+"and detalle like ? "
						+"and (?='' id_cat = ? ) "
						+"and (?='' id_prove = ?) "
						+"and (?='' or ?='' or " 
						+"fechaRegistro between ? and ?); ";
				pstm=conn.prepareStatement(sql);
				pstm.setString(1, "%"+nombre+"%");
				pstm.setString(2,"%"+stock+"%");
				pstm.setString(3,detalle);
				pstm.setInt(4,idCategoria);
				pstm.setInt(5,idCategoria);
				pstm.setInt(6,idProveedor);
				pstm.setInt(7,idProveedor);
				pstm.setString(10, desde);
				pstm.setString(11, hasta);
				pstm.setString(12, desde);
				pstm.setString(13, hasta);
				System.out.println("SQL.--->"+pstm);
				rs=pstm.executeQuery();
				Articulo obj = null;
				while(rs.next()) {
					obj=new Articulo();
					obj.setIdArticulo(rs.getInt("id_art"));
					obj.setNombre(rs.getString("nomar"));
					obj.setStock(rs.getString("stock"));
					obj.setDetalle(rs.getString("detalle"));
					obj.setIdCategoria(rs.getInt("id_cat"));
					obj.setIdProveedor(rs.getInt("id_prove"));				
					obj.setFechaRegistro(rs.getDate("fere"));
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
