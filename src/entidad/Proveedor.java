package entidad;

import java.sql.Date;

public class Proveedor {
  private int idProveedor ;
  private String nombre;
  private String apellido;
  private String dni;
  private String direccion;
  private String telefono;
  private String correo;
  private String pais;
  private Date fechaRegistro;
  
  
public int getIdProveedor() {
	return idProveedor;
}
public void setIdProveedor(int idProveedor) {
	this.idProveedor = idProveedor;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getApellido() {
	return apellido;
}
public void setApellido(String apellido) {
	this.apellido = apellido;
}
public String getDni() {
	return dni;
}
public void setDni(String dni) {
	this.dni = dni;
}
public String getDireccion() {
	return direccion;
}
public void setDireccion(String direccion) {
	this.direccion = direccion;
}
public String getTelefono() {
	return telefono;
}
public void setTelefono(String telefono) {
	this.telefono = telefono;
}
public String getCorreo() {
	return correo;
}
public void setCorreo(String correo) {
	this.correo = correo;
}
public String getPais() {
	return pais;
}
public void setPais(String pais) {
	this.pais = pais;
}
public Date getFechaRegistro() {
	return fechaRegistro;
}
public void setFechaRegistro(Date fechaRegistro) {
	this.fechaRegistro = fechaRegistro;
}
  
  
}
