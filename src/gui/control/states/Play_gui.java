package gui.control.states;

import core.Core;
import core.gamestates.Play;
import core.rendering.SwapRender;
import core.rendering.Texture;
import data.*;
import entities.Player;
import entities.creatures.Attributes;
import entities.creatures.Creature;
import entities.creatures.CreatureValues;
import gui.control.GUIMultiState;
import gui.control.GUISingleState;
import gui.elements.buttons.DoublePNGButton;
import gui.elements.non_interfaceable.Tag;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;
import util.vectors.vec2;

import java.awt.*;


/**
 * Created by Francis O'Brien - 3/9/2015 - 4:13 AM
 */

public class Play_gui extends GUIMultiState {
    private Player player;
    private Play playState;
    



    /// SUB STATE IDs ///
    public static final int PLAY_MAIN = 0;
    public static final int CREATURE_INFO = 1;
    public static final int BUILD = 2;
    public static final int CHILDREN = 3;
    public static final int TECH = 4;
    public static final int JOB_ST = 5;

    public static CreatureInfo_GUI creature_state;
    public static Job_GUI job_state;
    /// END SUB STATE IDs ///

    /// Button IDs ///
    private final int BUILD_BUTTON = 0;
    private final int TECH_BUTTON = 1;
    /// END BUTTON IDs ///

    // Graphics //
    public static java.awt.Font fontb18 = new java.awt.Font("Consolas", java.awt.Font.BOLD, 18);
    public static TrueTypeFont bold_18;

    public static java.awt.Font fontp16 = new java.awt.Font("Consolas", Font.PLAIN, 16);
    public static TrueTypeFont stn_16;

    public static java.awt.Font fontp12 = new java.awt.Font("Consolas", Font.PLAIN, 12);
    public static TrueTypeFont stn_12;

    private SwapRender play_pause;

    private static Color TEAL = new Color(90, 225, 184);
    private static Color LIGHT_GREY = new Color(155, 155, 155);
    private static Color LIGHT_GREY2 = new Color(96, 96, 96);
    private static Color DARK_GREY = new Color(48, 48, 48);
    private static Color BLUE = new Color(27, 117, 188);
    private static Color PURPLE = new Color(167, 127, 255);
    // End Graphics //

    public Play_gui(int id, Player player, SwapRender play_pause, Play playState) {
        super(id);
    //TODO ADD MAP
        this.player = player;
        this.playState = playState;
        this.play_pause = play_pause;
        creature_state = new CreatureInfo_GUI(CREATURE_INFO);
        job_state = new Job_GUI(JOB_ST);
    }

    @Override
    public void init() {
        addElement(new Tag(new vec2(0, Core.HEIGHT - 144), Renders.PLAY_MENU_BACKGROUND, null));
        addElement(new DoublePNGButton(BUILD_BUTTON, new vec2(Core.WIDTH - Renders.BUILD_IDLE.getWidth() - 20, Core.HEIGHT - 132), Renders.BUILD_IDLE, Renders.BUILD_MO, Renders.BUILD_MO));
        addElement(new DoublePNGButton(TECH_BUTTON, new vec2(Core.WIDTH - Renders.TECH_IDLE.getWidth() - 20, Core.HEIGHT - 72), Renders.TECH_IDLE, Renders.TECH_MO, Renders.TECH_MO));
        addElement(new Tag(new vec2(12, Core.HEIGHT - 27), play_pause, null));
        addElement(new Tag(new vec2(225, Core.HEIGHT - 35), Renders.CREATURE_ICON, null));
        addElement(new Tag(new vec2(325, Core.HEIGHT - 33), Renders.CREDIT_ICON, null));

        addSubState(PLAY_MAIN, new MainPlay_GUI(PLAY_MAIN));
        addSubState(CREATURE_INFO, creature_state);
        addSubState(BUILD, new Build_GUI(BUILD));
        addSubState(CHILDREN, new ChildrenPopup_GUI(CHILDREN));
        addSubState(TECH, new Tech_GUI(TECH));
        addSubState(JOB_ST, job_state);

        bold_18 = new TrueTypeFont(fontb18, true);
        stn_16 = new TrueTypeFont(fontp16, true);
        stn_12 = new TrueTypeFont(fontp12, true);

    }

    @Override
    public void enter() {
        enterState(PLAY_MAIN);
    }

    @Override
    public void exit() {

    }

    @Override
    protected void buttonClicked(int buttonID, int mouseButton, int clickCount) {
        switch (buttonID){
            case BUILD_BUTTON:
                enterState(BUILD);
                break;
            case TECH_BUTTON:
                enterState(TECH);
                break;
        }
    }

    @Override
    public void textRender(Graphics graphics) {
        graphics.setFont(bold_18);
        graphics.setColor(LIGHT_GREY);
        renderBoxText("Year:" + GameVars.year, 25, 28, graphics);
        renderBoxText("Day:" + GameVars.day, 105, 28, graphics);
        renderBoxText(":" + player.getNumCreatures() + "/" + player.getCreatureCap(), 235, 28, graphics);
        renderBoxText(":" + player.getCurrency(), 335, 28, graphics);
        renderBoxText("Tech Progress:", 450, 28, graphics);
        graphics.fillRect(606, Core.HEIGHT - 28, 500, 20);
        graphics.setColor(BLUE);
        graphics.fillRect(606, Core.HEIGHT - 28, 500 * (player.getTech_progress()/player.getTech_goal()), 20);

        graphics.setColor(DARK_GREY);
        graphics.setFont(stn_12);
        for (String s : Messages.messages){

            graphics.drawString(s, 610, Core.HEIGHT - 50 - (15 * Messages.messages.indexOf(s)));
        }
        graphics.setFont(bold_18);
    }

    @Override
    protected void customUpdate() {

    }

    @Override
    protected void customDraw(Graphics graphics) {
        current_state.draw(graphics);
        graphics.setColor(TEAL);
    }

    @Override
    public void customMouseClicked(int mouseButton, int clickCount, boolean fromOverlay){
        Creature c = playState.checkCreatureClick(mouse.getPosition());
        if (c != null){
            creature_state.setPortrait(c);
            enterState(CREATURE_INFO);
        }

    }

    @Override
    public void keyPressed(int key, char c) {

    }



    private void renderBoxText(String s, float x, float y, Graphics g){
        g.drawString(s, x + 12, Core.HEIGHT - y);
    }


    public void childrenpopup(Attributes[] attr, int[] colors, boolean[] isMale) {
        ((ChildrenPopup_GUI) subStates.get(CHILDREN)).setValues(attr, colors, isMale);
        enterState(CHILDREN);
    }

    public static final int MORE_INFO = 0;

    private class Tech_GUI extends GUISingleState {

        public Tech_GUI(int id) {
            super(id);
        }

        @Override
        public void init() {
            addElement(new DoublePNGButton(MORE_INFO, new vec2(90, Core.HEIGHT - 120), Renders.GLASS_IDLE, Renders.GLASS_MO, Renders.GLASS_DWN));
        }

        @Override
        public void enter() {
            allUp();
            if (player.getCurrent_tech() != 99){
                getDPB(player.getCurrent_tech()).setDown();
            }
        }

        @Override
        public void exit() {

        }

        @Override
        protected void buttonClicked(int buttonID, int mouseButton, int clickCount) {
            allUp();
            getDPB(buttonID).setDown();
            player.setTech_goal(1000 * (player.getMoreInfoLevel() + 1));
            player.setTech_progress(0);
            player.setReasearching(true);
            player.setCurrent_tech(player.MORE_INFO);
        }

        @Override
        public void textRender(Graphics graphics) {
            graphics.drawString("More Genetic Info", 20, Core.HEIGHT - 80);
        }
    }

    private class Job_GUI extends GUISingleState {

        private int guix = 32;
        private int guiy = Core.HEIGHT - 112;

        int SCIENTIST = 0;
        int BUILDER = 1;
        private Creature creature;

        public Job_GUI(int id) {
            super(id);
        }

        @Override
        public void init() {
            addElement(new DoublePNGButton(SCIENTIST, new vec2(guix + 15, guiy), Renders.TECH_BUY_IDLE, Renders.TECH_BUY_MO, Renders.TECH_BUY_MO));
            addElement(new DoublePNGButton(BUILDER, new vec2(guix + 54 + 60, guiy), Renders.PICK_IDLE, Renders.PICK_MO, Renders.DRILL_BUY_MO));
        }

        @Override
        public void enter() {

        }

        @Override
        public void exit() {
            creature = null;
        }

        @Override
        protected void buttonClicked(int buttonID, int mouseButton, int clickCount) {
            if (buttonID == SCIENTIST){
                creature.setJob(CreatureValues.Job.SCIENTIST);
                creature_state.setPortrait(creature);
                enterState(CREATURE_INFO);
            }else if (buttonID == BUILDER){
                creature.setJob(CreatureValues.Job.BUILDER);
                creature_state.setPortrait(creature);
                enterState(CREATURE_INFO);
            }
        }

        @Override
        public void textRender(Graphics graphics) {
            graphics.drawString("Scientist", 20, Core.HEIGHT - 132);
            graphics.drawString("Builder", 54 + 44 + 35, Core.HEIGHT - 132);
            graphics.drawString("Miner", 38 + 54 + 45, Core.HEIGHT - 78);
        }

        public void setCreature(Creature creature) {
            this.creature = creature;
        }
    }

    private class ChildrenPopup_GUI extends GUISingleState {

        private Attributes[] attr;
        private int[] colors;
        private boolean[] isMale;
        private String[] names;
        private Texture[] portrait;
        private boolean keep[];

        public ChildrenPopup_GUI(int id) {
            super(id);
        }

        @Override
        public void init() {

        }
        private int confirm_id = 99;
        public void setValues(Attributes[] attr, int[] colors, boolean[] isMale){
            this.attr = attr;
            this.colors = colors;
            this.isMale = isMale;
            portrait = new Texture[isMale.length];
            names = new String[isMale.length];
            keep = new boolean[isMale.length];
            for (int i = 0; i < isMale.length; i++){
                keep[i] = true;
                names[i] = isMale[i] ? Names.getMale() : Names.getFemale();
                portrait[i] = Portraits.getPortrait(colors[i], CreatureValues.Job.NONE, isMale[i]);
                addElement(new DoublePNGButton(i, new vec2(left + width - 50, guiy + 35 + (100 * i)), Renders.KILL_IDLE, Renders.KILL_MO, Renders.KILL_DWN));
            }
            addElement(new DoublePNGButton(isMale.length, new vec2(left + width - 130, guiy + 305), Renders.CONFIRM_IDLE, Renders.CONFIRM_MO, Renders.CONFIRM_IDLE));
            confirm_id = isMale.length;
        }
        int width = 538;
        int left = (Core.WIDTH - width - 20) / 2;
        @Override
        public void draw(Graphics graphics) {
            if (exit) {
                exit = false;
                enterState(PLAY_MAIN);
            }
            graphics.setColor(DARK_GREY);
            graphics.fillRect(left, 100, width + 20, 400);
            graphics.setColor(LIGHT_GREY2);
            graphics.fillRect(left + 10, 110, width, 380);
            super.draw(graphics);
        }

        @Override
        public void enter() {

        }

        @Override
        public void exit() {
            buttons.clear();
        }
        boolean exit = false;
        @Override
        protected void buttonClicked(int buttonID, int mouseButton, int clickCount) {
            if (buttonID != confirm_id) {
                keep[buttonID] = !keep[buttonID];
                DoublePNGButton b = getDPB(buttonID);
                if (b.isDowned()) b.up();
                else b.setDown();
            } else  {
                int count = 0;
                int rem = playState.player.getCreatureCap() - playState.player.getNumCreatures();
                for (int i = 0; i < keep.length; i++){
                    if (keep[i]) count++;
                }

                if (rem < count) Messages.newMessage(count - rem + " over cap, kill more babies");
                else {
                    for (int i = 0; i < keep.length; i++) {
                        if (keep[i]) {
                            Messages.newMessage(names[i] + " was born!");
                            playState.creatureManager.createCreature(names[i], attr[i], colors[i], CreatureValues.Job.NONE, isMale[i], new vec2((19*64) + 20, (8 * 64) - (20 * i)));
                        }
                    }
                    exit = true;
                }
            }
        }

        private int guix = left + 30;
        private int guiy = 130;

        @Override
        public void textRender(Graphics graphics) {
            for (int i = 0; i < isMale.length; i++) {
                int offset = 100 * i;
                graphics.setColor(DARK_GREY);
                graphics.fillRect(guix - 10, guiy - 10 + offset, 62, 62);
                graphics.setColor(LIGHT_GREY2);
                graphics.fillRect(guix, guiy + offset, 42, 42);

                graphics.setColor(PURPLE);
                graphics.setFont(bold_18);

                graphics.drawString(names[i], guix + 65, guiy - 12 + offset);
                graphics.drawString("Age:" + 0, guix + 65, guiy + 11 + offset);
                graphics.drawString("Job:None", guix + 65, guiy + 34 + offset);

                graphics.drawString("Strength:" + attr[i].getStrenghtString(), guix + 210, guiy - 12 + offset);
                graphics.drawString("Longevity:" + attr[i].getLongevityString(), guix + 210, guiy + 11 + offset);
                graphics.drawString("Inteligence:" + attr[i].getInteligenceString(), guix + 210, guiy + 34 + offset);

                graphics.drawString("Speed:" + attr[i].getSpeedString(), guix + 370, guiy - 12 + offset);
                graphics.drawString("Fertility:" + attr[i].getFertilityString(), guix + 370, guiy + 11 + offset);

                portrait[i].draw(guix, guiy + offset);
            }
        }
    }

    private class MainPlay_GUI extends GUISingleState {
        // BUTTON IDs //

        public MainPlay_GUI(int id) {
            super(id);
        }

        public void init() {

        }
        public void enter() {

        }
        public void exit() {

        }
        protected void buttonClicked(int buttonID, int mouseButton, int clickCount) {

        }
        public void textRender(Graphics graphics) {


        }
    }

    private class CreatureInfo_GUI extends GUISingleState {

        private int guix = 32;
        private int guiy = Core.HEIGHT - 112;

        private int age;
        private String name;
        private String job;
        private Texture portrait;
        private Attributes attributes;
        private Creature creature;

        private boolean hasJob = false;
        private boolean isMale = false;

        // BUTTON IDs //
        public static final int JOB = 0;
        public static final int MATE = 1;

        public CreatureInfo_GUI(int id) {
            super(id);
        }

        public void setPortrait(Creature c){
            this.portrait = Portraits.getPortrait(c.getColor(), c.getJob(), c.isMale());
            this.creature = c;
            attributes = c.getAttributes();
            name = c.getName();
            age = c.getAge();
            job = c.getJobString();
            isMale = c.isMale();
            hasJob = c.getJob() != CreatureValues.Job.NONE;
        }

        public void init() {
            addElement(new DoublePNGButton(JOB, new vec2(560, Core.HEIGHT - 127), Renders.CREATURE_JOB_IDLE, Renders.CREATURE_JOB_MO, Renders.CREATURE_JOB_GREY));
            addElement(new DoublePNGButton(MATE, new vec2(560, Core.HEIGHT - 74), Renders.CREATURE_MATE_IDLE, Renders.CREATURE_MATE_MO, Renders.CREATURE_MATE_GREY));
        }

        public void enter() {
            if (hasJob) {
                getDPB(JOB).setDown();
                getDPB(MATE).setDown();
            }
            else if (isMale && playState.mating_building.hasMale()) getDPB(MATE).setDown();
            else if (!isMale && playState.mating_building.hasFemale()) getDPB(MATE).setDown();
        }

        public void exit() {
            allUp();
        }

        protected void buttonClicked(int buttonID, int mouseButton, int clickCount) {
            switch (buttonID) {
                case JOB:
                    if (getDPB(JOB).isDowned()) break;
                    job_state.setCreature(creature);
                    enterState(JOB_ST);
                    break;
                case MATE:
                    if (getDPB(MATE).isDowned()) break;
                    creature.mate(playState.mating_building);
                    break;
            }
        }


        public void textRender(Graphics graphics) {
            graphics.setColor(DARK_GREY);
            graphics.fillRect(guix - 10, guiy - 10, 62, 62);
            graphics.setColor(LIGHT_GREY2);
            graphics.fillRect(guix, guiy, 42, 42);

            graphics.setColor(PURPLE);

            graphics.drawString(name, guix + 65, guiy - 12);
            graphics.drawString("Age:" + age, guix + 65, guiy + 11);
            graphics.drawString("Job:" + job, guix + 65, guiy + 34);

            graphics.drawString("Strength:" + attributes.getStrenghtString(), guix + 210, guiy - 12);
            graphics.drawString("Longevity:" + attributes.getLongevityString(), guix + 210, guiy + 11);
            graphics.drawString("Inteligence:" + attributes.getInteligenceString(), guix + 210, guiy + 34);

            graphics.drawString("Speed:" + attributes.getSpeedString(), guix + 370, guiy - 12);
            graphics.drawString("Fertility:" + attributes.getFertilityString(), guix + 370, guiy + 11);

            portrait.draw(guix, guiy);

        }
    }

    private class Build_GUI extends GUISingleState {

        private int guix = 32;
        private int guiy = Core.HEIGHT - 112;

        // BUTTON IDs + Mouse States //
        private final int IDLE = 99;
        private final int TECH = 0;
        private final int DRILL = 1;

        private int build_state = IDLE;

        public Build_GUI(int id) {
            super(id);
        }

        public void init() {
            addElement(new DoublePNGButton(TECH, new vec2(guix, guiy), Renders.TECH_BUY_IDLE, Renders.TECH_BUY_MO, Renders.TECH_BUY_MO));
            addElement(new DoublePNGButton(DRILL, new vec2(guix + 34 + 20, guiy), Renders.DRILL_BUY_IDLE, Renders.DRILL_BUY_MO, Renders.DRILL_BUY_MO));
        }

        @Override
        protected void customMouseClicked(int mouseButton, int clickCount, boolean fromOverlay) {
            if (build_state != IDLE) {
                if (mouseButton == Input.MOUSE_RIGHT_BUTTON) {
                    resetState();
                } else if (build_state != IDLE && mouseButton == Input.MOUSE_LEFT_BUTTON) {
                    if (!playState.map.validLocation(mouse) || player.getCurrency() < 100) {
                        resetState();
                    }
                    player.updateCurrency(-100);
                    switch (build_state) {
                        case TECH:
                            playState.buildTech(playState.map.getTilePos(mouse.getPosition()));
                            resetState();
                            break;
                        case DRILL:
                            playState.buildDrill(playState.map.getTilePos(mouse.getPosition()));
                            resetState();
                            break;
                    }
                }
            }
        }

        private void resetState(){
            allUp();
            playState.map.mouseoverState(false);
            build_state = IDLE;
        }

        public void enter() {
            resetState();
        }

        public void exit() {
            parent.getDPB(BUILD_BUTTON).up();
            resetState();
        }
        protected void buttonClicked(int buttonID, int mouseButton, int clickCount) {
            switch (buttonID){
                case TECH:
                    playState.map.mouseoverState(true);
                    build_state = TECH;
                    allUp();
                    getDPB(TECH).setDown();
                    break;
                case DRILL:
                    playState.map.mouseoverState(true);
                    build_state = DRILL;
                    allUp();
                    getDPB(DRILL).setDown();
                    break;
            }
        }
        public void textRender(Graphics graphics) {
            graphics.drawString("Lab", 34, Core.HEIGHT - 132);
            graphics.drawString("100C", 28, Core.HEIGHT - 78);
            graphics.drawString("Drill", 34 + 34 + 15, Core.HEIGHT - 132);
            graphics.drawString("100C", 28 + 34 + 20, Core.HEIGHT - 78);


        }
    }
}
