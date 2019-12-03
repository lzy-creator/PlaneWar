package com.lzy.main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 作者：樱木老师
 * 英雄机
 */
public class Plane {

    // 飞机图片
    private BufferedImage image;
    // 出生的x和y的位置坐标
    private int x;
    private int y;

    // 飞机的宽度和高度
    private int width;
    private int height;

    // 构造方法初始化飞机
    public Plane(){
        try {
            // 读取飞机的图片
            image = ImageIO.read(Plane.class.getResource("/images/myplane.png"));
            // 设置默认的出生位置
            x = 180;
            y = 450;
            this.width = 128;
            this.height = 128;
        } catch (IOException e) {
            e.printStackTrace();
        }
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
