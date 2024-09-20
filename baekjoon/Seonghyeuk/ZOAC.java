import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Stack;

public class ZOAC {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		String str = sc.next();
		boolean[] check = new boolean[str.length()];
		char[] alpa = str.toCharArray();
		int cnt = 0;
		Stack<Integer> stack = new Stack<>();
		stack.add(0);
		while (cnt < str.length()) { // 모든 글자수 체크할때까지 점검하기
			int min = Integer.MAX_VALUE; // 가장 값이 작은 글자를 찾을 변수
			int idx = -1; // 가장 값이 작은 글자의 인덱스를 저장해줄 변수
			int beforeidx = stack.pop(); // 스텍에서 전에 저장한 위치 꺼내오기
			for (int i = beforeidx; i < str.length(); ++i) { // 전에 저장한 위치부터 글자의 끝사이에 가장 작은 글자 찾기
				if ((check[i] == false) && (alpa[i] < min)) { // 방문한적 없고 가장 작은 글자 찾으면! min값 갱신
					min = alpa[i]; // 가장 작은 글자의 값
					idx = i; // 가장 작은 글자의 인덱스
				}
			}
			if (idx == -1) { // idx 변수의 값이 -1이라는 의미는 전에 저장한 위치부터 문자열 끝까지 사이에서 저장할 글자값이 없다는 의미
				if(stack.isEmpty()) { // 만약 스텍이 비었다면 맨 앞부터 저장한 값까지 뒤지며 찾기
					for (int i = 0; i < beforeidx; ++i) { //
						if ((check[i] == false) && (alpa[i] < min)) {
							min = alpa[i];
							idx = i;
						}
					}
					if (idx != -1) { // 맨 앞부터 저장한 값 사이에 발견한 글자가 있다면
						stack.add(idx); // 해당 위치 stack에 저장
						check[idx] = true; // 해당 위치 방문처리
						for (int i = 0; i < str.length(); ++i) { // 돌면서
							if (check[i]) { // 방문처리된것들
								sb.append(alpa[i]); // stringBuilder에 넣기
							}
						}
						sb.append("\n"); // 한줄 띄기
					}
				} else { // 스텍이 비어있지 않다면
					while (!stack.isEmpty()) { // 스텍이 빌때까지
						int beforeidx2 = stack.pop(); // 하나 더꺼내서
						for (int i = beforeidx2; i < beforeidx; ++i) { // 그사이의 값 찾기
							if ((check[i] == false) && (alpa[i] < min)) {
								min = alpa[i];
								idx = i;
							}
						}
						if (idx != -1) { // 뒤져서 더할 글자 찾으면
							stack.add(beforeidx2); // stack에 두번째로 꺼낸거 먼저 넣어주고
							stack.add(idx); // stack에 현재 값의 위치 넣어주고
							check[idx] = true; // 방문처리
							for (int i = 0; i < str.length(); ++i) { // 돌면서
								if (check[i]) { // 방문처리 된것들
									sb.append(alpa[i]); // stringBuilder에 넣기
								}
							}
							sb.append("\n"); // 한줄 띄기
							break; // 반복문 탈출
						} else {
							beforeidx = beforeidx2; // 다음값을 꺼내서 활용하기 위해서 beforeidx에 beforeidx2값 저장
						}
					}
				}
			} else { // 순회해서 값을 찾으면
				stack.add(beforeidx); // stack에서 꺼낸 값 넣어주고
				stack.add(idx); // 현재 값 넣어주고
				check[idx] = true;  // 방문처리
				for (int i = 0; i < str.length(); ++i) { // 돌면서
					if (check[i]) { // 방문처리 된것들
						sb.append(alpa[i]); // stringBuilder에 넣기
					}
				}
				sb.append("\n"); // 한줄 띄기
			}
			++cnt; // 갯수 추가해주기
		}
		System.out.println(sb); // 마지막에 출력
	}
}
