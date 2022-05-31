package com.technokratos.aiocars.service.cdn;

import java.io.InputStream;

public interface ImageResizeService {

    InputStream resize(InputStream inputStream, int targetHeight, int targetWidth);
}
