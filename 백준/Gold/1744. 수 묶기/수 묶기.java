import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1744 {
  static int N;
  static int ans = 0, cnt = 0;
  static List<Integer> pos = new ArrayList<>();
  static List<Integer> neg = new ArrayList<>();

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1744.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());

    // 입력받은 숫자가 하나면 그 수 그대로 출력
    if (N == 1) {
      st = new StringTokenizer(br.readLine());
      System.out.println(Integer.parseInt(st.nextToken()));
    }
    // 입력받은 숫자가 2개 이상이면
    else {
      for (int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        // 양수, 0, 음수에 맞게 저장
        if (num == 0) {
          cnt++;
        } else if (num > 0) {
          pos.add(num);
        } else {
          neg.add(num);
        }
      }

      // 양수 배열을 내림차순 정렬
      pos.sort((o1, o2) -> Integer.compare(o2, o1));
      // 양수 배열의 앞쪽부터 숫자 2개씩 가져와서 두 수를 곱한 경우와 더한 경우 중 더 큰 경우를 정답에 더함
      for (int i = 0; i < pos.size() - 1; i += 2) {
        ans += Math.max(pos.get(i) * pos.get(i + 1), pos.get(i) + pos.get(i + 1));
      }
      // 양수 배열의 원소가 홀수개면 2개씩 가져왔을 때 마지막 수 하나가 남음
      // 그 수는 그냥 정답에 더해줌
      if (pos.size() % 2 == 1) {
        ans += pos.get(pos.size() - 1);
      }

      // 음수 배열을 오름차순 정렬
      neg.sort(null);
      // 음수 배열에서 숫자 2개씩 가져옴
      // 이 두 수를 곱하면 양수가 되기 때문에 곱해서 정답에 더함
      for (int i = 0; i < neg.size() - 1; i += 2) {
        ans += neg.get(i) * neg.get(i + 1);
      }
      // 음수 배열의 원소가 홀수개이고, 입력받은 0이 하나도 없으면 정답에 음수 배열의 마지막 원소를 더함
      // 입력받은 0이 하나라도 있으면 음수 배열의 마지막 원소와 0을 곱해서 0이 되기 때문에 더할 필요가 없음
      if (neg.size() % 2 == 1 && cnt == 0) {
        ans += neg.get(neg.size() - 1);
      }

      System.out.println(ans);
    }

    br.close();
  }
}
