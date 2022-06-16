package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entidad.Alumno;
import model.AlumnoModel;
import util.Conversiones;
import util.Validaciones;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.event.MouseMotionListener;
import java.sql.Date;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class FrmCrudAlumno extends JInternalFrame implements ActionListener, MouseListener, MouseMotionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabel;
	private JLabel lblNombres;
	private JLabel lblApellidos;
	private JLabel lblDni;
	private JLabel lblCorreo;
	private JLabel lblFechaDeNac;
	private JLabel lblPas;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDNI;
	private JTextField txtCorreo;
	private JTextField txtFecha;
	private JComboBox<String> cboPais;
	private JButton btnAdd;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel;
	
	int idSeleccionado = -1;
	int hoveredRow = -1, hoveredColumn = -1;
	
	

	public FrmCrudAlumno() {
		
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Mantenimiento de Alumno");
		setBounds(100, 100, 900, 625);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Mantenimiento Alumno");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		lblNewLabel.setBounds(254, 10, 364, 37);
		getContentPane().add(lblNewLabel);
		
		lblNombres = new JLabel("Nombres:");
		lblNombres.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNombres.setBounds(28, 77, 104, 13);
		getContentPane().add(lblNombres);
		
		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Dialog", Font.BOLD, 12));
		lblApellidos.setBounds(28, 124, 104, 13);
		getContentPane().add(lblApellidos);
		
		lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Dialog", Font.BOLD, 12));
		lblDni.setBounds(28, 182, 104, 13);
		getContentPane().add(lblDni);
		
		lblCorreo = new JLabel("Correo:");
		lblCorreo.setFont(new Font("Dialog", Font.BOLD, 12));
		lblCorreo.setBounds(433, 77, 115, 13);
		getContentPane().add(lblCorreo);
		
		lblFechaDeNac = new JLabel("Fecha de nacimiento :");
		lblFechaDeNac.setFont(new Font("Dialog", Font.BOLD, 12));
		lblFechaDeNac.setBounds(433, 124, 153, 13);
		getContentPane().add(lblFechaDeNac);
		
		lblPas = new JLabel("Pa\u00EDs:");
		lblPas.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPas.setBounds(433, 182, 115, 13);
		getContentPane().add(lblPas);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(117, 77, 292, 19);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(117, 124, 292, 19);
		getContentPane().add(txtApellido);
		
		txtDNI = new JTextField();
		txtDNI.addKeyListener(this);
		txtDNI.setColumns(10);
		txtDNI.setBounds(117, 182, 115, 19);
		getContentPane().add(txtDNI);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(596, 75, 259, 19);
		getContentPane().add(txtCorreo);
		
		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(596, 122, 124, 19);
		getContentPane().add(txtFecha);
		
		cboPais = new JComboBox<String>();
		cboPais.addItem("Seleccione:");
		cboPais.addItem("Perú");
		cboPais.addItem("Chile");
		cboPais.addItem("Ecuador");
		cboPais.addItem("Colombia");
		cboPais.addItem("Venezuela");
		cboPais.setBounds(599, 179, 121, 21);
		getContentPane().add(cboPais);
		
		btnAdd = new JButton("A\u00F1adir");
		btnAdd.addActionListener(this);
		btnAdd.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnAdd.setBounds(117, 234, 149, 32);
		getContentPane().add(btnAdd);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnModificar.setBounds(373, 234, 149, 32);
		getContentPane().add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(this);
		btnEliminar.addActionListener(this);
		btnEliminar.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnEliminar.setBounds(627, 234, 149, 32);
		getContentPane().add(btnEliminar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 285, 827, 290);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseMotionListener(this);
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nombres", "Apellidos", "DNI", "Correo", "Fecha de nacimiento", "Pa\u00EDs"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(35);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(40);
		table.getColumnModel().getColumn(6).setPreferredWidth(35);
		
		table.setSurrendersFocusOnKeystroke(true);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnSelectionAllowed(false);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		table.getTableHeader().setReorderingAllowed(false);
		
		table.setDefaultEditor(Object.class, null);
		
		table.setSelectionBackground(Color.blue);
		
		panel = new JPanel();
		panel.setBackground(new Color(211, 211, 211));
		panel.setBounds(99, 10, 677, 37);
		getContentPane().add(panel);
		
		lista();

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnModificar) {
			actionPerformedBtnModificar(e);
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		agrega();
	}
	protected void actionPerformedBtnModificar(ActionEvent e) {
		modifica();
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		elimina();
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			mouseClickedTable(e);
		}
		if (e.getSource() == btnEliminar) {
			mouseClickedBtnEliminar(e);
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
	protected void mouseClickedBtnEliminar(MouseEvent e) {
	}
	
	private void lista() {
		AlumnoModel almd = new AlumnoModel();
		List<Alumno> lista = almd.listaAlumno();
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		
		Object[] fila = null;
		for (Alumno x : lista) {
			fila = new Object[] {x.getIdAlumno(), x.getNombre(), x.getApellido(), x.getDni(), x.getCorreo(), x.getFechanac(), x.getPais(), x.getFechareg()};
			dtm.addRow(fila);
			}
		}
			
			
	private void agrega() {
		String name = txtNombre.getText();
		String ape = txtApellido.getText();
		String dni = txtDNI.getText();
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
				cleaning();
				lista();
				idSeleccionado = -1;
			} else {
				mensaje("Error en el registro");
			}
		}
		
		
	}
	
	private void elimina() {
		if (idSeleccionado == -1) {
			mensaje ("Seleccione una fila");
		} else {
			
			AlumnoModel am = new AlumnoModel();	
			int salida = am.eliminateAlumno(idSeleccionado);
			if (salida > 0) {
				mensaje ("Eliminado con éxito");
				cleaning();
				lista();
				idSeleccionado = -1;
			} else {
				mensaje("Error al eliminar");
			}
		}
	}
	
	private void modifica() {
		String name = txtNombre.getText();
		String ape = txtApellido.getText();
		String dni = txtDNI.getText();
		String email = txtCorreo.getText();
		String pais = cboPais.getSelectedItem().toString();
		String fecha = txtFecha.getText();
		
		if (idSeleccionado == -1) {
			mensaje ("Seleccione una fila a modificar");
		}else if (!name.matches(Validaciones.TEXTO)) {
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
			a.setIdAlumno(idSeleccionado);
			a.setNombre(name);
			a.setApellido(ape);
			a.setDni(dni);
			a.setCorreo(email);
			a.setPais(pais);
			a.setFechanac(Conversiones.toFecha(fecha));
			
			AlumnoModel am = new AlumnoModel();	
			int salida = am.ModifyAlumno(a);
			
			if (salida > 0) {
				mensaje ("Se actualizó correctamente");
				cleaning();
				lista();
				idSeleccionado = -1;
			} else {
				mensaje("Error en la actualización");
			}
		}
	}
	
	private void busca() {
		int fila = table.getSelectedRow();
		
		idSeleccionado = (Integer)table.getValueAt(fila, 0);
		String nombre = (String)table.getValueAt(fila, 1);
		String apellido = (String)table.getValueAt(fila, 2);
		String dni = (String)table.getValueAt(fila, 3);
		String correo = (String)table.getValueAt(fila, 4);
		Date fechanac = (Date)table.getValueAt(fila, 5);
		String pais = (String)table.getValueAt(fila, 6);

		
		System.out.println(idSeleccionado + " - " + nombre + " - " + apellido + " - " + dni + " - " + correo + " - " + fechanac + " - " + pais + " - ");
		
		txtNombre.setText(nombre);
		txtApellido.setText(apellido);
		txtDNI.setText(dni);
		txtCorreo.setText(correo);
		txtFecha.setText(String.valueOf(fechanac));
		cboPais.setSelectedItem(pais);


	}
	
	
	protected void mouseClickedTable(MouseEvent e) {
		busca();
	}
	public void mouseDragged(MouseEvent e) {
		if (e.getSource() == table) {
			mouseDraggedTable(e);
		}
	}
	public void mouseMoved(MouseEvent e) {
	}
	protected void mouseDraggedTable(MouseEvent e) {
	}
	
	 public void mensaje (String ms) {
		 JOptionPane.showMessageDialog(this, ms);
	 }
	 
	 public void cleaning() {
			txtNombre.setText(null);
			txtApellido.setText(null);
			txtDNI.setText(null);
			txtCorreo.setText(null);
			txtFecha.setText(null);
			cboPais.setSelectedIndex(-1);
			txtNombre.requestFocus();
		}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtDNI) {
			keyTypedTxtDNI(e);
		}
	}
	protected void keyTypedTxtDNI(KeyEvent e) {
		if (Character.isLetter(e.getKeyChar())) {
			
			 getToolkit().beep();
			 e.consume();
		      JOptionPane.showMessageDialog(null, "Debe ingresar sólo números","Error",JOptionPane.ERROR_MESSAGE); 
			 
		}
		
		String dni = txtDNI.getText() + e.getKeyChar();
		
		if (dni.length() > 8) {
			e.consume();
		}
	
	
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
}}
