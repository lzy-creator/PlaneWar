package com.lzy.main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 作者：樱木老师
 * 敌人的飞机
 */
public class BadPlane {

    // 子弹图片
    private BufferedImage image;

    // 子弹出生的坐标
    private int x;
    private int y;

    private int width;
    private int height;

    // 随机数
    Random random = new Random();

    /**
     * 敌人飞机
     */
    public BadPlane(){
        try {
            int i = random.nextInt(400);
            // 判断
            if(i < 50){
                i = i + 50;
            }
            this.x = i;
            this.y = 50;
            // 图片
            image = ImageIO.read(Plane.class.getResource("/images/dj1.png"));
            this.width = 88;
            this.height = 72;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 子弹移动的方法
     */
    public int move() {
        this.y = y+5;
        return this.y;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
