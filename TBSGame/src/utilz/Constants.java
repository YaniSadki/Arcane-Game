package utilz;

public class Constants {
    public static class PlayerNumber{
        public static final int VI=0;
        public static final int JAYCE=1;
        public static final int VIKTOR=2;
        public static final int CAITLYN=3;
        public static final int SEVIKA=4;
        public static final int SINGED=5;
        public static final int SILCO=6;
        public static final int JINX=7;
    }

    public static class PlayerConstants{
        public static final int IDLE=0;
        public static final int RUNNING=1;
        public static final int JUMP=2;
        public static final int FALLING=3;
        public static final int GROUND=4;
        public static final int HIT=5;
        public static final int ATTACK_1=6;
        public static final int ATTACK_JUMP_1=7;
        public static final int ATTACK_JUMP_2=8;

        public static int GetSpriteAmount(int player_action){
            switch(player_action){
                case RUNNING:
                    return 6;
                case IDLE:
                    return 5;
                case HIT:
                    return 4;
                case JUMP:
                case ATTACK_1:
                case ATTACK_JUMP_1:
                case ATTACK_JUMP_2:
                    return 3;
                case GROUND:
                    return 2;
                case FALLING:
                default:
                    return 1;
            }
        }
    }
}
