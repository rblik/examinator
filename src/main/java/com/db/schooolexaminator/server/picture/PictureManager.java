package com.db.schooolexaminator.server.picture;

import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
public class PictureManager {


    BufferedImage originalImage;
    BufferedImage currentImage;


    int rows;
    int columns;

    String saveFileName = "C:\\Users\\JavaSchoolStudent\\Desktop\\SchoolExaminator\\src\\main\\resources\\pictures\\ok.jpg";

    List<Integer> blackPieces = new ArrayList<Integer>();
    Random r = new Random();


    @SneakyThrows
    public PictureManager(String fileName, int rows, int columns) {
        File imageFile = new File("C:\\Users\\JavaSchoolStudent\\Desktop\\SchoolExaminator\\src\\main\\resources\\pictures\\picture.jpg");
        originalImage = ImageIO.read(imageFile);
        File blackFile = new File("C:\\Users\\JavaSchoolStudent\\Desktop\\SchoolExaminator\\src\\main\\resources\\pictures\\black.jpg");
        currentImage = ImageIO.read(blackFile);
        this.columns = columns;
        this.rows = rows;
        for (int i = 0; i < columns * rows; i++) {
            blackPieces.add(i);
        }
    }


    @SneakyThrows
    public void openPiece() {
        int ind = r.nextInt(blackPieces.size());
        Integer peaceNumber = blackPieces.get(ind);
        blackPieces.remove(ind);
        drawPeace(peaceNumber);
        ImageIO.write(currentImage, "jpg", new File(saveFileName));
    }

    private void drawPeace(Integer peaceNumber) {
        Graphics2D graph = currentImage.createGraphics();
        int numRow = peaceNumber / columns;
        int numCol = peaceNumber % rows;
        int height = currentImage.getHeight() / rows;
        int width = currentImage.getWidth() / columns;
        graph.drawImage(originalImage, numCol * width, numRow * height, (numCol + 1) * width, (numRow + 1) * height,
                numCol * width, numRow * height, (numCol + 1) * width, (numRow + 1) * height, new ImageObserver() {
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        System.out.println(infoflags + " " + x + " " + y + " " + width + " " + height);
                        return true;
                    }
                });

    }

    public String getImage() {
        return saveFileName;
    }

}
