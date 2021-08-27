import javax.swing.*;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;

public class Main implements ActionListener {
	JFrame ventana;
	JButton btn_boton[], btn_iniciar, btn_iniciar2, ver;
	JTextField mover, intentos, nombre;
	JTextArea presentacion;
	JLabel nom;
	int numintentos, y, x, dir, dir1, x1, y1, sec;
	boolean turno = false, contar = true, estado = false;
	String[][] todo;

	public static void main(String[] args) {
		new Main("Rompecabeza");
	}

	Main(String name) {

		ventana = new JFrame(name);
		ventana.setBounds(500, 100, 750, 490);
		ventana.setLayout(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);

		intentos = new JTextField();
		intentos.setBounds(480, 315, 130, 40);
		intentos.setEditable(false);
		intentos.setText("Numero de intentos: " + numintentos);
		ventana.add(intentos);

		mover = new JTextField();
		mover.setBounds(480, 270, 130, 40);
		mover.setEditable(false);
		ventana.add(mover);

		nombre = new JTextField();
		nombre.setBounds(480, 365, 130, 40);
		ventana.add(nombre);

		nom = new JLabel("Nombre");
		nom.setBounds(520, 395, 130, 40);
		ventana.add(nom);

		ver = new JButton("Ver top 5");
		ver.setBounds(320, 370, 140, 40);
		ver.addActionListener(this);
		ventana.add(ver);

		btn_iniciar = new JButton("Iniciar-Reiniciar");
		btn_iniciar.setBounds(320, 270, 140, 40);
		btn_iniciar.addActionListener(this);
		ventana.add(btn_iniciar);

		btn_iniciar2 = new JButton("Iniciar atajo");
		btn_iniciar2.setBounds(320, 320, 140, 40);
		btn_iniciar2.addActionListener(this);
		ventana.add(btn_iniciar2);

		presentacion = new JTextArea();
		presentacion.setBounds(10, 50, 295, 300);
		ventana.add(presentacion);
		presentacion.setEditable(false);
		presentacion.setBackground(UIManager.getColor("CheckBox.background"));

		presentacion.setText(" ");

		btn_boton = new JButton[16];

		for (int i = 0; i < 16; i++) {
			btn_boton[i] = new JButton(String.valueOf(i + 1));
			btn_boton[i].setBounds(380 + 55 * (i % 4), 40 + 55 * (i / 4), 50, 50);
			btn_boton[i].addActionListener(this);
			ventana.add(btn_boton[i]);
			btn_boton[i].setEnabled(false);
		}
		btn_boton[15].setVisible(false);
		ventana.setVisible(true);
	}

	public void evaluar() {
		for (int i = 0; i < 16; i++) {
			if (btn_boton[i].getLocation().x == 380 + 55 * (i % 4)
					&& btn_boton[i].getLocation().y == 40 + 55 * (i / 4)) {
				estado = true;
			} else {
				estado = false;
				break;
			}
		}
		if (estado == true) {
			contar = false;
			mover.setText("Orden correcto");
			for (JButton a : btn_boton) {
				a.setEnabled(false);
			}
			grabar();
		}
	}

	public void iniciar2() {
		if (nombre.getText().length() >= 2) {
			for (int i = 0; i < 16; i++) {
				btn_boton[i].setBounds(380 + 55 * (i % 4), 40 + 55 * (i / 4), 50, 50);
				btn_boton[i].setEnabled(true);
			}
			btn_boton[14].setLocation(545, 205);
			btn_boton[15].setLocation(490, 205);
			tiempo();
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Ingrese el nombre del jugador", "Alert",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public void iniciar() {
		if (nombre.getText().length() >= 2) {
			for (int s = 0; s < 16; s++) {
				int l1, l2, otro;
				try {
					l1 = btn_boton[s].getLocation().x;
					l2 = btn_boton[s].getLocation().y;
					if (s == 15) {
						for (JButton n : btn_boton)
							if (n.getLocation().x == 545 && n.getLocation().y == 205) {
								btn_boton[15].setLocation(545, 205);
								n.setLocation(l1, l2);
								break;
							}
					} else {
						otro = aleatorio(s);
						btn_boton[s].setLocation(btn_boton[otro].getLocation().x, btn_boton[otro].getLocation().y);
						btn_boton[otro].setLocation(l1, l2);
					}
				} catch (Exception e) {
					System.out.println("Error");
				}
				btn_boton[s].setEnabled(true);
			}
			tiempo();
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Ingrese el nombre del jugador", "Alert",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public int aleatorio(int n) {
		int max = 15;
		int min = 0;
		int a = (int) (Math.floor(Math.random() * (max - min)) + min);
		if (a == n) {
			a = aleatorio(n);
		}
		return a;
	}

	public void tiempo() {
		try {
			// Necesario para que el hilo anterior se detenga, en la condicion else.
			Thread.sleep(1000);
			contar = true;
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		sec = 1;
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				if (contar) {
					mover.setText("Segundos: " + sec);
					sec++;
				} else {
					timer.cancel();
					timer.purge();
				}
			}
		};
		timer.scheduleAtFixedRate(task, new Date(), 1000);
	}

	public void leer() {
		todo = new String[5][2];
		try {
			File f = new File("top5.txt");
			if (f.exists() != true) {
				f.createNewFile();
			}
			Scanner sc;
			sc = new Scanner(f);

			for (int filas = 0; filas < 5; filas++) {
				if (sc.hasNextLine() != true) {
					break;
				}
				for (int columnas = 0; columnas < 2; columnas++) {
					todo[filas][columnas] = sc.nextLine();
				}
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("Error lectura " + e.toString());
		}
	}

	public void grabar() {
		leer();
		boolean entregado = false;
		FileWriter fw;
		try {
			fw = new FileWriter("top5.txt", false);
			int filas = 0, limite = 5;
			while (filas < limite) {
				if (todo[filas][0] == null) {
					if (entregado == false) {
						fw.write(String.valueOf(sec) + "\r\n");
						fw.write(nombre.getText() + "\r\n");
					}
					break;
				} else if (sec < Integer.parseInt(todo[filas][0]) && entregado == false) {
					entregado = true;
					fw.write(String.valueOf(sec) + "\r\n");
					fw.write(nombre.getText() + "\r\n");
					limite--;
					if (filas < limite) {
						fw.write(todo[filas][0] + "\r\n");
						fw.write(todo[filas][1] + "\r\n");
					}
				} else {
					fw.write(todo[filas][0] + "\r\n");
					fw.write(todo[filas][1] + "\r\n");
				}
				filas++;
			}
			fw.close();
		} catch (Exception e) {
			System.out.println("Error al grabar " + e.toString());
		}
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btn_iniciar && turno != true) {
			contar = false;
			iniciar();
			numintentos = 0;
		} else if (e.getSource() == btn_iniciar2 && turno != true) {
			contar = false;
			iniciar2();
			numintentos = 0;
		} else if (e.getSource() == ver && turno != true) {
			leer();
			StringBuilder result = new StringBuilder();
			result.append("Tabla de posiciones:").append(System.lineSeparator());
			for (int filas = 0; filas < 5; filas++) {
				if (todo[filas][0] == null)
					break;
				result.append(todo[filas][0]).append(", " + todo[filas][1]).append(System.lineSeparator());
			}
			JOptionPane.showMessageDialog(null, result.toString());
		} else if (turno != true) {
			Timer timer = new Timer();
			JButton b = (JButton) e.getSource();
			x = b.getLocation().x;
			y = b.getLocation().y;
			x1 = btn_boton[15].getLocation().x;
			y1 = btn_boton[15].getLocation().y;

			if ((0 < x1 - x && x1 - x <= 55) && y == y1 || (0 < x - x1 && x - x1 <= 55) && y == y1
					|| x == x1 && (y - y1 <= 55 && 0 < y - y1) || x == x1 && (y1 - y <= 55 && 0 < y1 - y)) {
				btn_boton[15].setLocation(x, y);
				TimerTask task = new TimerTask() {
					@Override
					public void run() {
						turno = true;
						dir = 0;
						dir1 = 0;
						if ((0 < x1 - x && x1 - x <= 55) && y == y1 || (0 < x - x1 && x - x1 <= 55) && y == y1) {
							if ((0 < x1 - x && x1 - x <= 55) && y == y1) {
								dir = 1;
							} else {
								dir = -1;
							}
						} else if (x == x1 && (y - y1 <= 55 && 0 < y - y1) || x == x1 && (y1 - y <= 55 && 0 < y1 - y)) {
							if (x == x1 && (y1 - y <= 55 && 0 < y1 - y)) {
								dir1 = 1;
							} else {
								dir1 = -1;
							}
						} else {
							evaluar();
							timer.cancel();
							timer.purge();
							turno = false;
						}
						x = x + dir;
						y = y + dir1;
						b.setLocation(x, y);
					}
				};
				timer.scheduleAtFixedRate(task, new Date(), 10);
			}
			numintentos++;
		}
		intentos.setText("Numero de intentos: " + numintentos);
	}
}
