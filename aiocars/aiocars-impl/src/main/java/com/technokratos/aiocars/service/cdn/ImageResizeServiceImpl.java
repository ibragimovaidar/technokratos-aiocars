package com.technokratos.aiocars.service.cdn;

import com.technokratos.aiocars.exception.AiocarsServiceException;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.imgscalr.Scalr;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageResizeServiceImpl implements ImageResizeService {

    @Override
    public InputStream resize(InputStream inputStream, int targetHeight, int targetWidth) {
        try {
            BufferedImage originalImage = ImageIO.read(inputStream);
            BufferedImage resizedImage =
                    Scalr.resize(originalImage, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, targetWidth, targetHeight, Scalr.OP_ANTIALIAS);
            @Cleanup ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "jpg", byteArrayOutputStream);
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            log.error("Image resize error", e);
            throw new AiocarsServiceException("Image resize error", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
