package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entidad.Usuario;
import model.UsuarioModel;
import util.Conversiones;
import util.Validaciones;
import java.awt.Font;

public class FrmCrudUsuario extends JInternalFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtdni;
	private JTextField txtLogin;
	private JTextField txtPassword;
	private JTextField txtCorreo;
	private JTextField txtNacimiento;
	private JTextField txtDireccion;
	private JComboBox<String> cboPais;
	private JTable table;
	private JButton btnIngresar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;
	//-1 indica que no se ah selecionado nada en la grilla o tabla
	private int idSeleccionado = -1;
	
	int hoveredRow = -1, hoveredColumn = -1;
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudUsuario frame = new FrmCrudUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public FrmCrudUsuario() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Mantenimiento de Usuario");
		setBounds(100, 100, 900, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(44, 103, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(127, 100, 144, 19);
		contentPane.add(txtNombre);
		
		JLabel lblNewLabel_2 = new JLabel("Apellidos");
		lblNewLabel_2.setBounds(44, 142, 70, 13);
		contentPane.add(lblNewLabel_2);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(127, 139, 144, 19);
		contentPane.add(txtApellido);
		
		JLabel lblNewLabel_1_1 = new JLabel("DNI");
		lblNewLabel_1_1.setBounds(44, 183, 45, 13);
		contentPane.add(lblNewLabel_1_1);
		
		txtdni = new JTextField();
		txtdni.setColumns(10);
		txtdni.setBounds(127, 180, 144, 19);
		contentPane.add(txtdni);
		
		JLabel lblNewLabel_2_1 = new JLabel("Login");
		lblNewLabel_2_1.setBounds(44, 219, 60, 16);
		contentPane.add(lblNewLabel_2_1);
		
		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		txtLogin.setBounds(127, 216, 144, 19);
		contentPane.add(txtLogin);
		
		JLabel lblNewLabel_5 = new JLabel("Password");
		lblNewLabel_5.setBounds(44, 262, 60, 13);
		contentPane.add(lblNewLabel_5);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(127, 259, 147, 19);
		getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Correo");
		lblNewLabel_6.setBounds(381, 114, 45, 13);
		contentPane.add(lblNewLabel_6);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(467, 111, 121, 19);
		contentPane.add(txtCorreo);
		
		JLabel lblNewLabel_8 = new JLabel("Fecha de Nacimiento");
		lblNewLabel_8.setBounds(325, 156, 113, 13);
		getContentPane().add(lblNewLabel_8);
		
		txtNacimiento = new JTextField();
		txtNacimiento.setColumns(10);
		txtNacimiento.setBounds(467, 153, 121, 19);
		contentPane.add(txtNacimiento);
		
		JLabel lblNewLabel_9 = new JLabel("Direcci\u00F3n");
		lblNewLabel_9.setBounds(371, 199, 55, 13);
		contentPane.add(lblNewLabel_9);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(467, 196, 121, 19);
		contentPane.add(txtDireccion);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(this);
		btnIngresar.setBounds(686, 103, 85, 34);
		contentPane.add(btnIngresar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(686, 162, 85, 34);
		contentPane.add(btnActualizar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(686, 222, 85, 34);
		contentPane.add(btnEliminar);
		
		JLabel lblNewLabel_10 = new JLabel("Pa\u00EDs");
		lblNewLabel_10.setBounds(388, 233, 38, 13);
		contentPane.add(lblNewLabel_10);
		
		cboPais = new JComboBox<String>();
		cboPais.addItem("Seleccione");
		cboPais.addItem("Perú");
		cboPais.addItem("Chile");
		cboPais.addItem("Argentina");
		cboPais.addItem("Colombia");
		cboPais.setBounds(465, 235, 123, 21);
		contentPane.add(cboPais);
		
		JLabel lblNewLabel = new JLabel("Mantenimiento de Usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(298, 26, 290, 34);
		contentPane.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 288, 846, 223);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Código", "Nombre", "Apellido", "DNI", "Login", "Password", "Correo","FechaNacimiento", "Dirección", "País"
			}
		));
		
		//alineaciÃ³n colum 2 y 5 pone al centro appelido y password
				DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
				rightRenderer.setHorizontalAlignment(JLabel.CENTER);
				table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
		
				//tamaño de la fila	
				table.getColumnModel().getColumn(0).setPreferredWidth(50);
				table.getColumnModel().getColumn(1).setPreferredWidth(80);
				table.getColumnModel().getColumn(2).setPreferredWidth(80);
				table.getColumnModel().getColumn(3).setPreferredWidth(80);
				table.getColumnModel().getColumn(4).setPreferredWidth(80);
				table.getColumnModel().getColumn(5).setPreferredWidth(80);
				table.getColumnModel().getColumn(6).setPreferredWidth(100);
				table.getColumnModel().getColumn(7).setPreferredWidth(100);
				table.getColumnModel().getColumn(8).setPreferredWidth(80);
				table.getColumnModel().getColumn(9).setPreferredWidth(70);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
				
				//selecciona una sola fila
				table.setRowSelectionAllowed(true);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
				//desabilita mover las columnas
				table.getTableHeader().setReorderingAllowed(false);
				
				//color de la fila seleccionada
				table.setSelectionBackground(Color.BLUE);
				
				  //No se pueda editar
			    table.setDefaultEditor(Object.class, null);
				  
			  //Efecto Rollover
			    table.addMouseMotionListener(new MouseMotionListener() {
			        @Override
			        public void mouseMoved(MouseEvent e) {
			            Point p = e.getPoint();
			            hoveredRow = table.rowAtPoint(p);
			            hoveredColumn = table.columnAtPoint(p);
			            table.setRowSelectionInterval(hoveredRow, hoveredRow);
			            table.repaint();    
			        }
			        @Override
			        public void mouseDragged(MouseEvent e) {
			            hoveredRow = hoveredColumn = -1;
			            table.repaint();
			        }
			    });
			    
		scrollPane.setViewportView(table);
		
		lista();

	}
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}

	void limpiarCajasTexto() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtdni.setText("");
		txtLogin.setText("");
		txtPassword.setText("");
		txtCorreo.setText("");
		txtNacimiento.setText("");
		txtDireccion.setText("");
		cboPais.setSelectedIndex(0);
		txtNombre.requestFocus();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminar) {
			handle_btnEliminar_actionPerformed(e);
		}
		if (e.getSource() == btnActualizar) {
			handle_btnActualizar_actionPerformed(e);
		}
		if (e.getSource() == btnIngresar) {
			handle_btnIngresar_actionPerformed(e);
		}
	}
	protected void handle_btnIngresar_actionPerformed(ActionEvent e) {
		inserta();
	}
	protected void handle_btnActualizar_actionPerformed(ActionEvent e) {
		actualiza();
	}
	protected void handle_btnEliminar_actionPerformed(ActionEvent e) {
		elimina();
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			handle_table_mouseClicked(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void handle_table_mouseClicked(MouseEvent e) { 
		busca();
	}
	private void lista() {
		UsuarioModel model = new UsuarioModel();
		List<Usuario> lista = model.listaUsuario();
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		
		Object[] fila = null; 
		for (Usuario x : lista) {
			fila = new Object[]{ x.getIdUsuario(), x.getNombre(), x.getApellido(), x.getDni(), x.getLogin(),x.getPassword(),x.getCorreo()
					, x.getFechaNacimiento(),x.getDireccion(),x.getPais()};
			dtm.addRow(fila);
		}
			
	}
	private void inserta() {
		String nom = txtNombre.getText().trim();
		String ape = txtApellido.getText().trim();
		String dni = txtdni.getText().trim();
		String log = txtLogin.getText().trim();
		String pas = txtPassword.getText().trim();
		String corr = txtCorreo.getText().trim();
		String fechNa = txtNacimiento.getText().trim();
		String direc = txtDireccion.getText().trim();
		String pais = cboPais.getSelectedItem().toString();
		

		if(!nom.matches(Validaciones.TEXTO)) {
			mensaje("El nombre es de 3 a 30 caracteres ");
			
		}else if(!ape.matches(Validaciones.TEXTO)) {
			mensaje("El apellido es de 3 a 30 caracteres ");
			
		}else if(!dni.matches(Validaciones.DNI)) {
		mensaje("El dni es de 8 dígitos");
		
		}else if(!log.matches(Validaciones.LOGIN)) {
			mensaje("El login es de mas de 3 caracteres ");
		
		}else if(!pas.matches(Validaciones.PAS)) {
			mensaje("ingrese un pasword con mas de 5 caracter o digitos ");	
			
		}else if(!corr.matches(Validaciones.CORREO)) {
			mensaje("El correo esta incorrecto ");	
			
		}else if (!fechNa.matches(Validaciones.FECHA)) {

			mensaje("la fecha es yy-mm-dd");
		}else if (!direc.matches(Validaciones.DIRECCION)) {

			mensaje("complete bien la direccion");
		}else if (cboPais.getSelectedIndex() ==0) {

			mensaje("selecione el pais");
			
		}else {
			Usuario obj = new Usuario();
			obj.setNombre(nom);
			obj.setApellido(ape);
			obj.setDni(dni);
			obj.setLogin(log);
			obj.setPassword(pas);
			obj.setCorreo(corr);
			obj.setFechaNacimiento(Conversiones.toFecha(fechNa));
			obj.setDireccion(direc);
			obj.setPais(pais);
			
			UsuarioModel model = new UsuarioModel();
			int salida = model.insertaUsuario(obj);
			
			if(salida > 0 ) {
				lista();
				idSeleccionado = -1;
				limpiarCajasTexto();
				
				mensaje("Registro exitoso");
			}else {
				mensaje("Error en el registro");
			}
		}
	}
	private void busca() {
		int fila = table.getSelectedRow();
		
		idSeleccionado = (Integer)table.getValueAt(fila, 0);
		String nom =  (String)table.getValueAt(fila, 1);
		String ape =  (String)table.getValueAt(fila, 2);
		String dni = (String)table.getValueAt(fila, 3);
		String log =  (String)table.getValueAt(fila, 4);
		String pass =  (String)table.getValueAt(fila, 5);
		String corr = (String)table.getValueAt(fila, 6);
		Date FechNa = (Date)table.getValueAt(fila, 7);
		String direc =  (String)table.getValueAt(fila, 8);
		String pais = (String)table.getValueAt(fila, 9);
		
		System.out.println(idSeleccionado + " - " +  nom + " - " + ape + " - " + dni + " - "
		+ log + " - " + pass + " - " + corr + " - " + FechNa + " - " + direc + " - " + pais);
		
		txtNombre.setText(nom);
		txtApellido.setText(ape);
		txtdni.setText(dni);
		txtLogin.setText(log);
		txtPassword.setText(pass);
		txtCorreo.setText(corr);
		txtNacimiento.setText(String.valueOf(FechNa));
		txtDireccion.setText(direc);
		cboPais.setSelectedItem(pais);
				
	}
	private void elimina() {
		if (idSeleccionado == -1) {
			mensaje("Seleccione una fila");
		}else {
			UsuarioModel model = new UsuarioModel();
			int salida = model.eliminaUsuario(idSeleccionado);
			if (salida > 0) {
				lista();
				idSeleccionado = -1;
				limpiarCajasTexto();
				mensaje("Se eliminó correctamente");
			}else {
				mensaje("Error en la eliminación");
			}
		}
	}
	private void actualiza() {
		String nom = txtNombre.getText().trim();
		String ape = txtApellido.getText().trim();
		String dni = txtdni.getText().trim();
		String log = txtLogin.getText().trim();
		String pas = txtPassword.getText().trim();
		String corr = txtCorreo.getText().trim();
		String fechNa = txtNacimiento.getText().trim();
		String direc = txtDireccion.getText().trim();
		String pais = cboPais.getSelectedItem().toString();
		
		if(idSeleccionado == -1) {
			mensaje("seleccione una fila de la tabla");
		}
		else if(!nom.matches(Validaciones.TEXTO)) {
			mensaje("El nombre es de 3 a 30 caracteres ");
			
		}else if(!ape.matches(Validaciones.TEXTO)) {
			mensaje("El apellido es de 3 a 30 caracteres ");
			
		}else if(!dni.matches(Validaciones.DNI)) {
		mensaje("El dni es de 8 dígitos");
		
		}else if(!log.matches(Validaciones.LOGIN)) {
			mensaje("El login es de mas de 3 caracteres ");
		
		}else if(!pas.matches(Validaciones.PAS)) {
			mensaje("ingrese un pasword con mas de 5 caracter o digitos ");	
			
		}else if(!corr.matches(Validaciones.CORREO)) {
			mensaje("El correo esta incorrecto ");	
			
		}else if (!fechNa.matches(Validaciones.FECHA)) {

			mensaje("la fecha es yy-mm-dd");
		}else if (!direc.matches(Validaciones.DIRECCION)) {

			mensaje("complete bien la direccion");
		}else if (cboPais.getSelectedIndex() ==0) {

			mensaje("selecione el pais");
			
		}else {
			Usuario obj = new Usuario();
			obj.setIdUsuario(idSeleccionado);
			obj.setNombre(nom);
			obj.setApellido(ape);
			obj.setDni(dni);
			obj.setLogin(log);
			obj.setPassword(pas);
			obj.setCorreo(corr);
			obj.setFechaNacimiento(Conversiones.toFecha(fechNa));
			obj.setDireccion(direc);
			obj.setPais(pais);
			
			UsuarioModel model = new UsuarioModel();
			int salida = model.actualizaUsuario(obj);
			
			if(salida > 0 ) {
				lista();
				idSeleccionado = -1;
				limpiarCajasTexto();
				
				mensaje("Actualizacion exitosa");
			}else {
				mensaje("Error en la Actualizacion");
			}
	}
	
	}
	}
