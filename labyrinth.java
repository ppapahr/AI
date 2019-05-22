import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;


public class labyrinth{
  // 1 in mazes means available cell
  // 0 means unavailable cell
  // -1 means cell has already been accessed
  public int[][] maze_ucs;
  public int[][] maze_Astar;
  public int ucs_cost;
  public int Astar_cost;
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
    maze_Astar = new int[N][N];
    Random rand = new Random();
    double r;
    for(int i=0; i<N; i++){
      for(int j=0; j<N; j++){
        r = rand.nextDouble();


		// 1 is empty and 0 is blocked, later -1 will mean that the search function has passed from that possision.

        if(r <= p){
          maze_ucs[i][j] = 1;
          maze_Astar[i][j] = 1;
        }
        else{
          maze_ucs[i][j] = 0;
          maze_Astar[i][j] = 0;
        }
      }
    }
  }



  // BFS

  public void ucsSolve(){
    Queue<node> bfsq = new Queue<node>();
    int N = maze_ucs[0].length;
    node start = new node(S);
    bfsq.put(start);
    while(!bfsq.isEmpty()){
      node temp = bfsq.get();
      int x = temp.pos[0];
      int y = temp.pos[1];
      maze_ucs[x][y] = -1;

      if(isFreeUcs(x+1,y)){
        node child = new node(temp, [x+1,y]);
        temp.children[6] = child;
        bfsq.put(child);
      }

      if(isFreeUcs(x-1,y)){
        node child = new node(temp, [x-1,y]);
        temp.children[1] = child;
        bfsq.put(child);
      }

      if(isFreeUcs(x,y+1)){
        node child = new node(temp, [x,y+1]);
        temp.children[4] = child;
        bfsq.put(child);
      }

      if(isFreeUcs(x,y-1)){
        node child = new node(temp, [x,y-1]);
        temp.children[3] = child;
        bfsq.put(child);
      }

      if(isFreeUcs(x+1,y+1)){
        node child = new node(temp, [x+1,y+1]);
        temp.children[7] = child;
        bfsq.put(child);
      }

      if(isFreeUcs(x+1,y-1)){
        node child = new node(temp, [x+1,y-1]);
        temp.children[5] = child;
        bfsq.put(child);
      }

      if(isFreeUcs(x-1,y+1)){
        node child = new node(temp, [x-1,y+1]);
        temp.children[2] = child;
        bfsq.put(child);
      }

      if(isFreeUcs(x-1,y-1)){
        node child = new node(temp, [x-1,y-1]);
        temp.children[0] = child;
        bfsq.put(child);
      }
    }
  }

  private static boolean isFreeUcs(int x, int y){   // method returns true if the cell we are about to go is empty and false if not
    int N = maze_ucs[0].length;                     // also checks if x and y are in bounds
    if((x < N && x >= 0) && (y < N && y >= 0) && maze_ucs[x][y] == 1){
      return true;
    }
    return false;
  }

  private static boolean isFreeAstar(int x, int y){
    int N = maze_Astar[0].length;
    if((x < N && x >= 0) && (y < N && y >= 0) && maze_Astar[x][y] == 1){
      return true;
    }
    return false;
  }

  public void printMaze(){
    String st = "";
    int N = maze_ucs[0].length; // can use either maze_ucs or maze_Astar
    for(int i=0; i<N; i++){
      for(int j=0; j<N; j++){
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

  public void aStar(){

	//int[] start = S;
	//int[] end1 = e1;
	//int[] end2 = e2;
	//int[][] matrix = m;

	ArrayList<node> deadEnds = new ArrayList<node>();
	ArrayList<node> searchField = new ArrayList<node>();

	node check = new node(S);
	maze_Astar[S[0]][S[1]] = -1;

	while(true){ // possible syntax error

		// checking if I 've reached an end point
		if(check.pos[0] == G1[0] && check.pos[1] == G1[1]){

			println("reached end point 1"); // debug message
			break;
		}
		if(check.pos[0] == G2[0] && check.pos[1] == G2[1]){

			println("reached end point 2"); // debug message
			break;
		}

		// adding the children of the checked node to the search field and to its children list
		// need to check how persistant the check node will be

		int x = check.pos[0];
		int y = check.pos[1];

		node n;

		if(maze_Astar[x - 1][y - 1] == 1){
			n = new node(check, [x-1,y-1]);
			searchField.add(n);
			check.children[0] = n;

		}

		if(maze_Astar[x][y - 1] == 1){
			n = new node(check, [x,y-1]);
			searchField.add(n);
			check.children[1] = n;

		}

		if(maze_Astar[x + 1][y - 1] == 1){
			n = new node(check, [x+1,y-1]);
			searchField.add(n);
			check.children[2] = n;

		}

		if(maze_Astar[x - 1][y] == 1){
			n = new node(check, [x-1,y]);
			searchField.add(n);
			check.children[3] = n;
		}

		if(maze_Astar[x + 1][y] == 1){
			n = new node(check, [x+1,y]);
			searchField.add(n);
			check.children[4] = n;

		}

		if(maze_Astar[x - 1][y + 1] == 1){
			n = new node(check, [x-1,y+1]);
			searchField.add(n);
			check.children[5] = n;
		}

		if(maze_Astar[x][y + 1] == 1){
			n = new node(check, [x,y+1]);
			searchField.add(n);
			check.children[6] = n;
		}

		if(maze_Astar[x + 1][y + 1] == 1){
			n = new node(check, [x+1,y+1]);
			searchField.add(n);
			check.children[7] = n;
		}

		// check if the node does not have any children (dead end)
		int dead = 1;
		for(int i = 0, i < 8, i++){

			if(check.children[i] != null){ // check if the node list initializes with "null" in all possitions
				dead = 0;                  // if the parents have shallow pointers to children then the program wont execute this line

			}

		}
		if(dead == 1){
			deadEnds.add(check);
		}


		if(searchField.isEmpty()){
			// call h(s) with dead end list
			break;
		}


		// call h(s) with search field list




	}

	// the node check will be the closest to one of the end points






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

	public int[] pos;                            //  the list possisions
	public node parent;                          //  0  1  2
	public node[] children = new node[8];        //  3  X  4
                                                 //  5  6  7
	// starting point constructor                //
	node(int[] coord){
    pos = new int[2];
		pos[0] = coord[0];
    pos[1] = coord[1];
		// return;

	}

	// constructor
	node(node p, int[] coord){
		parent = p;
		pos = new int[2];
    pos[0] = coord[0];
    pos[1] = coord[1];
		// return;
	}


	public node getParent(){
		return parent;
	}

	public node[] getChildren(){
		return children;
	}


	// depricated
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
