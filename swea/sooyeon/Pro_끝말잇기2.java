package swea.sooyeon;


import java.util.Arrays;
import java.util.Comparator;

class UserSolution {
    static class Word {
        int idx;
        String word;
        boolean hasSpoken;

        Word(int idx, String word, boolean hasSpoken) {
            this.idx = idx;
            this.word = word;
            this.hasSpoken = hasSpoken;
        }
    }

    static Word[] wordlist;
    static int N;
    public void init(int N, int M, char[][] mWords) {
        this.N = N;
        wordlist = new Word[M];
        for(int i=0;i<M;i++) {
            wordlist[i] = new Word(i, mWords[i].toString(), false);
        }

        Arrays.sort(wordlist, new Comparator<Word>() {

            @Override
            public int compare(Word o1, Word o2) {
                return o1.word.compareTo(o2.word);
            }
            
        });
    }

    public int playRound(int mID, char mCh) {
        char nowChar = mCh;
        while(true) {
            int turn = mID < N ? 0 : mID;
            for(Word word : wordlist) {

            }
        }
        return 0;
    }
}