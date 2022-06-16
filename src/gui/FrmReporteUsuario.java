package gui;
//imports
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import entidad.Usuario;
import model.UsuarioModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import util.GeneradorReporte;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FrmReporteUsuario extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtDNI;
	private JTextField txtInicio;
	private JTextField txtFin;
	private JTextField txtApellidos;
	private JButton btnFiltrar;
	private JPanel panelReporte;

	public FrmReporteUsuario() {
		getContentPane().setBackground(Color.CYAN);
		getContentPane().setForeground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte de Usuario");
		setBounds(100, 100, 1200, 550);
		getContentPane().setLayout(null);
		
		JLabel lblReporteDeUsuario = new JLabel("Reporte de Usuario");
		lblReporteDeUsuario.setForeground(Color.BLUE);
		lblReporteDeUsuario.setBackground(Color.WHITE);
		lblReporteDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteDeUsuario.setFont(new Font("Arial Black", Font.ITALIC, 18));
		lblReporteDeUsuario.setBounds(67, 10, 886, 48);
		getContentPane().add(lblReporteDeUsuario);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(156, 120, 69, 14);
		getContentPane().add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(253, 118, 149, 20);
		getContentPane().add(txtNombre);
		
		JLabel lblNewLabel_1 = new JLabel("DNI");
		lblNewLabel_1.setBounds(156, 198, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(253, 196, 149, 20);
		getContentPane().add(txtDNI);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de Inicio");
		lblFechaDeInicio.setBounds(463, 153, 91, 20);
		getContentPane().add(lblFechaDeInicio);
		
		txtInicio = new JTextField();
		txtInicio.setColumns(10);
		txtInicio.setBounds(578, 154, 149, 20);
		getContentPane().add(txtInicio);
		
		JLabel lblFechaDeFin = new JLabel("Fecha de Fin");
		lblFechaDeFin.setBounds(463, 198, 85, 14);
		getContentPane().add(lblFechaDeFin);
		
		txtFin = new JTextField();
		txtFin.setColumns(10);
		txtFin.setBounds(578, 196, 149, 20);
		getContentPane().add(txtFin);
		
		JLabel lblNewLabel_2 = new JLabel("Apellidos");
		lblNewLabel_2.setBounds(156, 157, 69, 13);
		getContentPane().add(lblNewLabel_2);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(253, 154, 149, 20);
		getContentPane().add(txtApellidos);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setFont(new Font("Arial Black", Font.ITALIC, 24));
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(879, 153, 162, 59);
		getContentPane().add(btnFiltrar);
		
		panelReporte = new JPanel();
		panelReporte.setBorder(new TitledBorder(null, "Reportes", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelReporte.setBounds(26, 237, 1099, 259);
		getContentPane().add(panelReporte);
		panelReporte.setLayout(new BorderLayout());

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFiltrar) {
			actionPerformedBtnFiltrarJButton(e);
		}
	}
	protected void actionPerformedBtnFiltrarJButton(ActionEvent e) {
		String nombre = txtNombre.getText();
		String apellido = txtApellidos.getText();
		String dni = txtDNI.getText();
		String desde = txtInicio.getText();
		String hasta = txtFin.getText();
		
		UsuarioModel model = new UsuarioModel();
		List<Usuario>  listaUsuario = model.consultaPorNombreDNIFecha(nombre, apellido, dni, desde, hasta);
		
		JRBeanCollectionDataSource dataSource = new  JRBeanCollectionDataSource(listaUsuario);
		
		String jasper = "reporteUsu.jasper";
		
		JasperPrint print = GeneradorReporte.genera(jasper, dataSource, null);
		
		JRViewer JRViewer = new JRViewer(print);
		
		panelReporte.removeAll();
		panelReporte.add(JRViewer);
		panelReporte.repaint();
		panelReporte.revalidate();
	}
}
