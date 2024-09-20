class Solution {
    public int solution(String name) {
        int answer = 0;
        int length = name.length();
        int leftRightCount = length - 1; // 최대 좌우 이동 횟수

        for (int i = 0; i < length; i++) {
            // 상하 이동
            char c = name.charAt(i);
            answer += Math.min(c - 'A', 'Z' - c + 1);

            // 좌우 이동 최적화
            int next = i + 1;
            while (next < length && name.charAt(next) == 'A') {
                next++;
            }
            leftRightCount = Math.min(leftRightCount, i + length - next + Math.min(i, length - next));
        }

        return answer + leftRightCount;
    }
}
