
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Clase que permite cargar una ventana con un area de texto
 * y las opciones de abrir o guardar un archivo
 * @author HENAO
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
			botonAbrir.setText("Abrir");
			botonAbrir.setBounds(50, 331, 80, 23);
			botonAbrir.addActionListener(this);
			
			/*Agregamos los componentes al Contenedor*/
			contenedor.add(labelTitulo);
			contenedor.add(scrollPaneArea);
			contenedor.add(botonAbrir);
			
			JButton btnNewButton = new JButton("Visualizar grafo");
			btnNewButton.setBounds(161, 331, 157, 23);
			getContentPane().add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Visualizar relaciones con 6 grafos");
			btnNewButton_1.setBounds(338, 331, 191, 23);
			getContentPane().add(btnNewButton_1);
			
			JButton btnNewButton_2 = new JButton("Autoenvio");
			btnNewButton_2.setBounds(411, 52, 89, 23);
			getContentPane().add(btnNewButton_2);
			
			JButton btnNewButton_3 = new JButton("Personas mas y menos");
			btnNewButton_3.setBounds(411, 86, 89, 23);
			getContentPane().add(btnNewButton_3);
			
			JButton btnNewButton_4 = new JButton("Minima cantia");
			btnNewButton_4.setBounds(411, 138, 89, 23);
			getContentPane().add(btnNewButton_4);
       		//Asigna un titulo a la barra de titulo
			setTitle("Proyecto");
			//tamaño de la ventana
			setSize(750,400);
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
				p.relacionar(array3);
			}
			
		}

		/**
		 * Permite mostrar la ventana que carga 
		 * archivos en el area de texto
		 * @return
		 */
		private ArrayList<String[]>  abrirArchivo() {
					
			String aux=""; 		
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
