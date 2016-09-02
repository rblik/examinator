package com.db.schooolexaminator.telegramserver.picture;

import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by JavaSchoolStudent on 02.09.2016.
 */
public class PictureAssistantImpl implements PictureAssistant {
    BufferedImage originalImage;
    BufferedImage currentImage;

    PictureManager pictureManager;


    private int rows;
    private int columns;

    private String fileNameToSave;

    List<Integer> blackPieces = new ArrayList<Integer>();
    Random r = new Random();

    @SneakyThrows
    public PictureAssistantImpl(PictureManager pictureManager, String fileName, Integer pupilId, int rows, int columns) {
        this.columns = columns;
        this.rows = rows;
        for (int i = 0; i < columns * rows; i++) {
            blackPieces.add(i);
        }
        fileNameToSave = "pic" + pupilId + ".jpg";
        this.pictureManager = pictureManager;

        File imageFile = new File(pictureManager.getPathToDir() + "\\" + fileName);
        System.out.println(pictureManager.getPathToDir() + "\\" + fileName);
        originalImage = ImageIO.read(imageFile);
        File blackFile = new File(pictureManager.getPathToDir()+ "\\" + "black.jpg");
        currentImage = ImageIO.read(blackFile);
    }


    @SneakyThrows
    public void openPiece() {
        int ind = r.nextInt(blackPieces.size());
        Integer peaceNumber = blackPieces.get(ind);
        blackPieces.remove(ind);
        drawPeace(peaceNumber);
        ImageIO.write(currentImage, "jpg", new File(fileNameToSave));
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

    public String getImageFileName() {
        System.out.println(fileNameToSave);
        return fileNameToSave;
    }
}
