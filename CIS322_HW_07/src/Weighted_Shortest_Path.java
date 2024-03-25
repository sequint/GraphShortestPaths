/*
 * Steven Quintana
 * 03/12/2024
 * Description: This program implements Dijkstra's algorithm to find weighted
 * shortest paths in a directed & weighted graph.
 */

import java.util.*;

public class Weighted_Shortest_Path
{
	// Set all vertices in the graph
	static Vertex a = new Vertex( "A" );
	static Vertex b = new Vertex( "B" );
	static Vertex c = new Vertex( "C" );
	static Vertex d = new Vertex( "D" );
	static Vertex e = new Vertex( "E" );
	static Vertex f = new Vertex( "F" );
	static Vertex g = new Vertex( "G" );
	
	public static void main( String[] args )
	{
		// Set the edges so the graph has adjacencies and weights
		setGraphEdges();
		
		// Set all shortest weighted paths using A vertex as the source
		dijkstra( a );
		
		printAllPaths();
	}
	
	private static void setGraphEdges()
	{
		Edge ab = new Edge( b, 5 );
		Edge ac = new Edge( c, 3 );
		a.addAdjacency( ab );
		a.addAdjacency( ac );
		
		Edge bg = new Edge( g, 1 );
		Edge be = new Edge( e, 3 );
		Edge bc = new Edge( c, 2 );
		b.addAdjacency( bg );
		b.addAdjacency( be );
		b.addAdjacency( bc );
		
		Edge ce = new Edge( e, 7 );
		c.addAdjacency( ce );
		
		Edge da = new Edge( a, 2 );
		d.addAdjacency( da );
		
		Edge ed = new Edge( d, 2 );
		Edge ef = new Edge( f, 1 );
		e.addAdjacency( ed );
		e.addAdjacency( ef );
		
		Edge ge = new Edge( e, 1 );
		g.addAdjacency( ge );
	}
	
	private static void dijkstra( Vertex source )
	{
		// Implemented using priority queue so that I am only passing the source vertex as a parameter
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		
		source.distance = 0;
		
		queue.add( source );
		
		while( !queue.isEmpty() )
		{
			// Get and remove the smallest unknown distance (head) from the queue
			Vertex v = queue.poll();
			
			v.known = true;
			
			// Iterate over each edge (holding adjacent vertices and weights)
			// and update path accordingly
			for( Edge edge : v.adjacencies )
			{
				Vertex adjV = edge.vertex;
				
				if ( adjV.isNotKnown() )
				{
					if( v.distance + edge.weight < adjV.distance )
					{
						// Remove the adjacent vertex from queue before the update
						queue.remove( adjV );
						
						// Update the adjacent vertex path information
						// since a shorter weighted path is found
						adjV.distance = v.distance + edge.weight;
						adjV.path = v;
						
						// Place updated vertex back in queue since it is still unknown and re-order
						queue.add( adjV );
					}
				}
			}
		}
	}
	
	private static void printAllPaths()
	{
		System.out.print( "Shortest weight path from A to B: " );
		printPathTo( b );
		System.out.println();
		
		System.out.print( "Shortest weight path from A to C: " );
		printPathTo( c );
		System.out.println();
		
		System.out.print( "Shortest weight path from A to D: " );
		printPathTo( d );
		System.out.println();
		
		System.out.print( "Shortest weight path from A to E: " );
		printPathTo( e );
		System.out.println();
		
		System.out.print( "Shortest weight path from A to F: " );
		printPathTo( f );
		System.out.println();
		
		System.out.print( "Shortest weight path from A to G: " );
		printPathTo( g );
		System.out.println();
	}
	
	private static void printPathTo( Vertex v )
	{
		if( v.path != null )
		{
			printPathTo( v.path );
			System.out.print( " to " );
		}
		
		System.out.print( v.name );
	}
	
	
	//**** Vertex Class ****//
	
	private static class Vertex implements Comparable<Vertex>
	{
		public String name;
		public List<Edge> adjacencies;
		public boolean known;
		public double distance;
		public Vertex path;
		
		// Constructor
		public Vertex( String n )
		{
			name = n;
			adjacencies = new ArrayList<Edge>();
			known = false;
			distance = Double.POSITIVE_INFINITY;
			path = null;
		}
		
		public void addAdjacency( Edge edge ) { adjacencies.add( edge ); }
		
		public boolean isNotKnown() { return !known; }

		@Override
		public int compareTo( Vertex adjV ) { return Double.compare( this.distance, adjV.distance ); }
	}
	
	
	//**** Edge Class ****//
	
	private static class Edge
	{
		public Vertex vertex;
		public int weight;
		
		// Constructor
		public Edge( Vertex v, int w )
		{
			vertex = v;
			weight = w;
		}
	}

}
