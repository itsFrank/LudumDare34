//package gui.control.overlays;
//
//import core.Core;
//import data.Textures;
//import entities.Player;
//import entities.towers.Tower;
//import gui.control.GUIOverlay;
//import gui.control.states.Play_gui;
//import gui.elements.buttons.DoublePNGButton;
//import map.Tile;
//import org.newdawn.slick.Graphics;
//import util.MouseWrapper;
//import util.vectors.vec2;
//
///**
// * Created by Francis O'Brien - 3/10/2015 - 9:00 AM
// */
//
//public class TowerMenuOverlay extends GUIOverlay {
//
//    private final int UPGRADE_ID = 1;
//    private final int SELL_ID = 0;
//
//    private Tower tower;
//    private vec2 towerCentre;
//    private Player player;
//    private Tile tile;
//    boolean hasUpgrade = true;
//
//    public TowerMenuOverlay(Play_gui parentState, MouseWrapper mouse, Tile tile, Tower tower, vec2 towerCentre, Player player) {
//        super(parentState, mouse);
//
//        this.tower = tower;
//        this.towerCentre = towerCentre;
//        this.player = player;
//        this.tile = tile;
//
//        if ((towerCentre.x - ((Textures.UPGRADE_IDLE.getWidth() / 2) - 60)) > Core.WIDTH){
//            addElement(new DoublePNGButton(SELL_ID, new vec2(towerCentre.x - ((Textures.UPGRADE_IDLE.getWidth() / 2)), towerCentre.y + (Textures.UPGRADE_IDLE.getHeight() / 2) + 80), Textures.SELL_IDLE, Textures.SELL_MO, null));
//        }else {
//            addElement(new DoublePNGButton(SELL_ID, new vec2(towerCentre.x - ((Textures.UPGRADE_IDLE.getWidth() / 2) - 60), towerCentre.y + (Textures.UPGRADE_IDLE.getHeight() / 2) + 20), Textures.SELL_IDLE, Textures.SELL_MO, null));
//        }
//
//        if (!tower.isMaxLevel()) {
//            if ((towerCentre.x - ((Textures.UPGRADE_IDLE.getWidth() / 2) + 60) < 0)){
//                addElement(new DoublePNGButton(UPGRADE_ID, new vec2(towerCentre.x - ((Textures.UPGRADE_IDLE.getWidth() / 2)), towerCentre.y + (Textures.UPGRADE_IDLE.getHeight() / 2) + 80), Textures.UPGRADE_IDLE, Textures.UPGRADE_MO, Textures.UPGRADE_ILLEGAL));
//            } else {
//                addElement(new DoublePNGButton(UPGRADE_ID, new vec2(towerCentre.x - ((Textures.UPGRADE_IDLE.getWidth() / 2) + 60), towerCentre.y + (Textures.UPGRADE_IDLE.getHeight() / 2) + 20), Textures.UPGRADE_IDLE, Textures.UPGRADE_MO, Textures.UPGRADE_ILLEGAL));
//            }
//        }else hasUpgrade = false;
//    }
//
//    @Override
//    public void init() {
//
//    }
//
//    @Override
//    public void customUpdate() {
//        if (hasUpgrade) {
//            if (player.getCurrency() < tower.getUpgradeCost()) ((DoublePNGButton) buttons.get(UPGRADE_ID)).setDown();
//            else ((DoublePNGButton) buttons.get(UPGRADE_ID)).up();
//        }
//    }
//
//    @Override
//    public void customDraw(Graphics graphics) {
//
//        graphics.drawString(tower.getInfo(), 750, 625);
//
//    }
//
//    @Override
//    public void customMouseClicked(int mouseButton, int clickCount) {
//    }
//
//    @Override
//    public void customClose() {
//    }
//
//    @Override
//    protected void buttonClicked(int buttonID, int mouseButton, int clickCount) {
//        switch (buttonID){
//            case UPGRADE_ID:
//                if (player.getCurrency() >= tower.getUpgradeCost()) {
//                    player.updateCurrency(-tower.getUpgradeCost());
//                    tower.upgrade();
//                }
//
//                close();
//                break;
//            case SELL_ID:
//                ((Play_gui)parentState).sellTower(tile);
//                close();
//                break;
//        }
//    }
//}
