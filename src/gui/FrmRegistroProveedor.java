package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import entidad.Proveedor;
import model.ProveedorModel;
import util.Validaciones;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class FrmRegistroProveedor extends JInternalFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblTitulo;
	private JLabel lblNombres;
	private JTextField txtNombres;
	private JLabel lblApellidos;
	private JTextField txtApellidos;
	private JLabel lblDni;
	private JTextField txtDni;
	private JLabel lblDireccion;
	private JTextField txtDireccion;
	private JLabel lblTelefono;
	private JTextField txtTelefono;
	private JLabel lblCorreo;
	private JTextField txtCorreo;
	private JLabel lblPais;
	private JButton btnNewButton;
	private JComboBox<String> cboPais;
	

	public FrmRegistroProveedor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Proveedor");
		setBounds(100, 100, 443, 436);
		getContentPane().setLayout(null);
		
		lblTitulo = new JLabel("Registro de Proveedor");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitulo.setBounds(86, 11, 194, 35);
		getContentPane().add(lblTitulo);
		
		lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(49, 110, 72, 14);
		getContentPane().add(lblNombres);
		
		txtNombres = new JTextField();
		txtNombres.setColumns(10);
		txtNombres.setBounds(177, 107, 144, 20);
		getContentPane().add(txtNombres);
		
		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(49, 138, 72, 14);
		getContentPane().add(lblApellidos);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(177, 132, 144, 20);
		getContentPane().add(txtApellidos);
		
		lblDni = new JLabel("Dni");
		lblDni.setBounds(49, 166, 72, 14);
		getContentPane().add(lblDni);
		
		txtDni = new JTextField();
		txtDni.addKeyListener(this);
		txtDni.setColumns(10);
		txtDni.setBounds(177, 160, 144, 20);
		getContentPane().add(txtDni);
		
		lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(49, 194, 72, 14);
		getContentPane().add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(177, 191, 144, 20);
		getContentPane().add(txtDireccion);
		
		lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(49, 222, 72, 14);
		getContentPane().add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.addKeyListener(this);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(177, 219, 144, 20);
		getContentPane().add(txtTelefono);
		
		lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(49, 250, 72, 14);
		getContentPane().add(lblCorreo);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(177, 247, 144, 20);
		getContentPane().add(txtCorreo);
		
		lblPais = new JLabel("Pais");
		lblPais.setBounds(49, 278, 72, 14);
		getContentPane().add(lblPais);
		
		cboPais= new JComboBox<String>();
		cboPais.addItem("Seleccione:");
		cboPais.addItem("Peru");
		cboPais.addItem("Ecuador");
		cboPais.addItem("Chile");
		cboPais.addItem("Colombia");
		cboPais.setBounds(177, 274, 144, 22);
		getContentPane().add(cboPais);
				
		
		btnNewButton = new JButton("Enviar");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(202, 306, 89, 23);
		getContentPane().add(btnNewButton);
	}
	
	public void mensaje(String msje) {
		JOptionPane.showMessageDialog(this, msje);
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			actionPerformedBtnNewButton(e);
		}
	}
	protected void actionPerformedBtnNewButton(ActionEvent e) {
		String nombre = txtNombres.getText();
		String apellido= txtApellidos.getText();
		String dni=txtDni.getText();
		String direccion= txtDireccion.getText();
		String telefono= txtTelefono.getText();
		String correo = txtCorreo.getText();
		String pais = cboPais.getSelectedItem().toString();
		
		if(!nombre.matches(Validaciones.TEXTO)) {
			mensaje("El nombre debe tener entre 3 a 30 caracteres");
		}else if (!apellido.matches(Validaciones.TEXTO)) {
			mensaje("El apellido debe tener entre 3 a 30 caracteres");
		}else if(!dni.matches(Validaciones.DNI)) {
			mensaje("El DNI debe tener entre 8 numeros");
		}else if(!direccion.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje("El direccion debe tener un numero y una letra");
		}else if(!telefono.matches(Validaciones.NUMERO)) {
			mensaje("Solo se pueden ingresar 9 numeros");			
		}else if(!correo.matches(Validaciones.CORREO)) {
			mensaje("El corrreo debe contener un @ y un .com");
		}else if(cboPais.getSelectedIndex()==0) {
			mensaje("Seleccione un pais");
		}else {
			Proveedor pro = new Proveedor();
			pro.setNombre(nombre);
			pro.setApellido(apellido);
			pro.setDni(dni);
			pro.setDireccion(direccion);
			pro.setTelefono(telefono);
			pro.setCorreo(correo);
			pro.setPais(pais);
			
			ProveedorModel proModel= new ProveedorModel();
			int salida = proModel.insertaProveedor(pro);
			if(salida>0) {
				mensaje("Se insertó Correctamente");
			}else {
				mensaje("Error en el registro");
			}
		}
		
	limpiar();	
	}
	
	
	
	
	public void limpiar() {
		txtNombres.setText(null);
		txtApellidos.setText(null);
		txtDni.setText(null);
		txtDireccion.setText(null);
		txtTelefono.setText(null);
		txtCorreo.setText(null);
		cboPais.setSelectedIndex(-1);
		txtNombres.requestFocus();
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtTelefono) {
			keyTypedTxtTelefono(e);
		}
		if (e.getSource() == txtDni) {
			keyTypedTxtDni(e);
		}
	}
	protected void keyTypedTxtDni(KeyEvent e) {
		if(Character.isLetter(e.getKeyChar())) {
			e.consume();}
		String dniGenerado = txtDni.getText().trim()+e.getKeyChar();
		if(dniGenerado.length()>8) {
			e.consume();
		}
		
	}
	protected void keyTypedTxtTelefono(KeyEvent e) {
		if(Character.isLetter(e.getKeyChar())) {
			e.consume();}
		String telefonoGenerado=txtTelefono.getText().trim()+e.getKeyChar();
		if(telefonoGenerado.length()>9) {
			e.consume();
		}
	}
}
