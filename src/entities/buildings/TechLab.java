package entities.buildings;

import data.GameVars;
import data.Renders;
import entities.creatures.Creature;
import entities.creatures.CreatureValues;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 12/13/2015 - 9:50 AM
 */

public class TechLab extends Building {

    public TechLab(vec2 pos) {
        super();
        this.pos = pos;
    }

    @Override
    public void preDraw() {
        if (creature != null && creature.getJob() == CreatureValues.Job.SCIENTIST){
            creature.drawOffset(0, 0);
        }
    }

    float prevDay = 0;

    @Override
    public void update(int delta) {
        super.update(delta);
        if (hasWorker && creature != null){
            float diff = GameVars.fullTime() - prevDay;
            manager.playState.player.addTechProgress((diff * ((float)(creature.getAttributes().inteligence + creature.getAttributes().inteligence))));
            prevDay = GameVars.fullTime();
        }
    }

    @Override
    protected void builtRender() {
        render = Renders.TECH_1;
    }

    public void arrived(Creature creature) {
        if (creature.getJob() == CreatureValues.Job.SCIENTIST) {
            creature.drawoff();
            prevDay = GameVars.fullTime();
        }
        this.creature = creature;
    }
}
