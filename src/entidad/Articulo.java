
package entidad;
import java.sql.Date;

public class Articulo {
	 private int idArticulo;
	 private String nombre;
	 private String stock;
	 private String detalle;
	 private int idCategoria;
	// private String nomCategoria;
	 private int idProveedor;
	// private String nomProveedor;
	 private Date fechaRegistro;
	public int getIdArticulo() {return idArticulo;}
	public void setIdArticulo(int idArticulo) {this.idArticulo = idArticulo;}
	
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	
	public String getStock() {return stock;}
	public void setStock(String stock) {this.stock = stock;}
	
	public String getDetalle() {return detalle;}
	public void setDetalle(String detalle) {this.detalle = detalle;}
	
	public int getIdCategoria() {return idCategoria;}
	public void setIdCategoria(int idCategoria) {this.idCategoria = idCategoria;}
	
	//public String getNomCategoria() {return nomCategoria;}
	//public void setNomCategoria(String nomCategoria) {this.nomCategoria = nomCategoria;}
	
	public int getIdProveedor() {return idProveedor;}
	public void setIdProveedor(int idProveedor) {this.idProveedor = idProveedor;}
	
	//public String getNomProveedor() {return nomProveedor;}
	//public void setNomProveedor(String nomProveedor) {this.nomProveedor = nomProveedor;}
	
	public Date getFechaRegistro() {return fechaRegistro;}
	public void setFechaRegistro(Date fechaRegistro) {this.fechaRegistro = fechaRegistro;}
	 
	 
	 
	 
}
