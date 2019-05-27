





public class minimax{
	
	private class Node{
		
		Node[] children = new Node[4];
		Node parent;
		int depth;
		int value;
		int[] cubes;
		
		// constructor
		Node(Node p, int[] c, int d) {
			
			parent = p;
			cubes = c;
			depth = d;
			
		}
		
		
		
	}
	
}