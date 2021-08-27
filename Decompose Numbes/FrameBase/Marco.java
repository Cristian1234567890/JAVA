package FrameBase;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.*;
import Ventana1.Ventana1;
import Secundaria.Secundaria;

public class Marco implements ActionListener{
	
	//fondo de ventanas
	private ImageIcon fondo = new ImageIcon(getClass().getResource("/Componentes/fondo.jpg"));
	
	//ventana secundaria
	static private Ventana1 primera;
	
	
	//declarar creacion de secundaria
	private Secundaria sec = new Secundaria();
	
	//Configurar frame
	private static JFrame r;
	private ImageIcon s= new ImageIcon(getClass().getResource("/Componentes/Fisc.png"));
	private Image image= s.getImage();
	
	
	//metodo que inicializa el frame(marco)
	public void crear() {
		
		//Ventana 1
		primera=  new Ventana1();
		primera.crear(fondo);

		
		//inicializar marco
		r = new JFrame();
		
		//agregar ventana principal al marco
		r.add(primera.panel);
		r.pack();
		r.setLocationRelativeTo(null);
		
		
		//configurar marco
		r.setResizable(false);
		r.setVisible(true);
        r.setTitle("Descomponer valores");
		r.setIconImage(image);
		//r.setSize(600, 300);
		

		//Metodo encargado de cerrar el programa al precionar exit(X)
 		r.addWindowListener(new WindowAdapter()
        {
              public void windowClosing(WindowEvent evt)
              {
                     System.exit(0);
              }
        });

	}
	
	//Metodo que realizar acciones al presionar un boton.
	 public void actionPerformed(ActionEvent e)
     {
			if(e.getActionCommand()=="cola")
			{
				try{
					sec.ingresar(primera.leer());
					primera.ingresar(sec.descomponer());
				}
				catch(Exception a)
				{
					System.out.println(a);
				}
				r.repaint();
				r.pack();
				//System.out.println("En construccion de cola :(");
			}
     }
	 
	 //Armador de los botones
	 public void armar(JButton a, Icon b, String c, String d)
	 {
			a.setBackground(new Color(171, 162, 167));
			a.setBorder(new TitledBorder(new LineBorder(Color.gray) ));
			a.setForeground(Color.WHITE);
			a.setRolloverIcon(b);
			a.setIcon(b);
			a.setToolTipText(c);
			a.setActionCommand(d);
			a.addActionListener(this);
	 }
}
