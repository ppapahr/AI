





public class minimax{
	
	
	
	
	public int[] choose(int[] cubes, int player) { // cubes is an list with black in position 0 and white in 1
		
		int[] value = new int [4];
		int move;
		
		// checking which players turn is
		
		// checking which moves can be done
		
		// remove 2 black and 1 white
		if( (cubes[0] > 1) && (cubes[1] > 0) ) {
			
			int[] c0 = new int[2]; 
			c0[0] = cubes[0] - 2;
			c0[1] = cubes[1] - 1;
			
			choose(c0, )
		}
		
		// remove 2 white and 1 black
		if( (cubes[0] > 0) && (cubes[1] > 1) ) {
			
			int[] c1 = new int[2];
			c1[0] = cubes[0] - 1;
			c1[1] = cubes[1] - 2;
			
		}
		
		// remove 1 black
		if(cubes[0] > 0) {
			
			int[] c2 = new int[2];
			c2[0] = cubes[0] - 1;
			c2[1] = cubes[1];
			
		}
		
		// remove 1 white
		if(cubes[1] > 0) {
			
			int[] c3 = new int[2];
			c3[0] = cubes[0];
			c3[1] = cubes[1] - 1;
			
			
			
		}
		
		int [] output = new int[2];
		
		
		
		output[0] = value;
		output[1] = move;
		return output;
	}

	
}