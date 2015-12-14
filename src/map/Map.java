package map;

import data.Renders;
import entities.Player;
import org.newdawn.slick.Color;
import util.MouseWrapper;
import util.vectors.ivec2;
import util.vectors.vec2;

import java.util.Random;

/**
 * Created by Francis O'Brien - 12/13/2015 - 6:43 AM
 */

public class Map {

    private boolean mouseoverState = false;
    private Tile[][] tiles = new Tile[9][20];
    private Player player;
    private MouseWrapper mouse;

    public Map(MouseWrapper mouse, Player player){
        this.mouse = mouse;
        this.player = player;

        Random r = new Random(System.currentTimeMillis());
        for (int j = 0; j < 20; j++){
            for (int i = 0; i < 9; i++){
                if (i > 4 && j > 6 && j < 13){
                    tiles[i][j] = new Tile(new ivec2(j, i), Renders.DARK_GREY_GROUND[r.nextInt(16)]);
                    tiles[i][j].setBuildeable(false);
                } else {
                    tiles[i][j] = new Tile(new ivec2(j, i), Renders.LIGHT_GREY_GROUND[r.nextInt(16)]);
                }
            }
        }
    }

    private Color green_filter = new Color(90, 225, 184);
    private Color red_filter = new Color(239, 65, 61);

    public void draw(){
        int tmpi = Integer.MAX_VALUE;
        int tmpj = Integer.MAX_VALUE;
        Color tmpfilter = red_filter;

        for (int j = 0; j < 20; j++){
            for (int i = 0; i < 9; i++){
                Tile tile = tiles[i][j];
                if (mouseoverState && i == tmpi && j == tmpj) tiles[i][j].draw(tmpfilter);
                else if (mouseoverState && tile.box().isWithin(mouse.getPosition())){
                    tmpi = i;
                    tmpj = j+1;
                    if (j < 19){
                        if (tiles[i][j].isBuildeable() && tiles[i][j + 1].isBuildeable()){
                            if (player.getCurrency() < 100){
                                tiles[i][j].draw(red_filter);
                            } else {
                                tiles[i][j].draw(green_filter);
                                tmpfilter = green_filter;
                            }
                        }else {
                            tiles[i][j].draw(red_filter);
                        }
                    } else {
                        tiles[i][j].draw(red_filter);
                    }
                } else {
                    tiles[i][j].draw();
                }
            }
        }
    }

    public void mouseoverState(boolean state) {
        mouseoverState = state;
    }

    public boolean validLocation(MouseWrapper mouse) {
        int i = (int) (mouse.getPosition().y / 64);
        int j = (int) (mouse.getPosition().x / 64);
        if (i > 8 || j > 18) return false;
        if (tiles[i][j].isBuildeable() && tiles[i][j+1].isBuildeable()){
            return true;
        }
        return false;
    }

    public vec2 getTilePos(vec2 pos) {
        int i = (int) (pos.y / 64);
        int j = (int) (pos.x / 64);
        return tiles[i][j].getPos();
    }

    public void setUnbuildeable(vec2 pos) {
        int i = (int) (pos.y / 64);
        int j = (int) (pos.x / 64);
        tiles[i][j].setBuildeable(false);
        tiles[i][j+1].setBuildeable(false);
        boolean igt0 = i > 0;
        boolean ilt9 = i < 8;
        boolean jgt0 = j > 0;
        boolean jlt19 = j + 1 < 19;
        if (igt0){
            tiles[i - 1][j].setBuildeable(false);
            tiles[i - 1][j + 1].setBuildeable(false);
            if (jgt0) tiles[i - 1][j - 1].setBuildeable(false);
            if (jlt19) tiles[i - 1][j + 2].setBuildeable(false);
        }
        if (ilt9){
            tiles[i + 1][j].setBuildeable(false);
            tiles[i + 1][j + 1].setBuildeable(false);
            if (jgt0) tiles[i + 1][j - 1].setBuildeable(false);
            if (jlt19) tiles[i + 1][j + 2].setBuildeable(false);
        }
        if (jgt0){
            tiles[i][j - 1].setBuildeable(false);
        }
        if (jlt19){
            tiles[i][j + 2].setBuildeable(false);
        }
    }
}
