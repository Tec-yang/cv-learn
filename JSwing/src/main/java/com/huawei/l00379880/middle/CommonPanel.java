package com.huawei.l00379880.middle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/***********************************************************
 * @Description : 测试本教程各课的通用面板
 * @author      : 梁山广
 * @date        : 2017/11/18 16:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class CommonPanel extends JComponent implements ActionListener {
    private static final String ROOT_PATH = "D:\\l00379880\\GithubProjects\\images\\";
    private BufferedImage image;
    private JButton lesson03Btn;
    private JButton lesson04Btn;
    private JButton saveBtn;

    public CommonPanel(BufferedImage image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (image != null) {
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 第三课图像处理:target.png(拉莫斯防守伊瓜因那张)
        if (e.getSource() == lesson03Btn) {
            Lesson03PixelOperation.process(image);
        }
        if (e.getSource() == lesson04Btn) {
            Lesson04PixelStatistic.process(image);
        }
        if (e.getSource() == saveBtn) {
            CommonMethods.save(image, ROOT_PATH + "middle\\target_result.png");
        }
        this.repaint();
    }

    public JButton getLesson03Btn() {
        lesson03Btn = new JButton("3像素读写");
        lesson03Btn.addActionListener(this);
        return lesson03Btn;
    }

    public JButton getLesson04Btn() {
        lesson04Btn = new JButton("4像素统计");
        lesson04Btn.addActionListener(this);
        return lesson04Btn;
    }

    public JButton getSaveBtn() {
        saveBtn = new JButton("保存");
        saveBtn.addActionListener(this);
        return saveBtn;
    }


    public static void main(String[] args) throws IOException {
        File file = new File(ROOT_PATH + "target.png");
        BufferedImage image = ImageIO.read(file);
        JFrame frame = new JFrame("图像的基本操作");
        // 添加图片到面板
        CommonPanel panel = new CommonPanel(image);
        frame.add(panel, BorderLayout.CENTER);
        // 添加按钮面板,添加各节课的处理事件
        JPanel jPanel = new JPanel();
        jPanel.add(panel.getLesson03Btn());
        jPanel.add(panel.getLesson04Btn());
        jPanel.add(panel.getSaveBtn());
        frame.add(jPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(image.getWidth() + 16, image.getHeight() + 38);
        frame.setVisible(true);
    }

}