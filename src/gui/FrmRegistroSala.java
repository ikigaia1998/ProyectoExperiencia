package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entidad.Sala;
import model.SalaModel;
import util.Validaciones;

public class FrmRegistroSala extends JInternalFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JComboBox<String> cboPiso;
	private JComboBox<String> cboSede;
	private JTextField txtNumero;
	private JTextField txtNumAlumn;
	private JTextField txtRecursos;
	private JButton btnRegistrar;

	public FrmRegistroSala() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Sala");
		setBounds(100, 100, 900, 550);
		getContentPane().setLayout(null);

		lblNewLabel = new JLabel("N\u00FAmero:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(301, 127, 154, 13);
		getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Piso:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(301, 168, 154, 13);
		getContentPane().add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("N\u00FAmero de alumnos:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(301, 208, 154, 13);
		getContentPane().add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Recursos;");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(301, 249, 154, 13);
		getContentPane().add(lblNewLabel_3);

		lblNewLabel_5 = new JLabel("Sede:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(301, 294, 154, 13);
		getContentPane().add(lblNewLabel_5);

		lblNewLabel_6 = new JLabel("Registro de sala");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel_6.setBounds(10, 26, 868, 53);
		getContentPane().add(lblNewLabel_6);

		cboPiso = new JComboBox<String>();
		cboPiso.setModel(new DefaultComboBoxModel<String>(new String[] { "Seleccione", "1", "2", "3", "4", "5" }));
		cboPiso.setBounds(465, 166, 96, 21);
		getContentPane().add(cboPiso);

		cboSede = new JComboBox<String>();
		cboSede.setModel(new DefaultComboBoxModel<String>(new String[] { "Seleccione", "Lima", "Bellavista ", "Bre\u00F1a",
				"Arequipa", "Trujillo", "Independencia", "Surco" }));
		cboSede.setBounds(465, 292, 96, 21);
		getContentPane().add(cboSede);

		txtNumero = new JTextField();
		txtNumero.setBounds(465, 126, 96, 19);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);

		txtNumAlumn = new JTextField();
		txtNumAlumn.addKeyListener(this);
		txtNumAlumn.setBounds(465, 207, 96, 19);
		getContentPane().add(txtNumAlumn);
		txtNumAlumn.setColumns(10);

		txtRecursos = new JTextField();
		txtRecursos.setBounds(465, 248, 96, 19);
		getContentPane().add(txtRecursos);
		txtRecursos.setColumns(10);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRegistrar.setBounds(392, 346, 109, 21);
		getContentPane().add(btnRegistrar);
	}

	public void mensaje(String msje) {
		JOptionPane.showMessageDialog(this, msje);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrar(e);
		}
	}

	protected void actionPerformedBtnRegistrar(ActionEvent e) {
		String num = txtNumero.getText();
		String piso = cboPiso.getSelectedItem().toString();
		String numAlu = txtNumAlumn.getText();
		String rec = txtRecursos.getText();
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
			obj.setSede(sede);
			
			SalaModel model = new SalaModel();
			int salida = model.insertaSala(obj);
			if (salida > 0) {
				mensaje("Se insertó correctamente");
			} else {
				mensaje("Error en el registro");
			}
		}
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