import java.io.BufferedReader;
// import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.TreeMap;

public class Main_2531 {
  private static int n, d, k, c, extra, ans;
  // 최초 입력받은 초밥 번호 저장
  private static List<Integer> sushi = new ArrayList<>();
  // 연속해서 먹을 초밥 번호 저장
  private static Deque<Integer> deque = new ArrayDeque<>();
  // 연속해서 먹을 초밥에서 각 초밥 번호가 몇개인지 저장
  private static TreeMap<Integer, Integer> cnt = new TreeMap<>();
  public static void main (String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/2531/2531.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] num = br.readLine().split(" ");
    n = Integer.parseInt(num[0]);
    d = Integer.parseInt(num[1]);
    k = Integer.parseInt(num[2]);
    c = Integer.parseInt(num[3]);

    for (int i = 0; i < n; i++) {
      sushi.add(Integer.parseInt(br.readLine()));
    }

    // 쿠폰으로 받은 초밥이 내가 먹은 초밥에 없으면 1, 있으면 0
    extra = 1;
    ans = 0;
    // 초밥의 맨 뒤와 맨 앞이 연결되어 있기 때문에 2 * n번 반복문 실행
    for (int i = 0; i < 2 * n; i++) {
      // 연속해서 먹은 초밥 개수가 k이면 제일 앞에 있는 초밥 제거
      if (deque.size() == k) {
        int preSushi = deque.removeFirst();
        // 해당 초밥의 개수를 하나 줄임
        cnt.put(preSushi, cnt.get(preSushi) - 1);
        // 해당 초밥이 없으면 제거
        if (cnt.get(preSushi) == 0) {
          cnt.remove(preSushi);
          // 제거한 초밥이 쿠폰 초밥이면 extra 1로 변경
          // 즉 현재 먹은 초밥에 쿠폰 초밥 1개를 더 먹을 수 있음
          if (preSushi == c) {
            extra = 1;
          }
        }
      }
      // 먹을 초밥을 집어넣음
      // index를 i % n으로 받아서 초밥 배열을 2바퀴 돔
      int nextSushi = sushi.get(i % n);
      // 먹을 초밥이 쿠폰 초밥이면 초밥 종류는 늘어나지 않기 때문에 extra는 0
      if (nextSushi == c) {
        extra = 0;
      }
      // 초밥 개수 저장
      cnt.put(nextSushi, cnt.getOrDefault(nextSushi, 0) + 1);
      deque.add(nextSushi);
      // 기존에 먹을 수 있는 최대 종류와 (현재 종류 + 쿠폰 초밥) 중 큰 값을 가져옴
      ans = Math.max(ans, cnt.size() + extra);
    }

    System.out.println(ans);
    br.close();
  }  
}
