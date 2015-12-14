package util.color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Francis O'Brien - 12/13/2015 - 7:16 AM
 */

public class PatternGenerator {

    public static void main(String[] args){
        for (int i = 0; i < 16; i++){
            makePNG(i);
        }
    }

    public static void makePNG(int num) {

        Random r = new Random(System.currentTimeMillis());

        int[] greens = {0x646464, 0x686868, 0x6b6b6b};

        int a = 0x783d01;
        int b = 0x7D4206;
        int c = 0x814707;
        int d = 0x743C01;

        BufferedImage image = new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);
        int[][] tiles = new int[8][8];

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                int ra = r.nextInt(3);
                tiles[i][j] = greens[ra];
// switch (ra){
//                    case 0:
//                        tiles[i][j] = a;
//                        break;
//                    case 1:
//                        tiles[i][j] = b;
//                        break;
//                    case 2:
//                        tiles[i][j] = c;
//                        break;
//                    case 3:
//                        tiles[i][j] = d;
//                        break;
//                }
            }
        }

        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                image.setRGB(i, j, tiles[i/8][j/8]);
            }
        }

        File output = new File("res/ground/grey_l_gen_" + num + ".png");
        try {
            ImageIO.write(image, "png", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
