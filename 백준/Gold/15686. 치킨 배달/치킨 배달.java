import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
  public static int n, m, tmp;
  public static List<int[]> house = new ArrayList<>();
  public static List<int[]> chicken = new ArrayList<>();
  // s : 전체 치킨집 중에서 현재 선택한 치킨집의 위치
  public static Stack<int[]> s = new Stack<>();
  public static int ans = Integer.MAX_VALUE;

  public static void findMinDistance (int start) {
    // 치킨집을 m개 고르면
    if (s.size() == m) {
      tmp = 0;
      // 각 집에 대해 현재 선택한 치킨집 중 가장 가까운 치킨집과의 거리를 구함
      for (int[] h : house) {
        int minDistance = Integer.MAX_VALUE;
        for (int[] c : s) {
          minDistance = Math.min(minDistance, Math.abs(h[0] - c[0]) + Math.abs(h[1] - c[1]));
        }
        // 현재 선택한 치킨집에 대해 도시의 치킨 거리를 구함
        tmp = tmp + minDistance;
      }
      // 도시의 치킨 거리 중 가장 짧은 거리를 구함
      ans = Math.min(ans, tmp);
    }
    // 현재 치킨집을 m개보다 적게 선택했으면
    else {
      for (int i = start; i < chicken.size(); i++) {
        // 선택하지 않은 치킨집을 s에 집어넣음
        s.add(new int[] {chicken.get(i)[0], chicken.get(i)[1]});
        // 선택한 치킨집 다음 위치에서 함수를 호출
        // 치킨집이 m개가 선택되면 치킨 거리를 구함
        // m개보다 적으면 다음 치킨집을 s에 집어넣음
        findMinDistance(i + 1);
        // i번째 치킨집에 대한 최소 거리를 구하는 과정이 끝났으면 pop을 통해 i번째 치킨집을 제외시킴
        s.pop();
      }
    }
  }

  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/15686/15686.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    n = Integer.parseInt(num[0]);
    m = Integer.parseInt(num[1]);

    for (int i = 0; i < n; i++) {
      String[] input = br.readLine().split(" ");
      // 집과 치킨집의 위치를 각각 저장
      for (int j = 0; j < n; j++) {
        if (Integer.parseInt(input[j]) == 1) {
          house.add(new int[] {i, j});
        }
        else if (Integer.parseInt(input[j]) == 2) {
          chicken.add(new int[] {i, j});
        }
      }
    }

    findMinDistance(0);

    System.out.println(ans);

    br.close();
  }
}
