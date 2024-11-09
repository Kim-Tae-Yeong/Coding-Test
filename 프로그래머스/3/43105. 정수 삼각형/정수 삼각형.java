import java.lang.reflect.Array;
import java.util.Arrays;

class Solution {
    

    public int solution(int[][] triangle) {
        int answer = Integer.MIN_VALUE;
        int length = triangle.length;
        int[][] map = new int[length][length];
        map[0][0] = triangle[0][0];
        for (int row = 1; row < length; row++) {
            for (int col = 0; col < triangle[row].length; col++) {
                if (triangle[row - 1].length == col) {
                    map[row][col] = triangle[row][col] + map[row - 1][col - 1];
                } else if (col == 0) {
                    map[row][col] = triangle[row][col] + map[row - 1][col];
                } else {
                    map[row][col] = triangle[row][col] + Math.max(map[row - 1][col - 1], map[row - 1][col]);
                }
            }
        }
        for (int elem : map[length - 1]) {
            answer = Math.max(answer, elem);
        }
        return answer;
    }
}