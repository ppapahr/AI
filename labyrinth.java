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

  public void printMaze(){

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
    children = new node[8];
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
