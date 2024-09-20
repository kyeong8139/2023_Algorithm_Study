import java.util.*;

public class softeer_플레이페어 {

    static class Node {
        int r, c;

        Node() {};

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static String message, key;
    static char[][] playfair;
    static boolean[] alphabet;
    static List<char[]> messages;
    static HashMap<Character, Node> nodes;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        message = sc.next();
        key = sc.next();

        playfair = new char[5][5];
        alphabet = new boolean[26];
        messages = new ArrayList<>();
        nodes = new HashMap<>();

        init();

        for(int i = 0; i < messages.size(); i++) {
            Node node1 = nodes.get(messages.get(i)[0]);
            Node node2 = nodes.get(messages.get(i)[1]);

            // 가로가 같을 때
            if(node1.r == node2.r) {
                sb.append(playfair[node1.r][(node1.c + 1) % 5]);
                sb.append(playfair[node2.r][(node2.c + 1) % 5]);
            }
            // 세로가 같을 때
            else if(node1.c == node2.c) {
                sb.append(playfair[(node1.r + 1) % 5][node1.c]);
                sb.append(playfair[(node2.r + 1) % 5][node2.c]);
            }
            // 다 다를 때
            else {
                sb.append(playfair[node1.r][node2.c]);
                sb.append(playfair[node2.r][node1.c]);
            }
        }

        System.out.println(sb);

    }

    static void init() {
        int idx = 0;
        o : for(int r = 0; r < 5; r++) {
            for(int c = 0; c < 5; c++) {
                while(alphabet[key.charAt(idx) - 'A']) {
                    idx++;
                    if(idx >= key.length()) break o;
                }

                alphabet[key.charAt(idx) - 'A'] = true;
                playfair[r][c] = key.charAt(idx);
                nodes.put(key.charAt(idx), new Node(r, c));
            }
        }

        idx = 0;
        o : for(int r = 0; r < 5; r++) {
            for(int c = 0; c < 5; c++) {
                if(playfair[r][c] != '\u0000') continue;

                while(idx == 9 || alphabet[idx]) {
                    idx++;
                    if(idx >= 26) break o;
                }
                alphabet[idx] = true;
                playfair[r][c] = (char) (idx + 'A');
                nodes.put((char) (idx + 'A'), new Node(r, c));
            }
        }

        idx = 0;
        while(idx < message.length()) {
            char[] temp = new char[2];
            char ch = message.charAt(idx);
            if(idx == message.length()-1) {
                temp[0] = ch;
                temp[1] = 'X';
                messages.add(temp);
                break;
            }

            if(ch == message.charAt(idx + 1)) {
                if(ch == 'X') {
                    temp[0] = ch;
                    temp[1] = 'Q';
                } else {
                    temp[0] = ch;
                    temp[1] = 'X';
                }
                idx += 1;
            } else {
                temp[0] = ch;
                temp[1] = message.charAt(idx + 1);
                idx += 2;
            }

            messages.add(temp);
        }
    }
}
