package com.lzy.main;

import com.lzy.utils.FrameUtils;

import javax.swing.*;

/**
 * 作者：樱木老师
 * 打飞机的程序
 */
public class PlaneGame {

    public static void main(String[] args) {
        // 使用GUI的编程，昨天讲过，画QQ登录页面
        // 创建窗口对象
        JFrame frame = new JFrame("快乐打飞机");
        // 设置窗口的大小、位置
        FrameUtils.init(frame,500,600);

        // 创建新的对象，面板对象
        PlanePanel planePanel = new PlanePanel(frame);
        // 把面板存放在窗口上
        frame.add(planePanel);

        // 先画英雄机，封装了一个类，飞机树形全部都有值了
        Plane plane = new Plane();
        // 把英雄飞机画上去
        planePanel.drawPlane(plane);

        // 生成很多子弹，把子弹画到面板上
        planePanel.biubiu();

        // 敌人的飞机
        planePanel.badPlanes();

        // 启动线程，让子弹产生动画效果，启动线程。
        new Thread(planePanel).start();

        // 绑定鼠标事件
        planePanel.addMouseMotionListener(planePanel);

        // 显示窗口
        frame.setVisible(true);
    }

}
