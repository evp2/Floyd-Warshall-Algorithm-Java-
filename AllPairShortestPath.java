
public class AllPairShortestPath {
	static int I = 99999;
	// static int L = 4;
	// static int W = 4;
	static int n = 5;
	public static int[][] next = new int[n][n];

	static void FloydWarshall(int g[][]) {
		int[][] dist = new int[n][n];
		int i, j, k;

		dist = g;

		int[][] predMatrix = new int[n][n];
		/*
		 * Predecessor Matrix: is defined as the predecessor of vertex i on a shortest
		 * path from vertex j with all intermediate vertices in the set 1,2,...,k
		 */
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
				if (i != j)
					predMatrix[i][j] = j + 1;
		}
		//System.out.println("PredMatrix _init");
		//printSolution(predMatrix);
		for (k = 0; k < n; k++) {
			for (j = 0; j < n; j++) {
				for (i = 0; i < n; i++) {
					// If vertex k is on the shortest path from
					// i to j, then update the value of dist[i][j]
					if (dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
						predMatrix[i][j] = predMatrix[i][k];
					}
				}
			}
		}
		//System.out.println("PredMatrix _post");
		//printSolution(predMatrix);
		
		printSolution(dist);
		
		//Path(dist, predMatrix);
		
		printResult(dist, predMatrix);
	}

	static void printResult(int[][] dist, int[][] pred) {
		System.out.println("pair     dist    path");
		for (int i = 0; i < pred.length; i++) {
			for (int j = 0; j < pred.length; j++) {
				if (i != j) {
					int u = i + 1;
					int v = j + 1;
					String path = String.format("%d -> %d    %2d     %s", u, v, (int) dist[i][j], u);
					do {
						u = pred[u - 1][v - 1];
						path += " -> " + u;
					} while (u != v);
					System.out.println(path);
				}
			}
		}
	}

	static void Path(int[][] dist, int[][] pred) {
//loop skips accesses matrix when i=j (0,0)(1,1)(2,2)...(n,n)
		for (int i = 0; i < pred.length; i++) {
			for (int j = 0; j < pred.length; j++) {
				if (i != j) {
					int u = i + 1;
					int v = j + 1;
					//System.out.println(" (u,v)= " + u + ", " + v );
					String path = String.format("%d -> %d    %2d     %s", u, v, (int) dist[i][j], u);
					do {
						System.out.println("  "+ pred[i][j]);
						u = pred[u - 1][v - 1];
						path += " -> " + u;
						//System.out.println("  u:=" + u);
					} while (u != v);
					System.out.println(path);
				}
			}
		}

	}

	static void printSolution(int dist[][]) {
		System.out.println("Following matrix: ");
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (dist[i][j] == I)
					System.out.print("I   \t");
				else
					System.out.print(dist[i][j] + "   \t");
			}
			System.out.println();
		}
	}



	public static void main(String[] args) {
		int graph[][] = { { 0, 5, I, 10 }, { I, 0, 3, I }, { I, I, 0, 1 }, { I, I, I, 0 } };
		// Assume an adjacency matrix representation
		// Assume vertices are numbered 1,2,…,n
		// The input is a n x n matrix
		int graph2[][] = { { 0, 3, 8, I, -4 }, { I, 0, I, 1, 7 }, { I, 4, 0, I, I }, { 2, I, -5, 0, I },
				{ I, I, I, 6, 0 } };
		int graph3[][] = { { 0, I, -2, I }, { 4, 0, 3, I }, { I, I, 0, 2 }, { I, -1, I, 0 } };

		int[][] weights = { { 1, 3, -2 }, { 2, 1, 4 }, { 2, 3, 3 }, { 3, 4, 2 }, { 4, 2, -1 } };
		
		printSolution(graph2);
		System.out.println("\n\tSolution Matrix:");
		FloydWarshall(graph2);

	}
}
