import java.io.BufferedReader;
// import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Main_1713 {
  private static int n, total, t;
  private static int[] info;
  // [학생 번호, 추천 횟수, 사진이 게시된 시점] 저장
  // 추천 횟수 오름차순, 사진이 게시된 시점 오름차순
  private static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
    if(o1[1] != o2[1]) {
      return o1[1] - o2[1];
    }
    else {
      return o1[2] - o2[2];
    }
  });
  // cnt[i] : i번째 학생의 추천 수
  private static int[] cnt;
  // idx[i] : i번째 학생의 사진이 게시된 시점
  private static int[] idx;
  private static int[] ans;
  private static StringBuffer sb = new StringBuffer();
  public static void main (String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/1713/1713.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    total = Integer.parseInt(br.readLine());
    info = new int[total];
    cnt = new int[101];
    idx = new int[101];
    String[] input = br.readLine().split(" ");
    for(int i = 0; i < total; i++) {
      info[i] = Integer.parseInt(input[i]);
    }

    for(int i = 0 ; i < total; i++) {
      // 처음 추천받은 학생이면
      if(cnt[info[i]] == 0) {
        // 사진틀이 모두 채워져있으면
        if(pq.size() == n) {
          // 사진 제거
          int[] del = pq.poll();
          // 추천 횟수 초기화
          cnt[del[0]] = 0;
          // 사진이 게시된 시점 초기화
          idx[del[0]] = 0;
        }
        // 현재 학생 정보 저장
        cnt[info[i]] = 1;
        idx[info[i]] = i;
        pq.add(new int[] {info[i], cnt[info[i]], i});
      }
      // 이미 추천을 받은 학생이면
      else {
        int[] tmp = new int[] {info[i], cnt[info[i]], idx[info[i]]};
        Iterator<int[]> it = pq.iterator();
        // 해당 학생의 이전 기록 제거
        while (it.hasNext()) {
          int[] current = it.next();
          if (Arrays.equals(current, tmp)) {
            it.remove();
            break;
          }
        }
        // 추천 횟수를 1 증가시킨 후 저장
        cnt[info[i]] += 1;
        pq.add(new int[] {info[i], cnt[info[i]], idx[info[i]]});
      }
    }

    t = pq.size();
    ans = new int[t];
    for(int i = 0 ; i < t; i++) {
      ans[i] = pq.poll()[0];
    }
    Arrays.sort(ans);

    for(int elem : ans) {
      sb.append(elem);
      sb.append(" ");
    }

    System.out.println(sb);

    br.close();
  }  
}
