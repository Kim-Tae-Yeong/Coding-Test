import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    

    public int solution(int[] priorities, int location) {
        int answer = 1;
        // q : {프로세스, 프로세스 실행 여부(0 : 미실행, 1 : 실행)}
        Queue<int[]> q = new LinkedList<>();
        List<Integer> l = new ArrayList<>();
        for (int elem : priorities) {
            q.add(new int[] { elem, 0 });
            l.add(elem);
        }
        // l : 프로세스를 우선순위 기준 내림차순 정렬
        l.sort((o1, o2) -> Integer.compare(o2, o1));
        // i : 현재 가장 높은 우선순위를 나타내는 index
        int i = 0;
        // idx : 현재 내가 확인하고 있는 프로세스의 index
        int idx = 0;
        while (true) {
            // 맨 앞 프로세스부터 하나씩 꺼내면서 확인
            int[] tmp = q.poll();
            // 꺼낸 프로세스가 미실행이면
            if (tmp[1] == 0) {
                // 꺼낸 프로세스의 우선순위가 가장 높으면
                if (tmp[0] == l.get(i)) {
                    // 그 프로세스가 내가 확인하고 싶은 위치의 프로세스이면 break
                    if (idx == location) {
                        break;
                    }
                    // 아니면 해당 프로세스를 실행 상태로 바꿈
                    tmp[1] = 1;
                    // 그 다음 우선순위로 넘어감
                    i += 1;
                    answer += 1;
                }
            }
            // 꺼낸 프로세스를 다시 집어넣음
            q.add(tmp);
            // 다음 프로세스로 넘어감
            idx = ((idx + 1) % priorities.length);
        }
        return answer;
    }
}
