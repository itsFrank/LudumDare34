package data;

/**
 * Created by Francis O'Brien - 12/13/2015 - 6:25 AM
 */

public class GameVars {

    public static int year = 0;
    public static int day = 0;

    private static int DAY_LENGTH = 250;
    private static int day_time = 0;

    public static float fullTime(){
        float rtn = ((float)year) + ((float)day)/365;
        return rtn;
    }

    public static void update(int delta){
        day_time += delta;
        if (day_time >= DAY_LENGTH){
            if (++day >= 365){
                year++;
                day = 0;
            }
            day_time = 0;
        }
    }


}
