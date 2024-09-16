import java.io.BufferedReader;
// import java.io.FileReader;
import java.io.InputStreamReader;

public class Main_1987 {
  public static int R, C, ans;
  public static int[][] map;
  // check : 해당 알파벳의 통과 여부 확인
  public static boolean[] check = new boolean[26];
  public static int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

  // 격자 범위 안에 들어있는지 확인
  public static boolean isInBound (int row, int col) {
    return (0 <= row && row < R && 0 <= col && col < C);
  }

  public static void backTracking (int row, int col, int cnt) {
    // 입력받은 행, 열을 기준으로 상, 하, 좌, 우 탐색
    for (int[] d : dir) {
      int nr = row + d[0];
      int nc = col + d[1];
      if (isInBound(nr, nc)) {
        int next = map[nr][nc];
        // 탐색한 위치의 알파벳을 통과하지 않은 경우
        if (!check[next]) {
          // 해당 위치의 알파벳 true로 변경
          check[next] = true;
          // 해당 위치에서 다시 상, 하, 좌, 우 탐색
          // 길이는 이전 길이에 1을 더함
          backTracking(nr, nc, cnt + 1);
          // 탐색이 종료되면 해당 위치의 알파벳 false로 변경
          check[next] = false;
        }
        // 탐색한 위치의 알파벳을 통과한 경우
        else {
          // 현재까지의 길이와 이전 길이 중 큰 값을 구함
          ans = Math.max(ans, cnt);
        }
      }
    }
    // 상, 하, 좌, 우 모두 갈 수 없을 때 아래의 코드가 없으면 정답을 구하지 않음
    // ex. AC
    // 때문에 상, 하, 좌, 우 탐색이 끝난 후에도 정답을 갱신함
    ans = Math.max(ans, cnt);
  }
  public static void main (String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/1987/1987.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    R = Integer.parseInt(num[0]);
    C = Integer.parseInt(num[1]);

    map = new int[R][C];

    for (int i = 0; i < R; i++) {
      String input = br.readLine();
      for (int j = 0; j < C; j++) {
        // 입력을 알파벳으로 받을 때 숫자로 변환하는 방법
        map[i][j] = input.charAt(j) - 'A';
      }
    }

    ans = Integer.MIN_VALUE;

    int start = map[0][0];    
    check[start] = true;
    backTracking(0, 0, 1);

    System.out.println(ans);

    br.close();
  }
}
