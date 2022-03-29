package com.ml.primerainfanciarest.helpers;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Interfaz que se encarga del manejo de multimedia (imágenes, audios y videos)
 */
public interface FileHelper {
    final String MAIN_PATH = "src//main//resources//static/";

    /**
     * Guarda el File recibido en el path indicado
     * Devuelve la imagen/audio/video como un conjunto de bytes
     * @param file
     * @param path
     * @return
     */
    public static byte[] saveFile(MultipartFile file, String path) {
        Path directory = Paths.get(MAIN_PATH + path);
        String absolutePath = directory.toFile().getAbsolutePath();
        try {
            byte[] byteFile = file.getBytes();
            Path imageCompletePath = Paths.get(absolutePath + "//" + file.getOriginalFilename());
            Files.write(imageCompletePath, byteFile);
            return byteFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Comprime la imagen/audio/video para ser guardada en la BDD
     * @param data
     * @return
     */
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        return outputStream.toByteArray();
    }

    /**
     * descomprime la imagen/audio/video para ser enviada al cliente
     * @param data
     * @return
     */
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
