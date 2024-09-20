package Btype.공장;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb;
    static class Box {
        int id;
        int weight;
        Box down;
        Box up;
        int belt;

        public Box() {}

        public Box(int id, int weight) {
            this.id = id;
            this.weight = weight;
            this.down = new Box();
            this.up = new Box();
        }

        public String toString() {
            return this.id + " " + this.weight + " " + this.belt;
        }
    }

    static class Belt {
        Box head = new Box();
        Box tail = new Box();
        int length = 0;
    }

    static HashMap<Integer, Box> map = new HashMap<>();
    static Belt[] belts;
    static int[] isBroken;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        int q = Integer.parseInt(br.readLine());

        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            System.out.println("command = " + command);
            switch(command) {
                case 100:
                    int n = Integer.parseInt(st.nextToken());
                    int m = Integer.parseInt(st.nextToken());
                    List<Box> boxes = new ArrayList<>();

                    for(int j = 0; j < n; j++) {
                        boxes.add(new Box());
                        boxes.get(j).id = Integer.parseInt(st.nextToken());
                    }

                    for(int j = 0; j < n; j++) {
                        boxes.get(j).weight = Integer.parseInt(st.nextToken());
                    }

                    init(n, m, boxes);
                    break;
                case 200:
                    int wMax = Integer.parseInt(st.nextToken());
                    sb.append(boxDown(wMax)).append("\n");
                    break;
                case 300:
                    int rId = Integer.parseInt(st.nextToken());
                    sb.append(remove(rId)).append("\n");
                    break;
                case 400:
                    int fId = Integer.parseInt(st.nextToken());
                    sb.append(check(fId)).append("\n");
                    break;
                case 500:
                    int bNum = Integer.parseInt(st.nextToken());
                    sb.append(broken(bNum)).append("\n");
                    break;
            }
        }

        System.out.println(sb);
    }

    static void init(int n, int m, List<Box> boxes) {
        int num = n / m;

        belts = new Belt[m];
        isBroken = new int[m];
        Box[] recent = new Box[m];

        for(int i = 0; i < m; i++) {
            belts[i] = new Belt();
            isBroken[i] = i;
        }

        int idx = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < num; j++) {
                Box box = boxes.get(idx++);
                box.belt = i;
                map.put(box.id, box);

                if (belts[i].length == 0) {
                    belts[i].head.up = box;
                    box.down = belts[i].head;
                    box.up = belts[i].tail;
                    belts[i].tail.down = box;
                } else {
                    recent[i].up = box;
                    belts[i].tail.down = box;
                    box.down = recent[i];
                    box.up = belts[i].tail;
                }

                belts[i].length++;
                recent[i] = box;
            }
        }
    }

    static long boxDown(int wMax) {
        long sum = 0;

        for(int i = 0; i < belts.length; i++) {
            // head의 위가 tail인 경우 -> 박스가 없는 경우
            if(belts[i].head.up.id == 0) continue;

            // 이미 고장난 벨트인 경우
            if(i != isBroken[i]) continue;

            Box box = belts[i].head.up;

            // 박스를 빼기
            box.down.up = box.up;
            box.up.down = box.down;

            // 벨트에서 제거
            if(box.weight <= wMax) {
                sum += box.weight;
                map.remove(box.id);

                belts[i].length--;
            }
            // 맨 뒤로 보내기
            else {
                box.up = belts[i].tail;
                box.down = belts[i].tail.down;
                belts[i].tail.down.up = box;
                belts[i].tail.down = box;
            }
        }

        return sum;
    }

    static int remove(int rId) {
        if(!map.containsKey(rId)) return -1;

        Box box = map.get(rId);
        int bNum = searchBelt(box.belt);

        box.down.up = box.up;
        box.up.down = box.down;

        map.remove(rId);
        belts[bNum].length--;

        return rId;
    }

    static int check(int fId) {
        if(!map.containsKey(fId)) return -1;

        Box box = map.get(fId);
        int bNum = searchBelt(box.belt);
        // 맨 뒤의 박스 미리 가지고 있기
        Box back = belts[bNum].tail.down;

        // 해당 박스부터 맨 뒤 박스까지 내리기
        belts[bNum].tail.down = box.down;
        box.down.up = belts[bNum].tail;

        // box가 맨 뒤일 때
        if(box.id == belts[bNum].tail.down.id) {
            box.down = belts[bNum].head;
            box.up = belts[bNum].head.up;
            belts[bNum].head.up = box;
            box.up.down = box;
        }
        // box가 맨 뒤가 아닐 때
        else {
            box.down = belts[bNum].head;
            back.up = box.down.up;
            box.down.up.down = back;
            belts[bNum].head.up = box;
        }

        return bNum + 1;
    }

    static int broken(int bNum) {
        bNum -= 1;

        if(bNum != isBroken[bNum]) return -1;

        // 고장나지 않은 벨트 찾기
        int idx = (bNum + 1) % belts.length;
        while(idx != bNum) {
            if(idx == isBroken[idx]) {
                isBroken[bNum] = idx;
                break;
            }
            idx = (idx + 1) % belts.length;
        }

        // 상자가 없다면 바로 idx + 1 반환
        if(belts[bNum].length == 0) return idx + 1;

        Box box = belts[bNum].head.up;

        // 상자가 한 개만 남았을 때
        if(belts[bNum].length == 1) {
            box.down = belts[idx].tail.down;
            box.down.up = box;
            box.up = belts[idx].tail;
            box.up.down = box;
        }
        else {
            Box back = belts[bNum].tail.down;

            box.down = belts[idx].tail.down;
            box.down.up = box;
            back.up = belts[idx].tail;
            back.up.down = back;
        }

        return bNum + 1;
    }

    static int searchBelt(int num) {
        int bNum = num;

        while(bNum != isBroken[bNum]) {
            bNum = isBroken[bNum];
        }

        return bNum;
    }

}
