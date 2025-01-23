import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1918 {
  static StringBuilder sb = new StringBuilder();
  // 우선순위 큐에 Info 클래스를 저장할 때
  // 1. 우선순위는 내림차순 정렬(우선순위가 높은 연산이 큐의 앞쪽에 위치)
  // 2. 우선순위가 같은 경우 들어온 시간은 오름차순 정렬(먼저 들어온 연산이 앞쪽에 위치)
  static PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2) -> {
    if (o1.priority == o2.priority) {
      return Integer.compare(o1.time, o2.time);
    } else {
      return Integer.compare(o2.priority, o1.priority);
    }
  });

  // (연산 기호, 우선순위, 들어온 시간)을 Info 클래스로 정의
  static class Info {
    Character cal;
    int priority;
    int time;

    public Info(Character cal, int priority, int time) {
      this.cal = cal;
      this.priority = priority;
      this.time = time;
    }
  }

  // 현재 연산 기호보다 우선순위가 높거나 같은 우선순위에 먼저 들어온 연산 기호를 뺌
  static void addCal(int priority, int time) {
    while (true) {
      if (pq.isEmpty()) {
        break;
      }
      Info info = pq.peek();
      if (info.priority > priority) {
        sb.append(pq.poll().cal);
      } else if (info.priority == priority && info.time < time) {
        sb.append(pq.poll().cal);
      } else {
        break;
      }
    }
  }

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1918.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    String input = st.nextToken();
    int priority = 0;
    for (int i = 0; i < input.length(); i++) {
      Character c = input.charAt(i);
      // 문자가 괄호면 우선순위를 2 증가시킴
      // * or / 두 연산 기호에서 우선순위를 1 증가시키는데 괄호는 이것보다 더 높은 우선순위를 가지기 때문
      if (c == '(') {
        priority = priority + 2;
      }
      // 괄호가 닫히면 증가시킨 우선순위를 원래대로 되돌림
      else if (c == ')') {
        priority = priority - 2;
      }
      // * or / 연산 기호는 이전 연산기호보다 먼저 연산되기 때문에 우선순위를 1 증가시킴
      else if (c == '*' || c == '/') {
        priority++;
        // 증가시킨 우선순위보다 높은 우선순위가 있다면 정답으로 옮김
        addCal(priority, i);
        // 큐에 현재 정보 저장
        pq.add(new Info(c, priority, i));
        // 우선순위를 원래대로 되돌림
        priority--;
      }
      // + or - 연산 기호는 이전 연산 기호와 동일한 우선순위를 가짐
      // 떄문에 우선순위를 변경할 필요가 없음
      else if (c == '+' || c == '-') {
        addCal(priority, i);
        pq.add(new Info(c, priority, i));
      }
      // 연산 기호가 아닌 피연산자(알파벳)이면 그냥 정답에 저장
      else {
        sb.append(c);
      }
    }

    // 큐에 남아있는 연산 기호를 정답에 옮김
    while (!pq.isEmpty()) {
      sb.append(pq.poll().cal);
    }

    System.out.println(sb.toString());
    br.close();
  }
}
