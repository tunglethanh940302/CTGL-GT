import java.io.BufferedReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

import javax.security.auth.callback.NameCallback;
import javax.swing.text.html.HTMLDocument.Iterator;

public class Graph {
	static int INF = 9999;
	
	int[][] a;
	
	int n;
	
	char[] b = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
	
	public Graph() {
		
	}
	
	public boolean loadData(String filepath) {											// load dữ liệu từ file
		Path path = Paths.get(filepath);												
		Charset charset = Charset.forName("US-ASCII");
		try(BufferedReader reader = Files.newBufferedReader(path,charset)){				// tạo BufferedReader và bắt try catch
			String line = null;															
			int count = 0;
			while((line = reader.readLine()) != null) {									// đọc từng dòng trong file và lưu vào biến String line
				String k[] = line.split(" ");											// chia chuỗi line thành các phần nhỏ và lưu vào mảng k[]
				if(k.length == 1) {
					a = new int[Integer.parseInt(k[0])][Integer.parseInt(k[0])];		// nếu k chứa 1 phần tử thì khởi tạo mảng 2 chiều a với số lượng phần tử = giá trị lưu trong mảng k[0]
				}else {
					for (int i = 0; i < k.length; i++) {								
						a[count][i] = Integer.parseInt(k[i]);							// nếu mảng k chứa nhiều hơn 1 phần tử thì lưu tất cả các phần tử của mảng k vào mảng 2 chiều a[count][i]
					}
					count++;															// tăng biến count thêm 1 
				}
			}
			n = count;																	// gán n = count
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
//		displayWeights();
		
		return true;	
	}
	

	
	public void displayWeights() {														// hiển thị ma trận
		System.out.println("\t\tThe weighted matrix of the graph:"
				+ "\n\t==================================================");
		for (int i = 0; i < a.length; i++) {											// duyệt mảng 2 chiều a[][] và in ra 
			for (int j = 0; j < a.length; j++) {
				System.out.print("\t");
				if(a[i][j] == INF){
					System.out.print("INF ");											// nếu vị trí a[i][j] == 9999 thì in INF và tới vòng lặp tiếp theo
					continue;
				}
				System.out.print(a[i][j] + " ");
				
			}
			System.out.println("");
		}
		System.out.println();
	}

    public void DFSUtil(int v, boolean visited[], Stack<Integer> stack)					// Duyệt đồ thị theo chiều sâu
    {
    	
        visited[v] = true;																// danh dau dinh v da duoc tham		
        System.out.print(b[v]);
        stack.add(v);																	// dua dinh v vao stack
        for (int i = 0; i < n; i++) {
  	       if((a[v][i] != 0 && a[v][i] != INF) && visited[i] == false) {
  	       		DFSUtil(i, visited, stack);
  	        }
        }
    }
    
	public void dfs(char A) {															// Duyệt đồ thị theo chiều sâu										
		loadData("MaTran.txt");
		Stack<Integer> stack = new Stack<Integer>();									// khởi tạo 1 stack rỗng
		boolean[] visited = new boolean[n];												// tạo mảng 1 chiều kiểu boolean có n phần tử	
		
		for (int i = 0; i < n; i++) {													// gán tất cả các giá trị trong mảng là false
			visited[i] = false;
		}
		int v = 0;	
		for (int i = 0; i < b.length; i++) {											// lấy thông tin vị trí của ký tự nhập vào
			if(b[i] == A) {
				v = i;
			}
		}
		System.out.print("DFS_Graph: ");								
		DFSUtil(v, visited, stack);														// chạy hàm DFSUtil
		System.out.println("\n");
	}
	
	
	public void bfsUtil(int v, boolean[] visited, Queue<Integer> queue) {				// Duyệt đồ thị theo chiều rộng
		
		queue.add(v);																	// thêm vào hàng đợi
		
		visited[v] = true;																// đánh dấu đã thăm
		while(!queue.isEmpty()) {														// trong khi hàng đợi còn phần tử thì
			int u = queue.poll();														// lấy phần tử đầu tiên ra khỏi hàng đợi
			System.out.print(b[u]);														// in ra màn hình		
			for (int i = 0; i < n; i++) {
				if((a[u][i] != 0 && a[u][i] != INF) && visited[i] == false) {			// duyệt các đỉnh liền kề
	  	       		queue.add(i);														// nếu có thì thêm vào queue
	  	       		visited[i] = true;													// đánh dấu đã thăm
	  	        }
			}
		}
	}
	
	public void bfs(char A) {															// Duyệt đồ thị theo chiều rộng
		loadData("MaTran.txt");															// load ma trận từ file
		Queue<Integer> queue = new LinkedList<Integer>();								// khởi tạo hàng đợi 
		int v = 0;
		for (int i = 0; i < b.length; i++) {											// xách đinh vị trí ký tự nhập vào
			if(b[i] == A) {
				v = i;
			}
		}
		boolean[] visited = new boolean[n];												// tạo mảng 1 chiều kiểu boolean có n phần tử					
		for (int i = 0; i < n; i++) {	
			visited[i] = false;															// gán tất cả các giá trị trong mảng là false
		}
		System.out.print("BFS_Graph: ");
		bfsUtil(v, visited, queue);														// gọi hàm bfsUtil
		System.out.println("\n");
	}


    void displayStep(int step, boolean[] selected, int[] dist, int[] path, int p, int[] sele, int nSele,
            boolean[] stopDisplay) {
        StringBuilder seletedVertex = new StringBuilder();
        StringBuilder minDist = new StringBuilder();
        String minDist2;
        String vDist;
        																				//add seleted vetices to String to print out
        for (int i = 0; i < step + 1; i++) {
            seletedVertex.append(b[sele[i]]);
        }

        																				//add distance and previous vertex to String to print out
        for (int j = 1; j < nSele; j++) {
            if (!stopDisplay[j]) {
                if (dist[j] == INF) {
                    vDist = "INF";
                } else {
                    vDist = String.valueOf(dist[j]);
                }
                minDist2 = String.format("\t(%s,%s)", vDist, b[path[j]]);
                minDist.append(minDist2);
            } else {
                minDist.append("\t");
            }
        }

        																				//Displaying steps
        String output = String.format("%3d: %-7s %s", step, seletedVertex, minDist);
        System.out.println(output);
    }

    void dijkstra(boolean[] selected, int[] dist, int[] path, int p, int q, boolean[] stopDisplay) {
        int[] previous = new int[n]; 													//store previous vertex
        ArrayList<Integer> uncheck = new ArrayList<>(); 								// store vertices that waiting to check
        int[] checked = new int[n]; 													// store checked vertices
        boolean found = false;															// use to stop when found the shortest path to target
        int step = 0;

        dist[p] = 0;
        previous[0] = -1;
        uncheck.add(p);
        selected[p] = true;																// mark vertex checked

        while ((!uncheck.isEmpty()) && (found == false)) {
            int current = 0;
            int minDist = Integer.MAX_VALUE;
            																			//get the uncheck vertex with min distance as current
            for (int v : uncheck) {
                if (minDist > dist[v]) {
                    minDist = dist[v];
                    current = v;
                }
            }
            																			//check current vertex
            for (int i = 0; i < a[current].length; i++) {
                if (a[current][i] != INF && a[current][i] != 0 && !selected[i]) {
                    int newDist = dist[current] + a[current][i];
                    if (newDist < dist[i]) {
//                        uncheck.remove((Integer) i);
                        dist[i] = newDist;
                        uncheck.add((Integer) i);
                        previous[i] = current;
                    }
                }
            }
            
            selected[current] = true; 													// mark current vertex as checked
            uncheck.remove((Integer) current); 											// remove vertex in waiting list            
            if (current == q) { 														// when the removed vertex is target mean found the short path to it
                found = true;
            }
            checked[step] = current; 													//add checked vertex

            																			//output each step to screen
            displayStep(step, selected, dist, previous, p, checked, n, stopDisplay);

            stopDisplay[current] = true; 												//stop display when remove(don't check anymore)
            step++; 																	// increate step count
        }

        System.out.println("The length of shortest path from A to E is " + dist[q]);

        																				//list of vertices and it previous vertex
        int sortPath = q;
        int count = 0;
        while (sortPath != -1) {
            path[count] = sortPath;
            sortPath = previous[sortPath];
            count++;
        }
    }

    void pathDijkstra(int[] dist, int[] path, int p, int q) {
        																				//use Set to remove duplicate
        Set<Integer> sorted = new HashSet<>();
        for (int i = path.length - 1; i >= 0; i--) {
            sorted.add(path[i]);
        }
        																				//print the path to screen
        for (int x : sorted) {
            if (x == p) {
                System.out.print(b[x]);
            } else {
                System.out.print("->" + b[x]);
            }
        }
        System.out.println("");
    }

     public void dijkstra(int p, int q) {
        System.out.println("Dijkstra algorithm for shortest path from A to E:");
        boolean[] selected = new boolean[n];
        int[] dist = new int[n];
        int[] path = new int[n];
        boolean[] stopDisplay = new boolean[n];

        																				//set all distance to INF
        for (int i = 0; i < dist.length; i++) {
            dist[i] = INF;
        }

        																				//output all city name
        System.out.print("\tThe S set:");
        for (int i = 1; i < a.length; i++) {
        	if(i == 1 || i == 4) 
        		System.out.print("\t");
            System.out.print(" " + b[i]);
        }
        System.out.println("\n");

        																				//Finding the shortest, output each step to screen
        dijkstra(selected, dist, path, p, q, stopDisplay);

        																				//output the shortest path to screen
        System.out.println("\nPath: ");
        pathDijkstra(dist, path, p, q);
    }
	
	
	
	
}

