import java.io.*;
import java.util.*;

public class Main {
  static int N, del, root, ans;
  static int[] parents;

  static void dfs(int current) {
    boolean leaf = true;
    for (int i = 0; i < N; i++) {
      if (i == del) {
        continue;
      }
      if (parents[i] == current) {
        leaf = false;
        dfs(i);
      }
    }
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
      if (parents[i] == -1) {
        root = i;
      }
    }

    st = new StringTokenizer(br.readLine());
    del = Integer.parseInt(st.nextToken());

    ans = 0;
    if (root != del) {
      dfs(root);
    }

    System.out.println(ans);

    br.close();
  }
}
