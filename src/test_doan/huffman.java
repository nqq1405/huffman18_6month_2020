package test_doan;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// package null

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.*;
import java.util.*;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 * @author Quang Nguyen Quy
 */
public class huffman extends JFrame {

    JPanel panel;
    JLabel l1;
    JTextField t1;
    JButton b1, b2;

    //frame nhap chuoi
    public huffman() {
        setTitle("STRING WINDOW");
        setLayout(new FlowLayout());
        setSize(1000, 250);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Để frame xuất hiện ở chính giữa màn hình

        panel = new JPanel(true);
        JPanel buttonsPanel = new JPanel();
        l1 = new JLabel("Nhập vào chuỗi ký tự");
        l1.setFont(new Font("Serif", Font.BOLD, 25));
        t1 = new JTextField(50);
        b1 = new JButton("Nhập");
        b1.setToolTipText("Nhập vào chuỗi");
        b2 = new JButton("Thoát");
        b2.setToolTipText("Thoát chương trình");

        buttonsPanel.add(l1);
        buttonsPanel.add(t1);
        buttonsPanel.add(b1);
        buttonsPanel.add(b2);
        add(buttonsPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        b1.addActionListener(new mylistener());
        b2.addActionListener(new mylistener());
        panel.setVisible(true);
        setVisible(true);
    }

    //Defining actions of the buttons when pressed// // gán sự kiện cho các nút
    private class mylistener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == b2) {
                System.exit(0);
            } else {
                String s = t1.getText();
                Tree t = new Tree();
                NewWindow NW = new NewWindow(t.buildTree(s));
                new bag_gia_tri(s);

                new bag_gia_tri(t.duyet010(s));
            }
        }
    }

    public static void main(String args[]) {

        huffman h = new huffman();
    }

}


class Node2 implements Comparable<Node2> { //lop node

    public String ch; // ký tự
    public int freq; // tần suất lặp(số lần lặp)
    public Node2 left, right;
    public int count; // giá trị

    Node2(String ch, int freq, Node2 left, Node2 right, int count) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
        this.count = count;
    }

    public String getCh() {
        return ch;
    }

    public int getFreq() {
        return freq;
    }

    public Node2 getLeft() {
        return left;
    }

    public Node2 getRight() {
        return right;
    }

    public int getCount() {
        return count;
    }

    //    private boolean isLeaf() {
//        assert ((left == null) && (right == null)) || ((left != null) && (right != null));
//        /**/
//        return (left == null) && (right == null);
//    }

    @Override
    public int compareTo(Node2 a) {
        if (freq < a.freq) {
            return -1;
        } else if (freq > a.freq) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setcount(int c) {
        this.count = c;
    }
}

//Tree lớp//
class Tree extends JFrame {

    public static int[] freqCount(String S) { // số lần xuất hiện ký tự trong 1 chuỗi

        int[] f = new int[1000];
        for (int i = 0; i < S.length(); i++) {
            // Ví dụ: kí tự sẽ tự động được ép về int có giá trị tương ứng
            // a = 65
            // A = 97
            f[S.charAt(i)]++; // giá trị phần tử tại kí tự tương ứng sẽ được tăng lên 1.

        }

        return f;
    }

    // Xây cây huffman từ 1 chuỗi
    public static Node2 buildTree(String S) {
        PriorityQueue<Node2> queue = new PriorityQueue<Node2>();
        int[] f = new int[1000];
        f = freqCount(S);
        for (char i = 0; i < f.length; i++) {
            if (f[i] > 0) {
                Node2 n = new Node2(i + "", f[i], null, null, 0);
                queue.add(n);
            }
        }
        int c = 1;
        while (queue.size() > 1) {
            Node2 l1 = queue.poll();
            l1.setcount(c);
            c++;
            Node2 l2 = queue.poll();
            l2.setcount(c);
            c++;
            Node2 parent = new Node2('\0' + l1.ch + l2.ch, l1.freq + l2.freq, l1, l2, 0);
            queue.add(parent);
        }
        PriorityQueue<Node2> queue3 = queue;
        return queue.poll();
    }


    public static PriorityQueue<Node2> duyet010(String S) {
        PriorityQueue<Node2> queue = new PriorityQueue<Node2>();
        int[] f = new int[1000];
        f = freqCount(S);
        for (char i = 0; i < f.length; i++) {
            if (f[i] > 0) {
                Node2 n = new Node2(i + "", f[i], null, null, 0);
                queue.add(n);
            }
        }
        int c = 1;
        while (queue.size() > 1) {
            Node2 l1 = queue.poll();
            l1.setcount(c);
            c++;
            Node2 l2 = queue.poll();
            l2.setcount(c);
            c++;
            Node2 parent = new Node2('\0' + l1.ch + l2.ch, l1.freq + l2.freq, l1, l2, 0);
            queue.add(parent);
        }
        return queue;
    }
}

class bag_gia_tri extends JFrame {

    public static TreeMap<Character, String> duyet = new TreeMap<>();

    public void sx(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] < a[j] && a[i] != 0 && a[j] != 0) {
                    int s = a[i];
                    a[i] = a[j];
                    a[j] = s;
                }
            }
        }
    }

    private static void duyet01(Node2 node, String s) {
        if (node != null) {
            if (node.right != null)
                duyet01(node.getRight(), s + "1");

            if (node.left != null)
                duyet01(node.getLeft(), s + "0");

            if (node.left == null && node.right == null)
                duyet.put(node.getCh().charAt(0), s);
        }
    }

    public bag_gia_tri(PriorityQueue<Node2> queue2) {
        this.setSize(300, 250);
        this.setTitle("Bảng Mã Huffman");

        String[] s = {"Ký Tự", "ma huffman"};
        JTable tb = new JTable();

        tb.setEnabled(false);
        DefaultTableModel tbl = (DefaultTableModel) tb.getModel();
        tbl.setColumnIdentifiers(s);

        duyet01(queue2.peek(), "");

        for (Map.Entry<Character, String> en : duyet.entrySet()) {
            Character k = en.getKey();
            String v = en.getValue();

            tbl.addRow(new Object[]{
                    "( " + k + " )", v
            });
        }

        JScrollPane jptb = new JScrollPane();
        jptb.setViewportView(tb);
        this.add(jptb);
        this.setVisible(true);
    }

    public bag_gia_tri(String S) {
        this.setSize(300, 250);
        this.setTitle("Tần suất Ký tự");

        int[] kt = new int[Character.MAX_VALUE]; // khởi tạo tất cả các ký tự
        kt = Tree.freqCount(S);

        String[] s = {"Ký Tự", "Số lần lặp", "Tần Suất Lặp"};
        JTable tb = new JTable();

//        tb.setEnabled(false);
        DefaultTableModel tbl = (DefaultTableModel) tb.getModel();
        tbl.setColumnIdentifiers(s);


        String tongkt = null;
        int tongkt_s = 0;
        for (int i = 0; i < kt.length; i++) {
            if (kt[i] != 0) {
                tbl.addRow(new Object[]{
                        "( " + (char) i + " )", kt[i], (float) kt[i] / S.length() + "%"
                });
                if (kt[i] > 0) {
                    tongkt += (char) i;
                    tongkt_s += kt[i];
                }
            }
        }
        tbl.addRow(new Object[]{
                "=>" + tongkt, "Tong ky tu: " + tongkt_s,
        });
        JScrollPane jptb = new JScrollPane();
        jptb.setViewportView(tb);
        this.add(jptb);
        this.setVisible(true);
    }
}

class NewWindow {

    Node2 ab;
    ArrayList<Node2> node = new ArrayList<Node2>();
    public int width = 700; //  size build tree mặc định rộng 700, cao với đỉnh nút với trục x là 10 này thay đổi phù hợp với Tree trong screen
    public int height = 10;
    ArrayList<Integer> x = new ArrayList<Integer>(); // danh sách các tọa độ x của các nút trong cây
    ArrayList<Integer> y = new ArrayList<Integer>(); // danh sách các tọa độ y của các nút trong cây

    public int index(Node2 n) {
        int a = 0;
        for (int i = 0; i < node.size(); i++) {
            if (n == node.get(i)) {
                a = i;
                break;
            }
        }
        return a;
    }

    /*LevelFirstSearch traversal of the tree Tìm kiếm điểm đầu tiên
 đi qua của cây*/
    public void bfs(Node2 root) {
        Queue<Node2> q = new LinkedList<Node2>();
        int c = 1;

        q.add(root);
        while (!q.isEmpty()) {
            Node2 n = (Node2) q.remove();
            node.add(n);
            if (n.left != null) {
                q.add(n.left);
            }
            if (n.right != null) {
                q.add(n.right);
            }
        }
        for (int i = 0; i < node.size(); i++) {
            x.add(0);
            y.add(0);
        }
        x.set(0, width);
        y.set(0, height);

        for (int j = 0; j < node.size(); j++) {
            if (node.get(j).left != null && node.get(j).right != null) {
                x.set(index(node.get(j).left), x.get(j) - 25 * c);
                x.set(index(node.get(j).right), x.get(j) + 25 * c);

                y.set(index(node.get(j).left), y.get(j) + 100);
                y.set(index(node.get(j).right), y.get(j) + 100);
                c++;
            }

        }

        ab.setcount(node.size());
    }

    //CREATING THE NEW WINDOW FOR TREE ANIMATION //
    public NewWindow(Node2 b) {

        ab = b;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Xây dựng Cây Huffman");

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());


                frame.add(new TestPane()); // add panel vào frame
//                frame.add(new bag_gia_tri());
                frame.setLocationRelativeTo(null); // Để frame xuất hiện ở chính giữa màn hình
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Để frame hiển thị ra full màn hình MAXIMIZED_BOTH
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            }
        });
        bfs(ab);

    }

    // TesPane Class ,animation done here //
    public class TestPane extends JPanel {

        private int b = node.size();

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(1366, 768);
        }

        public TestPane() {
            ActionListener animate = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    repaint();
                    b--;
                }
            };

            Timer timer = new Timer(1000, animate);
            timer.start();
            //LIGHT_GRAY
            setBackground(Color.LIGHT_GRAY);
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
//            g.setColor(Color.RED);
            //node.size() - b

            for (int j = node.size() - 1; j >= 0; j--) {
                if (node.get(j).count < node.size() - b) {
                    g2d.setColor(Color.pink);
                    // Thiết đặt độ lớn của vùng màu hiển trị trong cây
                    g2d.fillRect(x.get(j), y.get(j), 30, 30);

                    g2d.setColor(Color.BLUE);
                    // Thiết đặt độ lớn cho các ô vuông trong cây
                    g2d.drawRect(x.get(j), y.get(j), 30, 30);
                    g2d.setColor(Color.BLUE); // màu cho khung
                    g2d.drawString(Integer.toString(node.get(j).freq), x.get(j) + 5, y.get(j) + 13);
                    g2d.drawString((node.get(j).ch), x.get(j) + 10, y.get(j) + 20);
                    g2d.setColor(Color.BLUE); // màu cho chữ
                    if ((node.get(j)).left != null) {
                        g2d.drawString("0", (x.get(j) + x.get(index(node.get(j).left))) / 2, (y.get(j) + y.get(index(node.get(j).left))) / 2);
                        g2d.drawLine(x.get(j) + 20, y.get(j) + 20, x.get(index(node.get(j).left)), y.get(index(node.get(j).left)));
                        g2d.setColor(Color.RED); // RED

                    }
                    if ((node.get(j)).right != null) {
                        g2d.drawString("1", (x.get(j) + x.get(index(node.get(j).right))) / 2, (y.get(j) + y.get(index(node.get(j).right))) / 2);
                        g2d.drawLine(x.get(j) + 20, y.get(j) + 20, x.get(index(node.get(j).right)), y.get(index(node.get(j).right)));
                        g2d.setColor(Color.PINK);
                    }
                }
            }
        }
    }
}
