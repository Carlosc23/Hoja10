import java.sql.SQLException;
import java.util.ArrayList;
import org.graphstream.algorithm.Dijkstra;
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
	private int com[];
	public double pesos[];
	public Node[] nodos;
	public Edge[] aristas= new Edge[64];
	PageRank pageRank = new PageRank();
	public Graph graph,graph2,graph3;
	private Conexion con = new Conexion();
	public String [] nombres={"Per 1","Per 2","Per 3","Per 4","Per 5","Per 6","Per 7","Per 8","Per 9","Per 10","Per 11"
			,"Per 12","Per 13","Per 14"};
	public String [] nombres2={"","Per 1","Per 2","Per 3","Per 4","Per 5","Per 6","Per 7","Per 8","Per 9","Per 10","Per 11"
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
		//com= new int [14];
		graph.display();

		for (int i=0; i<14;i++){
			conta=0;
			for (String n: relaciones.get(i)){
				if (conta>=1){
					if(!(n.equals("0"))){
						aristas[c]=graph.addEdge(nombres[i]+nombres[conta-1], nombres[i], nombres[conta-1], true);
						aristas[c].addAttribute("length", n);
						aristas[c].addAttribute("label", "" + (int) aristas[c].getNumber("length"));
						con.relacionar(nombres[i],  nombres[conta-1], n);
						//com[i]+=Integer.parseInt(n);
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
						aristas[c].addAttribute("length", n);
						aristas[c].addAttribute("label", "" + (int) aristas[c].getNumber("length"));
						con.relacionar(nombres[i],  nombres[conta-1], n);
						//com[i]+=Integer.parseInt(n);
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
							aristas[c].addAttribute("length", n);
							aristas[c].addAttribute("label", "" + (int) aristas[c].getNumber("length"));
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
			e.printStackTrace();
		}
	}
	String comunicacion(ArrayList<String[]> relaciones){
		//ordenamos en forma ascendente el arreglo de n�meros enteros y lo imprimimos por pantalla
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
	 * Este m�todo ordena en forma ascendente el arreglo pasado como argumento usando el
	 * algoritmo de selecci�n.
	 * 
	 * @param arreglo el arreglo que sera ordenado.
	 */
	static void ordSelAsc(int[] arreglo) {
		//iteramos sobre los elementos del arreglo
		for (int i = 0 ; i < arreglo.length - 1 ; i++) {
			int min = i;

			//buscamos el menor n�mero
			for (int j = i + 1 ; j < arreglo.length ; j++) {
				if (arreglo[j] < arreglo[min]) {
					min = j;    //encontramos el menor n�mero
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
	/**
	 * @param origen
	 */
	void minima(String origen){
		pesos = new  double[14];
		int i=0;
		Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "length");
		dijkstra.init(graph);
		dijkstra.setSource(graph.getNode(origen));
		dijkstra.compute();
		// Print the lengths of all the shortest paths
		for (Node node : graph){
			pesos[i]=dijkstra.getPathLength(node);
			//System.out.printf("%s->%s:%10.2f%n", dijkstra.getSource(), node,
			//	dijkstra.getPathLength(node));
			i++;	
		}

	}
	/**
	 * @param origen
	 * @param destino
	 * @return
	 */
	double minima(String origen,String destino ){
		Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "length");
		dijkstra.init(graph);
		dijkstra.setSource(graph.getNode(origen));
		dijkstra.compute();
		// Print the lengths of all the shortest paths
		//System.out.printf("%s->%s:%10.2f%n", dijkstra.getSource(), graph.getNode(destino),
		//	dijkstra.getPathLength(graph.getNode(destino)));
		return dijkstra.getPathLength(graph.getNode(destino));
	}
	void simplificarGrafo(){
		graph2= new SingleGraph("Proyec");
		graph2.addAttribute("ui.stylesheet", "node {fill-color: red; size-mode: dyn-size;} edge {fill-color:grey;}");
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		String query = "MATCH (x)-[rel:CORREOS]->(y)\n" +
                "WHERE x <> y\n" +
                "RETURN rel.length, x.name, y.name";
		try {
			while(con.getQuery(query).next()){
				Node no=graph2.addNode(con.getQuery(query).getString("x.name"));
				no.addAttribute("ui.label", con.getQuery(query).getString("x.name"));
				Edge e= graph2.addEdge(con.getQuery(query).getString("x.name")+con.getQuery(query).getString("y.name"), 
						con.getQuery(query).getString("x.name"), con.getQuery(query).getString("y.name"), true);
				e.addAttribute("length", con.getQuery(query).getString("rel.length"));
				e.addAttribute("label", "" + (int) e.getNumber("length"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		graph2.display();
	}
}

