import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2110 {
  // left : 인접한 공유기 사이의 최소 거리
  // right : 인접한 공유기 사이의 최대 거리
  static int N, C, left, right;
  static int[] pos;

  static int getCount(long size) {
    // 공유기 1개부터 시작
    int count = 1;
    // 맨 처음 집에 공유기 설치
    int lastPos = pos[0];

    for (int i = 1; i < N; i++) {
      // 각 집과 이전에 공유기를 설치한 집과의 거리를 구함
      // 두 집 사이의 거리가 size보다 크면 현재 집에 공유기를 설치해야함
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

    // 집을 거리가 먼 순으로 정렬
    Arrays.sort(pos);

    // 최소 거리는 1로 설정
    left = 1;
    // 최대 거리는 첫 번째 집에서 가장 멀리 떨어진 집과의 거리로 설정
    right = pos[N - 1] - pos[0];

    while (left < right) {
      // 중간 거리를 구함
      int mid = (left + right) / 2;
      // 해당 거리로 공유기를 설치할 때 필요한 개수를 구함
      int count = getCount(mid);
      // 현재 거리를 기준으로 설치한 공유기가 C보다 많거나 같으면 최소 거리를 늘림
      if (count >= C) {
        left = mid + 1;
      }
      // 공유기가 C보다 적으면 최대 거리를 줄임
      else {
        right = mid;
      }
    }

    System.out.println(left);

    br.close();
  }
}
