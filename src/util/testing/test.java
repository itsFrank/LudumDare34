package util.testing;


import java.util.Random;

/**
 * Created by Francis O'Brien - 1/28/2015 - 3:31 AM
 */

public class test {

    public static void main(String args[]){

        Random r = new Random(System.currentTimeMillis());
        int num = 99;
        for (int i = 0; i < 20; i++){
            int rand = r.nextInt(100);
            int diff = rand - num;
            rand = rand - (rand*(diff/100));
            System.out.println((int) Math.round(r.nextGaussian() * 7 + 50));

        }
    }

}
