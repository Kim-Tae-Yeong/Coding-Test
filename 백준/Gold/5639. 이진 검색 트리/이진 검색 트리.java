import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Main_5639 {
  private static class node {
    int num;
    node leftNode;
    node rightNode;

    // tree의 node 생성
    node(int num) {
      this.num = num;
      this.leftNode = null;
      this.rightNode = null;
    }
  }

  private static class binaryTree {
    private static node root;

    private static void insert(int num) {
      root = insertRecursively(root, num);
    }

    private static node insertRecursively(node current, int num) {
      // tree가 비어있거나 leaf node까지 가면 새로운 node 생성
      if (current == null) {
        return new node(num);
      }

      // 현재 node의 값보다 작으면 왼쪽 node에서 값 비교
      if (num < current.num) {
        current.leftNode = insertRecursively(current.leftNode, num);
      }
      // 현재 node의 값보다 크면 오른쪽 node에서 값 비교
      else if (num > current.num) {
        current.rightNode = insertRecursively(current.rightNode, num);
      }

      // 현재 값을 가진 node를 생성 후 반환
      return current;
    }

    // 후위 탐색 : 왼쪽 -> 오른쪽 -> 자신
    private static void postOrder(node current) {
      if (current != null) {
        postOrder(current.leftNode);
        postOrder(current.rightNode);
        System.out.println(current.num);
      }
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new
    // FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/5639/5639.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      try {
        // 숫자를 계속 입력받다가 error가 발생하면 break
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
