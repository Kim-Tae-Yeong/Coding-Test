import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2343 {
  static int N, M;
  // left : 블루레이 최소 크기
  // right : 블루레이 최대 크기
  static int left = 0, right = 0;
  static int[] lessons;

  static int getBlueRayCount(int[] lessons, int size) {
    // 블루레이 1개부터 시작
    int count = 1;
    // 블루레이에 담은 강의가 size보다 큰지 작은지 비교
    int sum = 0;

    for (int lesson : lessons) {
      // 현재 블루레이에 담긴 강의 + 다음 강의가 블루레이 크기를 넘어가면
      if (sum + lesson > size) {
        // 블루레이 개수 증가
        count++;
        // 블루레이 크기 초기화
        sum = 0;
      }
      // 블루레이에 강의를 담음
      sum += lesson;
    }

    // 블루레이의 총 개수 반환
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
      // 블루레이 최소 크기는 가장 큰 강의 하나를 담을 떄
      left = Math.max(left, lessons[i]);
      // 블루레이 최대 크기는 모든 강의를 하나의 블루레이에 담을 때
      right += lessons[i];
    }

    // 블루레이 최소 크기(left)와 블루레이 최대 크기(right)가 같아지면 그때 크기가 최적의 크기
    while (left < right) {
      // 블루레이 중간 크기를 구함
      int mid = (left + right) / 2;
      // 해당 블루레이 크기로 강의를 담았을 때 몇개의 블루레이가 필요한지 확인
      int count = getBlueRayCount(lessons, mid);

      // 해당 블루레이 크기로 강의를 담았을 때 M보다 많이 필요하면 블루레이 최소 크기(left)를 mid + 1로 키움
      if (count > M) {
        left = mid + 1;
      }
      // 블루레이가 M보다 적게 필요하면 블루레이 최대 크기(right)를 mid로 줄임
      else {
        right = mid;
      }
    }

    System.out.println(left);

    br.close();
  }
}
