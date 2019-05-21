import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;


public class labyrinth{
  // 1 in mazes means available cell
  // 0 means unavailable cell
  // -1 means cell has already been accessed
  public int[][] maze_ucs;
  public int[][] maze_astar;
  public int ucs_cost;
  public int astar_cost;
  public int[] S;
  public int[] G1;
  public int[] G2;

  public void setPoints(int[] start, int[] e1, int[] e2){
    S = start;
    G1 = e1
    G2 = e2;
    return;
  }

  public void generateMaze(int N, double p){
    maze_ucs = new int[N][N];
    maze_astar = new int[N][N];
    Random rand = new Random();
    double r;
    for(int i=0; i<N; i++){
      for(int j=0; j<N; j++){
        r = rand.nextDouble();


		// 1 is empty and 0 is blocked, later -1 will mean that the search function has passed from that possision.

        if(r <= p){
          maze_ucs[i][j] = 1;
          maze_astar[i][j] = 1;
        }
        else{
          maze_ucs[i][j] = 0;
          maze_astar[i][j] = 0;
        }
      }
    }
  }



  // BFS

  public void ucsSolve(){

  }

  public void printMaze(){
    String st = "";
    for(int i=0; i<maze_ucs.length; i++){ // can use either maze_ucs or maze_astar
      for(int j=0; j<maze_ucs.length; j++){
        if(maze_ucs[i][j] == 1){
          st += "1"; // we make the convention that 1 means empty cell and 0 means blocked cell
        }
        else{
          st += "0";
        }
      }
      st += "\n";
    }
    System.out.println(st);
  }


  // A*

  public void aStar(int[] s, int[] e1, int[] e2, int[][] m){

	int[] start = s;
	int[] end1 = e1;
	int[] end2 = e2;
	int[][] matrix = m;

	ArrayList deadEnds = new ArrayList();
	ArrayList searchField = new ArrayList();



  }



  // finder
  private node h(node[] searchField){

	  node chosenOne;

	if(searchField.length == 0){
		println("searchField size 0");
		return;
	}

	if(this.distance(searchField[0].pos, G1) < this.distance(searchField[0].pos, G2)){

		double min = this.distance(searchField[0].pos, G2);

	}
	else{

		double min = this.distance(searchField[0].pos, G1);

	}
	chosenOne = searchField[0];

	for(int i = 1, i < searchField.length, i ++){

		if(min > this.distance(searchField[i].pos, G1)){

			min = this.distance(searchField[i].pos, G1);
			chosenOne = searchField[i];
		}
		if(min > this.distance(searchField[i].pos, G2)){

			min = this.distance(searchField[i].pos, G2);
			chosenOne = searchField[i];
		}

	}

	return chosenOne;


  }


  // calculating the distance of you points in the matrix using pithagorean theorem
  private double distance(int[] a, int[] b){

	  int d;
	  int x;
	  int y;
	  x = abs(a[0] - b[0]);
	  y = abs(a[1] - b[1]);
	  d = (x*x) + (y*y);

	  return Math.sqrt((double)d); //need to check the casting to double
  }



}

public class node{

	public int[] pos;
	public node parent;
	public node[] children;

	// starting point constructor
	node(int[] coord){

		pos = coord;
		return;

	}

	// constructor
	node(node p, int[] coord){
		parent = p;
		pos = coord;
		return;
	}


	public node getParent(){
		return parent;
	}

	public node[] getChildren(){
		return children;
	}

	public void setChildren(node[] c){
		children = c;

		return;
	}


}



// implementation of a FIFO queue
public class Queue<Item>
{
	private class Node
	{
		Item item;
		Node next;
		Node (Item item)
		{
			this.item = item;
			next = null;

		}

	}
    private Node head,tail;
    Queue()
    {
    	head = null;
    	tail = null;
    }
    boolean isEmpty()
    {
    	return(head == null);
    }
    void put(Item item)
    {
    	Node t = tail;
    	tail = new Node(item);
    	if (isEmpty())
    	{
    		head = tail;
    	}
    	else
    	{
    		t.next = tail;
    	}
    }
    Item get()
    {
    	Item item = head.item;
    	Node t = head.next;
    	head = t;
    	return item;
    }
}
