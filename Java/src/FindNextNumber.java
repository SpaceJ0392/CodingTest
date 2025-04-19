public class FindNextNumber {
    public static void main(String[] args) {
        int base = Integer.parseInt(args[0]);
        int answer = base;
        int cnt0 = 0, cnt1 = 0;

        //1. 오른쪽에서부터 0의 개수 탐색
        while((base & 1) == 0){
            cnt0++;
            base >>= 1;
        }

        //2. 오른쪽에서 0이 끊긴 뒤 1의 개수 탐색
        while((base & 1) == 1){
            cnt1++;
            base >>= 1;
        }

        //3. 다음 큰 숫자는 두번째 1을 하나 당기고, 나머지 1은 오른쪽으로 밀착.
        int total = cnt0 + cnt1;
        answer |= 1 << total;
        answer &= -(1 << total); //masking
        answer |= (1 << (cnt1 - 1)) - 1;
    }
}
