import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Main {
  private static int n, total, t;
  private static int[] info;
  private static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
    if(o1[1] != o2[1]) {
      return o1[1] - o2[1];
    }
    else {
      return o1[2] - o2[2];
    }
  });
  private static int[] cnt;
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
      if(cnt[info[i]] == 0) {
        if(pq.size() == n) {
          int[] del = pq.poll();
          cnt[del[0]] = 0;
          idx[del[0]] = 0;
        }
        cnt[info[i]] = 1;
        idx[info[i]] = i;
        pq.add(new int[] {info[i], cnt[info[i]], i});
      }
      else {
        int[] tmp = new int[] {info[i], cnt[info[i]], idx[info[i]]};
        Iterator<int[]> it = pq.iterator();
        while (it.hasNext()) {
          int[] current = it.next();
          if (Arrays.equals(current, tmp)) {
            it.remove();
            break;
          }
        }
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
