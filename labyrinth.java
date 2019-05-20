import java.util.ArrayList;
import java.util.Random;


public class labyrinth{
  public boolean[][] maze;



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
  
  private void h(node s){
	  
	  
	  
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
