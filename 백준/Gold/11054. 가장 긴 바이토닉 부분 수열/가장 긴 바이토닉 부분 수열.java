import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Main {
  private static int n;
  private static int ans = Integer.MIN_VALUE;
  private static int[] a;
  private static int[] cresendo;
  private static int[] decresendo;

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/11054/11054.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    a = new int[n + 2];
    String[] input = br.readLine().split(" ");
    for(int i = 1; i < n + 1; i++) {
      a[i] = Integer.parseInt(input[i - 1]);
    }
    a[n + 1] = -1;

    cresendo = new int[n + 1];
    cresendo[1] = 1;

    for(int i = 2; i < n + 1; i++) {
      for(int j = i - 1; j > -1; j--) {
        if(a[i] > a[j]) {
          cresendo[i] = Math.max(cresendo[i], cresendo[j] + 1);
        }
      }
    }

    decresendo = new int[n + 1];
    decresendo[n - 1] = 1;
    for(int i = n - 2; i > -1; i--) {
      for(int j = i + 1; j < n + 1; j++) {
        if (a[i + 1] > a[j + 1]) {
          decresendo[i] = Math.max(decresendo[i], decresendo[j] + 1);
        }
      }
    }

    for(int i = 0; i < n; i++) {
      ans = Math.max(ans, cresendo[i + 1] + decresendo[i]);
    }

    System.out.println(ans - 1);

    br.close();
  }
}
