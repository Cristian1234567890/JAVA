import javax.swing.*;
import java.awt.Component;
import java.awt.event.*;


public class Main implements ActionListener {
	JFrame ventana;
	JButton btn_boton, btn_iniciar, btn_reset, btn_esconder;
	JTextField mover, intentos, posicion;
	JTextArea presentacion;
	JLabel lb_num, lb_este, lb_norte, lb_sur, lb_oeste;
	int numintentos;
	
	Espia datos=new Espia();
	
	public static void main(String[] args) {
		new Main("Espia escondidiso");
	}

	Main (String name) {
		ventana = new JFrame(name);
		ventana.setBounds(500, 100, 800, 750);
		ventana.setLayout(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);

		posicion = new JTextField();
		posicion.setBounds(160, 550, 130, 40);
		posicion.setEditable(false);
		posicion.setVisible(false);
		ventana.add(posicion);
		
		intentos= new JTextField();
		intentos.setBounds(160, 600, 130, 40);
		intentos.setEditable(false);
		intentos.setText("Numero de intentos: "+numintentos);
		ventana.add(intentos);
		
		mover = new JTextField();
		mover.setBounds(160, 500, 130, 40);
		mover.setEditable(false);
		ventana.add(mover);
		
		btn_esconder = new JButton("Esconder y mostrar");
		btn_esconder.setBounds(300, 550, 175, 40);
		btn_esconder.addActionListener(this);
		ventana.add(btn_esconder);
		
		btn_iniciar = new JButton("Iniciar");
		btn_iniciar.setBounds(300, 500, 80, 40);
		btn_iniciar.addActionListener(this);
		ventana.add(btn_iniciar);

		btn_reset = new JButton("Reiniciar");
		btn_reset.setBounds(390, 500, 85, 40);
		btn_reset.addActionListener(this);
		ventana.add(btn_reset);

		presentacion = new JTextArea();
		presentacion.setBounds(10, 140, 295, 300);
		ventana.add(presentacion);
		presentacion.setEditable(false);
		presentacion.setBackground(UIManager.getColor("CheckBox.background"));
		
		presentacion.setText("  ");
		
		lb_este = new JLabel("Este");
		lb_este.setBounds(740, 260, 90, 40);
		ventana.add(lb_este);

		lb_norte = new JLabel("Norte");
		lb_norte.setBounds(540, 50, 90, 40);
		ventana.add(lb_norte);

		lb_sur = new JLabel("Sur");
		lb_sur.setBounds(540, 455, 90, 40);
		ventana.add(lb_sur);

		lb_oeste = new JLabel("Oeste");
		lb_oeste.setBounds(310, 260, 90, 40);
		ventana.add(lb_oeste);

		for (int i = 0; i < 10; i++) {
			lb_num = new JLabel(String.valueOf(10 - i));
			lb_num.setBounds(360, 90 + 35 * i, 30, 30);
			ventana.add(lb_num);
			lb_num = new JLabel(String.valueOf(i + 1));
			lb_num.setBounds(385 + 35 * i, 435, 30, 30);
			ventana.add(lb_num);
		}

		for (int i = 0; i < 100; i++) {
			btn_boton = new JButton();
			btn_boton.setBounds(380 + 35 * (i % 10), 90 + 35 * (i / 10), 30, 30);
			btn_boton.addActionListener(this);
			ventana.add(btn_boton);
		}

		ventana.setVisible(true);
	}

	

	public void actionPerformed(ActionEvent e) {

		int espia [] = datos.espia;			
		if (e.getSource() == btn_iniciar) {
			datos.iniciar();
			mover.setText("Iniciado");
			numintentos=0;
			intentos.setText("Numero de intentos: "+numintentos);
		} else if (e.getSource() == btn_reset) {
			datos.iniciar();
			numintentos=0;
			mover.setText("Reiniciado");
			intentos.setText("Numero de intentos: "+numintentos);
		}
		else if(e.getSource()==btn_esconder)
		{
			if(!posicion.isVisible())
			{
				posicion.setVisible(true);
			}
			else
			{
				posicion.setVisible(false);
			}
		}
		else {
			int x = (int) ((Component) e.getSource()).getLocation().x;
			x = 1+(x-380)/35;
			int y = (int) ((Component) e.getSource()).getLocation().y;
			y = 10-(y-90)/35;
	
			if (x == espia[0] && y == espia[1]) {
				mover.setText("Espia encontrado");
			}
			else if((y+1==espia[1] || y-1==espia[1]) && (x+1==espia[0] || x-1==espia[0]))
			{
				datos.moverEspia();
				mover.setText("El espia se movio");
			}
			else if((y==espia[1] || y-1==espia[1] || y+1==espia[1]) && (x==espia[0] || x-1==espia[0] || x+1==espia[0]) )
			{
				datos.moverEspia();
				mover.setText("El espia se movio");
			}
			else {
			// indicaciones de cordenadas
				if(y>espia[1])
				{
					if(x<espia[0])
					{
						mover.setText("Moverce al sureste");
					}
					else if(x>espia[0])
					{
						mover.setText("Moverce al suroeste");
					}
					else {
						mover.setText("Moverce al sur");
					}
				}
				else if(y<espia[1])
				{
					if(x<espia[0])
					{
						mover.setText("Moverce al noreste");
					}
					else if(x>espia[0])
					{
						mover.setText("Moverce al noroeste");	
					}
					else 
					{
						mover.setText("Moverce al norte");	
					}
				}
				else
				{
					if(x<espia[0]-1)
					{
						mover.setText("Moverce al este");	
					}
					else
					{
						mover.setText("Moverce al oeste");
					}
				}
				
			}
			numintentos++;
			intentos.setText("Numero de intentos: "+numintentos);
		}
		posicion.setText("Espia en: x "+ datos.espia[0]+", y "+ datos.espia[1]);
	}
}
