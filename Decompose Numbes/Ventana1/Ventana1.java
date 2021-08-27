package Ventana1;

import java.awt.Color;
import java.awt.Image;
import java.awt.TextArea;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import FrameBase.Marco;

public class Ventana1 {

	// Ventana 1
	public static JLabel panel;

	// text area
	private TextArea resultado = new TextArea("Resultado:\n");
	private JTextField jtextfield = new JTextField();

	// Boton de cola
	private JButton bcola = new JButton("Ingresar   ");
	private ImageIcon ic2 = new ImageIcon(getClass().getResource("/Componentes/show.png"));
	private Icon icono2 = new ImageIcon(ic2.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

	public void crear(ImageIcon a) {

		// Configuraciones de caja de texto
		jtextfield.setEditable(true);
		jtextfield.repaint();
		jtextfield.setForeground(Color.WHITE);
		jtextfield.setBounds(130, 60, 120, 35);
		jtextfield.setBackground(new Color(171, 162, 167));

		// Configuraciones del boton ingresar
		new Marco().armar(bcola, icono2, "Descomponer...", "cola");
		bcola.setBounds(130, 150, 120, 45);

		// Configurar panel
		panel = new JLabel(a);

		// configurar texto
		resultado.repaint();
		resultado.setEditable(false);
		resultado.setBounds(260, 20, 230, 210);
		resultado.setForeground(Color.WHITE);
		resultado.setBackground(new Color(171, 162, 167));

		// agregar botones al panel principal
		panel.add(resultado);
		panel.add(jtextfield);
		panel.add(bcola);
	}

	public void ingresar(String texto) {
		resultado.setText("");
		resultado.append(texto);
		resultado.repaint();
	}

	public int leer() {
		int res = 0;
		try{
			res = Integer.parseInt(jtextfield.getText());
		}catch(Exception e)
		{
			System.out.println("Ocurrio:"+e);
		}
		return res;
	}

}
