import java.util.*;
public class programmers_도넛 {

    class Solution {
        public int[] solution(int[][] edges) {
            Map<Integer, int[]> nodes = new HashMap<>();
            int[] answer = new int[4];
            for (int[] edge : edges) {
                int a = edge[0]; //a-> b
                int b = edge[1];
                if (!nodes.containsKey(a)) { //a를 포함하지 않으면
                    nodes.put(a, new int[] {0, 0});
                }
                if (!nodes.containsKey(b)) { //b를 포함하지 않으면
                    nodes.put(b, new int[] {0, 0});
                }
                // 해당 점의 가는것, 들어오는것을 판단
                nodes.get(a)[0] += 1; //가는 것 +1
                nodes.get(b)[1] += 1; //들어오는 것 +1
            }

            int[] cnt;
            for(int key : nodes.keySet()){
                cnt = nodes.get(key);

                if(cnt[0] >= 2 && cnt[1] == 0){ //나가는 것만 있을 때 정점
                    answer[0] = key;
                }else if(cnt[0] == 0 && cnt[1] > 0){ //들어오는 것 없고 나가는 것만
                    answer[2]++;
                }else if(cnt[0] >= 2 && cnt[1] >= 2){ //2개 이상씩일 때 8자
                    answer[3]++;
                }
            }

            //막대와 8자를 제외하면 도넛
            answer[1] = nodes.get(answer[0])[0] - answer[2] - answer[3];
            return answer;
        }
    }
}
