package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entidad.Autor;
import model.AutorModel;
import util.Conversiones;
import util.Validaciones;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class FrmCrudAutor extends JInternalFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblTitulo;
	private JLabel lblNombres;
	private JTextField txtNombres;
	private JLabel lblApellidos;
	private JTextField txtApellidos;
	private JLabel lblFechaNacimiento;
	private JTextField txtFechaNacimiento;
	private JLabel lblPais;
	private JComboBox<String> cboPais;
	private JLabel lblGrado;
	private JComboBox<String> cboGrado;
	private JButton btnIngresar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;
	private JTable tblAutor;

	int idSeleccionado = 1;
	int hoveredRow = -1, hoveredColumn = -1;

	public FrmCrudAutor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Mantenimiento de Autor");
		setBounds(100, 100, 900, 550);
		getContentPane().setLayout(null);

		lblTitulo = new JLabel("Mantenimiento de Autor");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setBounds(306, 11, 246, 33);
		getContentPane().add(lblTitulo);

		lblNombres = new JLabel("Nombres");
		lblNombres.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombres.setBounds(43, 71, 91, 19);
		getContentPane().add(lblNombres);

		txtNombres = new JTextField();
		txtNombres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNombres.setBounds(209, 69, 223, 22);
		getContentPane().add(txtNombres);
		txtNombres.setColumns(10);

		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblApellidos.setBounds(501, 71, 91, 19);
		getContentPane().add(lblApellidos);

		txtApellidos = new JTextField();
		txtApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(621, 69, 223, 22);
		getContentPane().add(txtApellidos);

		lblFechaNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFechaNacimiento.setBounds(43, 114, 156, 19);
		getContentPane().add(lblFechaNacimiento);

		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFechaNacimiento.setColumns(10);
		txtFechaNacimiento.setBounds(209, 112, 130, 22);
		getContentPane().add(txtFechaNacimiento);

		lblPais = new JLabel("Pa\u00EDs");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPais.setBounds(380, 113, 61, 19);
		getContentPane().add(lblPais);

		cboPais = new JComboBox<String>();
		cboPais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboPais.setModel(new DefaultComboBoxModel<String>(new String[] {"[Seleccione]", "Per\u00FA", "Chile", "Ecuador", "Colombia", "Argentina"}));
		cboPais.setBounds(441, 111, 130, 22);
		getContentPane().add(cboPais);

		lblGrado = new JLabel("Grado");
		lblGrado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGrado.setBounds(605, 115, 61, 19);
		getContentPane().add(lblGrado);

		cboGrado = new JComboBox<String>();
		cboGrado.setModel(new DefaultComboBoxModel<String>(
				new String[] { "[Seleccione]", "T\u00E9cnico", "Licenciado", "Doctor", "Autor" }));
		cboGrado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboGrado.setBounds(674, 111, 170, 22);
		getContentPane().add(cboGrado);

		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(this);
		btnIngresar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnIngresar.setBounds(209, 155, 130, 40);
		getContentPane().add(btnIngresar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnActualizar.setBounds(380, 155, 130, 40);
		getContentPane().add(btnActualizar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEliminar.setBounds(552, 155, 130, 40);
		getContentPane().add(btnEliminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 206, 801, 292);
		getContentPane().add(scrollPane);

		tblAutor = new JTable();
		tblAutor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tblAutor.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nombres", "Apellidos", "Fecha de nacimiento", "Pa\u00EDs", "Grado"
			}
		));
		tblAutor.addMouseListener(this);
		scrollPane.setViewportView(tblAutor);

		// Poner un tamaño y centrar los datos de las columnas 0 y 3 
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tblAutor.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tblAutor.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

		// Seleccionar una sola fila
		tblAutor.setRowSelectionAllowed(true);
		tblAutor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Deshabilita el movimiento de columnas
		tblAutor.getTableHeader().setReorderingAllowed(false);
		// No permite editar desde la tabla
		tblAutor.setDefaultEditor(Object.class, null);
		// Color de la fila seleccionada
		tblAutor.setSelectionBackground(Color.GRAY);
		
		// Poner un tamaño a las columnas 
		tblAutor.getColumnModel().getColumn(0).setPreferredWidth(70);
		tblAutor.getColumnModel().getColumn(1).setPreferredWidth(190);
		tblAutor.getColumnModel().getColumn(2).setPreferredWidth(190);
		tblAutor.getColumnModel().getColumn(3).setPreferredWidth(140);
		tblAutor.getColumnModel().getColumn(4).setPreferredWidth(110);
		tblAutor.getColumnModel().getColumn(5).setPreferredWidth(100);

		// Efecto Rollover
		tblAutor.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				Point p = e.getPoint();
				hoveredRow = tblAutor.rowAtPoint(p);
				hoveredColumn = tblAutor.columnAtPoint(p);
				tblAutor.setRowSelectionInterval(hoveredRow, hoveredRow);
				tblAutor.repaint();
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				hoveredRow = hoveredColumn = -1;
				tblAutor.repaint();
			}
		});

		// Traemos el listado de autores
		listar();

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(e);
		}
		if (e.getSource() == btnIngresar) {
			actionPerformedBtnIngresar(e);
		}
	}

	protected void actionPerformedBtnIngresar(ActionEvent e) {
		insertar();
	}

	protected void actionPerformedBtnActualizar(ActionEvent e) {
		actualizar();
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
		eliminar();
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblAutor) {
			mouseClickedTblAutor(e);
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

	protected void mouseClickedTblAutor(MouseEvent e) {
		buscarFila();
	}

	private void listar() {
		AutorModel mAutor = new AutorModel();
		List<Autor> listado = mAutor.listarAutor();

		DefaultTableModel dtm = (DefaultTableModel) tblAutor.getModel();
		dtm.setRowCount(0);

		Object[] fila = null;
		for (Autor a : listado) {
			fila = new Object[] { a.getIdAutor(), a.getNombres(), a.getApellidos(), a.getFechaNacimiento(), a.getPais(),
					a.getGrado() };
			dtm.addRow(fila);

		}

	}

	private void insertar() {
		// Obtenemos datos de las cajas de texto
		String nombresUI = txtNombres.getText();
		String apellidosUI = txtApellidos.getText();
		String fechaNacimientoUI = txtFechaNacimiento.getText();
		String paisUI = cboPais.getSelectedItem().toString();
		String gradoUI = cboGrado.getSelectedItem().toString();

		// Hacemos las validaciones
		if (!nombresUI.matches(Validaciones.TEXTO)) {
			mensaje("Los nombres deben tener entre 3 a 30 caracteres");
		} else if (!apellidosUI.matches(Validaciones.TEXTO)) {
			mensaje("Los apellidos deben tener entre 3 a 30 caracteres");
		} else if (!fechaNacimientoUI.matches(Validaciones.FECHA)) {
			mensaje("La fecha debe tener el fromato yyyy-mm-dd");
		} else if (cboPais.getSelectedIndex() == 0) {
			mensaje("Seleccione un país");
		} else if (cboGrado.getSelectedIndex() == 0) {
			mensaje("Seleccione un grado");
		} else {
			// CreaciÓn del objeto
			Autor oAutor = new Autor();
			oAutor.setNombres(nombresUI);
			oAutor.setApellidos(apellidosUI);
			oAutor.setFechaNacimiento(Conversiones.toFecha(fechaNacimientoUI));
			oAutor.setPais(paisUI);
			oAutor.setGrado(gradoUI);

			// Envio del objeto a la BD
			AutorModel mAutor = new AutorModel();
			int salida = mAutor.insertarAutor(oAutor);

			if (salida > 0) {
				listar();
				idSeleccionado = -1;
				limpiarDatos();
				mensaje("Se registró el autor correctamente");
			} else {
				mensaje("Error en el registro");
			}
		}

	}

	private void eliminar() {
		if (idSeleccionado == -1) {
			mensaje("Debe seleccionar una fila para eliminar");
		} else {
			AutorModel mAutor = new AutorModel();
			int salida = mAutor.eliminarAutor(idSeleccionado);

			if (salida > 0) {
				listar();
				idSeleccionado = -1;
				limpiarDatos();
				mensaje("Se eliminó el autor correctamente");
			} else {
				mensaje("Error al eliminar");
			}
		}
	}

	private void actualizar() {
		// Obtenemos datos de las cajas de texto
		String nombresUI = txtNombres.getText();
		String apellidosUI = txtApellidos.getText();
		String fechaNacimientoUI = txtFechaNacimiento.getText();
		String paisUI = cboPais.getSelectedItem().toString();
		String gradoUI = cboGrado.getSelectedItem().toString();

		// Hacemos las validaciones
		if (idSeleccionado == -1) {
			mensaje("Debe seleccionar una fila para actualizar");
		} else if (!nombresUI.matches(Validaciones.TEXTO)) {
			mensaje("Los nombres deben tener entre 3 a 30 caracteres");
		} else if (!apellidosUI.matches(Validaciones.TEXTO)) {
			mensaje("Los apellidos deben tener entre 3 a 30 caracteres");
		} else if (!fechaNacimientoUI.matches(Validaciones.FECHA)) {
			mensaje("La fecha debe tener el fromato yyyy-mm-dd");
		} else if (cboPais.getSelectedIndex() == 0) {
			mensaje("Seleccione un país");
		} else if (cboGrado.getSelectedIndex() == 0) {
			mensaje("Seleccione un grado");
		} else {
			// CreaciÓn del objeto
			Autor oAutor = new Autor();
			oAutor.setIdAutor(idSeleccionado);
			oAutor.setNombres(nombresUI);
			oAutor.setApellidos(apellidosUI);
			oAutor.setFechaNacimiento(Conversiones.toFecha(fechaNacimientoUI));
			oAutor.setPais(paisUI);
			oAutor.setGrado(gradoUI);

			// Envio del objeto a la BD
			AutorModel mAutor = new AutorModel();
			int salida = mAutor.actualizarAutor(oAutor);

			if (salida > 0) {
				listar();
				idSeleccionado = -1;
				limpiarDatos();
				mensaje("Se actualizó el autor correctamente");
			} else {
				mensaje("Error en la actualización");
			}
		}

	}

	private void buscarFila() {
		int fila = tblAutor.getSelectedRow();
		idSeleccionado = (Integer) tblAutor.getValueAt(fila, 0);
		String nombres = (String) tblAutor.getValueAt(fila, 1);
		String apellidos = (String) tblAutor.getValueAt(fila, 2);
		Date fechaNacimiento = (Date) tblAutor.getValueAt(fila, 3);
		String pais = (String) tblAutor.getValueAt(fila, 4);
		String grado = (String) tblAutor.getValueAt(fila, 5);

		txtNombres.setText(nombres);
		txtApellidos.setText(apellidos);
		txtFechaNacimiento.setText(String.valueOf(fechaNacimiento));
		cboPais.setSelectedItem(pais);
		cboGrado.setSelectedItem(grado);
	}

	public void limpiarDatos() {
		txtNombres.setText("");
		txtApellidos.setText("");
		txtFechaNacimiento.setText("");
		cboPais.setSelectedIndex(0);
		cboGrado.setSelectedIndex(0);
		txtNombres.requestFocus();

	}

	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}
}
