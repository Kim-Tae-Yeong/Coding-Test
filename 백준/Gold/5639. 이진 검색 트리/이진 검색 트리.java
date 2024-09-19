import java.io.BufferedReader;
// import java.io.FileReader;
import java.io.InputStreamReader;

public class Main_5639 {
  // 각 node를 class로 설정
  public static class treeNode {
    // 현재 node의 value
    int value;
    // 현재 node의 왼쪽 node
    treeNode left;
    // 현재 node의 오른쪽 node
    treeNode right;

    // 초기에는 왼쪽과 오른쪽에 아무 node도 없기 때문에 null로 설정
    treeNode (int value) {
      this.value = value;
      left = null;
      right = null;
    }
  }

  public static class binaryTree {
    public static treeNode root;

    // value를 binaryTree에 집어넣음
    public static void insert (int value) {
      root = insertRecursively(root, value);
    }
    
    private static treeNode insertRecursively (treeNode current, int value) {
      // tree가 비어 있으면 새로운 node를 root로 설정
      // 즉 가장 먼저 집어넣는 value가 root로 설정됨
      if (current == null) {
        return new treeNode(value);
      }
      // 현재 node의 value보다 집어넣은 node의 value가 더 작으면 현재 node를 현재 node의 왼쪽 node로 바꾼 후 실행
      if (value < current.value) {
        current.left = insertRecursively(current.left, value);
      }
      // 현재 node의 value보다 집어넣은 node의 value가 더 크면 현재 node를 현재 node의 오른쪽 node로 바꾼 후 실행
      else if (value > current.value) {
        current.right = insertRecursively(current.right, value);
      }
      return current;
    }

    // postOrder의 탐색 순서 : 왼쪽 -> 오른쪽 -> 자신
    public static void postOrder (treeNode node) {
      if (node != null) {
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.value);
      }
    }
  }

  public static void main (String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/5639/5639.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 종료 조건이 없는 숫자 입력받는 방법
    while (true) {
      try {
        int num = Integer.parseInt(br.readLine());
        binaryTree.insert(num);
      } catch (NumberFormatException e) {
        break;
      }
    }

    binaryTree.postOrder(binaryTree.root);
    
    br.close();
  }
}
