import java.io.*;
import java.util.*;

public class Solution {

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1859.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int T = Integer.parseInt(st.nextToken());

    for (int i = 0; i < T; i++) {
      st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int[] arr = new int[N];

      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        arr[j] = Integer.parseInt(st.nextToken());
      }

      int max = arr[N - 1];
      long ans = 0;
      for (int j = N - 1; j > -1; j--) {
        if (arr[j] < max) {
          ans += (max - arr[j]);
        } else {
          max = arr[j];
        }
      }

      System.out.println("#" + (i + 1) + " " + ans);
    }
    br.close();
  }
}