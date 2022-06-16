package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import entidad.Autor;
import model.AutorModel;
import util.Conversiones;
import util.Validaciones;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class FrmRegistroAutor extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblTitulo;
	private JLabel lblNombres;
	private JLabel lblApellidos;
	private JLabel lblFechaNacimiento;
	private JLabel lblPais;
	private JLabel lblGrado;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtFechaNacimiento;
	private JButton btnRegistrar;
	private JComboBox<String> cboPais;
	private JComboBox<String> cboGrado;
	

	public FrmRegistroAutor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Autor");
		setBounds(100, 100, 900, 550);
		getContentPane().setLayout(null);
		
		lblTitulo = new JLabel("Registro de Autor");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTitulo.setBounds(292, 21, 293, 27);
		getContentPane().add(lblTitulo);
		
		lblNombres = new JLabel("Nombres");
		lblNombres.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNombres.setBounds(169, 92, 191, 19);
		getContentPane().add(lblNombres);
		
		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblApellidos.setBounds(169, 145, 191, 19);
		getContentPane().add(lblApellidos);
		
		lblFechaNacimiento = new JLabel("Fecha de Nacimiento");
		lblFechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblFechaNacimiento.setBounds(169, 202, 191, 19);
		getContentPane().add(lblFechaNacimiento);
		
		lblPais = new JLabel("Pa\u00EDs");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblPais.setBounds(169, 256, 191, 19);
		getContentPane().add(lblPais);
		
		lblGrado = new JLabel("Grado");
		lblGrado.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblGrado.setBounds(169, 317, 191, 19);
		getContentPane().add(lblGrado);
		
		txtNombres = new JTextField();
		txtNombres.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtNombres.setBounds(410, 81, 273, 30);
		getContentPane().add(txtNombres);
		txtNombres.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(410, 134, 273, 30);
		getContentPane().add(txtApellidos);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtFechaNacimiento.setColumns(10);
		txtFechaNacimiento.setBounds(410, 191, 273, 30);
		getContentPane().add(txtFechaNacimiento);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnRegistrar.setBounds(386, 379, 177, 66);
		getContentPane().add(btnRegistrar);
		
		cboPais = new JComboBox<String>();
		cboPais.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cboPais.setBounds(411, 254, 272, 30);
		cboPais.addItem("[Seleccione]");
		cboPais.addItem("Perú");
		cboPais.addItem("Chile");
		cboPais.addItem("Ecuador");
		cboPais.addItem("Colombia");
		getContentPane().add(cboPais);
		
		cboGrado = new JComboBox<String>();
		cboGrado.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cboGrado.setBounds(411, 306, 272, 30);
		cboGrado.addItem("[Seleccione]");
		cboGrado.addItem("Técnico");
		cboGrado.addItem("Licenciado");
		cboGrado.addItem("Doctor");
		cboGrado.addItem("Autor");
		getContentPane().add(cboGrado);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrarJButton(e);
		}
	}
	public void mensaje(String ms){
		JOptionPane.showMessageDialog(this, ms);
	}
	
	public void limpiarDatos() {
		txtNombres.setText("");
		txtApellidos.setText("");
		txtFechaNacimiento.setText("");
		cboPais.setSelectedIndex(0);
		cboGrado.setSelectedIndex(0);
		txtNombres.requestFocus();
	
		
	}
	
	protected void actionPerformedBtnRegistrarJButton(ActionEvent e) {
		//Obtenemos datos de las cajas de texto
		String nombresUI = txtNombres.getText();
		String apellidosUI = txtApellidos.getText();
		String fechaNacimientoUI = txtFechaNacimiento.getText();
		String paisUI = cboPais.getSelectedItem().toString();
		String gradoUI = cboGrado.getSelectedItem().toString();
		
		//Hacemos las validaciones
		if(!nombresUI.matches(Validaciones.TEXTO)) {
			mensaje("Los nombres deben tener entre 3 a 30 caracteres");
		}else if(!apellidosUI.matches(Validaciones.TEXTO)) {
			mensaje("Los apellidos deben tener entre 3 a 30 caracteres");
		}else if(!fechaNacimientoUI.matches(Validaciones.FECHA)) {
			mensaje("La fecha debe tener el fromato yyyy-mm-dd");
		}else if(cboPais.getSelectedIndex() == 0) {
			mensaje("Seleccione un país");
		}else if(cboGrado.getSelectedIndex() == 0) {
			mensaje("Seleccione un grado");
		}else {
			//CreaciÓn del objeto
			Autor oAutor = new Autor();
			oAutor.setNombres(nombresUI);
			oAutor.setApellidos(apellidosUI);
			oAutor.setFechaNacimiento(Conversiones.toFecha(fechaNacimientoUI));
			oAutor.setPais(paisUI);
			oAutor.setGrado(gradoUI);
		
			
			//Envio del objeto a la BD
			AutorModel model = new AutorModel();
			int salida = model.insertarAutor(oAutor);
			
			if (salida>0) {
				mensaje("Se registró el autor correctamente");
				limpiarDatos();
			}else {
				mensaje("Error en el registro");
			}
		}
		
		
		
	}
}
