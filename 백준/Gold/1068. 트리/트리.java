import java.io.*;
import java.util.*;

public class Main_1068 {
  static int N, del, root, ans;
  static int[] parents;

  static void dfs(int current) {
    // leaf 노드인지 확인
    boolean leaf = true;
    for (int i = 0; i < N; i++) {
      // 탐색할 노드가 제거한 노드면 continue
      if (i == del) {
        continue;
      }
      // 탐색할 노드의 부모 노드가 현재 노드이면
      if (parents[i] == current) {
        // leaf 노드가 아님
        leaf = false;
        // 해당 노드에서 dfs 실행
        dfs(i);
      }
    }
    // leaf 노드이면 개수 증가
    if (leaf) {
      ans++;
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1068.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    parents = new int[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      parents[i] = Integer.parseInt(st.nextToken());
      // root 노드 확인
      if (parents[i] == -1) {
        root = i;
      }
    }

    st = new StringTokenizer(br.readLine());
    del = Integer.parseInt(st.nextToken());

    ans = 0;
    // 제거할 노드가 루트 노드가 아닌 경우에만 dfs 실행
    if (root != del) {
      dfs(root);
    }

    System.out.println(ans);

    br.close();
  }
}
