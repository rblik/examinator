package com.db.schooolexaminator.services;

import com.db.schooolexaminator.model.Picture;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Blik on 03/13/2017.
 */
public interface PictureService {

    Picture getByConf(Integer confId);

    void save(Picture picture);

    @SneakyThrows
    static Picture resizeImage(Picture picture) {
        BufferedImage read = toImage(picture);

        int type = read.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : read.getType();
        BufferedImage resizedImage = new BufferedImage(1920, 1440, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(read, 0, 0, 1920, 1440, null);
        g.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "jpg", baos);
        baos.flush();
        picture.setContent(baos.toByteArray());
        baos.close();
        return picture;
    }

    @SneakyThrows
    static BufferedImage toImage(Picture picture) {
        byte[] content = picture.getContent();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content);
        return ImageIO.read(byteArrayInputStream);
    }
}
