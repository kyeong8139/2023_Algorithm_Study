import java.util.*;
import java.util.Map.Entry;

public class 메뉴리뉴얼_72411 {
    static int[] order;
    static HashMap<Integer, Integer> count = new HashMap<>();
    static HashSet<Integer> courses, list;

    public static ArrayList<String> solution(String[] orders, int[] course) {
        order = new int[orders.length];
        for (int i = 0; i < orders.length; i++) {
            for (int j = 0; j < orders[i].length(); j++) {
                order[i] |= 1 << orders[i].charAt(j) - 'A';
            }
        }

        courses = new HashSet<>();
        for (int c : course) {
            courses.add(c);
        }

        list = new HashSet<>();
        for (int i = 0; i < order.length; i++) {
            dfs(i, 1, order[i]);
        }

        ArrayList<String>[] ar = new ArrayList[11];

        for(int i=0;i<11;i++) {
            ar[i] = new ArrayList<>();
        }

        int[] value = new int[11];
        for(Entry<Integer, Integer> entry: count.entrySet()) {
            int idx = Integer.bitCount(entry.getKey());
            if(value[idx] < entry.getValue()) {
                System.out.println(entry.getKey()+" "+entry.getValue()+" "+bitsToString(entry.getKey()));
                ar[idx].clear();
                value[idx] = entry.getValue();
                ar[idx].add(bitsToString(entry.getKey()));
            } else if(value[idx] == entry.getValue()) {
                ar[idx].add(bitsToString(entry.getKey()));
            }
        }

        ArrayList<String> answer = new ArrayList<>();
        for(ArrayList<String> tempArr : ar) {
            for(String tempString : tempArr) {
                answer.add(tempString);
            }
        }

        Collections.sort(answer);
        return answer;
    }

    private static void dfs(int idx, int dep, int orderList) {
        // if (idx == order.length - 1) {
        //     return;
        // }
        for (int i = idx + 1; i < order.length; i++) {
            int temp = orderList & order[i];
            System.out.println(temp);
            int bitCount = Integer.bitCount(temp);
            if (courses.contains(bitCount)) {
                count.put(temp, count.getOrDefault(temp, 0)+1);
            }
            dfs(i, dep + 1, temp);
        }
    }

    private static String bitsToString(int bits) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if ((bits & (1 << i)) != 0) {
                sb.append((char) ('A' + i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] s1 = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] i1 = {2,3,5};
        // String[] arr = ;
        System.out.println(solution(s1, i1).toString());
    }
}
