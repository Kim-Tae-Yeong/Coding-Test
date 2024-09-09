import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
  public static int n;
  public static String[][] graph;
  public static String preOrderAns = "";
  public static String inOrderAns = "";
  public static String postOrderAns = "";
  public static Map<Character, Integer> alphabet = new HashMap<>();

  public static void preOrder (int start) {
    preOrderAns = preOrderAns + graph[start][0];
    if (!graph[start][1].equals(".")) {
      int left = alphabet.get(graph[start][1].charAt(0));
      preOrder(left);
    }
    if (!graph[start][2].equals(".")) {
      int right = alphabet.get(graph[start][2].charAt(0));
      preOrder(right);
    }
  }

  public static void inOrder (int start) {
    if (!graph[start][1].equals(".")) {
      int left = alphabet.get(graph[start][1].charAt(0));
      inOrder(left);
    }
    inOrderAns = inOrderAns + graph[start][0];
    if (!graph[start][2].equals(".")) {
      int right = alphabet.get(graph[start][2].charAt(0));
      inOrder(right);
    }
  }

  public static void postOrder (int start) {
    if (!graph[start][1].equals(".")) {
      int left = alphabet.get(graph[start][1].charAt(0));
      postOrder(left);
    }
    if (!graph[start][2].equals(".")) {
      int right = alphabet.get(graph[start][2].charAt(0));
      postOrder(right);
    }
    postOrderAns = postOrderAns + graph[start][0];
  }

  public static void main (String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/1991/1991.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());

    graph = new String[n][3];
    for (char c = 'A'; c <= 'Z'; c++) {
      alphabet.put(c, c - 'A');
    }

    for (int i = 0; i < n; i++) {
      String[] input = br.readLine().split(" ");
      int idx = alphabet.get(input[0].charAt(0));
      for (int j = 0; j < 3; j++) {
        graph[idx][j] = input[j];
      }
    }
    
    preOrder(0);
    System.out.println(preOrderAns);

    inOrder(0);
    System.out.println(inOrderAns);

    postOrder(0);
    System.out.println(postOrderAns);

    br.close();
  }
}
