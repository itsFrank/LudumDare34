package entities.managers;

import core.gamestates.Play;
import entities.creatures.Attributes;
import entities.creatures.Creature;
import org.newdawn.slick.Graphics;
import util.vectors.vec2;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Francis O'Brien - 1/27/2015 - 7:37 PM
 *
 * This class and the wave class are extremely convoluted and quite inefficient, they will need to be refactored at some point for readability
 *
 */

public class CreatureManager {

    private LinkedList<Creature> creatures;
    private ArrayList<Creature> toBeRemoved;
    //TODO Generational tree

    public Play playState;

    public CreatureManager(Play playState){
        creatures = new LinkedList<Creature>();
        toBeRemoved = new ArrayList<Creature>();

        this.playState = playState;
    }

    public void addCreature(Creature c){
        creatures.add(c);
        c.setManager(this);
        c.setPlayState(playState);
        playState.player.addCreature();
    }

    public void createCreature(String name, Attributes attributes, int color, int job, boolean isMale, vec2 initialPos){
        Creature c = Creature.createNewCreature(name, attributes, color, job, isMale);
        addCreature(c);
        c.getPos().setSelf(initialPos);
    }

    public void update(int delta){
        for (Creature c : creatures) {
            c.update(delta);
        }
        removeFlagged();
    }

    public void render(){
        for (Creature c : creatures) {
            c.draw();
            Graphics g = playState.getGameContainer().getGraphics();
        }
    }

    private void removeFlagged(){
        for (Creature c : toBeRemoved){
            creatures.remove(c);
        }
    }

    public LinkedList<Creature> getCreatures(){
        return creatures;
    }

    public Creature creatureAt(vec2 position) {
        Creature rtn = null;
        for (Creature c : creatures){
            if (c.isWithin(position)){
                rtn = c;
            }
        }
        return rtn;
    }

    public void kill(Creature creature) {
        toBeRemoved.add(creature);
    }
}
