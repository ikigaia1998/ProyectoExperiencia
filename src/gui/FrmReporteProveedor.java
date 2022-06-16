package gui;

import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import entidad.Proveedor;
import model.ProveedorModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import util.GeneradorReporte;

import javax.swing.border.BevelBorder;

import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.BorderLayout;

public class FrmReporteProveedor extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblReporteProveedor;
	private JLabel lblNewLabel_1;
	private JTextField txtNombre;
	private JLabel lblNewLabel_2;
	private JTextField txtDireccion;
	private JLabel lblNewLabel_3;
	private JTextField txtFecDesde;
	private JLabel lblNewLabel_4;
	private JTextField txtApellido;
	private JLabel lblNewLabel_5;
	private JTextField txtTelefono;
	private JLabel lblNewLabel_6;
	private JTextField txtFecHasta;
	private JLabel lblNewLabel_7;
	private JTextField txtDni;
	private JComboBox cboPais;
	private JButton btnGenerar;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_8;
	private JTextField txtCorreo;
	private JPanel panelReporte;

	public FrmReporteProveedor() {
		getContentPane().setBackground(new Color(0, 153, 255));
		getContentPane().setForeground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte de Proveedor");
		setBounds(100, 100, 1183, 767);
		getContentPane().setLayout(null);
		
		lblReporteProveedor = new JLabel("Reporte Proveedor");
		lblReporteProveedor.setFont(new Font("Sylfaen", Font.BOLD, 24));
		lblReporteProveedor.setBounds(477, 11, 238, 37);
		getContentPane().add(lblReporteProveedor);
		
		lblNewLabel_1 = new JLabel("Nombres");
		lblNewLabel_1.setBounds(84, 107, 69, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(163, 104, 124, 20);
		getContentPane().add(txtNombre);
		
		lblNewLabel_2 = new JLabel("Direcci\u00F3n");
		lblNewLabel_2.setBounds(313, 107, 69, 14);
		getContentPane().add(lblNewLabel_2);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(379, 104, 132, 20);
		getContentPane().add(txtDireccion);
		
		lblNewLabel_3 = new JLabel("Fecha de Inicio");
		lblNewLabel_3.setBounds(521, 107, 110, 14);
		getContentPane().add(lblNewLabel_3);
		
		txtFecDesde = new JTextField();
		txtFecDesde.setColumns(10);
		txtFecDesde.setBounds(629, 104, 124, 20);
		getContentPane().add(txtFecDesde);
		
		lblNewLabel_4 = new JLabel("Apellidos");
		lblNewLabel_4.setBounds(84, 129, 69, 14);
		getContentPane().add(lblNewLabel_4);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(163, 129, 124, 20);
		getContentPane().add(txtApellido);
		
		lblNewLabel_5 = new JLabel("Tel\u00E9fono");
		lblNewLabel_5.setBounds(313, 132, 59, 14);
		getContentPane().add(lblNewLabel_5);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(379, 129, 132, 20);
		getContentPane().add(txtTelefono);
		
		lblNewLabel_6 = new JLabel("Fecha de Fin");
		lblNewLabel_6.setBounds(521, 135, 110, 14);
		getContentPane().add(lblNewLabel_6);
		
		txtFecHasta = new JTextField();
		txtFecHasta.setColumns(10);
		txtFecHasta.setBounds(629, 132, 124, 20);
		getContentPane().add(txtFecHasta);
		
		lblNewLabel_7 = new JLabel("DNI");
		lblNewLabel_7.setBounds(84, 157, 46, 14);
		getContentPane().add(lblNewLabel_7);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(163, 157, 86, 20);
		getContentPane().add(txtDni);
		
		cboPais = new JComboBox();
		cboPais.setModel(new DefaultComboBoxModel(new String[] {"", "Per\u00FA", "Chile", "Colombia", "Brazil"}));
		cboPais.setBounds(379, 153, 132, 22);
		getContentPane().add(cboPais);
		
		btnGenerar = new JButton("Generar");
		btnGenerar.addActionListener(this);
		btnGenerar.setFont(new Font("Arial", Font.BOLD, 13));
		btnGenerar.setForeground(Color.WHITE);
		btnGenerar.setBackground(new Color(102, 0, 51));
		btnGenerar.setBounds(838, 164, 145, 31);
		getContentPane().add(btnGenerar);
		
		lblNewLabel = new JLabel("Pa\u00EDs");
		lblNewLabel.setBounds(313, 157, 46, 14);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_8 = new JLabel("Correo");
		lblNewLabel_8.setBounds(521, 160, 110, 14);
		getContentPane().add(lblNewLabel_8);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(629, 157, 86, 20);
		getContentPane().add(txtCorreo);
		
		panelReporte = new JPanel();
		panelReporte.setBorder(new TitledBorder(null, "Reporte", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelReporte.setBackground(new Color(102, 153, 255));
		panelReporte.setBounds(10, 219, 1147, 507);
		getContentPane().add(panelReporte);
		panelReporte.setLayout(new BorderLayout(0, 0));

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGenerar) {
			actionPerformedBtnGenerar(e);
		}
	}
	protected void actionPerformedBtnGenerar(ActionEvent e) {
		String nom=txtNombre.getText();
		String ape=txtApellido.getText();
		String dni=txtDni.getText();
		String dir=txtDireccion.getText();
		String tel=txtTelefono.getText();
		String cor=txtCorreo.getText();
		String pais=cboPais.getSelectedItem().toString();
		String fecIni=txtFecDesde.getText();
		String fecFin=txtFecHasta.getText();
		
		ProveedorModel model = new ProveedorModel();
		List<Proveedor> lstProveedor = model.consultaValores(nom, ape, dni, dir, tel, cor, pais, fecIni, fecFin);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lstProveedor);
		
		String reporte ="Proveedor.jasper";
		
		JasperPrint print = GeneradorReporte.genera(reporte, dataSource,null);
		
		JRViewer jasperViewer = new JRViewer(print);
		panelReporte.removeAll();
		panelReporte.add(jasperViewer);
		panelReporte.repaint();
		panelReporte.revalidate();
		
	}
}
