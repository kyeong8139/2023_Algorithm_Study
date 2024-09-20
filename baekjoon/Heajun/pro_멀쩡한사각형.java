class pro_멀쩡한사각형 {
    public long solution(int w, int h) {
        long answer = 0;
        long W = (long)w;
        long H = (long)h;

        answer = W*H-(W+H-gcd(W,H));
        return answer;
    }

    public long gcd(long n1, long n2){

        if(n1 < n2){
            long t = n2;
            n2 = n1;
            n1 = t;
        }

        long tmp = 0;
        while (n2 > 0) {
            tmp = n1 % n2;
            n1 = n2;
            n2 = tmp;
        }

        return n1;
    }
}
