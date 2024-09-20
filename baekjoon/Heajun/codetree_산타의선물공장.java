import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class codetree_산타의선물공장 {
    static Map<Integer, Integer> idMap = new HashMap<>();
    static Map<Integer, Integer> beltMap = new HashMap<>();
    static Item[] items;
    static Belt[] belts;
    static int N, M;
    static StringBuilder sb  = new StringBuilder();

     static class Item{
         int id;
         int w;
         int next;
         int prev;

         Item(int x, int y) {
            id = x;
            w = y;
            next = -1;
            prev = -1;
         }

     }
     static class Belt {
         int first;
         int last;
         int size;
         boolean done;

         Belt(){
             first = -1;
             last = -1;
             size = 0;
             done = false;
         }

         public void addFirst(int key) {
             if(size == 0){
                 first = key;
                 last = key;
                 size++;
                 return;
             }
             items[last].prev = key;
             items[key].next = first;
             first = key;
             size++;
         }

         public void addLast(int key){
             if(size == 0){
                 addFirst(key);
                 return;
             }
             items[last].next = key;
             items[key].prev = last;
             last = key;
             size++;
         }

         public int popFront(){
             if(size == 0){
                 return -1;
             }
             int idx = first;
             if(size == 1){
                 first = -1;
                 last = -1;
             } else {
                 int next = items[first].next;
                 items[first].next = -1;
                 items[next].prev = -1;
                 first = next;
             }
             size--;
             idMap.put(items[idx].id, -1);
             beltMap.put(items[idx].id, -1);
             return idx;
         }

         public void remove(int idx) {
             if (first == idx) {
                 popFront();
             } else if (last == idx) {
                 int prev = items[last].prev;
                 items[last].prev = -1;
                 items[prev].next = -1;
                 last = prev;
                 size--;
             } else {
                 int prev = items[idx].prev;
                 int next = items[idx].next;
                 items[prev].next = next;
                 items[next].prev = prev;
                 size--;
             }

             idMap.put(items[idx].id, -1);
             beltMap.put(items[idx].id, -1);

         }

         public void reArrange(int idx) {
             if (first == idx) {
                 // nothing
             } else if (last == idx) {
                 int prev = items[last].prev;
                 items[last].prev = -1;
                 items[prev].next = -1;
                 last = prev;

                 items[idx].next = first;
                 items[first].prev = idx;
                 first = idx;
             } else {
                 int prev = items[idx].prev;
                 int L = last;
                 int F = first;

                 items[L].next = F;
                 items[F].prev = L;
                 items[prev].next = -1;
                 last = prev;
                 first = idx;
             }
         }

     }

    public static void main(String[] args) throws Exception {
       Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        for (int tc = 0; tc < T; ++tc) {
            String[] input = sc.nextLine().split(" ");
            switch (Integer.parseInt(input[0])) {
                case 100:
                    make(input);
                    break;
                case 200:
                    unload(input);
                    break;
                case 300:
                    remove(input);
                    break;
                case 400:
                    check(input);
                    break;
                case 500:
                    broken(input);
                    break;
                default:
                    break;
            }
        }
        System.out.println(sb);

    }
    private static void make(String[] inputs) {
        N = Integer.parseInt(inputs[1]);
        M = Integer.parseInt(inputs[2]);
        items = new Item[N];
        belts = new Belt[M];
        for (int i = 0; i < M; ++i)
            belts[i] = new Belt();
        for (int i = 0; i < N; ++i)
            items[i] = new Item(Integer.parseInt(inputs[3 + i]), Integer.parseInt(inputs[3 + i + N]));
        for (int i = 0; i < N; ++i) {
            int beltnum = i / (N / M);
            belts[beltnum].addLast(i);
            idMap.put(items[i].id, i);
            beltMap.put(items[i].id, beltnum);
        }

    }

    private static void unload(String[] inputs) {
        int limit = Integer.parseInt(inputs[1]);

        int sum = 0;
        for (int i = 0; i < M; ++i) {
            if (belts[i].done)
                continue;
            if (belts[i].size == 0)
                continue;
            int idx = belts[i].popFront();
            // 물건 하차
            if (items[idx].w <= limit) {
                sum += items[idx].w;
                continue;
            }
            // 다시 쌓기
            belts[i].addLast(idx);
            idMap.put(items[idx].id, idx);
            beltMap.put(items[idx].id, i);
        }
        sb.append(sum).append("\n");
    }

    private static void remove(String[] inputs) {
        int key = Integer.parseInt(inputs[1]);
        int pos = idMap.getOrDefault(key, -1);

        if (pos == -1) {
            sb.append(-1).append("\n");
            return;
        }
        int belt = beltMap.get(key);
        belts[belt].remove(pos);
        sb.append(key).append("\n");
    }

    private static void check(String[] inputs) {
        int key = Integer.parseInt(inputs[1]);
        int pos = idMap.getOrDefault(key, -1);

        if (pos == -1) {
            sb.append(-1).append("\n");
            return;
        }
        int belt = beltMap.get(key);
        belts[belt].reArrange(pos);
        sb.append(belt + 1).append("\n");
    }

    private static void broken(String[] inputs) {
        int n = Integer.parseInt(inputs[1]);
        if (belts[n - 1].done) {
            sb.append("-1").append("\n");
            return;
        }
        belts[n - 1].done = true;
        int next = -1;
        for (int i = 1; i < M; ++i) {
            next = (n - 1 + i) % M;
            if (!belts[next].done)
                break;
        }
        while (belts[n - 1].size > 0) {
            int idx = belts[n - 1].popFront();
            belts[next].addLast(idx);
            idMap.put(items[idx].id, idx);
            beltMap.put(items[idx].id, next);
        }
        sb.append(n).append("\n");
    }
}
