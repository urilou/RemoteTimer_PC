/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package udp_server;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Container;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.util.Enumeration;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author urilou
 */
class Udp_server extends JFrame {

    static final int PORT = 10010;
    static int netnum = 0;
    static String net;
    static String net0;
    static String net1;
    static String net2;
    static String net3;
    static String net4;
    static String net5;
    static String net6;
    static String net7;
    static String net8;
    static String net9;
    static boolean windowstate = true;

    public static void main(String args[]) throws Exception {
        Udp_server frame = new Udp_server("プレゼンタイマー");
        frame.setVisible(true);

////////////////////////////////////////////////////////////////////////////////
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            boolean runFlag = true;

            // ネットワークアドレスの取得
            Enumeration<NetworkInterface> enuIfs = NetworkInterface
                    .getNetworkInterfaces();
            if (null != enuIfs) {
                while (enuIfs.hasMoreElements()) {
                    NetworkInterface ni = (NetworkInterface) enuIfs
                            .nextElement();
                    Enumeration<InetAddress> enuAddrs = ni.getInetAddresses();
                    while (enuAddrs.hasMoreElements()) {
                        InetAddress in4 = (InetAddress) enuAddrs
                                .nextElement();
                        if (in4.getHostAddress().matches(".*fe80.*")) {
                        } else if (in4.getHostAddress().matches(".*:.*")) {
                        } else {
                            System.out.println("ネットワークアドレス:\t"
                                    + in4.getHostAddress());
                            net = in4.getHostAddress();
                            if (netnum == 0) {
                                net0 = net;
                            }
                            if (netnum == 1) {
                                net1 = net;
                            }
                            if (netnum == 2) {
                                net2 = net;
                            }
                            if (netnum == 3) {
                                net3 = net;
                            }
                            if (netnum == 4) {
                                net4 = net;
                            }
                            if (netnum == 5) {
                                net5 = net;
                            }
                            if (netnum == 6) {
                                net6 = net;
                            }
                            if (netnum == 7) {
                                net7 = net;
                            }
                            if (netnum == 8) {
                                net8 = net;
                            }
                            if (netnum == 9) {
                                net9 = net;
                            }
                            netnum = netnum + 1;
                        }
                    }
                }
            }

            while (runFlag) {
                System.out.println("待機中...");
                // 接続があるまでブロック
                Socket socket = serverSocket.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                String str;
                Robot robot = new Robot();

                while ((str = br.readLine()) != null) {
                    if ("test".equals(str)) {
                        if (windowstate) {
                            frame.setExtendedState(Frame.ICONIFIED);
                            windowstate = false;
                        } else {
                            frame.setExtendedState(Frame.NORMAL);
                            windowstate = true;
                        }
                    }

                    if ("powerpoint.page.next".equals(str)) {
                        robot.delay(100);
                        robot.keyPress(KeyEvent.VK_DOWN);
                        robot.keyRelease(KeyEvent.VK_DOWN);
                    }

                    if ("powerpoint.page.back".equals(str)) {
                        robot.delay(100);
                        robot.keyPress(KeyEvent.VK_UP);
                        robot.keyRelease(KeyEvent.VK_UP);
                    }

                    if ("windows.app.switch".equals(str)) {
                        robot.delay(100);
                        robot.keyPress(KeyEvent.VK_ALT);
                        robot.keyPress(KeyEvent.VK_TAB);
                        robot.delay(50);
                        robot.keyRelease(KeyEvent.VK_TAB);
                        robot.keyRelease(KeyEvent.VK_ALT);
                    }

                    if ("powerpoint.app.first".equals(str)) {
                        robot.delay(100);
                        robot.keyPress(KeyEvent.VK_F5);
                        robot.keyRelease(KeyEvent.VK_F5);
                    }

                    if ("windows.app.close".equals(str)) {
                        robot.delay(100);
                        robot.keyPress(KeyEvent.VK_ALT);
                        robot.keyPress(KeyEvent.VK_F4);
                        robot.delay(50);
                        robot.keyRelease(KeyEvent.VK_F4);
                        robot.keyRelease(KeyEvent.VK_ALT);
                    }

                    if ("powerpoint.app.current".equals(str)) {
                        robot.delay(100);
                        robot.keyPress(KeyEvent.VK_SHIFT);
                        robot.keyPress(KeyEvent.VK_F5);
                        robot.delay(50);
                        robot.keyRelease(KeyEvent.VK_F5);
                        robot.keyRelease(KeyEvent.VK_SHIFT);
                    }

                    System.out.println(str);

                    // exitという文字列を受け取ったら終了する
                    if ("exit".equals(str)) {
                        runFlag = false;
                    }
                }

                if (socket != null) {
                    socket.close();
                    socket = null;
                }
            }
        } catch (IOException e) {
        }

        if (serverSocket != null) {
            try {
                serverSocket.close();
                serverSocket = null;
            } catch (IOException e) {
            }
        }
////////////////////////////////////////////////////////////////////////////////
    }

    Udp_server(String title) throws AWTException {

        setTitle(title);
        Container cp = getContentPane();
        cp.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        java.awt.GraphicsEnvironment env = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
        java.awt.Rectangle desktopBounds = env.getMaximumWindowBounds();
        double dwidth = desktopBounds.getWidth();
        int iwidth = (int) dwidth;
        double dheight = desktopBounds.getHeight();
        int iheight = (int) dheight;
        int windowwidth = iwidth - 210;
        int windowheight = iheight - 380;
        setBounds(windowwidth, windowheight, 210, 380);
        setBackground(Color.white);
        getContentPane().setBackground(Color.white);

        JLabel label1 = new JLabel("ネットワークのアドレス");
        ImageIcon icon = new ImageIcon("./src/udp_server/test.png");
        JLabel label2 = new JLabel(icon);
        JLabel label3 = new JLabel("をタップして最小化");

        final JLabel netlabel0 = new JLabel();
        final JLabel netlabel1 = new JLabel();
        final JLabel netlabel2 = new JLabel();
        final JLabel netlabel3 = new JLabel();
        final JLabel netlabel4 = new JLabel();
        final JLabel netlabel5 = new JLabel();
        final JLabel netlabel6 = new JLabel();
        final JLabel netlabel7 = new JLabel();
        final JLabel netlabel8 = new JLabel();
        final JLabel netlabel9 = new JLabel();

        label1.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 16));
        label3.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 13));
        netlabel0.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 16));
        netlabel1.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 16));
        netlabel2.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 16));
        netlabel3.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 16));
        netlabel4.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 16));
        netlabel5.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 16));
        netlabel6.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 16));
        netlabel7.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 16));
        netlabel8.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 16));
        netlabel9.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 16));

        label1.setBounds(10, 0, 200, 20);
        label2.setBounds(25, 300, 25, 25);
        label3.setBounds(52, 300, 200, 20);

        netlabel0.setBounds(30, 30, 150, 20);
        netlabel1.setBounds(30, 50, 150, 20);
        netlabel2.setBounds(30, 70, 150, 20);
        netlabel3.setBounds(30, 90, 150, 20);
        netlabel4.setBounds(30, 110, 150, 20);
        netlabel5.setBounds(30, 130, 150, 20);
        netlabel6.setBounds(30, 150, 150, 20);
        netlabel7.setBounds(30, 170, 150, 20);
        netlabel8.setBounds(30, 190, 150, 20);
        netlabel9.setBounds(30, 210, 150, 20);

        cp.add(label1);
        cp.add(label2);
        cp.add(label3);
        cp.add(netlabel0);
        cp.add(netlabel1);
        cp.add(netlabel2);
        cp.add(netlabel3);
        cp.add(netlabel4);
        cp.add(netlabel5);
        cp.add(netlabel6);
        cp.add(netlabel7);
        cp.add(netlabel8);
        cp.add(netlabel9);

        JButton jb = new JButton("アドレスの取得");
        jb.setBounds(20, 250, 150, 30);
        jb.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                netlabel0.setText(net0);
                netlabel1.setText(net1);
                netlabel2.setText(net2);
                netlabel3.setText(net3);
                netlabel4.setText(net4);
                netlabel5.setText(net5);
                netlabel6.setText(net6);
                netlabel7.setText(net7);
                netlabel8.setText(net8);
                netlabel9.setText(net9);
            }
        });
        cp.add(jb);
    }
}
