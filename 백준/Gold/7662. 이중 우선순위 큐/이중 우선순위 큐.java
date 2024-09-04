import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {
  public static void main(String[] args) throws IOException {
    // BufferedReader br = new BufferedReader(new FileReader("/Users/kimtaeyeong/CodingTest/BaekJoon/7662/7662.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    
    for (int i = 0; i < t; i++) {
        int k = Integer.parseInt(br.readLine());
        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        for (int j = 0; j < k; j++) {
            String[] command = br.readLine().split(" ");
            char cmd = command[0].charAt(0);
            int num = Integer.parseInt(command[1]);
            
            if (cmd == 'I') {
                map.put(num, map.getOrDefault(num, 0) + 1);
            } else {
                if (map.isEmpty()) continue;
                
                int key = (num == 1) ? map.lastKey() : map.firstKey();
                int count = map.get(key);
                
                if (count == 1) {
                    map.remove(key);
                } else {
                    map.put(key, count - 1);
                }
            }
        }
        
        if (map.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            System.out.println(map.lastKey() + " " + map.firstKey());
        }
    }
  }
}
