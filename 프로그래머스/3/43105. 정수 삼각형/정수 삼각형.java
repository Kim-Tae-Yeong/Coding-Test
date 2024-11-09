
class Solution {
    

    public int solution(int[][] triangle) {
        int answer = Integer.MIN_VALUE;
        int length = triangle.length;
        // map[row][col] : (row, col) 위치까지 갈 때 거쳐간 숫자의 최댓값
        int[][] map = new int[length][length];
        // 시작 지점은 triangle 배열의 시작 지점과 동일
        map[0][0] = triangle[0][0];
        /*
         3 8
        8 1 0
        이런 삼각형이 있다고 가정했을 때 이를
        3 8
        8 1 0
        모양으로 바꿈
        이후 각 위치에 올 수 있는 경우는 (row - 1, col - 1)과 (row - 1, col) 두 가지 밖에 없음
        */
        for (int row = 1; row < length; row++) {
            for (int col = 0; col < triangle[row].length; col++) {
                // 현재 row의 가장 마지막 원소
                if (triangle[row - 1].length == col) {
                    map[row][col] = triangle[row][col] + map[row - 1][col - 1];
                } 
                // 현재 row의 첫 번째 원소
                else if (col == 0) {
                    map[row][col] = triangle[row][col] + map[row - 1][col];
                } 
                // 그 외의 경우
                else {
                    map[row][col] = triangle[row][col] + Math.max(map[row - 1][col - 1], map[row - 1][col]);
                }
            }
        }
        // map 배열의 마지막 row에 들어있는 원소 중 가장 큰 값을 찾음
        for (int elem : map[length - 1]) {
            answer = Math.max(answer, elem);
        }
        return answer;
    }
}
