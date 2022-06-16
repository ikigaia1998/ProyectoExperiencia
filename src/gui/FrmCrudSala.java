package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entidad.Sala;
import model.SalaModel;
import util.Validaciones;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class FrmCrudSala extends JInternalFrame implements ActionListener, MouseListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JTable tblSala;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtNumero;
	private JComboBox<String> cboPiso;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField txtNumAlumn;
	private JTextField txtRecursos;
	private JComboBox<String> cboSede;
	private JButton btnRegistrar;
	private JButton btnEliminar;
	private JButton btnActualizar;

	int idSeleccionado = 1;
	int hoveredRow = -1, hoveredColumn = -1;
	private JCheckBox chkEstado;

	public FrmCrudSala() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Mantenimiento de Sala");
		setBounds(100, 100, 900, 550);
		getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 292, 868, 219);
		getContentPane().add(scrollPane);

		tblSala = new JTable();
		tblSala.addMouseListener(this);
		tblSala.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Código","Número de sala", "N\u00FAmero de alumnos", "Piso", "Recursos", "Estado", "Sede" }));
		scrollPane.setViewportView(tblSala);

		lblNewLabel = new JLabel("Mantenimiento sala");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 868, 28);
		getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("N\u00FAmero:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(73, 75, 154, 13);
		getContentPane().add(lblNewLabel_1);

		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(237, 74, 96, 19);
		getContentPane().add(txtNumero);

		cboPiso = new JComboBox<String>();
		cboPiso.setModel(new DefaultComboBoxModel<String>(new String[] { "[Seleccione]", "1", "2", "3", "4", "5" }));
		cboPiso.setBounds(670, 74, 96, 21);
		getContentPane().add(cboPiso);

		lblNewLabel_2 = new JLabel("Piso:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(506, 76, 154, 13);
		getContentPane().add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("N\u00FAmero de alumnos:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(73, 126, 154, 13);
		getContentPane().add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Recursos:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(506, 127, 154, 13);
		getContentPane().add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("Sede:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(73, 181, 154, 13);
		getContentPane().add(lblNewLabel_5);

		txtNumAlumn = new JTextField();
		txtNumAlumn.addKeyListener(this);
		txtNumAlumn.setColumns(10);
		txtNumAlumn.setBounds(237, 125, 96, 19);
		getContentPane().add(txtNumAlumn);

		txtRecursos = new JTextField();
		txtRecursos.setColumns(10);
		txtRecursos.setBounds(670, 126, 96, 19);
		getContentPane().add(txtRecursos);

		cboSede = new JComboBox<String>();
		cboSede.setModel(new DefaultComboBoxModel<String>(new String[] { "[Seleccione]", "Lima", "Bellavista ",
				"Bre\u00F1a", "Arequipa", "Trujillo", "Independencia", "Surco" }));
		cboSede.setBounds(237, 179, 96, 21);
		getContentPane().add(cboSede);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(228, 245, 103, 28);
		getContentPane().add(btnRegistrar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(386, 245, 103, 28);
		getContentPane().add(btnEliminar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(535, 244, 103, 30);
		getContentPane().add(btnActualizar);

		// alineación
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
		tblSala.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		tblSala.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		tblSala.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

		// tamano de la fila
		tblSala.getColumnModel().getColumn(0).setPreferredWidth(15);
		tblSala.getColumnModel().getColumn(1).setPreferredWidth(120);
		tblSala.getColumnModel().getColumn(2).setPreferredWidth(120);
		tblSala.getColumnModel().getColumn(3).setPreferredWidth(80);
		tblSala.getColumnModel().getColumn(4).setPreferredWidth(60);
		tblSala.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		// selecciona una sola fila
		tblSala.setRowSelectionAllowed(true);
		tblSala.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// desabilita mover las columnas
		tblSala.getTableHeader().setReorderingAllowed(false);

		scrollPane.setViewportView(tblSala);

		// color de la fila seleccionada
		tblSala.setSelectionBackground(Color.blue);
		
		chkEstado = new JCheckBox("Activo");
		chkEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chkEstado.setSelected(true);
		chkEstado.setBounds(506, 172, 97, 30);
		getContentPane().add(chkEstado);

		// No se pueda editar
		tblSala.setDefaultEditor(Object.class, null);

		// Efecto Rollover
		tblSala.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				Point p = e.getPoint();
				hoveredRow = tblSala.rowAtPoint(p);
				hoveredColumn = tblSala.columnAtPoint(p);
				tblSala.setRowSelectionInterval(hoveredRow, hoveredRow);
				tblSala.repaint();
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				hoveredRow = hoveredColumn = -1;
				tblSala.repaint();
			}
		});

		listar();

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(e);
		}
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrar(e);
		}
	}

	protected void actionPerformedBtnRegistrar(ActionEvent e) {
		insertarDatos();
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
		eliminarSala();
	}

	protected void actionPerformedBtnActualizar(ActionEvent e) {
		actualizarSala();
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tblSala) {
			mouseClickedTblSala(e);
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

	protected void mouseClickedTblSala(MouseEvent e) {
		buscarFila();
	}

	private void listar() {
		SalaModel model = new SalaModel();
		List<Sala> listado = model.listaSala();

		DefaultTableModel dtm = (DefaultTableModel) tblSala.getModel();
		dtm.setRowCount(0);

		Object[] fila = null;
		for (Sala a : listado) {
			fila = new Object[] { a.getIdSala(), a.getNumero(), a.getNumAlumnos(), a.getPiso(), a.getRecursos(),
					getFormatoEstado(a.getestado()), a.getSede()}; 
			dtm.addRow(fila);

		}
	}

	private void insertarDatos() {
		String num = txtNumero.getText();
		String piso = cboPiso.getSelectedItem().toString();
		String numAlu = txtNumAlumn.getText();
		String rec = txtRecursos.getText();
		boolean estado = chkEstado.isSelected();
		String sede = cboSede.getSelectedItem().toString();

		if (!num.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje("El numero de sala es de 3 a 30 caracteres");
		} else if (cboPiso.getSelectedIndex() == 0) {
			mensaje("Seleccionar un piso");
		} else if (!numAlu.matches(Validaciones.NUMERO)) {
			mensaje("El número de alumnos debe ser mayor a 0");
		} else if (!rec.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje("El recurso es de 3 a 30 caracteres");
		} else if (cboSede.getSelectedIndex() == 0) {
			mensaje("Seleccione una sede");
		} else {
			Sala obj = new Sala();
			obj.setNumero(num);
			obj.setPiso(Integer.parseInt(piso));
			obj.setNumAlumnos(Integer.parseInt(numAlu));
			obj.setRecursos(rec);
			obj.setestado(getIntegerEstado(estado));
			obj.setSede(sede);

			SalaModel model = new SalaModel();
			int salida = model.insertaSala(obj);
			if (salida > 0) {
				limpiar();
				listar();
				idSeleccionado = -1;
				mensaje("Se insertó correctamente");
			} else {
				mensaje("Error en el registro");
			}
		}
	}

	private void actualizarSala() {
		String num = txtNumero.getText();
		String pis = cboPiso.getSelectedItem().toString();
		String alu = txtNumAlumn.getText();
		String rec = txtRecursos.getText();
		boolean estado = chkEstado.isSelected();
		String sed = cboSede.getSelectedItem().toString();
		
		if (!num.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje("El numero de sala es de 3 a 30 caracteres");
		} else if (cboPiso.getSelectedIndex() == 0) {
			mensaje("Seleccionar un piso");
		} else if (!alu.matches(Validaciones.NUMERO)) {
			mensaje("El número de alumnos debe ser mayor a 0");
		} else if (!rec.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje("El recurso es de 3 a 30 caracteres");
		} else if (cboSede.getSelectedIndex() == 0) {
			mensaje("Seleccione una sede");
		}else {
			Sala obj = new Sala();
			obj.setIdSala(idSeleccionado);
			obj.setNumero(num);
			obj.setPiso(Integer.parseInt(pis));
			obj.setNumAlumnos(Integer.parseInt(alu));
			obj.setRecursos(rec);
			obj.setestado(getIntegerEstado(estado));
			obj.setSede(sed);
			
			SalaModel model = new SalaModel();
			int salida = model.actualizaSala(obj);
			
			if (salida > 0) {
				listar();
				limpiar();
				idSeleccionado = -1;
				mensaje("Se actualizó correctamente");
			}else {
				mensaje("Error en la actualización");
			}
			
		}	
	}

	private void eliminarSala() {
		if(idSeleccionado == -1) {
			mensaje("Seleccione una Fila");
		}else {
			SalaModel model = new SalaModel();
			int salida = model.eliminaSala(idSeleccionado);
			
			if(salida>0) {
				listar();
				idSeleccionado = -1;
				limpiar();
				mensaje("Se eliminó correctamente");
			}else {
				mensaje("Error en la eliminación");
			}
		}
	}

	private void buscarFila() {
		int fila = tblSala.getSelectedRow();

		idSeleccionado = (Integer) tblSala.getValueAt(fila, 0);
		String numero = (String) tblSala.getValueAt(fila, 1);
		int alumnos = (Integer) tblSala.getValueAt(fila, 2);
		int piso = (Integer) tblSala.getValueAt(fila, 3);
		String recursos = (String) tblSala.getValueAt(fila, 4);
		String estado =  (String)tblSala.getValueAt(fila, 5);
		String sede = (String) tblSala.getValueAt(fila, 6);


		txtNumero.setText(numero);
		cboPiso.setSelectedIndex(piso);
		txtNumAlumn.setText(String.valueOf(alumnos));
		txtRecursos.setText(recursos);
		chkEstado.setSelected(getBooleanEstado(estado));
		cboSede.setSelectedItem(sede);
	}

	public void limpiar() {
		txtNumero.setText("");
		txtNumAlumn.setText("");
		txtRecursos.setText("");
		cboPiso.setSelectedIndex(0);
		cboSede.setSelectedIndex(0);
		txtNumero.requestFocus();
	}

	public void mensaje(String msje) {
		JOptionPane.showMessageDialog(this, msje);
	}
	private String getFormatoEstado(int estado) {
		return estado == 1? "Activo":"Inactivo";
	}
	private boolean getBooleanEstado(String estado) {
		return estado == "Activo"? true:false;
	}
	private int getIntegerEstado(boolean estado) {
		return estado? 1:0;
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtNumAlumn) {
			keyTypedTxtNumAlumn(e);
		}
	}
	protected void keyTypedTxtNumAlumn(KeyEvent e) {
		if (!Character.isDigit(e.getKeyChar())) {
			e.consume();
		}
	}
}
