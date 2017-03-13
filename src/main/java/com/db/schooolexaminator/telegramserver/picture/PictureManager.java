package com.db.schooolexaminator.telegramserver.picture;

import com.db.schooolexaminator.model.Configuration;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */

@org.springframework.stereotype.Component
@PropertySource("classpath:pictures.properties")
public class PictureManager {

    @Value("${EXAMINATOR_DATA_DIR}")
    @Getter
    @Setter
    private String persistentDir;

    @Value("${EXAMINATOR_HOMEDIR}")
    @Getter
    @Setter
    private String homeDir;

    protected static final String pic_black = "black";
    protected static final String pic_default = "picture";

    public void savePicture(Configuration conf, MultipartFile multipartFile) {

        if (multipartFile != null) {
            conf.setHasImage(true);
            String pathname = persistentDir + "/pictures/" + conf.getTitle() + "_" + conf.getEmails().get(0).getAddress() + ".jpg";
            supplyAsync(() -> savePicture(pathname, multipartFile));
        }
    }

    private Void savePicture(String pathname, MultipartFile multipartFile) {
        try {
            File file = new File(pathname);
            multipartFile.transferTo(file);
            BufferedImage read = ImageIO.read(file);
            int type = read.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : read.getType();
            BufferedImage bufferedImage = resizeImage(read, type);
            ImageIO.write(bufferedImage, "jpg", new File(pathname));
            return null;
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public String defaultPictureFileName(String pic) {
        return this.getHomeDir() + this.getPictureFileName(pic);
    }

    public String getPictureFileName(String configTitle) {
        return "/pictures/" + configTitle + ".jpg";
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(1920, 1440, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 1920, 1440, null);
        g.dispose();

        return resizedImage;
    }

}
