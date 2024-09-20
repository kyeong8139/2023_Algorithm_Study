// 수행시간 1102ms

import java.util.*;
import java.io.*;

public class Main {

	static class Box {
		int id;
		int order;
		int weight;
		int beltNumber;

		public Box(int id, int order) {
			this.id = id;
			this.order = order;
		}

		@Override
		public String toString() {
			return "Box [id=" + id + ", order=" + order + ", weight=" + weight + ", beltNumber=" + beltNumber + "]";
		}

	}

	static HashMap<Integer, Box> boxMap;
	static int[] prevBelt;
	static int totalBeltCnt;

	static int orderNumber;
	static int[] nextBox;
	static int[] prevBox;

	static int[] head;
	static int[] tail;
	
	static HashMap<Integer, Integer> idToOrder;
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int q = Integer.parseInt(br.readLine());

		for (int i = 0; i < q; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());

			switch (command) {
			case 100:
				int n = Integer.parseInt(st.nextToken());
				int m = Integer.parseInt(st.nextToken());

				Box[] boxes = new Box[n];
				for (int j = 0; j < n; j++) {
					boxes[j] = new Box(Integer.parseInt(st.nextToken()), ++orderNumber);
				}
				for (int j = 0; j < n; j++) {
					boxes[j].weight = Integer.parseInt(st.nextToken());
				}

				init(m, boxes);
				break;
			case 200:
				int wMax = Integer.parseInt(st.nextToken());
				putDownBox(wMax);
				break;
			case 300:
				int id = Integer.parseInt(st.nextToken());
				removeBox(id);
				break;
			case 400:
				id = Integer.parseInt(st.nextToken());
				checkBox(id);
				break;
			case 500:
				id = Integer.parseInt(st.nextToken());
				breakdown(id - 1);
				break;
			}
		}
		
		System.out.print(sb);
	}

	public static void init(int beltCnt, Box[] boxes) {
		boxMap = new HashMap<>();
		boxMap.put(0, new Box(0, 0));
		prevBelt = new int[beltCnt];
		totalBeltCnt = beltCnt;

		prevBox = new int[orderNumber + 1];
		nextBox = new int[orderNumber + 1];
		head = new int[beltCnt];
		tail = new int[beltCnt];
		idToOrder = new HashMap<>();

		for (int i = 0; i < beltCnt; i++) {
			prevBelt[i] = i;
		}

		int curBelt = 0;
		int cnt = 0;
		for (Box box : boxes) {
			box.beltNumber = curBelt;
			addLastOne(curBelt, box);

			if (++cnt == boxes.length / beltCnt) {
				curBelt = (curBelt + 1) % beltCnt;
				cnt = 0;
			}
		}
	}

	public static void addLastOne(int belt, Box box) {
		boxMap.put(box.order, box);
		idToOrder.put(box.id, box.order);

		int beltFirst = prevBelt[belt];
		while (prevBelt[beltFirst] != beltFirst) {
			beltFirst = prevBelt[beltFirst];
		}

		if (head[beltFirst] == 0) {
			head[beltFirst] = box.order;
			tail[beltFirst] = box.order;
		} else {
			nextBox[tail[beltFirst]] = box.order;
			prevBox[box.order] = tail[beltFirst];
			tail[beltFirst] = box.order;
		}
	}

	public static void myRemove(int belt, Box box) {
		boxMap.remove(box.order);
		idToOrder.remove(box.id);

		int beltFirst = prevBelt[belt];
		while (prevBelt[beltFirst] != beltFirst) {
			beltFirst = prevBelt[beltFirst];
		}

		if (head[beltFirst] == box.order) {
			head[beltFirst] = nextBox[box.order];
		}

		if (tail[beltFirst] == box.order) {
			tail[beltFirst] = prevBox[box.order];
		}

		if (prevBox[box.order] != 0) {
			nextBox[prevBox[box.order]] = nextBox[box.order];
		}

		if (nextBox[box.order] != 0) {
			prevBox[nextBox[box.order]] = prevBox[box.order];
		}

		prevBox[box.order] = 0;
		nextBox[box.order] = 0;

	}

	public static void putDownBox(int wMax) {
		long sumWeight = 0;
		for (int i = 0; i < totalBeltCnt; i++) {
			if (prevBelt[i] != i)
				continue;

			int headOrder = head[i];
			if (headOrder == 0)
				continue;

			Box box = boxMap.get(headOrder);
			myRemove(i, box);

			if (box.weight <= wMax) {
				sumWeight += box.weight;
			} else {
				addLastOne(i, box);
			}
		}
		
		sb.append(sumWeight).append("\n");
	}

	public static void removeBox(int id) {
		if (!idToOrder.containsKey(id)) {
			sb.append(-1).append("\n");
			return;
		}

		int order = idToOrder.get(id);
		Box box = boxMap.get(order);
		myRemove(box.beltNumber, box);
		sb.append(id).append("\n");
	}

	public static void checkBox(int id) {
		if (!idToOrder.containsKey(id)) {
			sb.append(-1).append("\n");
			return;
		}

		int order = idToOrder.get(id);
		Box box = boxMap.get(order);

		int beltIdx = box.beltNumber;
		int beltFirst = prevBelt[beltIdx];
		while (prevBelt[beltFirst] != beltFirst) {
			beltFirst = prevBelt[beltFirst];
		}

		int newHead = order;
		int newTail = prevBox[order] == 0 ? order : prevBox[order];
		int prevHead = head[beltFirst];
		int prevTail = tail[beltFirst];

		if (newHead != newTail) {
			prevBox[prevHead] = prevTail;
			nextBox[prevTail] = prevHead;
		}
		
		head[beltFirst] = newHead;
		tail[beltFirst] = newTail;
		prevBox[newHead] = 0;
		nextBox[newTail] = 0;

		sb.append(beltFirst + 1).append("\n");
	}

	public static void breakdown(int beltNumber) {
		if (prevBelt[beltNumber] != beltNumber) {
			sb.append(-1).append("\n");
			return;
		}

		int newBelt = (beltNumber + 1) % totalBeltCnt;
		while (prevBelt[newBelt] != newBelt) {
			newBelt = (newBelt + 1) % totalBeltCnt;
		}

		prevBelt[beltNumber] = newBelt;

		int curHead = head[beltNumber];
		int curTail = tail[beltNumber];
		if (curHead == 0) {
			System.out.println(beltNumber + 1);
			return;
		}

		if (head[newBelt] == 0) {
			head[newBelt] = curHead;
		}
		prevBox[curHead] = tail[newBelt];
		nextBox[tail[newBelt]] = curHead;
		tail[newBelt] = curTail;

		sb.append(beltNumber + 1).append("\n");
	}

}
