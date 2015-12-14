package entities.creatures;

import core.Core;
import core.gamestates.Play;
import core.rendering.StateRender;
import data.GameVars;
import data.Messages;
import data.Renders;
import entities.Entity;
import entities.buildings.Building;
import entities.buildings.MatingBldg;
import entities.managers.CreatureManager;
import util.vectors.vec2;

/**
 * Created by Francis O'Brien - 12/12/2015 - 3:06 AM
 */

public class Creature extends Entity{

    // Gameplay variables //
    private String name;
    private int age = 0;
    private int job = CreatureValues.Job.NONE;
    private int color;
    private boolean isMale;
    private boolean working = false;
    private float deathDate = 0;
    private float birthday = 0;

    // Job Specific //
    private Building building;

    // Physical variables //
    private vec2 destination;
    private static final float speed = 0.1f;
    private boolean destReached = true;
    private boolean destRight = true;
    private boolean destUp = true;

    // Data variables //
    private CreatureAI ai;
    private Attributes attributes;
    private boolean hasTask = false;

    // Graphics variables //
    private StateRender render;
    private boolean facingRight = true;
    private boolean walking = false;

    private CreatureManager manager;

    public Attributes getAttributes() {
        return attributes;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setManager(CreatureManager manager){
        this.manager = manager;
    }

    public boolean isWorking() {
        return working;
    }

    public void assignTo(Building b) {
        working = true;
        moveTo(b.getPos());
        building = b;
    }

    public void freeBuilder() {
        working = false;
        building = null;
        moveToIdle();
    }

    private void moveToIdle() {
        moveTo(new vec2(10 * 64, 12 * 64));
    }

    public void mate(MatingBldg mating_building) {
        working = true;
        building = mating_building;
        moveTo(mating_building.getPos().clone());
    }

    public void setJob(int job) {
        this.job = job;
        render = Renders.makeCreatureRender(color, job, isMale);
        super.render = render;
    }

    public enum animation_state{
        STAND_RIGHT(0), STAND_LEFT(1), WALK_RIGHT(2), WALK_LEFT(3);

        private final int value;
        private animation_state(int v){
            value = v;
        }

        public int getValue(){
            return value;
        }
    }

    public Creature(String name, Attributes attributes, StateRender render, int color, int job, boolean isMale) {
        super(render);
        this.name = name;
        this.color = color;
        this.job = job;
        this.isMale = isMale;
        this.ai = new CreatureAI(this);

        if (attributes == null){
            this.attributes = new Attributes();
        } else {
            this.attributes = attributes;
        }

        birthday = GameVars.fullTime();
        deathDate = GameVars.fullTime() + (100/(100 - ((float)this.attributes.longevity)));

        this.render = render;
        render.enterState(animation_state.STAND_RIGHT.getValue());
    }

    @Override
    public void update(int delta) {
        ai.update(delta);
        calcMove(delta);
        age = (int)(GameVars.fullTime() - birthday);
        if (GameVars.fullTime() > deathDate){
            kill();
        }
    }

    private void kill() {
        Messages.newMessage(name + "has died at the age of " + age + " years and " + (int)(365 * (GameVars.fullTime() - birthday - age)) + " days.");
        if (building != null)building.died(this);
        manager.kill(this);
    }

    @Override
    public void preDraw() {
        if (facingRight){
            if (walking) render.enterState(animation_state.WALK_RIGHT.getValue());
            else render.enterState(animation_state.STAND_RIGHT.getValue());
        } else {
            if (walking) render.enterState(animation_state.WALK_LEFT.getValue());
            else render.enterState(animation_state.STAND_LEFT.getValue());
        }
    }

    static int idle_left_bound = 7 * 64;
    static int idle_right_bound = 7 * 64 + 6 * 64;
    static int idle_up_bound = Core.HEIGHT - Renders.PLAY_MENU_BACKGROUND.getHeight() - 4 * 64 + 20;

    public void moveTo(vec2 dest){

        if (!working){
            if (dest.x < idle_left_bound) dest.x = idle_left_bound;
            else if (dest.x > idle_right_bound - render.getWidth()) dest.x = idle_right_bound - render.getWidth();

            if (dest.y < idle_up_bound - render.getHeight()) dest.y = idle_up_bound - render.getHeight();
        } else {
            if (dest.x < 0) dest.x = 0;
            else if (dest.x > Core.WIDTH - render.getWidth()) dest.x = Core.WIDTH - render.getWidth();

            if (dest.y < 0) dest.y = 0;

        }
        if (dest.y > Core.HEIGHT - render.getHeight() - Renders.PLAY_MENU_BACKGROUND.getHeight())
            dest.y = Core.HEIGHT - render.getHeight() - Renders.PLAY_MENU_BACKGROUND.getHeight();
        this.destination = dest;
        //physical
        destRight = dest.x > pos.x;
        destUp = dest.y > pos.y;
        destReached = false;
        //render
        walking = true;
        facingRight = destRight;
    }

    private void calcMove(int delta){
        if (!destReached){
            vec2 move = destination.difference(pos);
            move.normalize();
            move.multSelf(speed * delta);

            if (pos.difference(destination).length() < 1){
                destReached = true;
                walking = false;
                if (building != null) building.arrived(this);
            }

            pos.addSelf(move);
        }
    }

    public void drawOffset(float x, float y){
        preDraw();
        render.draw(pos.x + x, pos.y + y);
    }

    public static Creature createNewCreature(String name, Attributes attributes, int color, int job, boolean isMale){
        StateRender render = Renders.makeCreatureRender(color, job, isMale);
        return new Creature(name, attributes, render, color, job, isMale);
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public int getJob() {
        return job;
    }

    public String getJobString() {
        if (job == CreatureValues.Job.SCIENTIST) return "Scientist";
        if (job == CreatureValues.Job.BUILDER) return "Builder";
        else return "None";
    }

    public int getColor() {
        return color;
    }

    public boolean isMale() {
        return isMale;
    }

    public boolean isWalking() {
        return walking;
    }

    public boolean hasTask() {
        return hasTask;
    }

    @Override
    public void setPlayState(Play playState) {
        super.setPlayState(playState);
        ai.setPlayState(playState);
        {
        }
    }

    /// TESTING ///
    public void setPosition(float x, float y){
        pos.set(x, y);
    }

}
