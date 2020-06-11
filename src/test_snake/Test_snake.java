package test_snake;

import javax.swing.JFrame;

public class Test_snake extends JFrame {
    
    private int windows_row = 700;
    private int windows_col = 525;
    
    private openwindows ow = new openwindows();
    
    private static Test_snake ts;
    
    Test_snake(){
        this.setBounds(100 , 10 , windows_row , windows_col);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new openwindows());
        this.setTitle("可點擊\"空白鍵\"暫停遊戲  分數 : 0");
    }
    
    public static void main(String[] args) {
        ts = new Test_snake();
        ts.setVisible(true);
    }
    
    public void settitle(){
        if(ow.snake_switch && ow.time_out){
            ts.setTitle("可點擊\"空白鍵\"暫停遊戲  分數 : " + ((ow.positionX.size() - 3) * 10));
        }else if(ow.snake_switch && !ow.time_out){
            ts.setTitle("暫停");
        }else if(!ow.snake_switch){
            ts.setTitle("遊戲結束");
        }
    }
}
