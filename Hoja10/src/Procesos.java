
import java.util.ArrayList;
import org.graphstream.algorithm.PageRank;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

/**
 * @author Carlos
 *
 */
public class Procesos {
	/*Atributos*/
	private ArrayList<String[]> array2 = new ArrayList<String[]>();
	private int com[];
	public Node[] nodos;
	public Edge[] aristas= new Edge[64];
	PageRank pageRank = new PageRank();
	public Graph graph;
	private Conexion con = new Conexion();
	public String [] nombres={"Per 1","Per 2","Per 3","Per 4","Per 5","Per 6","Per 7","Per 8","Per 9","Per 10","Per 11"
			,"Per 12","Per 13","Per 14"};


	/**
	 * 
	 */
	void crearUsuariosGrafo(){
		con.eliminar();
		graph=null;
		graph= new SingleGraph("Proyecto");
		nodos = new Node [14];	
		for (int i=0;i<=13;i++){
			con.insertar(nombres[i].replace(" ",""), nombres[i]);
			nodos[i]=graph.addNode(nombres[i]);
			nodos[i].addAttribute("ui.label", nombres[i]);

		}
	}

	/**
	 * @param relaciones
	 */
	void relacionar(ArrayList<String[]> relaciones,boolean page){
		graph.addAttribute("ui.stylesheet", "node {fill-color: red; size-mode: dyn-size;} edge {fill-color:grey;}");
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		int conta=0,c=0;	
		com= new int [14];
		graph.display();

		for (int i=0; i<14;i++){
			conta=0;
			for (String n: relaciones.get(i)){
				if (conta>=1){
					if(!(n.equals("0"))){
						aristas[c]=graph.addEdge(nombres[i]+nombres[conta-1], nombres[i], nombres[conta-1], true);
						aristas[c].addAttribute("ui.label", n);
						con.relacionar(nombres[i],  nombres[conta-1], n);
						com[i]+=Integer.parseInt(n);
						c++;			

					}
				}
				conta++;

			}
		}
		if (page){
			pageRank();
		}

	}
	/**
	 * @param relaciones
	 */
	void relacionarb(ArrayList<String[]> relaciones){
		graph.addAttribute("ui.stylesheet", "node {fill-color: red; size-mode: dyn-size;} edge {fill-color:grey;}");
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		int conta=0,c=0;	
		for (int i=0; i<14;i++){
			conta=0;
			for (String n: relaciones.get(i)){
				//System.out.println(conta);
				if (conta>=1){
					if(Integer.parseInt(n)>=6){
						aristas[c]=graph.addEdge(nombres[i]+nombres[conta-1], nombres[i], nombres[conta-1], true);
						aristas[c].addAttribute("ui.label", n);
						con.relacionar(nombres[i],  nombres[conta-1], n);
						com[i]+=Integer.parseInt(n);
						c++;
					}	
				}
				conta++;
			}
		}
		graph.display();
	}
	/**
	 * @param relaciones
	 */
	void relacionarc(ArrayList<String[]> relaciones){
		graph.addAttribute("ui.stylesheet", "node {fill-color: red; size-mode: dyn-size;} edge {fill-color:grey;}");
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		int conta=0,c=0;	
		graph.display();
		for (int i=0; i<14;i++){
			conta=0;
			for (String n: relaciones.get(i)){
				//System.out.println(conta);
				if (conta>=1){
					if(!(n.equals("0"))){
						if(nombres[i]!=nombres[conta-1]){

							aristas[c]=graph.addEdge(nombres[i]+nombres[conta-1], nombres[i], nombres[conta-1], true);
							aristas[c].addAttribute("ui.label", n);
							con.relacionar(nombres[i],  nombres[conta-1], n);
							c++;	

						}
					}	
				}
				conta++;
			}
		}
	}
	/**
	 * 
	 */
	void pageRank(){
		pageRank.setVerbose(true);
		pageRank.init(graph);
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
	String comunicacion(ArrayList<String[]> relaciones){
		//ordenamos en forma ascendente el arreglo de números enteros y lo imprimimos por pantalla
		int conta;
		com= new int [14];
		for (int i=0; i<14;i++){
			conta=0;
			for (String n: relaciones.get(i)){
				if (conta>=1){
					if(!(n.equals("0"))){
						com[i]+=Integer.parseInt(n);			
					}
				}
				conta++;
			}
		}
		String texto="Los 3 empleados menos comunicados son:\n";
		ordSelAsc(com);
		int i=0;
		for (int num : com) {
			i++;
			if(i<4){
				//System.out.println("Per"+i+" "+num);
				texto+="Per"+i+" con "+num+" mensajes enviados.\n";
			}
			if(i>11){
				if (i==12){
					texto+="Los 3 empleados mas comunicados son: \n";
				}
				//System.out.println("Per"+i+" "+num);	
				texto+="Per"+i+" con "+num+" mensajes enviados.\n";
			}
		}
		return texto;
	}
	/**
	 * Este método ordena en forma ascendente el arreglo pasado como argumento usando el
	 * algoritmo de selección.
	 * 
	 * @param arreglo el arreglo que sera ordenado.
	 */
	static void ordSelAsc(int[] arreglo) {
		//iteramos sobre los elementos del arreglo
		for (int i = 0 ; i < arreglo.length - 1 ; i++) {
			int min = i;

			//buscamos el menor número
			for (int j = i + 1 ; j < arreglo.length ; j++) {
				if (arreglo[j] < arreglo[min]) {
					min = j;    //encontramos el menor número
				}
			}

			if (i != min) {
				//permutamos los valores
				int aux = arreglo[i];
				arreglo[i] = arreglo[min];
				arreglo[min] = aux;
			}
		}
	}
}

