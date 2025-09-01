public class VideoPlayer {
    public String solution(String video_len, String pos, String op_start,
                           String op_end, String[] commands) {

        int posTime = getRealTime(pos);
        int opStartTime = getRealTime(op_start);
        int opEndTime = getRealTime(op_end);
        int videoLen = getRealTime(video_len);

        int result = posTime;
        for(String command : commands){
            if(result >= opStartTime && result <= opEndTime) result = opEndTime;

            if(command.equals("next"))
                result = Math.min((result + 10), videoLen);
            else if (command.equals("prev"))
                result = Math.max((result - 10), 0);
        }
        if(result >= opStartTime && result <= opEndTime) result = opEndTime;
        return parseStringTime(result / 60) + ":" + parseStringTime(result % 60);
    }

    public String parseStringTime(int target){
        String targetStr = Integer.toString(target);
        return "0".repeat(2 - targetStr.length()) + targetStr;
    }

    public int getRealTime(String target){
        String[] time = target.split(":");
        return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
    }
}
