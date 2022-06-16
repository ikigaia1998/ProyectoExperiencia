package gui;
//import
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.util.List;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import entidad.Usuario;
import model.UsuarioModel;
import java.awt.Color;

public class FrmConsultaUsuario extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtDNI;
	private JTextField txtApellido;
	private JTextField txtInicio;
	private JTextField txtFin;
	private JButton btnFiltrar;
	private JTable table;

	public FrmConsultaUsuario() {
		getContentPane().setBackground(Color.CYAN);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Usuario");
		setBounds(100, 100, 1000, 550);
		getContentPane().setLayout(null);
		
		JLabel lblConsultaDeUsuario = new JLabel("Consulta de Usuario");
		lblConsultaDeUsuario.setForeground(Color.BLUE);
		lblConsultaDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaDeUsuario.setFont(new Font("Arial Black", Font.ITALIC, 19));
		lblConsultaDeUsuario.setBounds(46, 10, 886, 48);
		getContentPane().add(lblConsultaDeUsuario);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(59, 127, 69, 14);
		getContentPane().add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(156, 123, 149, 20);
		getContentPane().add(txtNombre);
		
		JLabel lblNewLabel_1 = new JLabel("DNI");
		lblNewLabel_1.setBounds(59, 221, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(156, 219, 149, 20);
		getContentPane().add(txtDNI);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(59, 172, 69, 14);
		getContentPane().add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(156, 170, 149, 20);
		getContentPane().add(txtApellido);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de Inicio");
		lblFechaDeInicio.setBounds(391, 215, 104, 20);
		getContentPane().add(lblFechaDeInicio);
		
		txtInicio = new JTextField();
		txtInicio.setColumns(10);
		txtInicio.setBounds(488, 215, 149, 20);
		getContentPane().add(txtInicio);
		
		JLabel lblFechaDeFin = new JLabel("Fecha de Fin");
		lblFechaDeFin.setBounds(712, 218, 85, 14);
		getContentPane().add(lblFechaDeFin);
		
		txtFin = new JTextField();
		txtFin.setColumns(10);
		txtFin.setBounds(807, 215, 149, 20);
		getContentPane().add(txtFin);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(611, 116, 149, 37);
		getContentPane().add(btnFiltrar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(46, 261, 912, 222);
		getContentPane().add(scrollPane_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Código", "Nombre", "Apellido", "DNI", "Login", "Password", "Correo","Fecha de Nacimiento", "Dirección", "País"
			}
		));
		scrollPane_1.setViewportView(table);
	}

	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFiltrar) {
			actionPerformedBtnFiltrarJButton(e);
		}
	}
	protected void actionPerformedBtnFiltrarJButton(ActionEvent e) {
		String nombre = txtNombre.getText();
		String apellido = txtApellido.getText();
		String dni = txtDNI.getText();
		String desde = txtInicio.getText();
		String hasta = txtFin.getText();
		
		UsuarioModel model = new UsuarioModel();
		List<Usuario>  listaUsuario = model.consultaPorNombreDNIFecha(nombre, apellido, dni, desde, hasta);
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		
		Object[] fila = null;
		
		for (Usuario x : listaUsuario) {
			fila = new Object[]{x.getIdUsuario(), x.getNombre(), x.getApellido(), x.getDni(),x.getLogin(),x.getPassword(),x.getCorreo()
					, x.getFechaNacimiento(), x.getDireccion(), x.getPais() };
			
			dtm.addRow(fila);
			
		}
		
	}
}
