package Btype.끝말잇기2;

import java.util.*;

class UserSolution {

    static class Word implements Comparable<Word> {
        String word;
        String reverse;

        public Word(String word, String reverse) {
            this.word = word;
            this.reverse = reverse;
        }

        @Override
        public int compareTo(Word o) {
            return word.compareTo(o.word);
        }
    }

    static TreeSet<Word>[] words;
    static HashSet<String> usedWords;
    static Queue<Word> preWords;
    static boolean[] people;
    static int N, M;

    public void init(int N1, int M1, char[][] mWords) {
        people = new boolean[N1 + 1];
        people[0] = true;
        usedWords = new HashSet<>();
        words = new TreeSet[26];

        N = N1;
        M = M1;

        for(int i = 0; i < 26; i++) {
            words[i] = new TreeSet<>();
        }

        for(int i = 0; i < M; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < mWords[i].length; j++) {
                if(mWords[i][j] == '\u0000') break;

                sb.append(mWords[i][j]);
            }

            Word word = new Word(sb.toString(), sb.reverse().toString());
            words[word.word.charAt(0) - 'a'].add(word);
        }
    }

    public int playRound(int mID, char mCh) {
        preWords = new ArrayDeque<>();

        char startCh = mCh;
        int startP = mID;

        while(!words[startCh - 'a'].isEmpty()) {
            while(people[startP % (N + 1)]) {
                startP = (startP + 1);
            }

            Word word = words[startCh - 'a'].pollFirst();
            preWords.add(word);
            usedWords.add(word.word);

            startCh = word.word.charAt(word.word.length() - 1);

            startP = (startP + 1) % (N + 1);
        }

        while(people[startP]) {
            startP = (startP + 1) % (N+1);
        }

        people[startP] = true;

        while(!preWords.isEmpty()) {
            Word used = preWords.poll();
            if(!usedWords.contains(used.reverse)) {
                words[used.reverse.charAt(0) - 'a'].add(new Word(used.reverse, used.word));
            }
        }

        System.out.println(startP);
        return startP;
    }
}
