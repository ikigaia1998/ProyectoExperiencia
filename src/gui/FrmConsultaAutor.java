package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
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

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmConsultaAutor extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblTitulo;
	private JLabel lblNombre;
	private JLabel lblFechaDeNacimiento;
	private JTextField txtNombres;
	private JLabel lblApellido;
	private JTextField txtApellidos;
	private JTextField txtFechaDesde;
	private JLabel lblGrado;
	private JLabel lblPais;
	private JButton btnFiltrar;
	private JComboBox<String> cboPais;
	private JComboBox<String> cboGrado;
	private JScrollPane scrollPane;
	private JTable tblAutor;
	private JLabel lblFechaNacimientoHasta;
	private JTextField txtFechaHasta;
	private JButton btnLimpiar;

	public FrmConsultaAutor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Autor");
		setBounds(100, 100, 1000, 550);
		getContentPane().setLayout(null);
		
		lblTitulo = new JLabel("Consulta de Autor");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setBounds(364, 21, 194, 25);
		getContentPane().add(lblTitulo);
		
		lblNombre = new JLabel("Nombres");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombre.setBounds(38, 58, 105, 20);
		getContentPane().add(lblNombre);
		
		lblFechaDeNacimiento = new JLabel("Fecha de nacimiento desde");
		lblFechaDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFechaDeNacimiento.setBounds(38, 95, 215, 20);
		getContentPane().add(lblFechaDeNacimiento);
		
		txtNombres = new JTextField();
		txtNombres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNombres.setBounds(125, 57, 249, 22);
		getContentPane().add(txtNombres);
		txtNombres.setColumns(10);
		
		lblApellido = new JLabel("Apellidos");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblApellido.setBounds(408, 57, 91, 20);
		getContentPane().add(lblApellido);
		
		txtApellidos = new JTextField();
		txtApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(512, 57, 249, 22);
		getContentPane().add(txtApellidos);
		
		txtFechaDesde = new JTextField();
		txtFechaDesde.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFechaDesde.setColumns(10);
		txtFechaDesde.setBounds(251, 94, 123, 22);
		getContentPane().add(txtFechaDesde);
		
		lblGrado = new JLabel("Grado");
		lblGrado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGrado.setBounds(407, 129, 61, 19);
		getContentPane().add(lblGrado);
		
		lblPais = new JLabel("Pa\u00EDs");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPais.setBounds(38, 128, 61, 20);
		getContentPane().add(lblPais);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFiltrar.setBounds(814, 50, 132, 47);
		getContentPane().add(btnFiltrar);
		
		cboPais = new JComboBox<String>();
		cboPais.setModel(new DefaultComboBoxModel<String>(new String[] {"[Seleccione]", "Per\u00FA", "Chile", "Ecuador", "Colombia", "Argentina"}));
		cboPais.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboPais.setBounds(125, 126, 145, 25);
		getContentPane().add(cboPais);
		
		cboGrado = new JComboBox<String>();
		cboGrado.setModel(new DefaultComboBoxModel<String>(new String[] {"[Seleccione]", "T\u00E9cnico", "Licenciado", "Doctor", "Autor"}));
		cboGrado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboGrado.setBounds(518, 123, 132, 25);
		getContentPane().add(cboGrado);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 166, 908, 325);
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
		scrollPane.setViewportView(tblAutor);
		
		lblFechaNacimientoHasta = new JLabel("Fecha de nacimiento hasta");
		lblFechaNacimientoHasta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFechaNacimientoHasta.setBounds(408, 97, 204, 20);
		getContentPane().add(lblFechaNacimientoHasta);
		
		txtFechaHasta = new JTextField();
		txtFechaHasta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFechaHasta.setColumns(10);
		txtFechaHasta.setBounds(638, 95, 123, 22);
		getContentPane().add(txtFechaHasta);
		
		btnLimpiar = new JButton("Limpiar filtros");
		btnLimpiar.addActionListener(this);
		btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLimpiar.setBounds(814, 108, 132, 47);
		getContentPane().add(btnLimpiar);
		
		
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
		
		
	}
	
	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLimpiar) {
			actionPerformedBtnLimpiar(e);
		}
		if (e.getSource() == btnFiltrar) {
			actionPerformedBtnFiltrar(e);
		}
	}
	protected void actionPerformedBtnFiltrar(ActionEvent e) {
		String nombresUI = txtNombres.getText();
		String apellidosUI = txtApellidos.getText();
		String fechaDesdeUI = txtFechaDesde.getText();
		String fechaHastaUI = txtFechaHasta.getText();
		String paisUI = cboPais.getSelectedItem().toString();
		String gradoUI = cboGrado.getSelectedItem().toString();
		if(paisUI != "[Seleccione]") {
			paisUI = cboPais.getSelectedItem().toString();
		}else {paisUI = "";}
		if(gradoUI != "[Seleccione]") {
			gradoUI = cboGrado.getSelectedItem().toString();
		}else {gradoUI = "";}
		
		AutorModel mAutor = new AutorModel();
		List<Autor> listAutor = mAutor.ConsultaReporteAutor(nombresUI, apellidosUI, fechaDesdeUI, fechaHastaUI, paisUI, gradoUI);
		
		DefaultTableModel dt = (DefaultTableModel) tblAutor.getModel();
		dt.setRowCount(0);
		Object[] fila = null;
		for(Autor autor: listAutor) {
			fila = new Object[] {
				autor.getIdAutor(), autor.getNombres(), autor.getApellidos(), autor.getFechaNacimiento(), autor.getPais(), autor.getGrado() 	
			};
			dt.addRow(fila);
						
		}
	
	
	}
	protected void actionPerformedBtnLimpiar(ActionEvent e) {
		limpiarDatos();
		DefaultTableModel dt = (DefaultTableModel) tblAutor.getModel();
		dt.setRowCount(0);
	}
	
	public void limpiarDatos() {
		txtNombres.setText("");
		txtApellidos.setText("");
		txtFechaDesde.setText("");
		txtFechaHasta.setText("");
		cboPais.setSelectedIndex(0);
		cboGrado.setSelectedIndex(0);
		txtNombres.requestFocus();
	}
	
	
}
