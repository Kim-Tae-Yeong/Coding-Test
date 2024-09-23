import java.io.BufferedReader;
// import java.io.FileReader;
import java.io.InputStreamReader;

public class Main_10830 {
  private static int n;
  // b는 int 크기를 넘어가기 때문에 long으로 받음
  private static long b;
  private static int[][] map;

  // matrix의 각 원소 계산
  private static int[][] cal (int[][] a, int[][] b) {
    int[][] tmp = new int[n][n];
    int row = 0;
    int col = 0;
    while (true) {
      if (row == n) {
        break;
      }
      int elem = 0;
      for (int i = 0; i < n; i++) {
        elem += a[row][i] * b[i][col];
      }
      // tmp의 원소는 int이기 때문에 int가 넘어버리는 숫자가 원소로 들어가면 음수 등장
      // 때문에 여기서도 값을 나눠줌
      tmp[row][col] = elem % 1000;
      if (col == n - 1) {
        col = 0;
        row += 1;
      }
      else {
        col += 1;
      }
    }
    return tmp;
  }

  // A ^ n = A ^ (n / 2) * A (n / 2)
  private static int[][] matrix (long idx) {
    if (idx == 1) {
      return map;
    }
    else {
      // idx가 홀수인지 확인
      boolean check = false;
      if (idx % 2 == 1) {
        check = true;
      }
      idx = idx / 2;
      // idx를 반으로 나눈 matrix를 가져옴
      int[][] half = matrix(idx);
      // 해당 matrix 제곱 계산
      int[][] ans = cal(half, half);
      // idx가 홀수이면, A ^ n = A ^ (n - 1) * A
      // idx가 홀수이면 입력받은 matrix를 한번 더 곱해줌
      if (check) {
        ans = cal(ans, map);
      }
      return ans;
    }
  }

  public static void main (String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/10830/10830.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    n = Integer.parseInt(num[0]);
    b = Long.parseLong(num[1]); 

    map = new int[n][n];

    for (int i = 0; i < n; i++) {
      String[] input = br.readLine().split(" ");
      for (int j = 0; j < n; j++) {
        map[i][j] = Integer.parseInt(input[j]);
      }
    }

    int[][] ans = matrix(b);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(ans[i][j] % 1000);
        System.out.print(" ");
      }
      System.out.println();
    }

    br.close();
  }
}
