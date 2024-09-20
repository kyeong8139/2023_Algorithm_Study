import java.io.*;
import java.util.*;

public class 플레이페어암호_6255 {
    static char[][] key;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String word = bf.readLine();
        key = new char[5][5];
        char[] temp = bf.readLine().toCharArray();
        Set<Character> alphabet = new HashSet<>();
        for(int i=0;i<26;i++) {
            alphabet.add((char) (65+i));
        }
        alphabet.remove('J');

        int idx = 0;
        for(int i=0;i<temp.length;i++) {
            if(!alphabet.contains(temp[i])) continue;
            alphabet.remove(temp[i]);
            key[idx == 0 ? 0 : idx/5][idx == 0 ? 0 : idx%5] = temp[i];
            idx++;
        }

        LinkedList<Character> left = new LinkedList<>(alphabet);
        
        if(idx < 25) {
            for(int i=0;idx != 25;idx++,i++) {
                key[idx == 0 ? 0 : idx/5][idx == 0 ? 0 : idx%5] = left.removeFirst();
            }
        }

        String ans = "";
        idx =0;
        while(idx < word.length()) {
            char leftone = word.charAt(idx);
            char rightone = ' ';
            try {
                rightone = word.charAt(idx+1);
            } catch (Exception e) {
                ans += getKey(leftone, 'X');
                System.out.println(ans);
                return;
            }
            if(leftone == rightone) {
                if(leftone != 'X') {
                    ans += getKey(leftone, 'X');
                    idx++;
                } else {
                    ans += getKey(leftone, 'Q');
                    idx++;
                }
            } else {
                ans += getKey(leftone, rightone);
                idx += 2;
            }
        }
        System.out.println(ans);

    }

    static String getKey(char leftone, char rightone) {
        int[] leftIdx = new int[2], rightIdx = new int[2];
        String returnKey = "";
        for(int i=0;i<5;i++) {
            for(int j=0;j<5;j++) {
                if(key[i][j] == leftone) {
                    leftIdx[0] = i; leftIdx[1] = j;
                }
                if(key[i][j] == rightone) {
                    rightIdx[0] = i; rightIdx[1] = j;
                }
            }
        }
        if(leftIdx[0] == rightIdx[0]) {
            return Character.toString(key[leftIdx[0]][leftIdx[1]+1 == 5 ? 0 : leftIdx[1]+1]) + Character.toString(key[rightIdx[0]][rightIdx[1]+1 == 5 ? 0 : rightIdx[1]+1]);
        } else if(leftIdx[1] == rightIdx[1]) {
            return Character.toString(key[leftIdx[0]+1 == 5 ? 0 : leftIdx[0]+1][leftIdx[1]]) + Character.toString(key[rightIdx[0]+1 == 5 ? 0 : rightIdx[0]+1][rightIdx[1]]);
        } else {
            return Character.toString(key[leftIdx[0]][rightIdx[1]]) + Character.toString(key[rightIdx[0]][leftIdx[1]]);
        }
    }
}