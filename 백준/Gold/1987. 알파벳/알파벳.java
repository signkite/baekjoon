import java.io.*;
import java.util.*;

// 촤표 및 이동 횟수를 저장하는 객체
class Point {
	int x;
	int y;
	int move;
	Point (int x, int y, int move) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int R, C;
	static char[][] board;
	static boolean[] check = new boolean[26];  // check[Alp - 'A'] == true : Alp가 이미 등장하였음을 뜻한다.
	static int maxMove;
	
	// dfs로 maxMove를 찾는 함수
	public static void dfs(int move, int x, int y) {
		boolean canMove = false;
		
		for (int i = 0; i < 4; ++i) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || R <= nx) continue;
			if (ny < 0 || C <= ny) continue;
			if (check[board[nx][ny] - 'A']) continue;
			
			canMove = true;
			check[board[nx][ny] - 'A'] = true;
			dfs(move + 1, nx, ny);
			check[board[nx][ny] - 'A'] = false;		
		}
		
		// 더이상 움직일 수 없는 곳까지 도달했다면 최대 이동횟수 갱신
		if (!canMove)
			maxMove = Math.max(move, maxMove);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 보드 입력받기
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new char[R][C];
		for (int r = 0; r < R; ++r) {
			String line = br.readLine();
			for (int c = 0; c < C; ++c) {
				board[r][c] = line.charAt(c);
			}
		}
		
		// (0, 0) 부터 dfs 시작
		check[board[0][0] - 'A'] = true;
		dfs(1, 0, 0);

		System.out.println(maxMove);
	}
}