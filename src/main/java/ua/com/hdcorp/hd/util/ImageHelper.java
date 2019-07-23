package ua.com.hdcorp.hd.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import static ua.com.hdcorp.hd.util.Constants.*;

@Component
public class ImageHelper {
    public void saveImage(MultipartFile file, Long postcardTypeId) {

        if (!StringUtils.isEmpty(file) && !StringUtils.isEmpty(file.getOriginalFilename())) {
            Path path = concatenateFolderPath(postcardTypeId);
            try {
                if (!Files.exists(path)) {
                    createFolder(path);
                }
                byte[] bytes = file.getBytes();
                File dir = new File(path.toString());
                File imageToSave = new File(dir.getAbsolutePath().concat(File.separator).concat(file.getOriginalFilename()));

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(imageToSave));
                stream.write(bytes);
                stream.close();

                BufferedImage originalImage = ImageIO.read(imageToSave);
                int originalImageType = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
                BufferedImage resizedImageJpg = resizeImage(originalImage, originalImageType);
                String extension = FilenameUtils.getExtension(imageToSave.getAbsolutePath());
                ImageIO.write(resizedImageJpg, extension, imageToSave);

            } catch (IOException e) {
            }
        }
    }

    private void createFolder(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
        }
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int originalImageType) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, originalImageType);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }

    public Path concatenateFolderPath(Long postcardTypeId) {
        return Paths.get(WINDOWS_ROOT_PATH
                .concat(File.separator)
                .concat(CONTENT_FOLDER)
                .concat(File.separator)
                .concat(IMG_FOLDER)
                .concat(String.valueOf(postcardTypeId)));
    }

    public String getImageName(MultipartFile file) {
        return file == null ? null : file.getOriginalFilename();
    }
}