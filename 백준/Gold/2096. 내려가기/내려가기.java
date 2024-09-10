import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  public static int n, minAns, maxAns;
  public static int[][] map, minMap, maxMap;
  // 다음 row로 내려갈 때 (현재 col - 1, 현재 col, 현재 col + 1) 3 방향으로 갈 수 있음
  public static int[] dir = {-1, 0, 1};

  // col이 알맞은 index를 갖는지 확인
  public static boolean isInBound (int col) {
    return (0 <= col && col < 3);
  }

  public static void findMax () {
    // 최댓값 배열에서 첫 번째 row 초기화
    for (int i = 0; i < 3; i++) {
      maxMap[0][i] = map[0][i];
    }
    // 각 row에 대해
    for (int i = 0; i < n - 1; i++) {
      // 각 col에 대해
      for (int j = 0; j < 3; j++) {
        // 각 col에 대해 갈 수 있는 방향 확인
        for (int d : dir) {
          int pos = j + d;
          if (isInBound(pos)) {
            // 갈 수 있는 방향이면 기존값과 현재 방향으로 내려온 값 중 더 큰 값으로 설정
            maxMap[i + 1][pos] = Math.max(maxMap[i + 1][pos], maxMap[i][j] + map[i + 1][pos]);
          }
        }
      }
    }
  }

  public static void findMin () {
    // 최솟값 배열에서 첫 번째 row 초기화
    for (int i = 0; i < 3; i++) {
      minMap[0][i] = map[0][i];
    }
    // 각 row에 대해
    for (int i = 0; i < n - 1; i++) {
      // 각 col에 대해
      for (int j = 0; j < 3; j++) {
        // 각 col에 대해 갈 수 있는 방향 확인
        for (int d : dir) {
          int pos = j + d;
          if (isInBound(pos)) {
            // 갈 수 있는 방향이면 기존값과 현재 방향으로 내려온 값 중 더 작은 값으로 설정
            minMap[i + 1][pos] = Math.min(minMap[i + 1][pos], minMap[i][j] + map[i + 1][pos]);
          }
        }
      }
    }
  }


  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/2096/2096.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    map = new int[n][3];

    // 최댓값 배열 및 정답 초기화
    maxMap = new int[n][3];
    for (int i = 0; i < n; i++){
      Arrays.fill(maxMap[i], Integer.MIN_VALUE);
    }
    maxAns = Integer.MIN_VALUE;

    // 최소값 배열 및 정답 초기화
    minMap = new int[n][3];
    for (int i = 0; i < n; i++){
      Arrays.fill(minMap[i], Integer.MAX_VALUE);
    }
    minAns = Integer.MAX_VALUE;

    for (int i = 0; i < n; i++) {
      String[] input = br.readLine().split(" ");
      for (int j = 0; j < 3; j++) {
        map[i][j] = Integer.parseInt(input[j]);
      }
    }
    
    findMax();

    findMin();

    // 최댓값 & 최솟값 배열의 마지막 row을 탐색하면서 가장 큰 값 & 작은 값 탐색
    for (int i = 0; i < 3; i++) {
      maxAns = Math.max(maxAns, maxMap[n - 1][i]);
      minAns = Math.min(minAns, minMap[n - 1][i]);
    }

    System.out.println(maxAns + " " + minAns);
    
    br.close();
  }
}
