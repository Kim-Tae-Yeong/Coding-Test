import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2343 {
  static int N, M;
  // left : 블루레이 최소 크기
  // right : 블루레이 최대 크기
  static int left = 0, right = 0, answer = 0;
  static int[] lessons;

  static int getBlueRayCount(int size) {
    // 블루레이 1개부터 시작
    int count = 1;
    // 현재까지 담긴 강의 시간
    int sum = 0;

    for (int lesson : lessons) {
      // 현재까지 담긴 강의 시간 + 현재 강의 시간이 size보다 크면
      if (sum + lesson > size) {
        // 블루레이 1개 추가
        count++;
        // 현재까지 담긴 강의시간은 0으로 초기화
        sum = 0;
      }
      // 현재 강의를 담음
      sum += lesson;
    }

    // 필요한 블루레이 개수 반환
    return count;
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader("./2343.txt"));
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    lessons = new int[N];

    st = new StringTokenizer(br.readLine());

    for (int i = 0; i < N; i++) {
      lessons[i] = Integer.parseInt(st.nextToken());
      // 블루레이 최소 크기는 강의 하나만 들어갈 때
      // 즉 가장 큰 강의 시간만큼의 크기 필요
      left = Math.max(left, lessons[i]);
      // 블루레이 최대 크기는 모든 강의를 한번에 담을 때
      right += lessons[i];
    }

    while (left <= right) {
      // 블루레이 중간 크기를 구함
      int mid = (left + right) / 2;
      // 중간 크기로 했을 때 블루레이가 몇 개 나오는지 구함
      int count = getBlueRayCount(mid);

      // M보다 많은 블루레이가 필요하면
      if (count > M) {
        // 최소 크기를 늘림
        left = mid + 1;
      }
      // M이랑 같거나 M보다 작으면
      else {
        // 현재 크기 저장
        answer = mid;
        // 최대 크기를 줄임
        right = mid - 1;
      }
    }

    System.out.println(answer);

    br.close();
  }
}
