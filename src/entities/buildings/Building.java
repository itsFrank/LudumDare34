package entities.buildings;

import data.Renders;
import entities.Entity;
import entities.creatures.Creature;
import entities.managers.BuildingManager;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created by Francis O'Brien - 12/12/2015 - 3:11 AM
 */

public abstract class Building extends Entity {

    private float buildProgress = 0;
    protected boolean built = false;
    private boolean hasBuilder = false;
    protected boolean hasWorker = false;
    protected Creature creature;
    protected BuildingManager manager;

    public Building() {
        super(Renders.BOXES);
    }

    @Override
    public void update(int delta) {
        if (!built){
            if (buildProgress > 99){
                creature.freeBuilder();
                creature = null;
                built = true;
                hasBuilder = false;
                builtRender();
                manager.built(this);
            }
            if (creature != null) {
                buildProgress += (creature.getAttributes().strenght + creature.getAttributes().inteligence) * 0.5 * delta/1000;
                if (buildProgress > 100) buildProgress = 100;
            }
        }
    }

    protected abstract void builtRender();

    private static Color BLUE = new Color(27, 117, 188);
    private static Color DARK_GREY = new Color(48, 48, 48);

    public void draw(Graphics graphics){
        preDraw();
        render.draw(pos.x, pos.y);
        if (!built) {
            graphics.setColor(DARK_GREY);
            graphics.fillRect(pos.x + 10, pos.y + 64 + 25, 104, 20);
            graphics.setColor(BLUE);
            graphics.fillRect(pos.x + 10, pos.y + 64 + 25, (buildProgress / 100) * 104, 20);
        }
    }

    public void setManager(BuildingManager manager) {
        this.manager = manager;
    }

    public void setHasBuilder(boolean hasBuilder){
        this.hasBuilder = hasBuilder;
    }

    public boolean isBuilt() {
        return built;
    }

    public boolean hasBuilder() {
        return hasBuilder;
    }

    public void arrived(Creature creature) {
        this.creature = creature;
    }

    public void died(Creature creature) {
        if (this.creature.equals(creature)){
            if (hasBuilder) hasBuilder = false;
            if (hasWorker) hasWorker = false;
            this.creature = null;
        }
    }

    public void setHasWorker(boolean hasWorker) {
        this.hasWorker = hasWorker;
    }

    public boolean hasCreature(){
        return hasWorker;
    }
}
