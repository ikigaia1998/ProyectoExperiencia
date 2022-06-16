package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import entidad.Proveedor;
import model.ProveedorModel;
import util.Excel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.ImageIcon;

public class FrmConsultaProveedor extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtNombre;
	private JLabel lblNewLabel_2;
	private JTextField txtApellido;
	private JLabel lblNewLabel_3;
	private JTextField txtDni;
	private JLabel lblNewLabel_4;
	private JTextField txtTelefono;
	private JLabel lblNewLabel_5;
	private JTextField txtDireccion;
	private JLabel lblNewLabel_6;
	private JComboBox cboPais;
	private JLabel lblNewLabel_7;
	private JTextField txtFecDesde;
	private JLabel lblNewLabel_8;
	private JTextField txtFecHasta;
	private JScrollPane scrollPane;
	private JTable tblConsulta;
	private JButton btnBuscar;
	private JLabel lblNewLabel_9;
	private JTextField txtCorreo;
	int hoveredRow=-1,hoveredColumn=-1;
	private JButton btnExportar;
	public FrmConsultaProveedor() {
		getContentPane().setBackground(new Color(153, 153, 255));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Proveedor");
		setBounds(100, 100, 1040, 550);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Consulta Proveedor");
		lblNewLabel.setFont(new Font("Sylfaen", Font.BOLD, 24));
		lblNewLabel.setBounds(354, 22, 259, 37);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Nombres");
		lblNewLabel_1.setBounds(39, 118, 69, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(118, 115, 140, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Apellidos");
		lblNewLabel_2.setBounds(39, 140, 69, 14);
		getContentPane().add(lblNewLabel_2);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(118, 140, 140, 20);
		getContentPane().add(txtApellido);
		
		lblNewLabel_3 = new JLabel("DNI");
		lblNewLabel_3.setBounds(39, 168, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(118, 168, 140, 20);
		getContentPane().add(txtDni);
		
		lblNewLabel_4 = new JLabel("Tel\u00E9fono");
		lblNewLabel_4.setBounds(268, 143, 59, 14);
		getContentPane().add(lblNewLabel_4);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(337, 140, 118, 20);
		getContentPane().add(txtTelefono);
		
		lblNewLabel_5 = new JLabel("Direcci\u00F3n");
		lblNewLabel_5.setBounds(268, 118, 69, 14);
		getContentPane().add(lblNewLabel_5);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(337, 115, 169, 20);
		getContentPane().add(txtDireccion);
		
		lblNewLabel_6 = new JLabel("Pa\u00EDs");
		lblNewLabel_6.setBounds(268, 168, 46, 14);
		getContentPane().add(lblNewLabel_6);
		
		cboPais = new JComboBox();
		cboPais.setModel(new DefaultComboBoxModel(new String[] {"", "Per\u00FA", "Chile", "Colombia", "Brazil"}));
		cboPais.setBounds(337, 164, 118, 22);
		getContentPane().add(cboPais);
		
		lblNewLabel_7 = new JLabel("Fecha de Inicio");
		lblNewLabel_7.setBounds(516, 121, 110, 14);
		getContentPane().add(lblNewLabel_7);
		
		txtFecDesde = new JTextField();
		txtFecDesde.setColumns(10);
		txtFecDesde.setBounds(624, 118, 110, 20);
		getContentPane().add(txtFecDesde);
		
		lblNewLabel_8 = new JLabel("Fecha de Fin");
		lblNewLabel_8.setBounds(516, 149, 110, 14);
		getContentPane().add(lblNewLabel_8);
		
		txtFecHasta = new JTextField();
		txtFecHasta.setColumns(10);
		txtFecHasta.setBounds(624, 146, 110, 20);
		getContentPane().add(txtFecHasta);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 210, 1004, 299);
		getContentPane().add(scrollPane);
		
		tblConsulta = new JTable();
		tblConsulta.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID Proveedor", "Nombres", "Apellidos", "DNI", "Direcci\u00F3n", "Tel\u00E9fono", "Correo", "Pa\u00EDs", "Fecha Registro"
			}
		));
		tblConsulta.getColumnModel().getColumn(1).setPreferredWidth(110);
		tblConsulta.getColumnModel().getColumn(2).setPreferredWidth(110);
		tblConsulta.getColumnModel().getColumn(6).setPreferredWidth(100);
		tblConsulta.getColumnModel().getColumn(8).setPreferredWidth(88);
		scrollPane.setViewportView(tblConsulta);
		
		//selecciona una sola fila
		tblConsulta.setRowSelectionAllowed(true);
		tblConsulta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//desabilita mover las columnas
		tblConsulta.getTableHeader().setReorderingAllowed(false);
		
		scrollPane.setViewportView(tblConsulta);
		
		//color de la fila seleccionada
		tblConsulta.setSelectionBackground(Color.GREEN);
		
	    //No se pueda editar
		tblConsulta.setDefaultEditor(Object.class, null);
	    
		//Efecto Rollover
		tblConsulta.addMouseMotionListener(new MouseMotionListener() {
	        @Override
	        public void mouseMoved(MouseEvent e) {
	            Point p = e.getPoint();
	            hoveredRow = tblConsulta.rowAtPoint(p);
	            hoveredColumn = tblConsulta.columnAtPoint(p);
	            tblConsulta.setRowSelectionInterval(hoveredRow, hoveredRow);
	            tblConsulta.repaint();    
	        }
	        @Override
	        public void mouseDragged(MouseEvent e) {
	            hoveredRow = hoveredColumn = -1;
	            tblConsulta.repaint();
	        }
	    });
		lista();
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Arial", Font.BOLD, 15));
		btnBuscar.setBackground(new Color(0, 153, 102));
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(786, 109, 145, 31);
		getContentPane().add(btnBuscar);
		
		lblNewLabel_9 = new JLabel("Correo");
		lblNewLabel_9.setBounds(516, 174, 110, 14);
		getContentPane().add(lblNewLabel_9);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(624, 171, 110, 20);
		getContentPane().add(txtCorreo);
		
		btnExportar = new JButton("Exportar");
		btnExportar.addActionListener(this);
		btnExportar.setFont(new Font("Arial", Font.BOLD, 15));
		btnExportar.setBackground(new Color(0, 153, 102));
		btnExportar.setBounds(786, 151, 145, 31);
		getContentPane().add(btnExportar);
	}

	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnExportar) {
			try {
				actionPerformedBtnExportar(e);
			} catch (IOException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(e);
		}
	}
	protected void actionPerformedBtnBuscar(ActionEvent e) {
		String nom=txtNombre.getText();
		String ape=txtApellido.getText();
		String dni=txtDni.getText();
		String dir=txtDireccion.getText();
		String tel=txtTelefono.getText();
		String cor=txtCorreo.getText();
		String pais=cboPais.getSelectedItem().toString();
		String fecIni=txtFecDesde.getText();
		String fecFin=txtFecHasta.getText();
		
		DefaultTableModel dtm=(DefaultTableModel)tblConsulta.getModel();
		dtm.setRowCount(0);
		ProveedorModel model= new ProveedorModel();
		List<Proveedor>lstProveedor = model.consultaValores(nom, ape, dni, dir, tel, cor, pais, fecIni, fecFin);
		Object[]fila = null;
		for(Proveedor x:lstProveedor) {
			fila=new Object[] {x.getIdProveedor(),x.getNombre(),x.getApellido(),x.getDni()
					,x.getDireccion(),x.getTelefono(),x.getCorreo(),x.getPais(),x.getFechaRegistro()};
			dtm.addRow(fila);
		}	
	}
	
	private void lista() {
		ProveedorModel model= new ProveedorModel();
		List<Proveedor> lista=model.listarProveedor();
		DefaultTableModel dtm=(DefaultTableModel) tblConsulta.getModel();
		dtm.setRowCount(0);
		Object[] fila = null;
		for (Proveedor x : lista) {
			fila = new Object[] {x.getIdProveedor(), x.getNombre(), x.getApellido(),
					x.getDni(), x.getDireccion(),x.getTelefono(),x.getCorreo(),x.getPais(),x.getFechaRegistro()};
			dtm.addRow(fila);
		}
	}
	protected void actionPerformedBtnExportar(ActionEvent e) throws IOException, SQLException {
		Excel rp = new Excel();
		rp.Reporte();
		
	}
}
