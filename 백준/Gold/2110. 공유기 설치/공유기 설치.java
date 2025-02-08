import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int N, C, left, right;
  static int[] pos;

  static int getCount(long size) {
    int count = 1;
    int lastPos = pos[0];

    for (int i = 1; i < N; i++) {
      if (pos[i] - lastPos > size) {
        count++;
        lastPos = pos[i];
      }
    }

    return count;
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./2110.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    pos = new int[N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      pos[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(pos);

    left = 1;
    right = pos[N - 1] - pos[0];

    while (left < right) {
      int mid = (left + right) / 2;
      int count = getCount(mid);
      if (count >= C) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    System.out.println(left);

    br.close();
  }
}
