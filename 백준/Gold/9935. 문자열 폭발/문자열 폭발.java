import java.io.BufferedReader;
// import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
  public static void main (String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/9935/9936.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String input = br.readLine();
    String boom = br.readLine();
    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < input.length(); i++) {
      stack.push(input.charAt(i));

      // 현재 stack에 담은 문자열이 폭발 문자열보다 길면
      if (stack.size() >= boom.length()) {
        boolean isBoom = true;
        
        // stack과 폭발 문자열의 끝 부분부터 글자가 동일한지 확인
        for (int j = 0; j < boom.length(); j++) {
          if (stack.get(stack.size() - 1 - j) != boom.charAt(boom.length() - 1 - j)) {
            // 만약 다르면 폭발이 일어나지 않음
            isBoom = false;
            break;
          }
        }

        // 폭발 문자열이 발견되면 해당 문자를 스택에서 제거
        if (isBoom) {
          for (int j = 0; j < boom.length(); j++) {
            stack.pop();
          }
        }
      }
    }

    // 결과 출력
    // StringBuilder : String과 달리 변경 가능한 문자열
    StringBuilder result = new StringBuilder();
    for (char c : stack) {
      result.append(c);
    }

    if (result.length() == 0) {
      System.out.println("FRULA");
    } else {
      System.out.println(result.toString());
    }

    br.close();
  }
}
