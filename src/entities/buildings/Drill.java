package entities.buildings;

import data.GameVars;
import data.Renders;
import entities.creatures.Creature;
import entities.creatures.CreatureValues;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 12/13/2015 - 12:58 PM
 */

public class Drill extends Building{

    float prevDay = 0;

    public Drill(vec2 pos) {
        super();
        this.pos = pos;
    }

    public void preDraw() {
        if (creature != null && creature.getJob() == CreatureValues.Job.BUILDER && hasWorker){
            creature.drawOffset(0, 0);
        }
    }

    @Override
    public void update(int delta) {
        super.update(delta);
        if (hasWorker && creature != null){
            float diff = GameVars.fullTime() - prevDay;
            int days = (int) (diff * 365);
            if (days > 10) {
            manager.playState.player.updateCurrency((int)((float)days * (((float)creature.getAttributes().strenght + (float)creature.getAttributes().speed)/200)));
                prevDay = GameVars.fullTime();
            }
        }
    }

    @Override
    protected void builtRender() {
        render = Renders.DRILL_1;
        built = true;
    }

    public void arrived(Creature creature) {
        if (creature.getJob() == CreatureValues.Job.BUILDER && hasWorker) {
            creature.drawoff();
            prevDay = GameVars.fullTime();
        }
        this.creature = creature;
    }

}
