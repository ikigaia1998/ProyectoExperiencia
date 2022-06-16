package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import entidad.Alumno;
import model.AlumnoModel;
import util.Conversiones;
import util.Validaciones;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class FrmRegistroAlumno extends JInternalFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtDni;
	private JTextField txtCorreo;
	private JTextField txtFecha;
	private JComboBox<String> cboPais;
	private JButton btnEnviar;

	public FrmRegistroAlumno() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Alumno");
		setBounds(100, 100, 900, 550);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Registro de Alumno");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(290, 40, 274, 58);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Nombres:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(171, 127, 170, 22);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Apellidos:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(171, 177, 170, 22);
		getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("DNI:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(171, 222, 170, 22);
		getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Correo:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(171, 268, 170, 22);
		getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Pa\u00EDs:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(171, 320, 170, 22);
		getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Fecha de Nacimiento:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(171, 367, 214, 22);
		getContentPane().add(lblNewLabel_6);
		
		txtNombres = new JTextField();
		txtNombres.setBounds(386, 132, 296, 19);
		getContentPane().add(txtNombres);
		txtNombres.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(386, 182, 296, 19);
		getContentPane().add(txtApellidos);
		
		txtDni = new JTextField();
		txtDni.addKeyListener(this);
		txtDni.setColumns(10);
		txtDni.setBounds(386, 227, 296, 19);
		getContentPane().add(txtDni);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(386, 273, 296, 19);
		getContentPane().add(txtCorreo);
		
		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(386, 372, 296, 19);
		getContentPane().add(txtFecha);
		
		cboPais = new JComboBox<String>();
		cboPais.addItem("Seleccione:");
		cboPais.addItem("Perú");
		cboPais.addItem("Chile");
		cboPais.addItem("Ecuador");
		cboPais.addItem("Colombia");
		cboPais.addItem("Venezuela");
		cboPais.setBounds(386, 324, 142, 21);
		getContentPane().add(cboPais);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(this);
		btnEnviar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEnviar.setBounds(345, 426, 142, 33);
		getContentPane().add(btnEnviar);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEnviar) {
			actionPerformedBtnEnviarJButton(e);
		}
	}
	protected void actionPerformedBtnEnviarJButton(ActionEvent e) {
		String name = txtNombres.getText();
		String ape = txtApellidos.getText();
		String dni = txtDni.getText();
		String email = txtCorreo.getText();
		String pais = cboPais.getSelectedItem().toString();
		String fecha = txtFecha.getText();
		
		if (!name.matches(Validaciones.TEXTO)) {
			mensaje ("El nombre debe tener entre 3 a 30 caracteres");
		} else if (!ape.matches(Validaciones.TEXTO)) {
			mensaje ("El apellido debe tener entre 3 a 30 caracteres");
		} else if (!dni.matches(Validaciones.DNI)) {
		mensaje ("El DNI debe tener entre 8 números");
		} else if (!email.matches(Validaciones.CORREO)) {
			mensaje ("Ingresar un correo válido"); 
		} else if (!fecha.matches(Validaciones.FECHA)) {
			mensaje ("El formato de la fecha debe ser yyyy-mm-dd. Ejm: 1993-08-09"); 
		} else if (cboPais.getSelectedIndex() == 0) {
			mensaje ("Seleccione un país"); 
		} else {
			Alumno a = new Alumno();
			a.setNombre(name);
			a.setApellido(ape);
			a.setDni(dni);
			a.setCorreo(email);
			a.setPais(pais);
			a.setFechanac(Conversiones.toFecha(fecha));
			
			AlumnoModel am = new AlumnoModel();	
			int salida = am.insertarAlumno(a);
			if (salida > 0) {
				mensaje ("Registro Completado");
				limpiar();
			} else {
				mensaje("Error en el registro");
			}
		}
		}
	
	 public void mensaje (String ms) {
		 JOptionPane.showMessageDialog(this, ms);
	 }
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtDni) {
			keyTypedTxtDniJTextField(e);
		}
	}
	protected void keyTypedTxtDniJTextField(KeyEvent e) {
		if (Character.isLetter(e.getKeyChar())) {
			
			 getToolkit().beep();
			 e.consume();
		      JOptionPane.showMessageDialog(null, "Debe ingresar sólo números","Error",JOptionPane.ERROR_MESSAGE); 
			 
		}
		
		String dni = txtDni.getText() + e.getKeyChar();
		
		if (dni.length() > 8) {
			e.consume();
		}
		
	}
	
	public void limpiar() {
		txtNombres.setText(null);
		txtApellidos.setText(null);
		txtDni.setText(null);
		txtCorreo.setText(null);
		txtFecha.setText(null);
		cboPais.setSelectedIndex(-1);
		txtNombres.requestFocus();
	}
}
