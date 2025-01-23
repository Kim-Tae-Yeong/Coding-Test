import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
  static StringBuilder sb = new StringBuilder();
  static PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2) -> {
    if (o1.priority == o2.priority) {
      return Integer.compare(o1.time, o2.time);
    } else {
      return Integer.compare(o2.priority, o1.priority);
    }
  });

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

  public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new FileReader("./1918.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    String input = st.nextToken();
    int priority = 0;
    for (int i = 0; i < input.length(); i++) {
      Character c = input.charAt(i);
      if (c == '(') {
        priority = priority + 2;
      } else if (c == ')') {
        priority = priority - 2;
      } else if (c == '*' || c == '/') {
        priority++;
        while (true) {
          if (pq.isEmpty()) {
            break;
          }
          Info info = pq.peek();
          if (info.priority > priority) {
            sb.append(pq.poll().cal);
          } else if (info.priority == priority && info.time < i) {
            sb.append(pq.poll().cal);
          } else {
            break;
          }
        }
        pq.add(new Info(c, priority, i));
        priority--;
      } else if (c == '+' || c == '-') {
        pq.add(new Info(c, priority, i));
        while (true) {
          if (pq.isEmpty()) {
            break;
          }
          Info info = pq.peek();
          if (info.priority > priority) {
            sb.append(pq.poll().cal);
          } else if (info.priority == priority && info.time < i) {
            sb.append(pq.poll().cal);
          } else {
            break;
          }
        }
      } else {
        sb.append(c);
      }
      // for (Info info : pq) {
      // System.out.print(info.cal);
      // }
      // System.out.println();
    }

    while (!pq.isEmpty()) {
      sb.append(pq.poll().cal);
    }

    System.out.println(sb.toString());
    br.close();
  }
}
