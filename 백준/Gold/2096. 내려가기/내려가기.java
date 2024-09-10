import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  public static int n, minAns, maxAns;
  public static int[][] map, minMap, maxMap;
  public static int[] dir = {-1, 0, 1};

  public static boolean isInBound (int col) {
    return (0 <= col && col < 3);
  }

  public static void findMin () {
    for (int i = 0; i < 3; i++) {
      minMap[0][i] = map[0][i];
    }
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < 3; j++) {
        for (int d : dir) {
          int pos = j + d;
          if (isInBound(pos)) {
            minMap[i + 1][pos] = Math.min(minMap[i + 1][pos], minMap[i][j] + map[i + 1][pos]);
          }
        }
      }
    }
  }

  public static void findMax () {
    for (int i = 0; i < 3; i++) {
      maxMap[0][i] = map[0][i];
    }
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < 3; j++) {
        for (int d : dir) {
          int pos = j + d;
          if (isInBound(pos)) {
            maxMap[i + 1][pos] = Math.max(maxMap[i + 1][pos], maxMap[i][j] + map[i + 1][pos]);
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

    minMap = new int[n][3];
    for (int i = 0; i < n; i++){
      Arrays.fill(minMap[i], Integer.MAX_VALUE);
    }
    minAns = Integer.MAX_VALUE;

    maxMap = new int[n][3];
    for (int i = 0; i < n; i++){
      Arrays.fill(maxMap[i], Integer.MIN_VALUE);
    }
    maxAns = Integer.MIN_VALUE;
    

    for (int i = 0; i < n; i++) {
      String[] input = br.readLine().split(" ");
      for (int j = 0; j < 3; j++) {
        map[i][j] = Integer.parseInt(input[j]);
      }
    }

    findMin();

    findMax();

    for (int i = 0; i < 3; i++) {
      minAns = Math.min(minAns, minMap[n - 1][i]);
      maxAns = Math.max(maxAns, maxMap[n - 1][i]);
    }

    System.out.println(maxAns + " " + minAns);
    

    br.close();
  }
}