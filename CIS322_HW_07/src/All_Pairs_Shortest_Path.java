/*
 * Steven Quintana
 * 03/12/2024
 * Description: This program uses dynamic programming via the
 * All Pairs Shortest Path algorithm to find all shortest paths in
 * a graph, then prints the shortest path graph values.
 */

public class All_Pairs_Shortest_Path
{
	public static void main( String[] args )
	{	
		final int A = 0, B = 1, C = 2, D = 3, E = 4, F = 5, G = 6;  // Matrix letters assigned index values
		
		// Textbook Figure 9.82 graph represented as an adjacency matrix
		int[][] initialGraph = new int[7][7];
		initialGraph[A][B] = 5;
		initialGraph[A][C] = 3;
		initialGraph[B][C] = 2;
		initialGraph[B][E] = 3;
		initialGraph[B][G] = 1;
		initialGraph[C][E] = 7;
		initialGraph[C][D] = 7;
		initialGraph[D][A] = 2;
		initialGraph[D][F] = 6;
		initialGraph[E][D] = 2;
		initialGraph[E][F] = 1;
		initialGraph[G][E] = 1;
		
		int[][] shortestPathValues = new int[7][7];
		int[][] shortestPath = new int[7][7];
		
		allPairsShortestPath( initialGraph, shortestPathValues, shortestPath );
		
		printShortestPathValues( shortestPathValues );
	}
	
	private static void allPairsShortestPath( int[][] adjM, int[][] pathValues, int[][] path )
	{
		final int NOT_A_VERTEX = -1;
		int matrixLength = adjM.length;
		
		// Initialize path and it's values
		for( int i = 0; i < matrixLength; i++ )
		{
			for( int j = 0; j < matrixLength; j++ )
			{
				pathValues[i][j] = adjM[i][j];
				path[i][j] = NOT_A_VERTEX;  // Initializes all paths to null, or -1
			}
		}
		
		// Compare all values in the matrix to each other
		// and update the shortest path values and path graphs
		// if a less than (shorter) comparison is found between two values
		for( int k = 0; k < matrixLength; k++ )
		{
			for( int i = 0; i < matrixLength; i++ )
			{
				for( int j = 0; j < matrixLength; j++ )
				{
					if ( pathValues[i][k] + pathValues[k][j] != 0 && pathValues[i][k] + pathValues[k][j] < pathValues[i][j] )
					{
						// Shortest path update
						pathValues[i][j] = pathValues[i][k] + pathValues[k][j];
						path[i][j] = k;
					}
				}
			}
		}
	}
	
	private static void printShortestPathValues( int[][] values )
	{
		final int rowD = 4;
		
		System.out.print( "Shortest path for D1 through D7: " );
		// Print matrix D1 through D7
		for( int col = 0; col < values.length; col++ )
		{
			if( col == values.length - 1 )
				System.out.print( values[col][rowD]);
			else
				System.out.print( values[col][rowD] + ", " );
		}
		
	}
}
