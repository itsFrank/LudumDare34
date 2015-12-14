package entities.creatures;

import core.gamestates.Play;
import entities.buildings.Building;
import entities.buildings.Drill;
import entities.buildings.TechLab;
import util.vectors.vec2;

import java.util.Random;

/**
 * Created by Francis O'Brien - 12/12/2015 - 10:30 PM
 */

public class CreatureAI {

    private static Random r = new Random(System.currentTimeMillis());

    private Creature creature;
    private int checks = 0;
    private long check_time = 0;
    private Play playState;

    public CreatureAI(Creature creature) {
        this.creature = creature;
    }

    public void update(int delta){
        if (!creature.isWorking()) {

            if (creature.getJob() == CreatureValues.Job.BUILDER) builderIdle();
            if (creature.getJob() == CreatureValues.Job.SCIENTIST) scientistIdle();

            if (creature.isWalking()) return;

            if (check_time > 300) {
                check_time = 0;
                int move_roll = r.nextInt(100 - (3 * checks));
                checks++;
                if (move_roll < 10) {
                    vec2 move = new vec2(r.nextInt(100) - 50, r.nextInt(100) - 50);
                    move.normalize();
                    move.multSelf(r.nextInt(30) + 30);
                    creature.moveTo(move.add(creature.getPos()));
                    checks = 0;
                }
            }
            check_time += delta;
        }
    }

    private void scientistIdle() {
        for (Building b : playState.buildingManager.getBuildings()){
            if (b.isBuilt() && b instanceof TechLab && !b.hasCreature()){
                creature.assignTo(b);
                b.setHasWorker(true);
            }
        }
    }

    private void builderIdle() {
        for (Building b : playState.buildingManager.getToBeBuilt()){
            if (!b.hasBuilder() && !b.isBuilt()){
                creature.assignTo(b);
                b.setHasBuilder(true);
                return;
            }
        }
        for (Building b : playState.buildingManager.getBuildings()){
            if (b.isBuilt() && b instanceof Drill && !b.hasCreature()){
                creature.assignTo(b);
                b.setHasWorker(true);
                return;
            }
        }
    }

    public void setPlayState(Play playState) {
        this.playState = playState;
    }
}
