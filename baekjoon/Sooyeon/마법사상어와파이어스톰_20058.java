import java.io.*;
import java.util.*;

public class 마법사상어와파이어스톰_20058 {
	
	static int n,q, arr[][], sz, dx[] = { 0, 1, 0, -1}, dy[] ={ -1, 0, 1, 0};
	
	public static boolean isInRange(int x, int y) {
		return x>=0 && x<sz &&y>=0 && y<sz;
	}
	
	static class Loc{
		
		int x, y;
		
		Loc(int x, int y){
			this.x =x;
			this.y = y;
		}
	}
	
	public static int search(int i, int j ,boolean[][]check) { //가장 큰덩어리를 찾는 코드 -> bfs를 써서 가장 큰 덩어리를 찾는다
		
		Queue<Loc>q = new ArrayDeque();

		q.add(new Loc(i,j));
		check[i][j]=true;
		
		int cnt = 1;
		
		while(!q.isEmpty()) {
			
			Loc p = q.poll();
			int x = p.x;
			int y = p.y;
			
			for(int t=0; t<4; t++) {
				
				int nx = x+dx[t];
				int ny = y+dy[t];
				if(isInRange(nx,ny)&&check[nx][ny]==false&&arr[nx][ny]>0) {
					
					q.add(new Loc(nx,ny));
					check[nx][ny]=true;
					cnt++;
				}
			}
		}
		
		return cnt;
		
	}
	
	
	public static void rotate(int sx , int sy, int ex, int ey, int msz,int[][]tmp) {
		
		int[][] narr = new int[msz][msz];
		
		for(int i=sx; i<=ex; i++) {
			
			int[] list = arr[i];
			for(int t= sy; t<=ey; t++) {
				narr[t-sy][sx+msz-i-1]=list[t];
			}
			
		}
		
		for(int i=sx; i<=ex; i++) {
			for(int j=sy; j<=ey; j++) {
				
				tmp[i][j]=narr[i-sx][j-sy];
				
			}
		}
		
	}
	
	
	
	public static void doMagic(int l) {
		
		int msz = (int)Math.pow(2, l);
		
		int[][] tmp = new int[sz][sz];
		
		for(int i=0; i<sz; i=i+msz) { 
			for(int j=0;j<sz; j=j+msz) {
				
				rotate(i,j,i+msz-1,j+msz-1,msz,tmp); //rotate 할 수 있는 지점을 모두 rotate 시켜준다
				
			}
		}

		for(int i=0; i<sz; i++) {
			for(int j=0; j<sz; j++) {
				arr[i][j] = tmp[i][j]; 
			}
		}
		
		for(int i=0; i<sz; i++) {
			for(int j=0; j<sz; j++) {
				
				int x = i;
				int y = j;
				
				int cnt =0;
				
                if(arr[x][y]>0) { //얼음이 있는 자리 일경우
                    for(int t=0; t<4; t++) {  //4방향 탐색을 해서 0이 아닌 값이 있으면 cnt 를 늘려준다.
                        
                        int nx =x+dx[t];
                        int ny =y+dy[t];
                        
                        if(isInRange(nx,ny) && arr[nx][ny]!=0) {
                            cnt++;
                        }
                        
                    }
                    
                    if(cnt<3 && tmp[x][y]>0) { //cnt 값이 3보다 작을경우 1을 감소시킨다.
                        --tmp[x][y];
                    }
                }
				
			}
		}
		
		for(int i=0; i<sz; i++) {
			for(int j=0; j<sz; j++) {
				arr[i][j]=tmp[i][j];
			}
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		
		sz =(int)Math.pow(2,n); //배열 전체 크기 구하기 
		
		arr = new int[sz][sz];
		
		for(int i=0; i<sz; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j=0; j<sz; j++) {
				
				arr[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(bf.readLine());
		
		for(int i=0; i<q; i++) {
			
			int magic = Integer.parseInt(st.nextToken());
			doMagic(magic);
			
		}
		
		int ans1 =0;
		for(int i=0; i<sz; i++) {
			for(int j=0; j<sz; j++) {
				
				ans1 += arr[i][j]; //전체 얼음 개수를 구해준다
				
			}
		}
		
		int ans2 =0;
		
		boolean[][] check = new boolean[sz][sz];
		for(int i=0; i<sz; i++) {
			for(int j=0; j<sz; j++) {
				
				if(check[i][j]!=true && arr[i][j]>0) {
				int res = search(i,j,check);
				ans2 = Math.max(ans2,res);
				}
				
			}
		}
		
		System.out.println(ans1);
		System.out.println(ans2);
		
	}

}