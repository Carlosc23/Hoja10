import org.graphstream.algorithm.PageRank;
	import org.graphstream.algorithm.generator.DorogovtsevMendesGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.graph.Graph;
	import org.graphstream.graph.Node;
	import org.graphstream.graph.implementations.SingleGraph;

	public class DemoPageRank {
		public static void main(String[] args) throws InterruptedException {
			Graph graph = new SingleGraph("Tutorial 1");
			graph.addNode("A");
			graph.addNode("B");
			graph.addNode("C");
			graph.addEdge("AB", "A", "B");
			graph.addEdge("BC", "B", "C");
			graph.display();
			PageRank pageRank = new PageRank();
			pageRank.setVerbose(true);
			pageRank.init(graph);
			
			
				for (Node node : graph) {
					double rank = pageRank.getRank(node);
					node.addAttribute("ui.size", 5 + Math.sqrt(graph.getNodeCount() * rank * 20));
					node.addAttribute("ui.label", String.format("%.2f%%", rank * 100));
				}
				Thread.sleep(10);
			}
		}
	