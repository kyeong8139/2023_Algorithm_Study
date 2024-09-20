import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 산타 {
    static final int maxBelt = 15;
    static final int maxBox = 100010;

    static class Belt {
        int no;
        int front;
        int back;
        int state;

        Belt() {
            this.no = 0;
            this.front = -1;
            this.back = -1;
            this.state = 0;
        }
    }

    static class Present {
        int weight;
        int beltId;
        int prev;
        int next;

        Present() {
            this.weight = 0;
            this.beltId = 0;
            this.prev = -1;
            this.next = -1;
        }
    }

    static int n, m;
    static int[] input = new int[maxBox];
    static HashMap<Integer, Present> box = new HashMap<>();
    static HashMap<Integer, Belt> belt = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        st.nextToken();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int cnt = 0;
        int beltId = 1;
        int pivot = n / m;

        for (int i = 0; i < n; i++) {
            int id = Integer.parseInt(st.nextToken());
            input[i] = id;

            if (cnt == 0) {
                Belt newBelt = new Belt();
                newBelt.front = id;
                newBelt.back = id;
                newBelt.no = pivot;
                belt.put(beltId, newBelt);

                Present newBox = new Present();
                newBox.beltId = beltId;
                box.put(id, newBox);
            } else if (cnt == pivot - 1) {
                belt.get(beltId).back = id;

                Present newBox = new Present();
                newBox.beltId = beltId;
                newBox.prev = input[i - 1];
                box.put(id, newBox);
                box.get(input[i - 1]).next = id;
            } else {
                Present newBox = new Present();
                newBox.beltId = beltId;
                newBox.prev = input[i - 1];
                box.put(id, newBox);
                box.get(input[i - 1]).next = id;
            }
            cnt++;
            if (cnt == pivot) {
                cnt = 0;
                beltId += 1;
            }
        }

        for (int i = 0; i < n; i++) {
            int w = Integer.parseInt(st.nextToken());
            box.get(input[i]).weight = w;
        }

        for (int time = 1; time < q; time++) {
            st = new StringTokenizer(bf.readLine());
            String func = st.nextToken();

            switch (func) {
                case "200":
                int wMax = Integer.parseInt(st.nextToken());
                cnt = 0;
    
                for (int i = 1; i <= m; i++) {
                    if (belt.get(i).no == 0) continue;
    
                    int id = belt.get(i).front;
    
                    if (box.get(id).weight <= wMax) {
                        cnt += box.get(id).weight;
    
                        if (belt.get(i).no == 1) {
                            belt.get(i).front = -1;
                            belt.get(i).back = -1;
                        } else {
                            int next = box.get(id).next;
                            box.get(next).prev = -1;
                            belt.get(i).front = next;
                        }
                        belt.get(i).no -= 1;
                        box.remove(id);
                    } else {
                        if (belt.get(i).no == 1) continue;
    
                        int next = box.get(id).next;
                        int back = belt.get(i).back;
    
                        box.get(id).prev = back;
                        box.get(id).next = -1;
    
                        box.get(next).prev = -1;
                        box.get(back).next = id;
    
                        belt.get(i).front = next;
                        belt.get(i).back = id;
                    }
                }
                System.out.println(cnt);
                    break;
                
                case "300":
                int rId = Integer.parseInt(st.nextToken());
                if (box.containsKey(rId) && box.get(rId).weight != 0) {
                    beltId = box.get(rId).beltId;
                    int prev = box.get(rId).prev;
                    int next = box.get(rId).next;
                    belt.get(beltId).no -= 1;
    
                    if (prev == -1 && next == -1) {
                        belt.get(beltId).front = -1;
                        belt.get(beltId).back = -1;
                    } else if (prev == -1) {
                        belt.get(beltId).front = next;
                        box.get(next).prev = -1;
                    } else if (next == -1) {
                        belt.get(beltId).back = prev;
                        box.get(prev).next = -1;
                    } else {
                        box.get(prev).next = next;
                        box.get(next).prev = prev;
                    }
                    box.remove(rId);
                    System.out.println(rId);
                } else {
                    System.out.println(-1);
                }
                break;

                case "400":
                int fId = Integer.parseInt(st.nextToken());
                if (!box.containsKey(fId) || box.get(fId).weight == 0) {
                    System.out.println(-1);
                } else {
                    int prev = box.get(fId).prev;
                    int next = box.get(fId).next;
                    beltId = box.get(fId).beltId;
                    int front = belt.get(beltId).front;
                    int back = belt.get(beltId).back;
    
                    System.out.println(beltId);
                    if (belt.get(beltId).no == 1) return;
                    if (prev == -1) return;
                    else if (next == -1) {
                        belt.get(beltId).front = fId;
                        belt.get(beltId).back = prev;
    
                        box.get(fId).prev = -1;
                        box.get(prev).next = -1;
    
                        box.get(front).prev = fId;
                        box.get(fId).next = front;
                    } else {
                        belt.get(beltId).front = fId;
                        belt.get(beltId).back = prev;
    
                        box.get(fId).prev = -1;
                        box.get(prev).next = -1;
    
                        box.get(back).next = front;
                        box.get(front).prev = back;
                    }
                }
                break;

                case "500":
                int bNum = Integer.parseInt(st.nextToken());
                if (belt.get(bNum).state == -1) {
                    System.out.println(-1);
                    return;
                } else {
                    System.out.println(bNum);
                }
    
                if (belt.get(bNum).no == 0) {
                    belt.get(bNum).state = -1;
                    return;
                }
    
                int flag = 0;
                for (int i = bNum + 1; i <= m; i++) {
                    if (belt.get(i).state == -1) continue;
                    flag = 1;
                    belt.get(i).no += belt.get(bNum).no;
                    if (belt.get(i).no == 0) {
                        belt.get(i).front = belt.get(bNum).front;
                        belt.get(i).back = belt.get(bNum).back;
                        int k = belt.get(bNum).front;
                        while (box.get(k).next != -1) {
                            box.get(k).beltId = i;
                            k = box.get(k).next;
                        }
                        box.get(k).beltId = i;
                    } else {
                        int fromFront = belt.get(bNum).front;
                        int toBack = belt.get(i).back;
    
                        box.get(toBack).next = fromFront;
                        box.get(fromFront).prev = toBack;
    
                        belt.get(i).back = belt.get(bNum).back;
                        while (box.get(fromFront).next != -1) {
                            box.get(fromFront).beltId = i;
                            fromFront = box.get(fromFront).next;
                        }
                        box.get(fromFront).beltId = i;
                        belt.get(i).back = fromFront;
                    }
                    belt.get(bNum).state = -1;
                    if (flag != 0) break;
                }
    
                if (flag == 0) {
                    for (int i = 1; i < bNum; i++) {
                        if (belt.get(i).state == -1) continue;
                        flag = 1;
                        belt.get(i).no += belt.get(bNum).no;
                        if (belt.get(i).no == 0) {
                            belt.get(i).front = belt.get(bNum).front;
                            belt.get(i).back = belt.get(bNum).back;
                            int k = belt.get(bNum).front;
                            while (box.get(k).next != -1) {
                                box.get(k).beltId = i;
                                k = box.get(k).next;
                            }
                            box.get(k).beltId = i;
                        } else {
                            int fromFront = belt.get(bNum).front;
                            int toBack = belt.get(i).back;
    
                            box.get(toBack).next = fromFront;
                            box.get(fromFront).prev = toBack;
    
                            belt.get(i).back = belt.get(bNum).back;
                            while (box.get(fromFront).next != -1) {
                                box.get(fromFront).beltId = i;
                                fromFront = box.get(fromFront).next;
                            }
                            box.get(fromFront).beltId = i;
                            belt.get(i).back = fromFront;
                        }
                        belt.get(bNum).state = -1;
                        if (flag != 0) break;
                    }
                }
                default:
                
                    break;
            }

        }

    }

}
