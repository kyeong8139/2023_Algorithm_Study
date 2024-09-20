class Solution {
    
    int answer = Integer.MAX_VALUE;
    
    public int solution(String name) {
        char[] target = name.toCharArray();
        char[] cur = new char[name.length()];
        for (int i = 0; i < cur.length; i++) {
            cur[i] = 'A';
        }
        
        getAnswer(target, cur, 0, 0);
        return answer;
    }
    
    public int getDist (char target, char cur) {
        if (target > cur) {
            return Math.min(target - cur, cur - 'A' + 'Z' - target + 1);
        } else {
            return Math.min(cur - target, target - 'A' + 'Z' - cur + 1);
        }
    }
    
    public void getAnswer(char[] target, char[] cur, int cursor, int cnt) {
        int dist = 0;
        boolean hasChanged = false;
        while (dist <= target.length / 2) {
            int rightCursor = (cursor + dist) % target.length;
            if (cur[rightCursor] != target[rightCursor]) {
                hasChanged = true;
                int result = getDist(target[rightCursor], cur[rightCursor]) + dist;
                
                cur[rightCursor] = target[rightCursor];
                getAnswer(target, cur, rightCursor, result + cnt);
                cur[rightCursor] = 'A';
            }
            
            int leftCursor = (cursor - dist + target.length) % target.length;
            if (cur[leftCursor] != target[leftCursor]) {
                hasChanged = true;
                int result = getDist(target[leftCursor], cur[leftCursor]) + dist;
                
                cur[leftCursor] = target[leftCursor];
                getAnswer(target, cur, leftCursor, result + cnt);
                cur[leftCursor] = 'A';
            }
            
            dist++;
        }
        
        if (!hasChanged) {
            answer = Math.min(answer, cnt);
        }
    }
}
