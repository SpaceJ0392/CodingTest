import java.util.Arrays;

public class SealedSpell {
    public String solution(long n, String[] bans) {
        String answer = "";

        //26진수 변환
        long[] bansIdxArray = new long[bans.length];
        for(int i = 0; i < bans.length; i++) {
            bansIdxArray[i] = toBase26(bans[i].toCharArray());
        }


        //생략된 bans를 포함할 때의 진짜 n값 찾기.
        long targetIdx = findRealN(n, bansIdxArray);

        //targetIdx의 26진수 변환 결과
        answer = fromBase26(targetIdx);
        return answer;
    }

    public long toBase26(char[] targetBan){ // 반환 타입을 long으로
        long base26N = 0; // 변수 타입도 long으로

        for(int i = 0; i < targetBan.length; i++) {
            int alphaN = targetBan[i] - 'a' + 1;
            base26N *= 26;
            base26N += alphaN;
        }
        return base26N;
    }

    public long findRealN(long n, long[] bansIdxArray){
        Arrays.sort(bansIdxArray);
        for(long bansIdx : bansIdxArray) {
            if(n >= bansIdx) n++;
        }
        return n;
    }

    public String fromBase26(long targetIdx){
        StringBuilder answer = new StringBuilder();
        while(targetIdx > 0) {
            answer.append((char) ('a' + (targetIdx - 1) % 26));
            targetIdx = (targetIdx - 1) / 26;
        }

        return answer.reverse().toString();
    }
}
