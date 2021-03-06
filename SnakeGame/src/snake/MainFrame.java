package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 游戏界面
 * @author somebody
 * @date 2021/3/4 -16:31
 */


public class MainFrame extends JFrame{
    private Snake snake;//蛇
    private JPanel jPanel;//游戏棋盘
    private Timer timer;//定时器，在规定的时间内调用蛇移动的方法
    private Node food1;//食物1
    private Node food2;//食物2

    //构造器
    public MainFrame() throws HeadlessException{
        //初始化窗口参数
        initFrame();
        //初始化游戏棋盘
        inintGamePanel();
        //初始化蛇
        initSnake();
        //初始化定时器
        initTimer();
        //设置键盘键盘监听
        setKeyListener();
        //初始化食物
        initFood();
    }
    //初始化食物的方法
    private void initFood() {
        food1=new Node();//创建食物节点1
        food1.random();//调用节点的方法，随机生成位置

        food2=new Node();
        food2.random();
    }

    //设置键盘监听
    private void setKeyListener() {

    addKeyListener(new KeyAdapter() {
        //KeyAdapter是某个接口的实现类，里面可以重新键盘按下，键盘松开的方法
        //这里重写键盘按下的方法keyPressed
        @Override
        public void keyPressed(KeyEvent e) {
            //System.out.println(e.getKeyCode());//打印键盘对应键的编号 上(38)、下(40)、左(37)、右(39)
            //判断键盘按下的按键
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP: //上  或者38
                if(snake.getDirection()!=Direction.DOWN){//蛇的运动不是向下可以往上走
                    snake.setDirection(Direction.UP);
                }
                break;

                case KeyEvent.VK_DOWN: //下  或者40
                if(snake.getDirection()!=Direction.UP) {
                    snake.setDirection(Direction.DOWN);
                }
                break;
                case KeyEvent.VK_LEFT: //左  或者37
                if(snake.getDirection()!=Direction.RIGHT) {
                    snake.setDirection(Direction.LEFT);
                }
                break;
                case KeyEvent.VK_RIGHT://右   或者39
                if(snake.getDirection()!=Direction.LEFT) {
                    snake.setDirection(Direction.RIGHT);
                }
                break;
            }
        }



        });

    }

    //初始化计时器
    private void initTimer() {
        //创建定时器对象
        timer = new Timer();
        //初始化定时任务
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {//执行move方法
                snake.move();


                //移动完后判断蛇是否吃食物（头结点跟食物节点是否重合）
                Node head = snake.getBody().getFirst();//获取蛇头
                if(head.getX()==food1.getX()&&head.getY()==food1.getY()){
                    snake.eat(food1);//蛇集合添加一个食物头节点的，并沿着这个新头结点移动
                    food1.random();//蛇吃完食物，食物位置改变
                }
                if(head.getX()==food2.getX()&&head.getY()==food2.getY()){
                    snake.eat(food2);//蛇集合添加一个食物头节点的，并沿着这个新头结点移动
                    food2.random();//蛇吃完食物，食物位置改变
                }


                //重绘游戏棋盘
                jPanel.repaint();
            }
        };
        //每100毫秒，执行一次定时任务  //第一个参数 一开始100毫秒后执行， 第二个参数：下次都100毫秒后执行
        timer.scheduleAtFixedRate(timerTask,100,100);
    }
    //初始化蛇
    private void initSnake() {
       snake = new Snake();
    }

    //初始化游戏棋盘
    private void inintGamePanel() {
         jPanel=new JPanel(){
            //绘制游戏棋盘中的内容
            @Override
            public void paint(Graphics g) {
            //清空游戏棋盘
            g.clearRect(0,0,600,600);


            // Graphics g 可以看做是一个画笔，它提供很多方法可以绘制一些基本图形（矩形，圆形）

            //绘制40条横线
                for(int i=0;i<=40;i++){
                    g.drawLine(0,i*15,600,i*15);
                }
            //绘制40条竖线
                for(int i=0;i<=40;i++){
                    g.drawLine(i*15,0,i*15,600);
                }
            //绘制蛇身体
                LinkedList<Node> body = snake.getBody();
                for(Node node :body){
                    //循环集合给集合的每一个节点，用画笔填充给矩形颜色 (前面的参数棋盘的某给位置，后面两个参数为矩形的长宽)
                    //g.fillRect(node.getX()*15,node.getY()*15,15,15);
                    g.setColor(Color.GREEN);
                    g.fillRoundRect(node.getX()*15,node.getY()*15,15,15,10,10);


                }
                //绘制食物
                g.setColor(Color.red);
                g.fillRoundRect(food1.getX()*15,food1.getY()*15,15,15,10,10);
                g.setColor(Color.BLUE);
                g.fillRoundRect(food2.getX()*15,food2.getY()*15,15,15,10,10);

                //绘制其他东西
               Font myFont = new Font("Serif",Font.PLAIN,30);
                g.setFont(myFont);
                int snakeLength=snake.getBody().size();
                String str="当前蛇的长度"+snakeLength;
                g.drawString(str,0, 600);
                //绘制当前蛇的长度



            }
        };
        //把棋盘添加到窗体中
        add(jPanel);
    }

    //初始化窗口参数
    private  void initFrame(){
     //1.设置窗口标题
     setTitle("贪吃蛇");
    //设置窗口大小
     setSize(610,640);
     //设置窗口位置
     setLocation(400,400);
     //设置窗口关闭按钮的作用
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     //设置窗口不可变
     setResizable(false);
    }

    public static void main(String[] args) {
        //创建窗体并显示在屏幕上
         new MainFrame().setVisible(true);
    }

}
