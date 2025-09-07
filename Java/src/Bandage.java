public class Bandage {
    public static void main(String[] args) {
        Bandage bandage = new Bandage();
        bandage.solution(new int[] {}, 30, new int[][] {{}});
    }

    public int solution(int[] bandage, int health, int[][] attacks) {
        int maxHealth = health;
        int prevAttackTime = 0;

        for(int[] attack : attacks){
            int attackTime = attack[0], damage = attack[1];
            int term = attackTime - prevAttackTime;

            int predictedHP = health + ((term - 1) * bandage[1])
                    + (bandage[2] * ((term - 1) / bandage[0]));
            health = Math.min(predictedHP, maxHealth);
            health -= damage;
            prevAttackTime = attack[0];

            if(health <= 0) {
                health = -1;
                break;
            }
        }
        return health;
    }
}
