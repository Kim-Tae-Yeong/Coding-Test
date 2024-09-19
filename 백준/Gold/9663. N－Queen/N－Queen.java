ㅍimport java.io.BufferedReader;
// import java.io.FileReader;
import java.io.InputStreamReader;

public class Main_9663 {
  private static int n, ans;
  // 2차원 배열의 값이 0인 부분에 queen을 놓을 수 있음
  private static int[][] map;

  private static boolean isInBound (int row, int col) {
    return (0 <= row && row < n && 0 <= col && col < n);
  }

  // queen을 특정 위치에 두었을 때, 놓을 수 없는 위치 표시
  // 현재 위치보다 위쪽은 표시하지 않아도 됨
  // 각 row마다 queen이 하나씩 포함되어야 함
  // 때문에 위, 오른쪽 위, 왼쪽 위에는 둘 수 없음
  private static void insertQueen (int row, int col) {
    // 현재 자리
    map[row][col] += 1;
    for (int i = 1; i < n; i++) {
      // 오른쪽
      if (isInBound(row, col + i)) {
        map[row][col + i] += 1;
      }
      // 왼쪽
      if (isInBound(row, col - i)) {
        map[row][col - i] += 1;
      }
      // 아래
      if (isInBound(row + i, col)) {
        map[row + i][col] += 1;
      }
      // 오른쪽 아래
      if (isInBound(row + i, col + i)) {
        map[row + i][col + i] += 1;
      }
      // 왼쪽 아래
      if (isInBound(row + i, col - i)) {   
        map[row + i][col - i] += 1;
      }
    }
  }

  // queen을 특정 위치에서 제거했을 때, 놓을 수 있는 위치 표시
  // insertQueen과 같은 이유로 위, 오른쪽 위, 왼쪽 위는 표시하지 않아도 됨
  private static void deleteQueen (int row, int col, int cnt) {
    // 현재 위치
    map[row][col] -= 1;
    for (int i = 1; i < n; i++) {
      // 오른쪽
      if (isInBound(row, col + i)) {
        map[row][col + i] -= 1;
      }
      // 왼쪽
      if (isInBound(row, col - i)) {
        map[row][col - i] -= 1;
      }
      // 아래
      if (isInBound(row + i, col)) {
        map[row + i][col] -= 1;
      }
      // 오른쪽 아래
      if (isInBound(row + i, col + i)) {
        map[row + i][col + i] -= 1;
      }
      // 왼쪽 아래
      if (isInBound(row + i, col - i)) {   
        map[row + i][col - i] -= 1;
      }
    }
  }

  private static void queen(int row, int col, int cnt) {
    // 현재 자리에 queen을 위치시킴
    insertQueen(row, col);
    
    // 현재 row가 마지막 row일 때
    if (row == n - 1) {
      // 놓은 퀸의 개수가 원하는 개수와 같으면 정답
      if (cnt == n) {
        ans = ans + 1;
      }
    }
    // 현재 row가 마지막 row가 아니면
    else {
      // 다음 row에 대해서 queen을 놓을 수 있는 위치 확인
      for (int i = 0; i < n; i++) {
        if (map[row + 1][i] == 0) {
          queen(row + 1, i, cnt + 1);
        }
      }
    }

    // 현재 자리에 대해서 탐색이 끝나면 현재 자리에 위치한 queen 제거
    deleteQueen(row, col, cnt);
  }  

  public static void main (String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/9663/9663.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    map = new int[n][n];
    ans = 0;

    for (int col = 0; col < n; col++) {
      queen(0, col, 1);
    }
    
    System.out.println(ans);

    br.close();
  }
}
