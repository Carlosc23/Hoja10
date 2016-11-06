import org.graphstream.graph.Edge;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class ManejadorGrafo {
	public static void main(String args[]) {
	
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		Graph graph = new SingleGraph("Tutorial 1");

		Node a = graph.addNode("A");
		Node b = graph.addNode("B");

		a.setAttribute("xy", 0, 0);
		a.setAttribute("xy", 1, 1);

		graph.addEdge("AB", "A", "B", true);
		graph.addEdge("BA", "B", "A", true);

		graph.display(false);
		//graph.display();
	}
}
