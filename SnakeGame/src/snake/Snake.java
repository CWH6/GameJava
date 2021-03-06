package snake;

import java.util.LinkedList;

/**  Snake 类表示蛇
 *      一条蛇有多个节点,使用LinkedList集合存储Node节点，
 *      蛇出生时有6个节点
 * 
 * @author somebody
 * @date 2021/3/4 -21:09
 */


public class Snake {
    //蛇身体
    private LinkedList<Node> body;
    //蛇的移动方向(枚举类，设置默认向左)
    private Direction direction=Direction.LEFT;
    //蛇是否活着
    private  boolean isLiving=true;
    //构造器 :在创建Snake对象时执行inintSnake()初始化蛇身体
    public Snake(){
        initSanke();
    }

    private void initSanke() {
        //创建集合
        body = new LinkedList<>();
        //创建6个节点，添加到集合中
        body.add(new Node(16,20));
        body.add(new Node(17,20));
        body.add(new Node(18,20));
        body.add(new Node(19,20));
        body.add(new Node(20,20));
        body.add(new Node(21,20));
        body.add(new Node(22,20));
        body.add(new Node(23,20));
        body.add(new Node(24,20));
    }

    //蛇会沿着蛇头移动
    //控制社移动，在蛇头的运动方向添加一个节点，然后把蛇尾的节点删除
    public void move(){
        if(isLiving){//判断蛇是否活着
            //获取蛇头
            Node head = body.getFirst();
            //判断蛇移动的方法，进行添加蛇头
            switch(direction){
                case UP://(3,3)  向上（3，2） 所以（x,y-1）
                    body.addFirst(new Node(head.getX(),head.getY()-1));
                    break;
                case DOWN://(3,3)  向下（3，4） 所以（x,y+1）
                    body.addFirst(new Node(head.getX(),head.getY()+1));
                    break;
                case LEFT://(3,3)  向左（2，3） 所以（x-1,y）
                    body.addFirst(new Node(head.getX()-1,head.getY()));
                    break;
                case RIGHT://(3,3)  向右（4,3）  所以（x+1,y）
                    body.addFirst(new Node(head.getX()+1,head.getY()));
                    break;
            }
            //删除最后的节点
            body.removeLast();

            //判断蛇是否撞墙
            head=body.getFirst();
            if(head.getX()<0||head.getY()<0||head.getX()>=40||head.getY()>=40){
                isLiving=false;// 蛇不动

            }
            //判断蛇是否碰到自己的身体
            for(int i=1;i<body.size();i++){//循环蛇的身体，遍历身体的节点（除了头）
                Node node = body.get(i);//获取节点
                if(head.getX()==node.getX()&&head.getY()==node.getY()){
                    //判断头节点是否跟身体节点重合
                    isLiving=false;//蛇不动

                }
            }

        }
    }



    // Body 的get与set 方法

    public LinkedList<Node> getBody() {
        return body;
    }

    public void setBody(LinkedList<Node> body) {
        this.body = body;
    }

    // Direction 的get与set 方法
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    //蛇吃食物的方法 ：沿着蛇头方向添加一个节点
    public void eat(Node food) {
        Node head = body.getFirst();
        //判断蛇移动的方法，进行添加蛇头
        switch(direction){
            case UP://(3,3)  向上（3，2） 所以（x,y-1）
                body.addFirst(new Node(head.getX(),head.getY()-1));
                break;
            case DOWN://(3,3)  向下（3，4） 所以（x,y+1）
                body.addFirst(new Node(head.getX(),head.getY()+1));
                break;
            case LEFT://(3,3)  向左（2，3） 所以（x-1,y）
                body.addFirst(new Node(head.getX()-1,head.getY()));
                break;
            case RIGHT://(3,3)  向右（4,3）  所以（x+1,y）
                body.addFirst(new Node(head.getX()+1,head.getY()));
                break;
        }

    }

}
