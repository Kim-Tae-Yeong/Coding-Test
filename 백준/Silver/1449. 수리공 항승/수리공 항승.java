import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1449 {
  static int N, L;
  static int ans = 0;
  static List<Integer> l = new ArrayList<>();
  static boolean check = false;
  static double end;

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1449.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    L = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      l.add(Integer.parseInt(st.nextToken()));
    }

    l.sort(null);

    // 물이 새는 곳의 위치를 확인
    for (int i = 0; i < N; i++) {
      // 현재 물이 새는 곳의 위치가 테이프의 끝자리보다 먼 곳이면
      if (l.get(i) > end) {
        // 테이프 추가
        ans++;
        // 테이프 끝자리 갱신
        end = l.get(i) - 0.5 + L;
      }
    }

    System.out.println(ans);

    br.close();
  }
}
