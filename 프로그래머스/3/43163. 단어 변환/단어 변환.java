import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    

    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        boolean check = false;
        List<char[]> word = new ArrayList<>();
        for (String elem : words) {
            word.add(elem.toCharArray());
            if (elem.equals(target)) {
                check = true;
            }
        }
        if (!check) {
            return 0;
        }
        int length = words.length;
        int wordLength = words[0].length();
        char[] start = begin.toCharArray();
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[length];
        for (int i = 0; i < length; i++) {
            int cnt = 0;
            for (int j = 0; j < wordLength; j++) {
                if (start[j] != word.get(i)[j]) {
                    cnt++;
                }
            }
            if (cnt == 1) {
                if (words[i].equals(target)) {
                    return 1;
                }
                q.add(new int[] { i, 1 });
                visited[i] = true;
            }
        }
        if (q.isEmpty()) {
            return 0;
        }

        List<List<Integer>> idx = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            List<Integer> tmp = new ArrayList<>();
            idx.add(tmp);
            for (int j = 0; j < length; j++) {
                int cnt = 0;
                for (int k = 0; k < wordLength; k++) {
                    if (word.get(i)[k] != word.get(j)[k]) {
                        cnt++;
                    }
                }
                if (cnt == 1) {
                    idx.get(i).add(j);
                }
            }
        }

        while (!q.isEmpty()) {
            int[] info = q.poll();
            int nextIdx = info[0];
            int cnt = info[1];
            for (int elem : idx.get(nextIdx)) {
                if (!visited[elem]) {
                    if (words[elem].equals(target)) {
                        answer = cnt + 1;
                        break;
                    }
                    q.add(new int[] { elem, cnt + 1 });
                }
            }
            if (answer != 0) {
                break;
            }
        }
        return answer;
    }
}