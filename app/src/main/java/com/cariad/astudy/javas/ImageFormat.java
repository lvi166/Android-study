package com.cariad.astudy.javas;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class ImageFormat {


    private Interpreter tflite;

    public ImageFormat(AssetManager assetManager, String modelPath) throws IOException {
        tflite = new Interpreter(loadModelFile(assetManager, modelPath));
    }

    private MappedByteBuffer loadModelFile(AssetManager assetManager, String modelPath) throws IOException {
        try (AssetFileDescriptor fileDescriptor = assetManager.openFd(modelPath);
             FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
             FileChannel fileChannel = inputStream.getChannel()) {
            return fileChannel.map(FileChannel.MapMode.READ_ONLY, fileDescriptor.getStartOffset(), fileDescriptor.getDeclaredLength());
        }
    }

    public Bitmap process(Bitmap inputBitmap) {
        int width = inputBitmap.getWidth();
        int height = inputBitmap.getHeight();

        // 预处理：将 Bitmap 转换为浮点数数组
        float[][][][] input = bitmapToFloatArray(inputBitmap);
        float[][][][] output = new float[1][height][width][3];

        // 执行模型推理
        tflite.run(input, output);

        // 后处理：将浮点数组转换回 Bitmap
        return floatArrayToBitmap(output, width, height);
    }

    private float[][][][] bitmapToFloatArray(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float[][][][] result = new float[1][height][width][3];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = bitmap.getPixel(x, y);
                result[0][y][x][0] = (Color.red(pixel)) / 255.0f;
                result[0][y][x][1] = (Color.green(pixel)) / 255.0f;
                result[0][y][x][2] = (Color.blue(pixel)) / 255.0f;
            }
        }
        return result;
    }

    private Bitmap floatArrayToBitmap(float[][][][] array, int width, int height) {
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int r = (int) (array[0][y][x][0] * 255);
                int g = (int) (array[0][y][x][1] * 255);
                int b = (int) (array[0][y][x][2] * 255);
                output.setPixel(x, y, Color.rgb(r, g, b));
            }
        }
        return output;
    }



}
