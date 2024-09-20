class Solution {
    public static long solution(int w, int h) {
		long answer = 0;

		for (int i = 0; i < h; i++) {
			answer += (Math.ceil((double)(i+1)*w/h) - Math.floor((double)i*w/h));
        }

		return (long) w*h-answer;
	}
}
