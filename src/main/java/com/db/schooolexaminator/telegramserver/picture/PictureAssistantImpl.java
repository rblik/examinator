package com.db.schooolexaminator.telegramserver.picture;

import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.db.schooolexaminator.telegramserver.picture.PictureManager.pic_black;
import static com.db.schooolexaminator.telegramserver.picture.PictureManager.pic_default;

/**
 * Created by JavaSchoolStudent on 02.09.2016.
 */
public class PictureAssistantImpl implements PictureAssistant {
    private BufferedImage originalImage;
    private BufferedImage currentImage;

    PictureManager pictureManager;


    private int rows;
    private int columns;

    private String fileNameToSave;

    private List<Integer> blackPieces = new ArrayList<Integer>();
    private Random r = new Random();

    public PictureAssistantImpl(PictureManager pictureManager, BufferedImage image, Integer pupilId, int rows, int columns) {
        this.columns = columns;
        this.rows = rows;
        for (int i = 0; i < columns * rows; i++) {
            blackPieces.add(i);
        }
        fileNameToSave = "pic" + pupilId + ".jpg";
        this.pictureManager = pictureManager;

        if (image!=null) {
                originalImage = image;
        } else {
            try {
                originalImage = ImageIO.read(new File(pictureManager.defaultPictureFileName(pic_default)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            currentImage = ImageIO.read(new File(pictureManager.defaultPictureFileName(pic_black)));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
