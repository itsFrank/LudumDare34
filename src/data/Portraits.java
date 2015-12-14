package data;

import core.rendering.Texture;
import entities.creatures.CreatureValues;
import javafx.util.Pair;

import java.util.HashMap;

/**
 * Created by Francis O'Brien - 12/13/2015 - 2:01 AM
 */

public class Portraits {

    public static HashMap<Integer, HashMap<Integer, Pair<Texture, Texture>>> portraits = new HashMap<>();

    // CYAN //
    public static final Texture CYAN = new Texture("/res/gui/portraits/cyan.png");
    public static final Texture CYAN_FEMALE = new Texture("/res/gui/portraits/cyan_female.png");

    public static final Texture CYAN_SCIENTIST = new Texture("/res/gui/portraits/cyan_scientist.png");
    public static final Texture CYAN_SCIENTIST_FEMALE = new Texture("/res/gui/portraits/cyan_scientist_female.png");

    public static final Texture CYAN_BUILDER = new Texture("/res/gui/portraits/cyan_builder.png");
    public static final Texture CYAN_BUILDER_FEMALE = new Texture("/res/gui/portraits/cyan_builder_female.png");

    static {
        HashMap<Integer, Pair<Texture, Texture>> CYAN_map = new HashMap<>();
        CYAN_map.put(CreatureValues.Job.NONE, new Pair<>(CYAN, CYAN_FEMALE));
        CYAN_map.put(CreatureValues.Job.SCIENTIST, new Pair<>(CYAN_SCIENTIST, CYAN_SCIENTIST_FEMALE));
        CYAN_map.put(CreatureValues.Job.BUILDER, new Pair<>(CYAN_BUILDER, CYAN_BUILDER_FEMALE));
        portraits.put(CreatureValues.Color.CYAN, CYAN_map);
    }

    // GREEN //
    public static final Texture GREEN = new Texture("/res/gui/portraits/green.png");
    public static final Texture GREEN_FEMALE = new Texture("/res/gui/portraits/green_female.png");

    public static final Texture GREEN_SCIENTIST = new Texture("/res/gui/portraits/green_scientist.png");
    public static final Texture GREEN_SCIENTIST_FEMALE = new Texture("/res/gui/portraits/green_scientist_female.png");

    public static final Texture GREEN_BUILDER = new Texture("/res/gui/portraits/green_builder.png");
    public static final Texture GREEN_BUILDER_FEMALE = new Texture("/res/gui/portraits/green_builder_female.png");

    static {
        HashMap<Integer, Pair<Texture, Texture>> GREEN_map = new HashMap<>();
        GREEN_map.put(CreatureValues.Job.NONE, new Pair<>(GREEN, GREEN_FEMALE));
        GREEN_map.put(CreatureValues.Job.SCIENTIST, new Pair<>(GREEN_SCIENTIST, GREEN_SCIENTIST_FEMALE));
        GREEN_map.put(CreatureValues.Job.BUILDER, new Pair<>(GREEN_BUILDER, GREEN_BUILDER_FEMALE));
        portraits.put(CreatureValues.Color.GREEN, GREEN_map);
    }

    // ORANGE //
    public static final Texture ORANGE = new Texture("/res/gui/portraits/orange.png");
    public static final Texture ORANGE_FEMALE = new Texture("/res/gui/portraits/orange_female.png");

    public static final Texture ORANGE_SCIENTIST = new Texture("/res/gui/portraits/orange_scientist.png");
    public static final Texture ORANGE_SCIENTIST_FEMALE = new Texture("/res/gui/portraits/orange_scientist_female.png");

    public static final Texture ORANGE_BUILDER = new Texture("/res/gui/portraits/orange_builder.png");
    public static final Texture ORANGE_BUILDER_FEMALE = new Texture("/res/gui/portraits/orange_builder_female.png");

    static {
        HashMap<Integer, Pair<Texture, Texture>> ORANGE_map = new HashMap<>();
        ORANGE_map.put(CreatureValues.Job.NONE, new Pair<>(ORANGE, ORANGE_FEMALE));
        ORANGE_map.put(CreatureValues.Job.SCIENTIST, new Pair<>(ORANGE_SCIENTIST, ORANGE_SCIENTIST_FEMALE));
        ORANGE_map.put(CreatureValues.Job.BUILDER, new Pair<>(ORANGE_BUILDER, ORANGE_BUILDER_FEMALE));
        portraits.put(CreatureValues.Color.ORANGE, ORANGE_map);
    }

    // PURPLE //
    public static final Texture PURPLE = new Texture("/res/gui/portraits/purple.png");
    public static final Texture PURPLE_FEMALE = new Texture("/res/gui/portraits/purple_female.png");

    public static final Texture PURPLE_SCIENTIST = new Texture("/res/gui/portraits/purple_scientist.png");
    public static final Texture PURPLE_SCIENTIST_FEMALE = new Texture("/res/gui/portraits/purple_scientist_female.png");

    public static final Texture PURPLE_BUILDER = new Texture("/res/gui/portraits/purple_builder.png");
    public static final Texture PURPLE_BUILDER_FEMALE = new Texture("/res/gui/portraits/purple_builder_female.png");

    static {
        HashMap<Integer, Pair<Texture, Texture>> PURPLE_map = new HashMap<>();
        PURPLE_map.put(CreatureValues.Job.NONE, new Pair<>(PURPLE, PURPLE_FEMALE));
        PURPLE_map.put(CreatureValues.Job.SCIENTIST, new Pair<>(PURPLE_SCIENTIST, PURPLE_SCIENTIST_FEMALE));
        PURPLE_map.put(CreatureValues.Job.BUILDER, new Pair<>(PURPLE_BUILDER, PURPLE_BUILDER_FEMALE));
        portraits.put(CreatureValues.Color.PURPLE, PURPLE_map);
    }

    public static Texture getPortrait(int color, int job, boolean isMale){
        if (isMale){
            return portraits.get(color).get(job).getKey();
        } else {
            return portraits.get(color).get(job).getValue();
        }
    }

}
