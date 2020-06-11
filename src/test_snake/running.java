package test_snake;

public class running extends openwindows {
    
    public void snake_run(){
        
        
        //蛇有吃到食物
        if((int)positionX.get(0) == food_position[0] && (int)positionY.get(0) == food_position[1]){
            positionX.add("");
            positionY.add("");
            direction.add("");
            food_position[0] = -1;
            ts.settitle();
        }
        
        
        //蛇身、尾座標移動
        for(int position = 1 ; position < positionX.size() ; position ++){
            positionX.set(positionX.size() - position , positionX.get(positionX.size() - position - 1));
            positionY.set(positionY.size() - position , positionY.get(positionY.size() - position - 1));
            direction.set(direction.size() - position , direction.get(direction.size() - position - 1));
        }
        
        
        //蛇頭座標移動
        if(dir.equals("up")){
            positionY.set(0 , ((int)positionY.get(0)) - 1);
        }else if(dir.equals("down")){
            positionY.set(0 , ((int)positionY.get(0)) + 1);
        }else if(dir.equals("left")){
            positionX.set(0 , ((int)positionX.get(0)) - 1);
        }else if(dir.equals("right")){
            positionX.set(0 , ((int)positionX.get(0)) + 1);
        }
        direction.set(0 , dir);
        
        
        //邊界座標設定
        if((int)positionX.get(0) > block_number[0] - 1){
            positionX.set(0 , 0);
        }else if((int)positionX.get(0) < 0){
            positionX.set(0 , block_number[0] - 1);
        }
        if((int)positionY.get(0) > block_number[1] - 1){
            positionY.set(0 , 0);
        }else if((int)positionY.get(0) < 0){
            positionY.set(0 , block_number[1] - 1);
        }
        
        
        //判斷是否咬到蛇身
        for(int snake_body = 1 ; snake_body < positionX.size() ; snake_body ++){
            if((int)positionX.get(0) == (int)positionX.get(snake_body) && (int)positionY.get(0) == (int)positionY.get(snake_body)){
                snake_switch = false;
                ts.settitle();
            }
        }
    }
    
    public void replay_set_up(){
        snake_switch = false;
        time_out = false;
        positionX.clear();
        positionY.clear();
        direction.clear();
        dir = "right";
        //延遲讓舊執行緒跑完
        try {
            Thread.sleep(game_speed * 3);
        } catch (InterruptedException ex) {}
    }
    
    public int[] random(int startrow , int startcol){
        int position_number[] = {(int)(Math.random() * startrow) , (int)(Math.random() * startcol)};
        //判斷食物是否與蛇重疊
        for(int snake_body = 0 ; snake_body < positionX.size() ; snake_body ++){
            if(position_number[0] == (int)positionX.get(snake_body) && position_number[1] == (int)positionY.get(snake_body)){
                position_number[0] = (int)(Math.random() * startrow);
                position_number[1] = (int)(Math.random() * startcol);
                snake_body = 0;
            }
        }
        food_number = (int)((Math.random() * 6) + 1);
        return position_number;
    }
}
