package com.lzy.main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * 作者：樱木老师
 * 子弹
 */
public class Bullet {

    // 子弹图片
    private BufferedImage image;

    // 子弹出生的坐标
    private int x;
    private int y;

    private int width;
    private int height;

    /**
     * 子弹
     */
    public Bullet(int x,int y){
        try {
            this.x = x + 54;
            this.y = y;
            // 图片
            image = ImageIO.read(Plane.class.getResource("/images/bullet.png"));
            this.width = 18;
            this.height = 25;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 子弹移动的方法
     */
    public int move() {
        this.y = y-10;
        return this.y;
    }

    /**
     * 消除子弹和飞机的效果
     * @param badPlanes
     */
    public boolean clear(List<BadPlane> badPlanes) {
        // 遍历
        for (int i = 0; i < badPlanes.size(); i++) {
            BadPlane badPlane = badPlanes.get(i);
            // 判断横坐标和纵坐标
            int badx = badPlane.getX();
            int bady = badPlane.getY();

            int numx = badx - this.x;
            int numy = bady+badPlane.getHeight() - this.y;

            if(numx >=  -88 && numx <= 0 && numy > -10 && numy < 10){
                badPlanes.remove(badPlane);
                return true;
            }
        }
        return false;
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
