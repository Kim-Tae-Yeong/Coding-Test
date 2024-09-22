import java.io.BufferedReader;
// import java.io.FileReader;
import java.io.InputStreamReader;

public class Main_2448 {
  private static int n;
  private static char[][] map;
  private static StringBuilder sb = new StringBuilder();

  private static void drawStar (int row, int col, int size) {
    // 가장 작은 삼각형일 때
    if (size == 3) {
      map[row][col] = '*';
      map[row + 1][col - 1] = '*';
      map[row + 1][col + 1] = '*';
      map[row + 2][col - 2] = '*';
      map[row + 2][col - 1] = '*';
      map[row + 2][col] = '*';
      map[row + 2][col + 1] = '*';
      map[row + 2][col + 2] = '*';
    }
    // 그게 아닐 때
    else {
      // 삼각형 크기를 절반으로 줄임
      int s = size / 2;
      // 위쪽 삼각형 탐색
      drawStar(row, col, s);
      // 대각선 왼쪽 아래 삼각형 탐색
      drawStar(row + s, col - s, s);
      // 대각선 오른쪽 아래 삼각형 탐색
      drawStar(row + s, col + s, s);
    }
  }
  public static void main (String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/2448/2448.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    
    // 최초 배열 공백으로 초기화
    map = new char[n][n * 2 - 1];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < 2 * n - 1; j++) {
        map[i][j] = ' ';
      }
    }

    drawStar(0, n - 1, n);

    // 배열을 그냥 출력하면 시간 초과 발생
    // 때문에 StringBuilder 사용
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < 2 * n - 1; j++) {
        sb.append(map[i][j]);
      }
      if (i != n - 1) {
        sb.append("\n");
      }
    }

    System.out.println(sb.toString());

    br.close();
  }  
}
