import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;
import java.util.Scanner;


public class labyrinth{
  // 1 in mazes means available cell
  // 0 means unavailable cell
  // -1 means cell has already been accessed
  public ArrayList<Integer> ucs_path = new ArrayList<Integer>();
  public ArrayList<Integer> Astar_path = new ArrayList<Integer>();
  public node final_ucs;
  public node final_Astar;
  public int[][] maze_ucs;
  public int[][] maze_Astar;
  public int ucs_cost = 0;
  public int Astar_cost = 0;
  public int Astar_expansion;
  public int ucs_expansion;
  public int[] S = new int[2];
  public int[] G1 = new int[2];
  public int[] G2 = new int[2];


  // main

  public static void main(String[] args){

    int size;
    double probability;
    int[] start = new int[2];
    int[] g1 = new int[2];
    int[] g2 = new int[2];

    // User input

    Scanner input = new Scanner(System.in);

    System.out.println("type the size of the maze.");
    size = input.nextInt();
    System.out.println("type the probability for generated accessible cells");
    probability = input.nextDouble();
    System.out.println("type the possition of the start");
    start[0] = input.nextInt();
    start[1] = input.nextInt();
    System.out.println("type the possition of the end 1");
    g1[0] = input.nextInt();
    g1[1] = input.nextInt();
    System.out.println("type the possition of the end 2");
    g2[0] = input.nextInt();
    g2[1] = input.nextInt();

    //Start initializing the problem

    labyrinth problem = new labyrinth();
    // Initializing starting and end points in problem
    problem.setPoints(start, g1, g2);
    problem.generateMaze(size, probability);
    problem.printMaze();
    // Solving the problem with UCS and A*
    problem.aStar();
    problem.ucsSolve();
    // Calculating Cost of the two methods
    problem.calculateCost();
    // Printing A* solution
    System.out.println("Astar solution: \n");
    System.out.println("\n");
    problem.printPathAstar();
    // Printing UCS solution
    System.out.println("Ucs solution: \n");
    System.out.println("\n");
    problem.printPathUcs();
    // Printing the methods' costs and expansions
    System.out.println("UCS cost: " + problem.ucs_cost);
    System.out.println("A* cost: " + problem.Astar_cost);
    System.out.println("UCS expansion: " + problem.ucs_expansion);
    System.out.println("A* expansion: " + problem.Astar_expansion);




  }


  public void setPoints(int[] start, int[] e1, int[] e2){
    S[0] = start[0];
    S[1] = start[1];

    G1[0] = e1[0];
    G1[1] = e1[1];

    G2[0] = e2[0];
    G2[1] = e2[1];
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
  public boolean ucsSolve(){
    ucs_expansion = 0;
    Queue<node> bfsq = new Queue<node>();
    int N = maze_ucs[0].length;
    node start = new node(S);
    bfsq.put(start);
    int a = start.pos[0];
    int b = start.pos[1];
	
	// we make the starting position passable
    if(maze_ucs[a][b] == 0){
      maze_ucs[a][b] == 1;;
    }
    while(!bfsq.isEmpty()){
      node temp = bfsq.get();
      int x = temp.pos[0];
      int y = temp.pos[1];
      maze_ucs[x][y] = -1;

      if((x==G1[0] && y==G1[1]) || (x==G2[0] && y==G2[1])){
        final_ucs = temp;
        System.out.println("exit found \n");
        return true;
      }

      if(this.isFreeUcs(x+1,y)){
        int[] temparr = new int[2];
        temparr[0] = x+1;
        temparr[1] = y;
        node child = new node(temp, temparr);
        temp.children[6] = child;
        bfsq.put(child);
      }

      if(this.isFreeUcs(x-1,y)){
        int[] temparr = new int[2];
        temparr[0] = x-1;
        temparr[1] = y;
        node child = new node(temp, temparr);
        temp.children[1] = child;
        bfsq.put(child);
      }

      if(this.isFreeUcs(x,y+1)){
        int[] temparr = new int[2];
        temparr[0] = x;
        temparr[1] = y+1;
        node child = new node(temp, temparr);
        temp.children[4] = child;
        bfsq.put(child);
      }

      if(this.isFreeUcs(x,y-1)){
        int[] temparr = new int[2];
        temparr[0] = x;
        temparr[1] = y-1;
        node child = new node(temp, temparr);
        temp.children[3] = child;
        bfsq.put(child);
      }

      if(this.isFreeUcs(x+1,y+1)){
        int[] temparr = new int[2];
        temparr[0] = x+1;
        temparr[1] = y+1;
        node child = new node(temp, temparr);
        temp.children[7] = child;
        bfsq.put(child);
      }

      if(this.isFreeUcs(x+1,y-1)){
        int[] temparr = new int[2];
        temparr[0] = x+1;
        temparr[1] = y-1;
        node child = new node(temp, temparr);
        temp.children[5] = child;
        bfsq.put(child);
      }

      if(this.isFreeUcs(x-1,y+1)){
        int[] temparr = new int[2];
        temparr[0] = x-1;
        temparr[1] = y+1;
        node child = new node(temp, temparr);
        temp.children[2] = child;
        bfsq.put(child);
      }

      if(this.isFreeUcs(x-1,y-1)){
        int[] temparr = new int[2];
        temparr[0] = x-1;
        temparr[1] = y-1;
        node child = new node(temp, temparr);
        temp.children[0] = child;
        bfsq.put(child);
      }
      ucs_expansion += 1;
    }
    System.out.println("no exit found \n");
    return false;
  }

  public void printPathUcs(){
    String st = "";
    int check = 0;
    int N = maze_ucs[0].length;
    for(int i=0; i<N; i++){
      for(int j=0; j<N; j++){
        if(maze_ucs[i][j] == -1){
          check = 0;
          for(int k=0; k<ucs_path.size(); k += 2){
            if(i == ucs_path.get(k) && j == ucs_path.get(k+1)){
              check ++;
              st += "*";
              break;
            }
          }
          if(check == 0){
            st += "1";
          }
        }
        else if(maze_ucs[i][j] == 1){
          st += "1";
        }
        else if(maze_ucs[i][j] == 0){
          st += "0";
        }
      }
      st += "\n";
    }
    System.out.println(st);
  }

  public void printPathAstar(){
    String st = "";
    int check = 0;
    int N = maze_Astar[0].length;
    for(int i=0; i<N; i++){
      for(int j=0; j<N; j++){
        if(maze_Astar[i][j] == -1){
          check = 0;
          for(int k=0; k<Astar_path.size(); k += 2){
            if(i == Astar_path.get(k) && j == Astar_path.get(k+1)){
              check ++;
              st += "*";
              break;
            }
          }
          if(check == 0){
            st += "1";
          }
        }
        else if(maze_Astar[i][j] == 1){
          st += "1";
        }
        else if(maze_Astar[i][j] == 0){
          st += "0";
        }
      }
      st += "\n";
    }
    System.out.println(st);
  }

  public void calculateCost(){
    node t = final_ucs;
    if(t.parent == null){
      ucs_path.add(t.pos[0]);
      ucs_path.add(t.pos[1]);
    }
    while(t.parent != null){
      ucs_path.add(t.pos[0]);
      ucs_path.add(t.pos[1]);
      ucs_cost += 1;
      t = t.parent;
    }
    ucs_path.add(t.pos[0]);
    ucs_path.add(t.pos[1]);

    t = final_Astar;
    if(t.parent == null){
      Astar_path.add(t.pos[0]);
      Astar_path.add(t.pos[1]);
    }
    while(t.parent != null){
      Astar_path.add(t.pos[0]);
      Astar_path.add(t.pos[1]);
      Astar_cost += 1;
      t = t.parent;
    }
    Astar_path.add(t.pos[0]);
    Astar_path.add(t.pos[1]);
    return;
  }

  private boolean isFreeUcs(int x, int y){   // method returns true if the cell we are about to go is empty and false if not
    int N = maze_ucs[0].length;                     // also checks if x and y are in bounds
    if((x < N && x >= 0) && (y < N && y >= 0) && maze_ucs[x][y] == 1){
      return true;
    }
    return false;
  }

  private boolean isFreeAstar(int x, int y){
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

  public boolean aStar(){

	
	// we make the starting point passable
	if(maze_Astar[S[0]][S[1]] == 0) {
		maze_Astar[S[0][S[1]] == 1;
	}


	Astar_expansion = 0;

	ArrayList<node> deadEnds = new ArrayList<node>();
	ArrayList<node> searchField = new ArrayList<node>();

	node check = new node(S);
	maze_Astar[S[0]][S[1]] = -1;

	while(true){

		int x = check.pos[0];
		int y = check.pos[1];

		// mark the spot as visited
		maze_Astar[x][y] = -1;

		// checking if I have reached an end point
		if(check.pos[0] == G1[0] && check.pos[1] == G1[1]){

			System.out.println("reached end point 1 \n "); // debug message
			break;
		}
		if(check.pos[0] == G2[0] && check.pos[1] == G2[1]){

			System.out.println("reached end point 2 \n"); // debug message
			break;
		}

		// adding the children of the checked node to the search field and to its children list
		// need to check how persistant the check node will be


		//System.out.println("visited a spot"+ x + y);
		if(this.isFreeAstar(x-1,y-1)){
			int[] temparr = new int[2];
	        temparr[0] = x-1;
	        temparr[1] = y-1;
	        node child = new node(check, temparr);
	        if(!searchField.contains(child)) {
	        	//System.out.println("child 0 added");
	        	searchField.add(child);
				check.children[0] = child;
	        }

		}




		if(this.isFreeAstar(x-1,y)){
			int[] temparr = new int[2];
	        temparr[0] = x-1;
	        temparr[1] = y;
	        node child = new node(check, temparr);
	        if(!searchField.contains(child)) {
	        	//System.out.println("child 1 added");
	        	searchField.add(child);
				check.children[1] = child;
	        }

		}

		if(this.isFreeAstar(x-1,y+1)){
			int[] temparr = new int[2];
	        temparr[0] = x-1;
	        temparr[1] = y+1;
	        node child = new node(check, temparr);
	        if(!searchField.contains(child)) {
	        	//System.out.println("child 2 added");
	        	searchField.add(child);
				check.children[2] = child;
	        }

		}

		if(this.isFreeAstar(x,y-1)){
			int[] temparr = new int[2];
	        temparr[0] = x;
	        temparr[1] = y-1;
	        node child = new node(check, temparr);
	        if(!searchField.contains(child)) {
	        	//System.out.println("child 3 added");
	        	searchField.add(child);
				check.children[3] = child;
	        }
		}

		if(this.isFreeAstar(x,y+1)){
			int[] temparr = new int[2];
	        temparr[0] = x;
	        temparr[1] = y+1;
	        node child = new node(check, temparr);
			if(!searchField.contains(child)) {
				//System.out.println("child 4 added");
	        	searchField.add(child);
				check.children[4] = child;
	        }

		}

		if(this.isFreeAstar(x+1,y-1)){
			int[] temparr = new int[2];
	        temparr[0] = x+1;
	        temparr[1] = y-1;
	        node child = new node(check, temparr);
	        if(!searchField.contains(child)) {
	        	//System.out.println("child 5 added");
	        	searchField.add(child);
				check.children[5] = child;
	        }
		}

		if(this.isFreeAstar(x+1,y)){
			int[] temparr = new int[2];
	        temparr[0] = x+1;
	        temparr[1] = y;
	        node child = new node(check, temparr);
	        if(!searchField.contains(child)) {
	        	//System.out.println("child 6 added");
	        	searchField.add(child);
				check.children[6] = child;
	        };
		}

		if(this.isFreeAstar(x+1,y+1)){
			int[] temparr = new int[2];
	        temparr[0] = x+1;
	        temparr[1] = y+1;
	        node child = new node(check, temparr);
	        if(!searchField.contains(child)) {
	        	//System.out.println("child 7 added");
	        	searchField.add(child);
				check.children[7] = child;
	        }
		}

		// check if the node does not have any children (dead end)
		int dead = 1;
		/*for(int i = 0; i < 8; i++){

			if(check.children[i] != null){ // check if the node list initializes with "null" in all possitions
				dead = 0;                  // if the parents have shallow pointers to children then the program wont execute this line

			}

		}*/
		//System.out.println("children check done!");
//		if(dead == 1){
//			deadEnds.add(check);
//		}


		if(searchField.isEmpty()){
			check = eu(deadEnds);
			System.out.println("no exit");
			return false;
		}


		check = eu(searchField);
		searchField.remove(check);



		Astar_expansion ++;

	}

	// the node check will be the closest to one of the end points

	final_Astar = check;
	return true;

  }





// cost calculator for the eu function
public int euCost(node n){

		int cost = 0;
		node t = n;
		while(t.parent != null){

			cost ++;
			t = t.parent;

		}

		return cost;

	}


  // finder
  private node eu(ArrayList<node> searchField){

	  node chosenOne;

	  // calculate the cost up until the node **depricated**
	  //double cost = (double) euCost(n) + 1; // need to check casting to double

	if(searchField.isEmpty()){
		System.out.println("searchField is empty");
		return null;
	}

	// there is no need to check for empty spots in the arrayList if items are removed with their index id, the items are shifted to cover the space

	double min;

	if( ( this.distance(searchField.get(0).pos, G1) + this.euCost(searchField.get(0)) ) < ( this.distance(searchField.get(0).pos, G2) + this.euCost(searchField.get(0)) ) ){

		min = this.distance(searchField.get(0).pos, G1) + this.euCost(searchField.get(0));

	}
	else{

		min = this.distance(searchField.get(0).pos, G2) + this.euCost(searchField.get(0));

	}
	chosenOne = searchField.get(0);

	for(int i = 1; i < searchField.size(); i ++){

		if(min > (this.distance(searchField.get(i).pos, G1) + this.euCost(searchField.get(i)))){

			min = this.distance(searchField.get(i).pos, G1) + this.euCost(searchField.get(i));
			chosenOne = searchField.get(i);
		}
		if(min > (this.distance(searchField.get(i).pos, G2) + this.euCost(searchField.get(i)))){

			min = this.distance(searchField.get(i).pos, G2) + this.euCost(searchField.get(i));
			chosenOne = searchField.get(i);
		}

	}

	return chosenOne;

  }


  // calculating the distance of you points in the matrix using pithagorean theorem
  private double distance(int[] a, int[] b){

	  int d;
	  int x;
	  int y;
	  x = Math.abs(a[0] - b[0]);
	  y = Math.abs(a[1] - b[1]);
	  d = (x*x) + (y*y);

	  return Math.sqrt((double)d); //need to check the casting to double
  }


}

class node{

	public int[] pos;                            //  the list posisions
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

  public void copy(node t){
    this.pos[0] = t.pos[0];
    this.pos[1] = t.pos[1];
    this.parent = t.parent;
    this.children = t.children;
  }

}



// implementation of a FIFO queue
class Queue<Item>
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
