import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj14500테트로미노 {
    static class Technomino{
        int rest, total;
        Set<Integer> root;

        Technomino(int rest, int total, Set<Integer> root){
            this.rest = rest;
            this.total = total;
            this.root = root;
        }

        @Override
        public String toString() {
            return "Technomino{" +
                    "rest=" + rest +
                    ", total=" + total +
                    ", root=" + root.toString() +
                    '}';
        }
    }
    static int[][] delta = {{-1,1,0,0},{0,0,-1,1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int max4 = -1;
        Set<Integer> done = new HashSet<>();
        for(int r =0; r<N;++r) {
            str = br.readLine();
            st = new StringTokenizer(str);
            for(int c =0; c<M;++c) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }


        for(int r =0; r<N;++r) {
            for(int c =0; c<M;++c) {
                Queue<Technomino> q = new ArrayDeque<>();
                HashSet<Integer> visited = new HashSet<>();
                visited.add(r*1000+c);
                q.add(new Technomino(3, map[r][c], visited));
                while(!q.isEmpty()) {
                    Technomino t = q.poll();
                    for (int loc : t.root) {
                        int locR = loc/1000;
                        int locC = loc%1000;
                        for (int dir = 0 ; dir<4;++dir){
                            int nr = locR + delta[0][dir];
                            int nc = locC + delta[1][dir];
                            if(!done.contains(nr*1000+nc)&&!t.root.contains(nr*1000+nc)) { // 가본적 없고
                                if((nr>=0)&&(nr<N)&&(nc>=0)&&(nc<M)){
                                    if(t.rest==1){
                                        if(t.total+map[nr][nc]>max4){
                                            max4 = t.total+map[nr][nc];
                                        }
                                    } else {
                                        HashSet<Integer> newVisited = new HashSet<>(t.root);
                                        newVisited.add(nr*1000+nc);
                                        q.add(new Technomino(t.rest-1, t.total+map[nr][nc], newVisited));
                                    }
                                }
                            }
                        }
                    }
                }
                done.add(r*1000+c);
            }
        }
        System.out.println(max4);
    }
}