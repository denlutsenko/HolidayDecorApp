package ua.com.hdcorp.hd.photo;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static ua.com.hdcorp.hd.utils.FileUtils.ROOT_FOLDER;
import static ua.com.hdcorp.hd.utils.FileUtils.ROOT_PATH;

@Component
public class PhotoHandler {
    private final int IMG_WIDTH = 100;
    private final int IMG_HEIGHT = 100;

    public PhotoHandler() {
    }

    public void savePhoto(MultipartFile file, Long categoryId) {
        if (file != null) {
            String newCategoryFolder = String.valueOf(categoryId);
            Path path = Paths.get(ROOT_PATH + File.separator + ROOT_FOLDER + File.separator + newCategoryFolder);
            try {
                if (!Files.exists(path)) createFolder(path);
                byte[] bytes = file.getBytes();
                File dir = new File(path.toString());
                String photoName = file.getOriginalFilename();
                File serverFile = new File(dir.getAbsolutePath() + File.separator + photoName);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                BufferedImage originalImage = ImageIO.read(serverFile);
                int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
                BufferedImage resizeImageJpg = resizeImage(originalImage, type);
                String extension = FilenameUtils.getExtension(serverFile.getAbsolutePath());
                ImageIO.write(resizeImageJpg, extension, serverFile);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }


    private void createFolder(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPhotoName(MultipartFile file) {
        return file.getOriginalFilename();
    }

    public String getPhotoLocationPath(Long categoryId) {
        String category = String.valueOf(categoryId);
        return Paths.get(ROOT_PATH + File.separator + ROOT_FOLDER + File.separator + category).toString();
    }


    //convert photo to byte array;
    public static byte[] getPhotoBytes(String path) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            InputStream inputImage = new FileInputStream(path);

            byte[] buffer = new byte[1024];
            int l = inputImage.read(buffer);
            while (l >= 0) {
                outputStream.write(buffer, 0, l);
                l = inputImage.read(buffer);
            }
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

}
