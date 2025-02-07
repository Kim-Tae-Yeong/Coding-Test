import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int N, M;
  static int left = 0, right = 0;
  static int[] lessons;

  static int getBlueRayCount(int[] lessons, int size) {
    int count = 1;
    int sum = 0;

    for (int lesson : lessons) {
      if (sum + lesson > size) {
        count++;
        sum = 0;
      }
      sum += lesson;
    }

    return count;
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./2343.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    lessons = new int[N];

    st = new StringTokenizer(br.readLine());

    for (int i = 0; i < N; i++) {
      lessons[i] = Integer.parseInt(st.nextToken());
      left = Math.max(left, lessons[i]);
      right += lessons[i];
    }

    while (left < right) {
      int mid = (left + right) / 2;
      int count = getBlueRayCount(lessons, mid);

      if (count > M) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    System.out.println(left);

    br.close();
  }
}
