import java.util.*;

public class 도넛과막대그래프 {
	public static class Word {
		int word;
		int depth;

		public Word(int word, int depth) {
			this.word = word;
			this.depth = depth;
		}

	}

	public static void main(String[] args) {
		int[][] edges = {{2,3}, {4,3}, {1,1}, {2,1}};
		
		int[] result = solution(edges);

		System.out.println(Arrays.toString(result));
	}

	public static int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        HashSet<Integer> nodelist = new HashSet<>();
        
        int n = 0;
        for(int i = 0; i<edges.length; i++){
            if(n<edges[i][0]){
                n=edges[i][0];
            }
            if(n<edges[i][1]){
                n=edges[i][1];
            }
        }
        
        int[][] cnt = new int[n+1][2];
        
        
        for(int i = 0; i<edges.length; i++){
            int a = edges[i][0];
            int b = edges[i][1];
            nodelist.add(a);
            nodelist.add(b);
            cnt[a][0]++;
            cnt[b][1]++;
        }
        
        for(int i = 1; i<n+1; i++){
            if(cnt[i][0]>1 && cnt[i][1]==0){
                answer[0]=i;
                break;
            }
        }
        
        int total = 0;
        for(int i = 0; i<edges.length; i++){
            if(edges[i][0]==answer[0]){
                cnt[edges[i][1]][1]--;
                total++;
            }            
        }
        
        for(int i = 1; i<n+1; i++){
            if(i==answer[0]){
                continue;
            }
            if(cnt[i][0]==2 && cnt[i][1]==2){
                answer[3]++;
                total--;
            }
            if( (cnt[i][0]==0 && cnt[i][1]==1) || (cnt[i][0]==1 && cnt[i][1]==0) ){
                answer[2]++;
                total--;
            }
            if((cnt[i][0]==0 && cnt[i][1]==0) && nodelist.contains(i)){
                answer[2]+=2;
                total-=2;
            }
               
        }
        answer[2]/=2;
        answer[1]=total+answer[2];
        
        return answer;
	}
}
