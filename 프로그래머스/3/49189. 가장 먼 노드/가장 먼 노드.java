import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    

    public int solution(int n, int[][] vertex) {
        int answer = 0;
        List<List<Integer>> l = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            List<Integer> tmp = new ArrayList<>();
            l.add(tmp);
        }
        // 각 노드의 연결 정보 저장
        for (int[] elem : vertex) {
            l.get(elem[0]).add(elem[1]);
            l.get(elem[1]).add(elem[0]);
        }
        // visited[i] : 1번 노드에서 i번째 노드까지의 최단 거리
        int[] visitied = new int[n + 1];
        Queue<int[]> q = new LinkedList<>();
        // 1번 노드에서 한번에 갈 수 있는 노드의 정보 저장
        for (int elem : l.get(1)) {
            q.add(new int[] { elem, 1 });
        }
        while (!q.isEmpty()) {
            int[] info = q.poll();
            int idx = info[0];
            int cnt = info[1];
            // 현재 노드에 방문한 적이 없으면
            if (visitied[idx] == 0) {
                // 방문 기록 갱신
                visitied[idx] = cnt;
                // 현재 노드에서 갈 수 있는 노드 중
                for (int elem : l.get(idx)) {
                    // 시작(1번) 노드가 아니고, 방문하지 않은 노드가 있다면
                    if (elem != 1 && visitied[elem] == 0) {
                        // {해당 노드, 현재 노드까지의 거리 + 1} 정보 저장
                        q.add(new int[] { elem, cnt + 1 });
                    }
                }
            }
        }
        int maxNum = Integer.MIN_VALUE;
        // 각 노드의 거리 확인
        for (int elem : visitied) {
            // 가장 먼 거리의 노드와 거리가 같다면
            if (maxNum == elem) {
                // 정답(노드 수) 증가
                answer++;
            }
            // 현재 노드가 가장 먼 거리의 노드이면
            else if (maxNum < elem) {
                // 가장 먼 거리 갱신
                maxNum = elem;
                // 정답(노드 수) 1로 초기화
                answer = 1;
            }
        }
        return answer;
    }
}
