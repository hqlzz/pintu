package li.ui;

import li.ui.LoginJFrame;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.PublicKey;
import java.util.Random;


public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    int[][] data = new int[4][4];
    int x=0;
    int y=0;
    
    String path="image\\animal\\animal3\\";


    int[][] win={
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };

    JMenu changeImage=new JMenu("更换图片");
    JMenu aboutJMenu=new JMenu("关于我们");

    JMenuItem girl=new JMenuItem("美女");
    JMenuItem animal=new JMenuItem("动物");
    JMenuItem sport=new JMenuItem("运动");


    JMenuItem replayItem=new JMenuItem("重新游戏");
    JMenuItem reLoginItem=new JMenuItem("重新登录");
    JMenuItem closeItem=new JMenuItem("关闭游戏");
    JMenuItem accountItem=new JMenuItem("创作者");




    int step=0;
    public GameJFrame(){

        initJFrame();

        initJMenuBar();

        initData();

        initImage();

        this.setVisible(true);
    }


    private void initData() {
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {

            int index = r.nextInt(tempArr.length);

            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }



        for (int i = 0; i < tempArr.length; i++) {
            if(tempArr[i]==0){
                x=i/4;
                y=i%4;
            }
            data[i / 4][i % 4] = tempArr[i];

        }


    }

    private void initImage() {

        this.getContentPane().removeAll();

        if (victory()) {
            JLabel winJLabel=new JLabel(new ImageIcon("image\\win.png"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount=new JLabel("步数"+step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num=data[i][j];
                JLabel jLabel=new JLabel(new ImageIcon(path+num+".jpg"));
                jLabel.setBounds(105*j+83,105*i+134,105,105);
                jLabel.setBorder(new BevelBorder(1));
                this.getContentPane().add(jLabel);
            }
        }
        ImageIcon bg=new ImageIcon("image\\background.png");
        JLabel background=new JLabel(bg);
        background.setBounds(40,40,508,560);
        this.getContentPane().add(background);

        this.getContentPane().repaint();

    }


    private void initJMenuBar() {
        JMenuBar JMenuBar =new JMenuBar();
        JMenu functionJMenu=new JMenu("功能");



        changeImage.add(girl);
        changeImage.add(animal);
        changeImage.add(sport);

        functionJMenu.add(changeImage);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(accountItem);

        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);

        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        JMenuBar.add(functionJMenu);
        JMenuBar.add(aboutJMenu);

        this.setJMenuBar(JMenuBar);
    }

    private void initJFrame() {
        this.setSize(603,680);

        this.setTitle("超好玩的拼图游戏");

        this.setAlwaysOnTop(true);

        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(3);

        this.setLayout(null);

        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==65){
            this.getContentPane().removeAll();
            JLabel all=new JLabel(new ImageIcon(path+"all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);
            ImageIcon bg=new ImageIcon("image\\background.png");
            JLabel background=new JLabel(bg);
            background.setBounds(40,40,508,560);
            this.getContentPane().add(background);
            this.getContentPane().repaint();


        }

    }


    @Override
    public void keyReleased(KeyEvent e) {

        if(victory()){
            return;

        }
        int code=e.getKeyCode();
        if(code==37){

            if(y==0){
                return;
            }
            System.out.println("向左移动");
            data[x][y]=data[x][y-1];
            data[x][y-1]=0;
            y--;
            step++;
            initImage();
        }
        else if(code==38){
            if(x==0){
                return;
            }
            System.out.println("向上移动");
            data[x][y]=data[x-1][y];
            data[x-1][y]=0;
            x--;
            step++;
            initImage();
        }
        else if(code==39){

            if(y==3){
                return;
            }
            System.out.println("向右移动");
            data[x][y]=data[x][y+1];
            data[x][y+1]=0;
            y++;
            step++;
            initImage();
        }
        else if(code==40){

            if(x==3){
                return;
            }
            System.out.println("向下移动");
            data[x][y]=data[x+1][y];
            data[x+1][y]=0;
            x++;
            step++;
            initImage();
        }
        else if(code==65){
            initImage();

        }
        else  if(code==87){
            data=new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initImage();
        }

    }


    public boolean victory(){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if(data[i][j]!=win[i][j]){
                    return false;
                }

            }
        }
        return  true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        if(obj==replayItem){
            System.out.println("重新游戏");
            step=0;
            initData();
            initImage();


        }else if(obj==reLoginItem){
            System.out.println("重新登录");
            this.setVisible(false);
            new LoginJFrame();


        }else if(obj==closeItem) {
            System.out.println("关闭游戏");
            System.exit(0);

        }else if(obj==accountItem) {
            System.out.println("创作者");
            JDialog jDialog=    new JDialog();
            JLabel jLabel=new JLabel(new ImageIcon("image\\about .png"));
            jLabel.setBounds(0,0,697,984);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(697,984);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setVisible(true);
        } else if (obj==girl) {
            System.out.println("美女");
            Random random=new Random();
            int num=random.nextInt(11)+1;
            path="image\\girl\\girl"+num+"\\";
            step=0;
            initData();
            initImage();
        }else if (obj==animal) {
            System.out.println("动物");
            Random random=new Random();
            int num=random.nextInt(8)+1;
            path="image\\animal\\animal"+num+"\\";
            step=0;
            initData();
            initImage();

        }else if (obj==sport) {
            System.out.println("运动");
            Random random=new Random();
            int num=random.nextInt(10)+1;
            path="image\\sport\\sport"+num+"\\";
            step=0;
            initData();
            initImage();
        }

    }
}
