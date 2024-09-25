import java.io.BufferedReader;
// import java.io.FileReader;
import java.io.InputStreamReader;

public class Main_11054 {
  private static int n;
  private static int ans = Integer.MIN_VALUE;
  private static int[] a;
  // cresendo[i] : 1 ~ i번째까지 a[i]를 포함한 커지는 수열 중 최대 길이
  private static int[] cresendo;
  // decresendo[i] : i ~ n번째까지 a[i]를 포함한 작아지는 수열 중 최대 길이
  private static int[] decresendo;

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/11054/11054.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    a = new int[n + 2];
    String[] input = br.readLine().split(" ");
    // a[0]을 0으로 초기화시켜서 커지는 수열 길이를 구할 때 가장 작은 원소가 나오면 해당 수열의 길이를 1로 만듦
    for(int i = 1; i < n + 1; i++) {
      a[i] = Integer.parseInt(input[i - 1]);
    }
    // a[n + 1]을 -1로 초기화시켜서 작아지는 수열 길이를 구할 때 가장 작은 원소가 나오면 해당 수열의 길이를 1로 만듦
    a[n + 1] = -1;

    cresendo = new int[n + 1];
    cresendo[1] = 1;

    // a[0] ~ a[n]까지 비교
    // i번째 원소에 대해 0 ~ (i - 1)번째 원소까지 비교하면서 a[i]가 더 크면 기존 커지는 수열 길이와 (해당 원소까지의 커지는 수열 길이 + 1) 중 큰 값을 가져옴
    for(int i = 2; i < n + 1; i++) {
      for(int j = i - 1; j > -1; j--) {
        if(a[i] > a[j]) {
          cresendo[i] = Math.max(cresendo[i], cresendo[j] + 1);
        }
      }
    }

    decresendo = new int[n + 1];
    decresendo[n - 1] = 1;

    // a[1] ~ a[n + 1]
    // i번째 원소에 대해 (i + 1) ~ (n + 1)번째 원소까지 비교하면서 a[i]가 더 크면 기존 작아지는 수열 길이와 (해당 원소까지의 작아지는 수열 길이 + 1) 중 큰 값을 가져옴
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
