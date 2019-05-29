import java.util.Scanner;

// 5 white 6 black



public class minimax{

	// main
	public static void main(String[] args) {

		minimax game = new minimax();
		Scanner input = new Scanner(System.in);
		int playerMove = -1;
		int lastPlayer = 1;

		int[] max = new int[2];
		// int player = 1;

		// cubes is a list with black in position 0 and white in 1
		int[] cubes = new int[2];
		cubes[0] = 10;
		cubes[1] = 9;

		System.out.println("Black cubes: " + cubes[0] +"\nWhite cubes: " + cubes[1]);
		while(true) {


			max = game.choose(cubes, 1);
			if(max[0] == 0) {
				System.out.println("MAX removed 2 black and 1 white");
				cubes[0] = cubes[0] - 2;
				cubes[1] = cubes[1] - 1;
			}
			else if(max[0] == 1) {
				System.out.println("MAX removed 1 black and 2 white");
				cubes[0] = cubes[0] - 1;
				cubes[1] = cubes[1] - 2;
			}
			else if(max[0] == 2) {
				System.out.println("MAX removed 1 black");
				cubes[0] = cubes[0] - 1;
			}
			else if(max[0] == 3) {
				System.out.println("MAX removed 1 white");
				cubes[1] = cubes[1] - 1;
			}
			else {
				System.out.println("error in int[2] max");
			}

			System.out.println("Black cubes: " + cubes[0] +"\nWhite cubes: " + cubes[1]);

			// check if the game has ended
			if(cubes[0] == 0 && cubes[1] == 0) {
				break;
			}
			lastPlayer = -1;
			System.out.println("Players turn");
			System.out.println("choose move: \n2 black 1 white: 0\n1 black 2 white: 1\n1 black: 2\n1 white: 3 ");

			// player chooses what move he wants to make

			playerMove = input.nextInt();

			// check if the player typed a correct move that can be done
			while((playerMove != 0) && (playerMove != 1) && (playerMove != 2) && (playerMove != 3)) {
				System.out.println("Invalid move!");
				playerMove = input.nextInt();
			}

			while( (playerMove == 0) && !(cubes[0] > 1) && (cubes[1] > 0) ) {
				System.out.println("Move can not be performed, choose another move.");
			}

			while( (playerMove == 1) && !(cubes[0] > 0) && (cubes[1] > 1) ) {
				System.out.println("Move can not be performed, choose another move.");
			}

			while( (playerMove == 2) && !(cubes[0] > 0) ) {
				System.out.println("Move can not be performed, choose another move.");
			}

			while( (playerMove == 3) && !(cubes[1] > 0) ) {
				System.out.println("Move can not be performed, choose another move.");
			}

			// end of code for player move check

			if(playerMove == 0) {
				System.out.println("PLAYER removed 2 black and 1 white");
				cubes[0] = cubes[0] - 2;
				cubes[1] = cubes[1] - 1;
			}
			if(playerMove == 1) {
				System.out.println("PLAYER removed 1 black and 2 white");
				cubes[0] = cubes[0] - 1;
				cubes[1] = cubes[1] - 2;
			}
			if(playerMove == 2) {
				System.out.println("PLAYER removed 1 black");
				cubes[0] = cubes[0] - 1;
			}
			if(playerMove == 3) {
				System.out.println("PLAYER removed 1 white");
				cubes[1] = cubes[1] - 1;
			}

			System.out.println("Black cubes: " + cubes[0] +"\nWhite cubes: " + cubes[1]);

			// check if the game has ended
			if(cubes[0] == 0 && cubes[1] == 0) {
				break;
			}

			lastPlayer = 1;
		}
		System.out.println("Game ended");

		if(lastPlayer == 1 && (max[0] == 0 || max[0] == 1 || max[0] == 3)) {
			System.out.println("MAX wins!");
		}
		else if(lastPlayer == -1 && (playerMove == 0 || playerMove == 1 || playerMove == 3)) {
			System.out.println("Player wins!");
		}
		else {
			System.out.println("Its a tie!");
		}

	}









	public int[] choose(int[] cubes, int player) {

		// pos 0 is what move was done and pos 1 is what value that move has
		int [] output = new int[2];

		// check if there are cubes left
		if(cubes[0] == 0 && cubes[1] == 0) {

			output[0] = -1;
			output[1] = -1;
			return output;
		}



		int[][] moves = new int[4][2];

		// checking which moves can be done

		// remove 2 black and 1 white (move 0)
		if( (cubes[0] > 1) && (cubes[1] > 0) ) {

			int[] c0 = new int[2];
			c0[0] = cubes[0] - 2;
			c0[1] = cubes[1] - 1;

			moves[0] = choose(c0, player * (-1) );
		}

		// remove 2 white and 1 black (move 1)
		if( (cubes[0] > 0) && (cubes[1] > 1) ) {

			int[] c1 = new int[2];
			c1[0] = cubes[0] - 1;
			c1[1] = cubes[1] - 2;

			moves[1] = choose(c1, player * (-1) );
		}

		// remove 1 black (move 2)
		if(cubes[0] > 0) {

			int[] c2 = new int[2];
			c2[0] = cubes[0] - 1;
			c2[1] = cubes[1];

			moves[2] = choose(c2, player * (-1) );
		}

		// remove 1 white (move 3)
		if(cubes[1] > 0) {

			int[] c3 = new int[2];
			c3[0] = cubes[0];
			c3[1] = cubes[1] - 1;

			moves[3] = choose(c3, player * (-1) );
		}

		// checks if any of the moves finish the game and gives them values
		moves = fixValues(moves, player);

		// choose which value is best or worst
		int move = chooseValue(moves, player);

		output[0] = move;
		output[1] = moves[move][1];

		return output;
	}


	public int chooseValue(int[][] moves,int player) {
		int move = -1;
		if(player == 1) {

			int max = 0;

			for(int i = 0; i < 4; i ++) {

				if(moves[i][1] > max) {

					max = moves[i][1];
					move = i;
				}
			}
		}
		else{

			int min = 4;

			for(int i = 0; i < 4; i ++) {

				if((moves[i][1] < min) && (moves[i][1] > 0) ) {

					min = moves[i][1];
					move = i;
				}
			}
		}
		return move;

	}

	// finish values are:
	// MAX victory = 3
	// tie = 2
	// MAX loss = 1
	public int[][] fixValues(int[][] values, int player){

		if(player == 1) {

			for(int i = 0; i < 4; i ++) {

				if( (values[i][0] == -1) && (i != 2) ) {

					values[i][1] = 3;

				}
				if( (values[i][0] == -1) && (i == 2) ) {

					values[i][1] = 2;

				}

			}
		}
		else {

			for(int i = 0; i < 4; i ++) {

				if( (values[i][0] == -1) && (i != 2) ) {

					values[i][1] = 1;

				}
				if( (values[i][0] == -1) && (i == 2) ) {

					values[i][1] = 2;

				}

			}

		}
		return values;

	}


}
