package entities.creatures;

import core.gamestates.Play;
import entities.Player;

import java.util.Random;

/**
 * Created by Francis O'Brien - 12/12/2015 - 6:34 PM
 */

public class Attributes {

    public static Random r = new Random(System.currentTimeMillis());
    public static int num_attributes = 5;
    public static int mutation_factor = 5;

    public int strenght;
    public int speed;
    public int inteligence;
    public int longevity;
    public int fertility;


    public Attributes(){
        strenght = r.nextInt(98) + 1;
        speed = r.nextInt(98) + 1;
        inteligence = r.nextInt(98) + 1;
        longevity = r.nextInt(98) + 1;
        fertility = r.nextInt(98) + 1;
    }

    public Attributes(int strenght, int speed, int inteligence, int longevity, int fertility) {
        this.strenght = strenght;
        this.speed = speed;
        this.inteligence = inteligence;
        this.longevity = longevity;
        this.fertility = fertility;
    }

    public int getAttribute(int i){
        switch (i) {
            case 0:
                return strenght;
            case 1:
                return speed;

            case 2:
                return inteligence;

            case 3:
                return longevity;

            case 4:
                return fertility;
        }
        throw new RuntimeException("No attribute at index: " + i);
    }

    public void setAttribute(int i, int value){
        if (value <= 0) value = 1;
        if (value > 99) value = 99;
        switch (i) {
            case 0:
                strenght = value;
                break;
            case 1:
                speed = value;
                break;
            case 2:
                inteligence = value;
                break;
            case 3:
                longevity = value;
                break;
            case 4:
                fertility = value;
                break;
            default:
                throw new RuntimeException("No attribute at index: " + i);
        }
    }

    public Attributes genChildAttributes(Attributes mate){
        Attributes a = new Attributes();

        for (int i = 0; i < num_attributes; i++) {
            int mutation_roll = r.nextInt(100);

            if (mutation_roll < mutation_factor) {
                boolean high = r.nextInt(3) > 1;
                if (high){
                    if (getAttribute(i) > 80){
                        a.setAttribute(i, (int) Math.round(r.nextGaussian() * 7 + getAttribute(i)));
                    } else if (mate.getAttribute(i) > 80){
                        a.setAttribute(i, (int) Math.round(r.nextGaussian() * 7 + mate.getAttribute(i)));
                    } else {
                        a.setAttribute(i, (int) Math.round(r.nextGaussian() * 7 + 80));
                    }
                } else {
                    if (getAttribute(i) < 20){
                        a.setAttribute(i, (int) Math.round(r.nextGaussian() * 7 + getAttribute(i)));
                    } else if (mate.getAttribute(i) < 20){
                        a.setAttribute(i, (int) Math.round(r.nextGaussian() * 7 + mate.getAttribute(i)));
                    } else {
                        a.setAttribute(i, (int) Math.round(r.nextGaussian() * 7 + 20));
                    }
                }
            } else {
                boolean thisMate = r.nextInt(2) < 1;
                if (thisMate){
                    a.setAttribute(i, (int) Math.round(r.nextGaussian() * 7 + getAttribute(i)));
                } else {
                    a.setAttribute(i, (int) Math.round(r.nextGaussian() * 7 + mate.getAttribute(i)));
                }
            }
        }
        return a;
    }

    private String getString(int value){
        if (Play.player.getInfo_level() == Player.LOW) {
            if (value < 33) return "Low";
            if (value < 66) return "Med";
            else return "High";
        } if (Play.player.getInfo_level() == Player.MED) {
            if (value < 20) return "V-Low";
            if (value < 40) return "Low";
            if (value < 60) return "Medium";
            if (value < 80) return "High";
            else return "V-High";
        } else {
            return "" + value;
        }
    }

    public String getStrenghtString() {
        return getString(strenght);
    }

    public String getSpeedString() {
        return getString(speed);
    }

    public String getInteligenceString() {
        return getString(inteligence);
    }

    public String getLongevityString() {
        return getString(longevity);
    }

    public String getFertilityString() {
        return getString(fertility);
    }
}
