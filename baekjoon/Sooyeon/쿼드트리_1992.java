import java.io.*;
import java.util.*;

public class 쿼드트리_1992 {

	static String ans = "";
	static HashSet<Character> set = new HashSet<>();
	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(bf.readLine());
		
		char[][] arr = new char[N][];
		
		for(int i=0;i<N;i++) {
			arr[i] = bf.readLine().toCharArray();
			for(int j=0;j<arr.length;j++) {
				set.add(arr[i][j]);
			}
		}
		
		if(set.size()==1) {
			Iterator<Character> itr = set.iterator(); 
			System.out.println(itr.next());
		} else {
			quadTree(arr);
			System.out.println(ans);
		}
	}
	
	static void quadTree(char[][] arr) {
		
		int x = arr.length/2, y = arr[x].length/2;	
		char[][] newArr = new char[x][y];
		ans+="(";
		
		//1사분면
		set.clear();

		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				newArr[i][j] = arr[i][j];
				set.add(arr[i][j]);
			}
		}
		if(set.size()==2) {
			quadTree(newArr);
		} else {
			Iterator<Character> itr = set.iterator();
			ans+=Character.toString(itr.next());
		}
		
		//2사분면
		set.clear();

		for(int i=0;i<x;i++) {
			for(int j=y;j<arr[i].length;j++) {
				newArr[i][j-y] = arr[i][j];
				set.add(arr[i][j]);
			}
		}
		if(set.size()==2) {
			quadTree(newArr);
		} else {
			Iterator<Character> itr = set.iterator();
			ans+=Character.toString(itr.next());
		}
		
		//3사분면
		set.clear();

		for(int i=x;i<arr.length;i++) {
			for(int j=0;j<y;j++) {
				newArr[i-x][j] = arr[i][j];
				set.add(arr[i][j]);
			}
		}
		if(set.size()==2) {

			quadTree(newArr);
		} else {
			Iterator<Character> itr = set.iterator();
			ans+=Character.toString(itr.next());
		}
		
		//4사분면
		set.clear();

		for(int i=x;i<arr.length;i++) {
			for(int j=y;j<arr[x].length;j++) {
				newArr[i-x][j-y] = arr[i][j];
				set.add(arr[i][j]);
			}
		}
		if(set.size()==2) {

			quadTree(newArr);
		} else {
			Iterator<Character> itr = set.iterator();
			ans+=Character.toString(itr.next());
		}
		
		ans+=")";
	}
}
