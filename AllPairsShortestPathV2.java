/*
Pseudo Code
Floyd-Warshall(W) {
	n = rows[W]
	for i=1 to n do
	       for j = 1 to n do
	              D[i,j] = W[i,j]

	for k=1 to n do
	       for i=1 to n do
	             for j=1 to n do
		     if (D[i,k]+D[k,j] < D[i,j])
			D[i,j]=D[i,k]+D[k,j]	

     return D
}	
*/
public class AllPairsShortestPathV2 {
	static int n = 5;//n=rows[W]
	static int I = 99999999;//represents INFINITY

	static void FloydWarshall(int W[][]) {
		int[][] dist = new int[n][n];// will represent the weight of the shortest path from vertex i to vertex j
		int i, j, k;

		dist=W;

			// Floyd-Warshall Algorithm O(n^3)
			for (k = 0; k < n; k++)
				for (j = 0; j < n; j++)
					for (i = 0; i < n; i++)
						// If vertex k is on the shortest path from
						// i to j, then update the value of dist[i][j]
						if (dist[i][k] + dist[k][j] < dist[i][j]) {
							dist[i][j] = dist[i][k] + dist[k][j];
						}
		
		printMatrix(dist);
	}

	public static void main(String[] args) {	
		// Assume an adjacency matrix representation
		// Assume vertices are numbered 1,2,…,n
		// The input is a n x n matrix (see README.md)
		int graph2[][] = { 
				{ 0, 3, 8, I, -4 },
				{ I, 0, I, 1, 7 }, 
				{ I, 4, 0, I, I }, 
				{ 2, I, -5, 0, I },
				{ I, I, I, 6, 0 } 
				};
		FloydWarshall(graph2);
	}
	static void printMatrix(int dist[][]) {
		System.out.println("New Matrix: ");
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
}

/*
OUTPUT:
New Matrix: 
0   	1   	-3   	2   	-4   	
3   	0   	-4   	1   	-1   	
7   	4   	0   	5   	3   	
2   	-1   	-5   	0   	-2   	
8   	5   	1   	6   	0   	
 
*/