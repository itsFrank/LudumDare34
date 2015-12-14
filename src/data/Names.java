package data;

import util.fileIO.Reader;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Francis O'Brien - 12/13/2015 - 2:26 PM
 */

public class Names {

    public static ArrayList<String> MALE = getNames("res/names/male.txt");
    public static ArrayList<String> FEMALE = getNames("res/names/females.txt");

    public static Random r = new Random(System.currentTimeMillis());

    private static ArrayList<String> getNames(String path){
        ArrayList<String> lines = Reader.getStringLineArray(path);
        ArrayList<String> line = new ArrayList<>();
        for (String s : lines){
            line.add(s.split("\t")[0]);
        }
        return lines;
    }

    public static String getMale(){
        return MALE.get(r.nextInt(MALE.size()));
    }

    public static String getFemale(){
        return FEMALE.get(r.nextInt(FEMALE.size()));
    }
}
