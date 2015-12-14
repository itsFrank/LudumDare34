package entities.buildings;

import data.Renders;
import entities.creatures.Creature;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 12/13/2015 - 1:29 PM
 */

public class MatingBldg extends Building {

    private Creature male;
    private Creature female;

    public MatingBldg(vec2 pos){
        this.pos = pos;
        built = true;
    }

    @Override
    public void builtRender() {
        manager.built(this);
        render = Renders.MATING_BLDG;
    }

    @Override
    public void preDraw() {
        if (male != null){
            male.drawOffset(10, +4);
        }
        if (female != null){
            female.drawOffset(84, +4);
        }
    }

    private int timer = 0;

    @Override
    public void update(int delta) {
        super.update(delta);
        if (male != null && female != null) {
            timer += delta;
            if (timer >= 2000) {
                timer = 0;
                male.drawon();
                male.freeBuilder();
                female.getPos().addSelf(new vec2(64, 0));
                female.drawon();
                female.freeBuilder();
                manager.birthBabies(male, female);
                male = null;
                female = null;
            }
        }
    }

    public boolean hasMale() {
        return male != null;
    }

    public boolean hasFemale() {
        return female != null;
    }

    public void arrived(Creature creature) {
        creature.drawoff();
        if (creature.isMale()) male = creature;
        else female = creature;
    }

    public void died(Creature creature) {
        if (this.male.equals(creature)){
            this.male = null;
        }else if (this.female.equals(creature)){
            this.female = null;
        }
    }
}
