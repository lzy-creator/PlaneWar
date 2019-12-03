package com.lzy.utils;

import javax.swing.*;

/**
 * 作者：樱木老师
 */
public class FrameUtils {

    public static void init(JFrame frame,int width,int height){
        // 设置大小和位置
        frame.setSize(width,height);
        // 居中显示
        frame.setLocationRelativeTo(null);
        // 设置关闭
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
