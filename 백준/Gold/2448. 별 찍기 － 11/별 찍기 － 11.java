import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Main {
  private static int n;
  private static char[][] map;

  private static void draw(int row, int col, int size) {
    // 크기가 3이면 현재 위치에서 별 그리기
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
    // 크기가 3보다 크면
    else {
      // 크기를 절반으로 줄임
      size = size / 2;
      // 현재 위치에서 함수 실행
      draw(row, col, size);
      // 좌하단으로 이동 후 함수 실행
      draw(row + size, col - size, size);
      // 우하단으로 이동 후 함수 실행
      draw(row + size, col + size, size);
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new
    // FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/2448/2448.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    // row : n & col : 2 * n 배열 생성
    map = new char[n][2 * n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < 2 * n; j++) {
        // 공백으로 초기화
        map[i][j] = ' ';
      }
    }
    // row = 0 & col = n - 1 위치에서 재귀 함수 실행
    draw(0, n - 1, n);
    for (char[] row : map) {
      System.out.println(row);
    }
    br.close();
  }
}
