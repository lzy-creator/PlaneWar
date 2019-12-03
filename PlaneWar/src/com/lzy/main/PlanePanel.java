package com.lzy.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 作者：樱木老师
 * 飞机的面板类
 */
public class PlanePanel extends JPanel implements Runnable,MouseMotionListener{

    // 窗口对象
    private JFrame frame;
    // 英雄机
    private Plane plane;
    // 用来存储子弹
    private List<Bullet> bulletList = new ArrayList<>();
    // 敌人飞机集合
    private List<BadPlane> badPlanes = new ArrayList<>();

    // 创建面板，存放背景图片
    public PlanePanel(JFrame frame){
        this.frame = frame;
        // 创建对象
        JLabel label = new JLabel(new ImageIcon(PlanePanel.class.getResource("/images/bg_01.jpg")));
        this.add(label);
        // 存放面板
        frame.add(this);
    }

    /**
     * 把英雄机画到面板上
     * @param plane
     */
    public void drawPlane(Plane plane) {
        this.plane = plane;
        // 画
        this.repaint();
    }

    /**
     * 画内容的方法
     * @param g
     */
    public void paint(Graphics g) {
        super.paint(g);
        if(plane != null){
            // 先画英雄机
            g.drawImage(plane.getImage(),plane.getX(),plane.getY(),null);
        }

        // 画子弹
        for (int i = 0; i < bulletList.size(); i++) {
            Bullet bullet = bulletList.get(i);
            // 让子弹动
            int y = bullet.move();

            // 消除效果
            boolean clear = bullet.clear(badPlanes);
            if(clear == true){
                bulletList.remove(bullet);
                sum++;
            }

            // 如果子弹的y距离已经是负数了，说明子弹已经到达了屏幕的边缘，清楚子弹
            if(y <= 0){
                bulletList.remove(bullet);
            }
            // 画上
            g.drawImage(bullet.getImage(),bullet.getX(),bullet.getY(),null);
        }

        // 再画敌人飞机
        for (int i = 0; i < badPlanes.size(); i++) {
            BadPlane badPlane = badPlanes.get(i);
            // 让飞机移动，获取到移动的y值
            int y = badPlane.move();
            if((y + badPlane.getHeight()) >= frame.getHeight() ){
                badPlanes.remove(badPlane);
            }
            g.drawImage(badPlane.getImage(),badPlane.getX(),badPlane.getY(),null);
        }

        // 画个数
        g.setColor(Color.pink);
        g.drawString("您打飞机的次数："+sum+"次",30,30);
        if(sum >= 10){
            g.drawString("老弟，身体确实不错啊！",30,50);
        }
    }

    /**
     * 射出子弹的方法
     */
    public void biubiu() {
        // 读取雪花的图片
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                // 创建子弹对象
                Bullet bullet = new Bullet(plane.getX(),plane.getY());
                // 子弹移动的方法
                bullet.move();
                // 把子弹存入到list集合
                bulletList.add(bullet);
            }
        };
        Timer timer = new Timer();
        // 开启定时任务
        timer.schedule(timerTask,0,500);
    }

    /**
     * 启动线程，开始效果
     */
    public void run() {
        // System.out.println("启动线程了...");
        // 创建子弹
        while(true){
            // 定时画面板
            this.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 画很多架敌人飞机
     */
    public void badPlanes() {
        // 读取雪花的图片
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                // 创建敌人飞机
                BadPlane badPlane = new BadPlane();
                badPlane.move();
                badPlanes.add(badPlane);
            }
        };
        Timer timer = new Timer();
        // 开启定时任务
        timer.schedule(timerTask,0,1000);
    }

    // 定义打掉飞机的个数
    int sum = 0;

    /**
     * 按住鼠标移动
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // 获取到移动的坐标
        int x = e.getX();
        // 给设计设置x位置
        plane.setX(x);
    }

}
