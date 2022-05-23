package TDACola;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Auxiliar.EmptyQueueException;


public class GUI_Test extends JFrame{
	private JLabel lblResultado, lblTamaño, lblTamañoMax, lblEncolar;
	private JTextField tfEncolar;
	private JButton btnEncolar, btnDesencolar, btnFront, btnIsEmpty;
	protected QueueEnlazada<Integer> cola;
//	protected QueueConArreglo<Integer> cola;
	
	public GUI_Test() {
		cola = new QueueEnlazada<Integer>();
//		cola = new QueueConArreglo<Integer>();
		setBounds(10,10, 400,250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Test Cola");
		setResizable(false);
		getContentPane().setLayout(null);
		
		armarLabels();
		armarTextField();
		armarBotones();
	}
	
	private void armarLabels() {
		lblEncolar = new JLabel("<html>Ingresa un entero <p> para encolar</html>");
		lblEncolar.setBounds(20, 48, 150, 36);
		lblEncolar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblTamaño = new JLabel("Tamaño: "+cola.size()+" elementos enteros.");
		lblTamaño.setBounds(20, 20, 200, 16);
		lblTamaño.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblTamañoMax = new JLabel("Tamaño maximo: Ilimitada"); //Si es con arreglo -> cola.maxSize()
		lblTamañoMax.setBounds(230, 20, 160, 16);
		lblTamañoMax.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblResultado = new JLabel("Bienvenido al test de la clase cola");
		lblResultado.setBounds(20, 170, 360, 24);
		lblResultado.setAlignmentX(CENTER_ALIGNMENT);
		lblResultado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		getContentPane().add(lblEncolar);
		getContentPane().add(lblTamaño);
		getContentPane().add(lblTamañoMax);
		getContentPane().add(lblResultado);
	}
	
	private void armarTextField() {
		tfEncolar = new JTextField();
		tfEncolar.setBounds(20 , 92, 210, 32);
		tfEncolar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		getContentPane().add(tfEncolar);
	}
	
	private void armarBotones() {
		btnEncolar = new JButton("Encolar");
		btnEncolar.setBounds(240, 92, 100, 32);
		btnEncolar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		btnEncolar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valorIngresado = tfEncolar.getText();
				int enteroIngresado = Integer.parseInt(valorIngresado);
				cola.enqueue(enteroIngresado);
				tfEncolar.setText("");
				lblTamaño.setText("Tamaño: "+cola.size()+" elementos enteros.");
				lblResultado.setText("Se ingreso el numero "+enteroIngresado+" en la cola");
				
			}
		});
		
		btnDesencolar = new JButton("Desencolar");
		btnDesencolar.setBounds(20, 132, 100, 32);
		btnDesencolar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		btnDesencolar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int elementoDesencolado = cola.dequeue();
					lblResultado.setText("Se desencolo el elemento "+elementoDesencolado+" de la cola");
					lblTamaño.setText("Tamaño: "+cola.size()+" elementos enteros.");
				} catch (EmptyQueueException exception) {
					lblResultado.setText("Error: "+exception.getMessage());
				}
			}
		});
		
		btnFront = new JButton("Cabeza");
		btnFront.setBounds(130, 132, 100, 32);
		btnFront.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		btnFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblResultado.setText("El elemento cabeza es: "+cola.front());
				} catch (EmptyQueueException exception) {
					lblResultado.setText("Error: "+exception.getMessage());
				}
			}
		});
		
		btnIsEmpty = new JButton("¿Esta vacio?");
		btnIsEmpty.setBounds(240, 132, 100, 32);
		btnIsEmpty.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		btnIsEmpty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cola.isEmpty()) {
					lblResultado.setText("La cola esta vacia");
				} else {
					lblResultado.setText("La cola no esta vacia");
				}
			}
		});
		
		getContentPane().add(btnEncolar);
		getContentPane().add(btnDesencolar);
		getContentPane().add(btnFront);
		getContentPane().add(btnIsEmpty);
	}
	
	public static void main (String [] args) {
		GUI_Test g1 = new GUI_Test();
		g1.setVisible(true);
	}
	
	
}
