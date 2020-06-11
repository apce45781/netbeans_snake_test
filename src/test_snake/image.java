package test_snake;

import java.awt.Image;
import java.awt.Toolkit;

public class image {
    
    private String image_file = "png/";
    
    private Image hand_up = Toolkit.getDefaultToolkit().getImage(image_file + "hand_up.png");
    private Image hand_down = Toolkit.getDefaultToolkit().getImage(image_file + "hand_down.png");
    private Image hand_left = Toolkit.getDefaultToolkit().getImage(image_file + "hand_left.png");
    private Image hand_right = Toolkit.getDefaultToolkit().getImage(image_file + "hand_right.png");
    
    private Image body_row = Toolkit.getDefaultToolkit().getImage(image_file + "body_row.png");
    private Image body_col = Toolkit.getDefaultToolkit().getImage(image_file + "body_col.png");
    
    private Image body_down_left = Toolkit.getDefaultToolkit().getImage(image_file + "body_down_left.png");
    private Image body_down_right = Toolkit.getDefaultToolkit().getImage(image_file + "body_down_right.png");
    private Image body_up_left = Toolkit.getDefaultToolkit().getImage(image_file + "body_up_left.png");
    private Image body_up_right = Toolkit.getDefaultToolkit().getImage(image_file + "body_up_right.png");
    
    private Image tail_up = Toolkit.getDefaultToolkit().getImage(image_file + "tail_up.png");
    private Image tail_down = Toolkit.getDefaultToolkit().getImage(image_file + "tail_down.png");
    private Image tail_right = Toolkit.getDefaultToolkit().getImage(image_file + "tail_right.png");
    private Image tail_left = Toolkit.getDefaultToolkit().getImage(image_file + "tail_left.png");
    
    private Image tail_down_left = Toolkit.getDefaultToolkit().getImage(image_file + "tail_down_left.png");
    private Image tail_down_right = Toolkit.getDefaultToolkit().getImage(image_file + "tail_down_right.png");
    private Image tail_left_down = Toolkit.getDefaultToolkit().getImage(image_file + "tail_left_down.png");
    private Image tail_left_up = Toolkit.getDefaultToolkit().getImage(image_file + "tail_left_up.png");
    private Image tail_right_down = Toolkit.getDefaultToolkit().getImage(image_file + "tail_right_down.png");
    private Image tail_right_up = Toolkit.getDefaultToolkit().getImage(image_file + "tail_right_up.png");
    private Image tail_up_left = Toolkit.getDefaultToolkit().getImage(image_file + "tail_up_left.png");
    private Image tail_up_right = Toolkit.getDefaultToolkit().getImage(image_file + "tail_up_right.png");
    
    
    private Image food_01 = Toolkit.getDefaultToolkit().getImage(image_file + "food_01.png");
    private Image food_02 = Toolkit.getDefaultToolkit().getImage(image_file + "food_02.png");
    private Image food_03 = Toolkit.getDefaultToolkit().getImage(image_file + "food_03.png");
    private Image food_04 = Toolkit.getDefaultToolkit().getImage(image_file + "food_04.png");
    private Image food_05 = Toolkit.getDefaultToolkit().getImage(image_file + "food_05.png");
    private Image food_06 = Toolkit.getDefaultToolkit().getImage(image_file + "food_06.png");
    private Image food_07 = Toolkit.getDefaultToolkit().getImage(image_file + "food_07.png");
    private Image food_08 = Toolkit.getDefaultToolkit().getImage(image_file + "food_08.png");
    private Image food_09 = Toolkit.getDefaultToolkit().getImage(image_file + "food_09.png");
    private Image food_10 = Toolkit.getDefaultToolkit().getImage(image_file + "food_10.png");
    private Image food_11 = Toolkit.getDefaultToolkit().getImage(image_file + "food_11.png");
        
    private String direction , part;
    
    image(String part){
        this.part = part;
    }
    
    public Image snake_switch(String direction){
        setDirection(direction);
        switch(getPart()){
            case "hand":
                if(getDirection().equals("up")){
                    return hand_up;
                }else if(getDirection().equals("down")){
                    return hand_down;
                }else if(getDirection().equals("left")){
                    return hand_left;
                }else{
                    return hand_right;
                }
            case "body":
                if(getDirection().equals("up") || getDirection().equals("down")){
                    return body_col;
                }else if(getDirection().equals("left") || getDirection().equals("right")){
                    return body_row;
                }else if(getDirection().equals("upleft") || getDirection().equals("rightdown")){
                    return body_up_left;
                }else if(getDirection().equals("upright") || getDirection().equals("leftdown")){
                    return body_up_right;
                }else if(getDirection().equals("downleft") || getDirection().equals("rightup")){
                    return body_down_left;
                }else{
                    return body_down_right;
                }
            case "tail":
                if(getDirection().equals("up")){
                    return tail_up;
                }else if(getDirection().equals("down")){
                    return tail_down;
                }else if(getDirection().equals("left")){
                    return tail_left;
                }else if(getDirection().equals("right")){
                    return tail_right;
                }else if(getDirection().equals("upleft")){
                    return tail_up_left;
                }else if(getDirection().equals("upright")){
                    return tail_up_right;
                }else if(getDirection().equals("downleft")){
                    return tail_down_left;
                }else if(getDirection().equals("downright")){
                    return tail_down_right;
                }else if(getDirection().equals("rightup")){
                    return tail_right_up;
                }else if(getDirection().equals("rightdown")){
                    return tail_right_down;
                }else if(getDirection().equals("leftup")){
                    return tail_left_up;
                }else{
                    return tail_left_down;
                }
            default:
                if(getDirection().equals("1")){
                    return food_01;
                }else if(getDirection().equals("2")){
                    return food_02;
                }else if(getDirection().equals("3")){
                    return food_03;
                }else if(getDirection().equals("4")){
                    return food_04;
                }else if(getDirection().equals("5")){
                    return food_05;
                }else if(getDirection().equals("6")){
                    return food_06;
                }else if(getDirection().equals("7")){
                    return food_07;
                }else if(getDirection().equals("8")){
                    return food_08;
                }else if(getDirection().equals("9")){
                    return food_09;
                }else if(getDirection().equals("10")){
                    return food_10;
                }else{
                    return food_11;
                }
        }
    }
    public void setDirection(String direction){
        this.direction = direction;
    }
    public String getDirection(){
        return direction;
    }
    public void setPart(String part){
        this.part = part;
    }
    public String getPart(){
        return part;
    }
}
