public class DeliveryAndPickUp {
    public static void main(String[] args) {
        DeliveryAndPickUp deliveryAndPickUp = new DeliveryAndPickUp();
        deliveryAndPickUp.solution(4, 5, new int[] {1,0,3,1,2}, new int[] {0,3,0,4,0});
    }

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int now = n, deliveryCnt = 0, pickupCnt = 0;
        for(int i = n - 1; i >= 0; i--){
            deliveryCnt += deliveries[i];
            pickupCnt += pickups[i];

            if(deliveries[i] == 0 && pickups[i] == 0) now--;
            if(deliveryCnt >= cap && pickupCnt >= cap){
                answer += (now * 2);
                now = i - 1;
                deliveryCnt = deliveries[i];
                pickupCnt = pickups[i];
                System.out.println(
                        deliveries[i] + " " + pickups[i] + " " + answer + " | " + now
                                + " " + (deliveryCnt) + " " + (pickupCnt)
                );
            }

            if(i == 0 && deliveryCnt != 0 && pickupCnt != 0) answer += (now * 2);
        }

        return answer;
    }
}
