import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Main {
  public static class treeNode {
    int value;
    treeNode left;
    treeNode right;

    treeNode (int value) {
      this.value = value;
      left = null;
      right = null;
    }
  }

  public static class binaryTree {
    public static treeNode root;

    public static void insert (int value) {
      root = insertRecursively(root, value);
    }

    private static treeNode insertRecursively (treeNode current, int value) {
      if (current == null) {
        return new treeNode(value);
      }
      if (value < current.value) {
        current.left = insertRecursively(current.left, value);
      }
      else if (value > current.value) {
        current.right = insertRecursively(current.right, value);
      }
      return current;
    }

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
