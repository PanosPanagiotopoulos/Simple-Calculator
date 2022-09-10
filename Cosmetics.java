package package2;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Cosmetics {
    public Font SetFont() {
        return  new Font("Serif" , Font.PLAIN , 30);
    }
    // FONT SET UP FOR THIS PROJECT // CAN BE CHANGED TO ANY FONT OR SIZE FOR FURTHER CAUSE //

    public Font SetFont(String UserFontName) {
        return new Font(UserFontName , Font.PLAIN , 30); // SIZE 30 IS DEFAULT //
    }

    public Font SetFont(String UserFontName , int FontTextSize) {
        return new Font(UserFontName , Font.PLAIN , FontTextSize);  // FONT.PLAIN IS DEFAULT //
    }
    public static Border MakeBorder() {
        return BorderFactory.createLineBorder(new Color(190,190,190), 3);   // THIS COLOR AND THICKNESS //
                                                                                               // IS DEFAULT //
    }

    public static Border MakeBorder(Color UserColor) {
        return BorderFactory.createLineBorder(UserColor, 3);
    }

    public static Border MakeBorder(Color UserColor , int UserThickness) {
        return BorderFactory.createLineBorder(UserColor , UserThickness);
    }

}
