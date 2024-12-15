import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Main {
  private static class node {
    int num;
    node leftNode;
    node rightNode;

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
      if (current == null) {
        return new node(num);
      }

      if (num < current.num) {
        current.leftNode = insertRecursively(current.leftNode, num);
      } else if (num > current.num) {
        current.rightNode = insertRecursively(current.rightNode, num);
      }

      return current;
    }

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