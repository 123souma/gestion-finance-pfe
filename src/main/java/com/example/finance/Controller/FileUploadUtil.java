package com.example.finance.Controller;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUploadUtil {

    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        File uploadDirFile = new File(uploadDir);

        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs(); // Crée le répertoire s'il n'existe pas
        }

        try (FileOutputStream outputStream = new FileOutputStream(uploadDir + "/" + fileName)) {
            FileCopyUtils.copy(multipartFile.getInputStream(), outputStream);
        }
    }
}
