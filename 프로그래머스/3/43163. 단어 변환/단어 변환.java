import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    

    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        // words 배열에 target이 있는지 없는지 확인
        boolean check = false;
        List<char[]> word = new ArrayList<>();
        for (String elem : words) {
            // words 배열에 있는 단어를 char[] 형태로 변환하여 저장
            word.add(elem.toCharArray());
            if (elem.equals(target)) {
                check = true;
            }
        }
        // words 배열에 target이 없으면 0 return
        if (!check) {
            return 0;
        }

        // length : 총 단어 개수
        int length = words.length;
        // wordLength = 각 단어의 길이
        int wordLength = words[0].length();
        char[] start = begin.toCharArray();
        // q : {현재 단어의 index, 횟수}
        Queue<int[]> q = new LinkedList<>();
        // visited[i] : i번째 단어를 만들었는지 여부
        // hot -> dot -> hot -> ... 이런 작업을 방지하기 위함
        boolean[] visited = new boolean[length];
        // begin에서 만들 수 있는 단어를 찾음
        for (int i = 0; i < length; i++) {
            int cnt = 0;
            for (int j = 0; j < wordLength; j++) {
                if (start[j] != word.get(i)[j]) {
                    cnt++;
                }
            }
            if (cnt == 1) {
                // begin에서 target을 한 번에 만들 수 있으면 1 return
                if (words[i].equals(target)) {
                    return 1;
                }
                // 그 외의 경우 {해당 단어의 index, 1}를 q에 저장
                q.add(new int[] { i, 1 });
                // 해당 단어 방문 여부 갱신
                visited[i] = true;
            }
        }
        // begin에서 words 배열 중 아무 단어도 만들지 못하면 0 return
        if (q.isEmpty()) {
            return 0;
        }

        // idx.get(i) : i번째 단어에서 만들 수 있는 단어의 index 저장
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
            // 현재 단어에서 만들 수 있는 단어 확인
            for (int elem : idx.get(nextIdx)) {
                // 해당 단어를 만들지 않았다면
                if (!visited[elem]) {
                    // 만든 단어가 target이면 answer를 (cnt + 1)로 설정
                    if (words[elem].equals(target)) {
                        answer = cnt + 1;
                        break;
                    }
                    // 그 외의 경우 q에 {해당 단어의 index, (cnt + 1)} 저장
                    q.add(new int[] { elem, cnt + 1 });
                    // 해당 단어 방문 여부 갱신
                    visited[elem] = true
                }
            }
            if (answer != 0) {
                break;
            }
        }
        return answer;
    }
}
