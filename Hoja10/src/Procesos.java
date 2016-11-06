import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.graphstream.algorithm.PageRank;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Graph;
/**
 * @author Carlos
 *
 */
public class Procesos {
	/*Atributos*/
	private ArrayList<String[]> array2 = new ArrayList<String[]>();
	public Node[] nodos;
	public Edge[] aristas;
	public Graph graph = new SingleGraph("Proyecto");
	private String [] nombres={"Per 1","Per 2","Per 3","Per 4","Per 5","Per 6","Per 7","Per 8","Per 9","Per 10","Per 11"
			,"Per 12","Per 13","Per 14"};
	
	
	/**
	 * 
	 */
	void crearUsuariosGrafo(){
		nodos = new Node [14];	
		for (int i=0;i<=13;i++){
			nodos[i]=graph.addNode(nombres[i]);
			nodos[i].addAttribute("ui.label", nombres[i]);
		}
	}
	
	void relacionar(ArrayList<String[]> relaciones){
		graph.addAttribute("ui.stylesheet", "node {fill-color: red; size-mode: dyn-size;} edge {fill-color:grey;}");
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		int conta=0,c=0;
		String [] aris={"ar1","ar2","ar3","ar4","ar5","ar6","ar7","ar8","ar9","ar10","ar11","ar12","ar13","ar14"};
		aristas = new Edge [64];
		graph.display();
		PageRank pageRank = new PageRank();
		pageRank.setVerbose(true);
		pageRank.init(graph);
		for (int i=0; i<14;i++){
				conta=0;
				for (String n: relaciones.get(i)){
					System.out.println(conta);
					if (conta>=1){
						if(!(n.equals("0"))){
							aristas[c]=graph.addEdge(nombres[i]+nombres[conta-1], nombres[i], nombres[conta-1], true);
							aristas[c].addAttribute("ui.label", n);
							c++;
						}	
					}
					conta++;
			}
			}
		
		}
	void relacionar2(ArrayList<String[]> relaciones){
		graph.addAttribute("ui.stylesheet", "node {fill-color: red; size-mode: dyn-size;} edge {fill-color:grey;}");
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		int conta=0,c=0;
		String [] aris={"ar1","ar2","ar3","ar4","ar5","ar6","ar7","ar8","ar9","ar10","ar11","ar12","ar13","ar14"};
		aristas = new Edge [64];	
		for (int i=0; i<14;i++){
				conta=0;
				for (String n: relaciones.get(i)){
					System.out.println(conta);
					if (conta>=1){
						if(Integer.parseInt(n)>=6){
							aristas[c]=graph.addEdge(nombres[i]+nombres[conta-1], nombres[i], nombres[conta-1], true);
							aristas[c].addAttribute("ui.label", n);
							c++;
						}	
					}
					conta++;
			}
			}
			graph.display();
		}
	void relacionar3(ArrayList<String[]> relaciones){
		graph.addAttribute("ui.stylesheet", "node {fill-color: red; size-mode: dyn-size;} edge {fill-color:grey;}");
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		int conta=0,c=0,c2=0;
		String [] aris={"ar1","ar2","ar3","ar4","ar5","ar6","ar7","ar8","ar9","ar10","ar11","ar12","ar13","ar14"};
		aristas = new Edge [64];	
		graph.display();
		PageRank pageRank = new PageRank();
		pageRank.setVerbose(true);
		pageRank.init(graph);
		for (int i=0; i<14;i++){
				conta=0;
				for (String n: relaciones.get(i)){
					//System.out.println(conta);
					if (conta>=1){
						if(!(n.equals("0"))){
							if(nombres[i]!=nombres[conta-1]){
								aristas[c]=graph.addEdge(nombres[i]+nombres[conta-1], nombres[i], nombres[conta-1], true);
								aristas[c].addAttribute("ui.label", n);
								c++;	
	
							}
						}	
					}
					conta++;
			}
			}
			
		for (int i=0;i<=13;i++){
			double rank = pageRank.getRank(nodos[i]);
			nodos[i].addAttribute("ui.size", 5 + Math.sqrt(graph.getNodeCount() * rank * 20));
			nodos[i].addAttribute("ui.label", String.format("%.2f%%", rank * 100)+" "+nombres[i]);
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

