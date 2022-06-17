package gui.Articulo;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import entidad.Articulo;
import entidad.Proveedor;
import model.ProveedorModel;
import util.Validaciones;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class FrmRegistroArticulo extends JInternalFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblTitulo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblStock;
	private JTextField txtStock;
	private JLabel lblDetalle;
	private JTextField txtDetalle;
	private JLabel lblCategoria;
	private JButton btnNewButton;
	private JComboBox<String> cboCategoria;
	private JLabel lblProveedor;
	private JComboBox<String> cboProveedor;
	

	public FrmRegistroArticulo() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Proveedor");
		setBounds(100, 100, 443, 436);
		getContentPane().setLayout(null);
		
		lblTitulo = new JLabel("Registro de Art\u00EDculo");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitulo.setBounds(127, 11, 194, 35);
		getContentPane().add(lblTitulo);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(49, 110, 72, 14);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(177, 107, 144, 20);
		getContentPane().add(txtNombre);
		
		lblStock = new JLabel("Stock");
		lblStock.setBounds(49, 138, 72, 14);
		getContentPane().add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(177, 132, 144, 20);
		getContentPane().add(txtStock);
		
		lblDetalle = new JLabel("Detalle");
		lblDetalle.setBounds(49, 166, 72, 14);
		getContentPane().add(lblDetalle);
		
		txtDetalle = new JTextField();
		txtDetalle.addKeyListener(this);
		txtDetalle.setColumns(10);
		txtDetalle.setBounds(177, 160, 144, 20);
		getContentPane().add(txtDetalle);
		
		lblCategoria = new JLabel("Categor\u00EDa");
		lblCategoria.setBounds(49, 224, 72, 14);
		getContentPane().add(lblCategoria);
		
		cboCategoria= new JComboBox<String>();
		cboCategoria.addItem("Seleccione:");
		cboCategoria.addItem("Peru");
		cboCategoria.addItem("Ecuador");
		cboCategoria.addItem("Chile");
		cboCategoria.addItem("Colombia");
		cboCategoria.setBounds(177, 220, 144, 22);
		getContentPane().add(cboCategoria);
				
		
		btnNewButton = new JButton("Enviar");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(127, 287, 89, 23);
		getContentPane().add(btnNewButton);
		
		lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(49, 195, 72, 14);
		getContentPane().add(lblProveedor);
		
		cboProveedor = new JComboBox<String>();
		cboProveedor.setBounds(177, 191, 144, 22);
		getContentPane().add(cboProveedor);
	}
	
	public void mensaje(String msje) {
		JOptionPane.showMessageDialog(this, msje);
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			actionPerformedBtnNewButton(e);
		}
	}
	protected void actionPerformedBtnNewButton(ActionEvent e) {
		//obtenemos datos
		String nombre = txtNombre.getText();
		String stock= txtStock.getText();
		String detalle=txtDetalle.getText();
		String categoria = cboCategoria.getSelectedItem().toString();
		String proveedor =cboProveedor.getSelectedItem().toString();
		
		
		if(!nombre.matches(Validaciones.TEXTO)) {
			mensaje("El nombre debe tener entre 3 a 30 caracteres");
		}else if (!stock.matches(Validaciones.NUMERO)) {
			mensaje("Ingrese un número");
		}else if(!detalle.matches(Validaciones.TEXTO)) {
			mensaje("Ingrese la descripción");
		}else if(cboCategoria.getSelectedIndex()==0) {
			mensaje("Seleccione una categoría");
		}else if(cboProveedor.getSelectedIndex()==0) {
			mensaje("Seleccione un proveedor");
		}else {
			Articulo art = new Articulo();
			art.setNombre(nombre);
			art.setStock(stock);
			art.setDetalle(detalle);
			art.setCategoria(categoria)
			;
			
			ProveedorModel proModel= new ProveedorModel();
			int salida = proModel.insertaProveedor(pro);
			if(salida>0) {
				mensaje("Se insertó Correctamente");
			}else {
				mensaje("Error en el registro");
			}
		}
		
	limpiar();	
	}
	
	
	
	
	public void limpiar() {
		txtNombre.setText(null);
		txtStock.setText(null);
		txtDetalle.setText(null);
		txtDireccion.setText(null);
		txtTelefono.setText(null);
		txtCorreo.setText(null);
		cboCategoria.setSelectedIndex(-1);
		txtNombre.requestFocus();
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtDetalle) {
			keyTypedtxtDetalle(e);
		}
	}
	protected void keyTypedtxtDetalle(KeyEvent e) {
		if(Character.isLetter(e.getKeyChar())) {
			e.consume();}
		String dniGenerado = txtDetalle.getText().trim()+e.getKeyChar();
		if(dniGenerado.length()>8) {
			e.consume();
		}
		
	}
}
