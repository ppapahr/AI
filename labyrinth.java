import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;


public class labyrinth{
  public boolean[][] maze;
  public int ucs_cost;
  public int astar_cost;
  public int[] S;
  public int[] G1;
  public int[] G2;

  public void generateMaze(int N, double p){
    maze = new boolean[N][N];
    Random rand = new Random();
    double r;
    for(int i=0; i<N; i++){
      for(int j=0; j<N; j++){
        r = rand.nextDouble();
        if(r <= p){
          maze[i][j] = true;
        }
        else{
          maze[i][j] = false;
        }
      }
    }
  }



  //BFS

  public void ucsSolve(){

  }

  public void printMaze(){
    String st = "";
    for(int i=0; i<maze.length; i++){
      for(int j=0; j<maze.length; j++){
        if(maze[i][j] == true){
          st += "1"; // kanoume tin simvasi oti to 1 simainei adeio keli (true) kai to 0 empodio (false)
        }
        else{
          st += "0";
        }
      }
      st += "\n";
    }
    System.out.println(st);
  }


  //A*

  public void aStar(){



  }

  private node h(node[] children){
	
	
	
	
  }
  
  
  //calculating the distance of you points in the matrix using pithagorean theorem
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

	//constructor
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