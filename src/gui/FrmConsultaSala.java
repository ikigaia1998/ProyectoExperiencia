package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class FrmConsultaSala extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	public FrmConsultaSala() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Sala");
		setBounds(100, 100, 1000, 550);
		getContentPane().setLayout(null);
	}

	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}

}
