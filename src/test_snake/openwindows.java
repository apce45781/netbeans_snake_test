package test_snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class openwindows extends JPanel implements KeyListener {
    
    //[X][Y]
    public static int windows_size[] = new int[2];
    //[X][Y]
    public static int block[] = new int[2];
    //貪吃蛇圖片寬高的格數[X][Y]
    public static int block_number[] = {20 ,15};
    //貪吃蛇開關
    public static boolean snake_switch = false;
    //暫停開關
    public static boolean time_out = false;

    public static ArrayList positionX = new ArrayList();
    public static ArrayList positionY = new ArrayList();
    
    
    public static ArrayList direction = new ArrayList();
    public static String dir = "right";
    
    public static int food_position[] = new int[2];
    
    //畫面刷新間隔(毫秒)
    public int game_speed = 150;
    
    //判別食物類別號碼(random - 1~6)
    public static int food_number ;
    
    public static running run = new running();
    public static Test_snake ts = new Test_snake();
    
    openwindows(){
        this.setFocusable(true);
        this.addKeyListener(this);
    }
    
    public void paint(Graphics g){
        
        //螢幕尺寸偵測 與發布
        if(windows_size[0] != this.getWidth() || windows_size[1] != this.getHeight()){
            windows_size[0] = this.getWidth();
            windows_size[1] = this.getHeight();
            block[0] = windows_size[0] / block_number[0];
            block[1] = windows_size[1] / block_number[1];
        }
        
        if(!snake_switch && !time_out){
            start_array();
        }
        
        //背景布置
        g.setColor(new Color(173 , 167 , 154));
        g.fillRect(0 , 0 , windows_size[0] , windows_size[1]);
        g.setColor(Color.BLACK);
        for(int number = 1 ; number < block_number[0] ; number ++){
            g.drawLine(block[0] * number , 0 , block[0] * number , windows_size[1]);
        }
        for(int number = 1 ; number < block_number[1] ; number ++){
            g.drawLine(0 , block[1] * number , windows_size[0] , block[1] * number);
        }
        
        //show 蛇身loop
        for(int body = 1 ; body < positionX.size() - 1 ; body ++){
            //條件(body == 1) => 判斷是否為蛇身的第一節 (頭為0直接跳過)
            if(body == 1 && dir.equals(direction.get(body))){
                g.drawImage(image("body" , (String)direction.get(body)) , (int)positionX.get(body) * block[0] , (int)positionY.get(body) * block[1] , block[0] , block[1] , this);
            }else if(body == 1){
                g.drawImage(image("body" , dir + direction.get(body)) , (int)positionX.get(body) * block[0] , (int)positionY.get(body) * block[1] , block[0] , block[1] , this);
            }else if(direction.get(body - 1).equals(direction.get(body))){
                g.drawImage(image("body" , (String)direction.get(body)) , (int)positionX.get(body) * block[0] , (int)positionY.get(body) * block[1] , block[0] , block[1] , this);
            }else{
                g.drawImage(image("body" , (String)direction.get(body - 1) + direction.get(body)) , (int)positionX.get(body) * block[0] , (int)positionY.get(body) * block[1] , block[0] , block[1] , this);
            }
        }
        
        //show 蛇尾
        if(direction.get(direction.size() - 2).equals(direction.get(direction.size() - 1))){
            g.drawImage(image("tail" , (String)direction.get(direction.size() - 1)) , (int)positionX.get(positionX.size() - 1) * block[0] , (int)positionY.get(positionY.size() - 1) * block[1] , block[0] , block[1] , this);
        }else{
            g.drawImage(image("tail" , (String)direction.get(direction.size() - 2) + (String)direction.get(direction.size() - 1)) , (int)positionX.get(positionX.size() - 1) * block[0] , (int)positionY.get(positionY.size() - 1) * block[1] , block[0] , block[0] , this);
        }
        
        
        //show 食物(已在random()判斷完是否與蛇身重疊)
        if(food_position[0] == -1 ||food_position[1] == -1){
            food_position = run.random(block_number[0] , block_number[1]);
            g.drawImage(image("food" , food_number + "") , food_position[0] * block[0] , food_position[1] * block[1] , block[0] , block[1] , this);
        }else{
            g.drawImage(image("food" , food_number + "") , food_position[0] * block[0] , food_position[1] * block[1] , block[0] , block[1] , this);
        }
        
        
        //show 蛇頭
        g.drawImage(image("hand" , (String)direction.get(0)) , (int)positionX.get(0) * block[0] , (int)positionY.get(0) * block[1] , block[0] , block[1] , this);
        
        
        //暫停or結束的字體設定
        if(!snake_switch){
            g.setColor(Color.WHITE);
            g.fillRect(0 , windows_size[1] / 2 - block[1] * 3 , windows_size[0] , block[1] * 6);
            g.setFont(new Font("" , Font.BOLD , block[1]));
            g.setColor(Color.BLACK);
            g.drawString("遊戲結束，共得 : " + ((positionX.size() - 3) * 10) + "分" , (windows_size[0] / 2) - (block[0] * 4) , (windows_size[1] / 2) - block[1]);
            g.drawString("可按\"F2\"重新開始" , (windows_size[0] / 2) - (block[0] * 3) , (windows_size[1] / 2) + block[1]);
        }else{
            if(!time_out){
                g.setColor(Color.WHITE);
                g.fillRect(0 , windows_size[1] / 2 - block[1] * 3 , windows_size[0] , block[1] * 6);
                g.setFont(new Font("" , Font.BOLD , block[1]));
                g.setColor(Color.BLACK);
                g.drawString("可點擊\"空白鍵\"繼續遊戲" , (windows_size[0] / 2) - (int)(block[0] * 4.5) , (windows_size[1] / 2) - block[1]);
                g.drawString("或按\"F2\"重新開始" , (windows_size[0] / 2) - (int)(block[0] * 3.5) , (windows_size[1] / 2) + block[1]);
            }
        }
        try{
            Thread.sleep(10);
        }catch(InterruptedException e){}
    }
    
    public void start_array(){
        snake_switch = true;
        time_out = true;
        positionX.add(5);
        positionX.add(4);
        positionX.add(3);
        positionY.add(3);
        positionY.add(3);
        positionY.add(3);
        direction.add("right");
        direction.add("right");
        direction.add("right");
        //-1即可觸發食物位置的random
        food_position[0] = -1;
        new Thread(new thread_snake()).start();
        ts.settitle();
    }
    
    class thread_snake implements Runnable {
        public void run(){
            while(snake_switch){
                try{
                    Thread.sleep(game_speed);
                }catch(InterruptedException e){}
                if(time_out){
                    //跑運作
                    run.snake_run();
                }
                //show畫面
                repaint();
            }
        }
    }
    
    public Image image(String case_name , String number){
        image sn[] = {new image("hand") , new image("body") , new image("tail") , new image("food")};
        switch(case_name){
            case "hand":
                return sn[0].snake_switch(number);
            case "body":
                return sn[1].snake_switch(number);
            case "tail":
                return sn[2].snake_switch(number);
            default:
                return sn[3].snake_switch(number);
        }
    }
    
//key down
    @Override
    public void keyPressed(KeyEvent e) {
        if(time_out){
            if(e.getKeyCode() == KeyEvent.VK_UP && !direction.get(0).equals("down")){
                dir = "up";
            }else if(e.getKeyCode() == KeyEvent.VK_DOWN && !direction.get(0).equals("up")){
                dir = "down";
            }else if(e.getKeyCode() == KeyEvent.VK_LEFT && !direction.get(0).equals("right")){
                dir = "left";
            }else if(e.getKeyCode() == KeyEvent.VK_RIGHT && !direction.get(0).equals("left")){
                dir = "right";
            }
        }
    }

    //key up
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE && snake_switch){
            if(time_out){
                time_out = false;
                ts.settitle();
            }else{
                time_out = true;
                ts.settitle();
            }
        }else if(snake_switch && !time_out && e.getKeyCode() == KeyEvent.VK_F2){
            if(JOptionPane.showConfirmDialog(this , "遊戲尚未結束，是否要重啟遊戲?" , "遊戲暫停" , JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                //遊戲重啟
                run.replay_set_up();
                start_array();
                ts.settitle();
            }
        }else if(!snake_switch && time_out && e.getKeyCode() == KeyEvent.VK_F2){
            //遊戲重啟
            run.replay_set_up();
            start_array();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    
}
