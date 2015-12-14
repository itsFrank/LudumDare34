package entities.managers;

import core.gamestates.Play;
import data.Names;
import entities.buildings.Building;
import entities.creatures.Attributes;
import entities.creatures.Creature;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Francis O'Brien - 1/27/2015 - 7:37 PM
 *
 * This class and the wave class are extremely convoluted and quite inefficient, they will need to be refactored at some point for readability
 *
 */

public class BuildingManager {

    private LinkedList<Building> buildings;
    private LinkedList<Building> toBeBuilt;
    private ArrayList<Building> toBeRemoved;

    public Play playState;

    public BuildingManager(Play playState){
        buildings = new LinkedList<>();
        toBeBuilt = new LinkedList<>();
        toBeRemoved = new ArrayList<>();

        this.playState = playState;
    }

    public void update(int delta){
        for (Building b : buildings) {
            b.update(delta);
        }
    }

    public void render(Graphics g){
        for (Building b : buildings) {
            b.draw(g);
            //Graphics g = playState.getGameContainer().getGraphics(); //For custom graphics
        }
    }

    private void removeFlagged(){
        for (Building b : toBeRemoved){
            buildings.remove(b);
            //waveCritters;
        }
    }

    public LinkedList<Building> getBuildings(){
        return buildings;
    }

    public LinkedList<Building> getToBeBuilt() {
        return toBeBuilt;
    }

    public void addBuilding(Building building) {
        building.setManager(this);
        buildings.add(building);
        toBeBuilt.add(building);
    }

    public void built(Building building) {
        toBeBuilt.remove(building);
    }

    public void birthBabies(Creature father, Creature mother){
        int fertility = father.getAttributes().fertility + mother.getAttributes().fertility;
        int children = Names.r.nextInt(fertility);
        if (children < 20) children = 0;
        else if (children < 90) children = 1;
        else if (children < 150) children = 2;
        else if (children < 200) children = 3;

        Attributes[] attr = new Attributes[children];
        int[] colors = new int[children];
        boolean[] isMale = new boolean[children];

        for (int i = 0; i < children; i++) {
            attr[i] = father.getAttributes().genChildAttributes(mother.getAttributes());
            colors[i] = Names.r.nextInt(4);
            isMale[i] = Names.r.nextInt(2) > 0;
        }

        playState.childrenpopup(attr, colors, isMale);
    }
}
