package com.epam.university.java.core.task054;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Task054Impl implements Task054 {

    @Override
    public BufferedImage grayscaleFilter(String inputFilePath, String outputFilePath) {
        BufferedImage result = null;
        try {
            File inputFile = new File(inputFilePath);
            File outputFile = new File(outputFilePath);
            BufferedImage image = ImageIO.read(inputFile);
            result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    Color color = new Color(image.getRGB(x, y));
                    int blue = color.getBlue();
                    int green = color.getGreen();
                    int red = color.getRed();
                    int grey = (int) (red * 0.299 + green * 0.587 + blue * 0.114);
                    Color newColor = new Color(grey, grey, grey);
                    result.setRGB(x, y, newColor.getRGB());
                }
            }
            ImageIO.write(result, "jpg", outputFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public BufferedImage sepiaFilter(String inputFilePath, String outputFilePath) {
        BufferedImage result = null;
        try {
            File inputFile = new File(inputFilePath);
            File outputFile = new File(outputFilePath);
            BufferedImage image = ImageIO.read(inputFile);
            result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    Color color = new Color(image.getRGB(x, y));
                    int blue = color.getBlue();
                    int green = color.getGreen();
                    int red = color.getRed();
                    int newRed = (int) (0.393 * red + 0.769 * green + 0.189 * blue);
                    int newGreen = (int) (0.349 * red + 0.686 * green + 0.168 * blue);
                    int newBlue = (int) (0.272 * red + 0.534 * green + 0.131 * blue);
                    if (newRed > 255) {
                        newRed = 255;
                    }
                    if (newGreen > 255) {
                        newGreen = 255;
                    }
                    if (newBlue > 255) {
                        newBlue = 255;
                    }
                    Color newColor = new Color(newRed, newGreen, newBlue);
                    result.setRGB(x, y, newColor.getRGB());
                }
            }
            ImageIO.write(result, "jpg", outputFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public BufferedImage reflectFilter(String inputFilePath, String outputFilePath) {
        BufferedImage result = null;
        try {
            File inputFile = new File(inputFilePath);
            File outputFile = new File(outputFilePath);
            BufferedImage image = ImageIO.read(inputFile);
            result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
            int width = image.getWidth();
            int height = image.getHeight();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    result.setRGB(width - 1 - x, y, image.getRGB(x, y));
                }
            }
            ImageIO.write(result, "jpg", outputFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public BufferedImage originalImage(String inputFilePath) {
        BufferedImage result = null;
        try {
            File inputFile = new File(inputFilePath);
            result = ImageIO.read(inputFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public int getRed(int pixel) {
        int red = (pixel & 0x00ff0000) >> 16;
        return red;
    }

    @Override
    public int getGreen(int pixel) {
        int green = (pixel & 0x0000ff00) >> 8;
        return green;
    }

    @Override
    public int getBlue(int pixel) {
        int blue = pixel & 0x000000ff;
        return blue;
    }
}
