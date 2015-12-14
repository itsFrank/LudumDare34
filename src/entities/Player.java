package entities;

import core.gamestates.Play;

/**
 * Created by Francis O'Brien - 3/10/2015 - 10:45 AM
 */

public class Player {

    public final int MORE_INFO = 0;

    /// INFO STATE ///
    public static int LOW = 0;
    public static int MED = 1;
    public static int HIGH = 2;

    /// TECH VARS ///
    private int current_tech = 99;
    private float tech_goal = 1;
    private float tech_progress = 1;
    private boolean reasearching = false;


    private int currency = 200;
    private int numCreatures;
    private int info_level = LOW;

    private Play playState;
    private int creatureCap = 6;
    private int moreInfoLevel;

    public void addTechProgress(float progress){
        tech_progress += progress;
        if (tech_progress >= tech_goal){
            tech_progress = tech_goal;
            if (current_tech == MORE_INFO){
                if (info_level != HIGH) info_level++;
                tech_goal = 1;
                tech_progress = 1;
                reasearching = false;
            }
        }
    }

    public void setCreatureCap(int creatureCap) {
        this.creatureCap = creatureCap;
    }

    public int getInfo_level() {
        return info_level;
    }

    public void updateCurrency(int value){
        currency += value;
    }

    public int getCurrency(){
        return currency;
    }

    public float getTech_progress() {
        return tech_progress;
    }

    public float getTech_goal() {
        return tech_goal;
    }

    public int getNumCreatures() {
        return numCreatures;
    }

    public void addCreature() {
        numCreatures++;
    }

    public int getCreatureCap() {
        return creatureCap;
    }

    public int getCurrent_tech() {
        return current_tech;
    }

    public void setTech_goal(float tech_goal) {
        this.tech_goal = tech_goal;
    }

    public void setTech_progress(float tech_progress) {
        this.tech_progress = tech_progress;
    }

    public void incrementMoreInfoLevel() {
        this.moreInfoLevel++;
    }

    public int getMoreInfoLevel() {
        return moreInfoLevel;
    }

    public boolean ieReasearching() {
        return false;
    }

    public void setReasearching(boolean reasearching) {
        this.reasearching = reasearching;
    }

    public void setCurrent_tech(int current_tech) {
        this.current_tech = current_tech;
    }
}
