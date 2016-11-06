
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextField;

/**
 * Clase que permite cargar una ventana con un area de texto
 * y las opciones de abrir o guardar un archivo
 * @author Carlos
 *
 */
public class ClaseFrame extends JFrame implements ActionListener
	{
		private Container contenedor;
		JLabel labelTitulo;/*declaramos el objeto Label*/
		JTextArea areaDeTexto;
		JButton botonAbrir;/*declaramos el objeto Boton*/
		JScrollPane scrollPaneArea;
		JFileChooser fileChooser; /*Declaramos el objeto fileChooser*/
		String texto="";
		private ArrayList<String[]> array2 = new ArrayList<String[]>();
		public  ArrayList<String[]> array3 = new ArrayList<String[]>();
		public ArrayList<String[]> array4 = new ArrayList<String[]>();
		public String query="";
		private JTextField txtNoHayArchivo;
		public ClaseFrame()//constructor
		{
			contenedor=getContentPane();
			contenedor.setLayout(null);
			
			/*Creamos el objeto*/
			fileChooser=new JFileChooser();
			
			/*Propiedades del Label, lo instanciamos, posicionamos y
			 * activamos los eventos*/
			labelTitulo= new JLabel();
			labelTitulo.setText("Proyecto");
			labelTitulo.setBounds(110, 20, 180, 23);
			
			areaDeTexto = new JTextArea();
			areaDeTexto.setEditable(false);
			//para que el texto se ajuste al area
			areaDeTexto.setLineWrap(true);
			//permite que no queden palabras incompletas al hacer el salto de linea
			areaDeTexto.setWrapStyleWord(true);
		   	scrollPaneArea = new JScrollPane();
			scrollPaneArea.setBounds(20, 50, 350, 270);
	        scrollPaneArea.setViewportView(areaDeTexto);
	       	
			/*Propiedades del boton, lo instanciamos, posicionamos y
			 * activamos los eventos*/
			botonAbrir= new JButton();
			botonAbrir.setText("Boton 1");
			botonAbrir.setBounds(30, 331, 91, 23);
			botonAbrir.addActionListener(this);
			
			/*Agregamos los componentes al Contenedor*/
			contenedor.add(labelTitulo);
			contenedor.add(scrollPaneArea);
			contenedor.add(botonAbrir);
			
			JButton btnNewButton = new JButton("Boton 2");
			btnNewButton.setBounds(131, 331, 89, 23);
			getContentPane().add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Boton 3");
			btnNewButton_1.setBounds(230, 331, 94, 23);
			getContentPane().add(btnNewButton_1);
			
			JButton btnNewButton_2 = new JButton("Boton 4");
			btnNewButton_2.setBounds(32, 378, 89, 23);
			getContentPane().add(btnNewButton_2);
			
			JButton btnNewButton_3 = new JButton("Boton 5");
			btnNewButton_3.setBounds(131, 378, 89, 23);
			getContentPane().add(btnNewButton_3);
			
			JButton btnNewButton_4 = new JButton("Boton 6");
			btnNewButton_4.setBounds(230, 378, 94, 23);
			getContentPane().add(btnNewButton_4);
			
			JTextPane txtpnInstruccionesnBoton = new JTextPane();
			txtpnInstruccionesnBoton.setEditable(false);
			txtpnInstruccionesnBoton.setFont(new Font("Dialog", Font.PLAIN, 14));
			txtpnInstruccionesnBoton.setBackground(SystemColor.control);
			txtpnInstruccionesnBoton.setText("Instrucciones: \r\nBoton 1: Cargar datos.csv.\r\nBoton 2: Visualizar grafo.\r\nBoton 3: Visualizar relaciones que tienen mas de 6 correos.\r\nBoton 4: Simplificar grafo.\r\nBoton 5: Page-Rank.\r\nBoton 6: Mostrar personas mas y menos comunicadas.\r\nBoton 7:  Mostrar la cantidad m\u00EDnima de correos que ha enviado una persona directa e indirectamente a otra persona o a todas las otras personas.");
			txtpnInstruccionesnBoton.setBounds(440, 50, 269, 304);
			getContentPane().add(txtpnInstruccionesnBoton);
			
			txtNoHayArchivo = new JTextField();
			txtNoHayArchivo.setEditable(false);
			txtNoHayArchivo.setForeground(Color.RED);
			txtNoHayArchivo.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			txtNoHayArchivo.setText("No hay archivo cargado.");
			txtNoHayArchivo.setBackground(Color.DARK_GRAY);
			txtNoHayArchivo.setBounds(440, 365, 204, 34);
			getContentPane().add(txtNoHayArchivo);
			txtNoHayArchivo.setColumns(10);
       		//Asigna un titulo a la barra de titulo
			setTitle("Proyecto");
			//tamaño de la ventana
			setSize(750,471);
			//pone la ventana en el Centro de la pantalla
			setLocationRelativeTo(null);
			
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		@Override
		public void actionPerformed(ActionEvent evento) {
			if (evento.getSource()==botonAbrir)
			{
				ArrayList<String[]>  archivo=abrirArchivo();
				//areaDeTexto.setText(archivo);
				int conta=0;
				for (String [] i: array2){
					conta++;
					if (conta>1){
						array3.add(i);
					}
				}
				conta=0;
				for(String[] n: array3){
					for (String i: n){
						texto+=i+" ";
					}
					texto+="\n";
				}
				areaDeTexto.setText(texto);
				Procesos p = new Procesos();
				p.crearUsuariosGrafo();
				p.relacionarc(array3);
			}
			
		}

		/**
		 * Permite mostrar la ventana que carga 
		 * archivos en el area de texto
		 * @return
		 */
		private ArrayList<String[]>  abrirArchivo() {
						
	 		//texto="";
	 		String bfRead;
	 		/*llamamos el metodo que permite cargar la ventana*/
			fileChooser.showOpenDialog(this);
			/*abrimos el archivo seleccionado*/
			File abre=fileChooser.getSelectedFile();

			/*recorremos el archivo, lo leemos para plasmarlo
			 *en el area de texto*/
			if(abre!=null)
			{ 				
				try {
					BufferedReader ar = new BufferedReader(new FileReader(abre));
					while ((bfRead = ar.readLine()) != null) {
						 String array []=  bfRead.split(";");
						 array2.add(array);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "No se encontro archivo");
				}
			}
				return array2;
		}
	}
