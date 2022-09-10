package package2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CalculatorSetUp extends Cosmetics implements ActionListener  {
    JFrame CalculatorAppPage;   // APP FRAME //
    JTextField TextDisplay;   // TEXTFIELD FOR THE USER //
    ArrayList<JButton> ButtonsFormat = new ArrayList<JButton>();      // BUTTONS ARRAY //

    int i = 0;  // LOOP COUNTER //
    byte cnt_minus = 0; // VARIABLE TO DETERMINE IF A MINUS HAS BEEN INPUTED AS A NUMBER FORMAT AND NOT OPERARD //
    double num1 = 1 , num2 = 1 , result = 1;        // FIRST NUMBER , 2ND NUMBER , RESULT OF THE 2 //
    char operand = ' '; // OPERAND PICK + , - , * , / , % , Power ( ^ ) //
    boolean Adding_Flag = false , operand_flag = false;   // FLAG FOR GENERAL USE //

    String Action_Temp = "";   // TEXTFIELD TEXT TEMPORARY VARIABLE //
    char[] Action;         // TEXTFIELD TEXT CHAR ARRAY TEMPORARY VARIABLE FOR SEARCH PURPOSES //

    CalculatorSetUp() { // DEFAULT              // FRAME SET UP //
        this.CalculatorAppPage = new JFrame();
        CalculatorAppPage.setTitle(" * Calculator * ");
        CalculatorAppPage.setSize(720,720);
        CalculatorAppPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CalculatorAppPage.getContentPane().setBackground(Color.darkGray);
        CalculatorAppPage.setLayout(null);
        CalculatorAppPage.setResizable(false);

        TextDisplay = new JTextField();       // TEXTFIELD SET UP //
        TextDisplay.setBounds(60,45,550,85);
        TextDisplay.setBackground(new Color(130,130,130));
        TextDisplay.setFont(SetFont());
        TextDisplay.setEditable(false);
        TextDisplay.setVisible(true);

        CalculatorAppPage.add(TextDisplay);
        CalculatorAppPage.setVisible(false);

    }

    public void setVisible() { CalculatorAppPage.setVisible(true); }     // START-SHOW APP //


    public void AddButtons() {
        int j = 2 , k = 85 , dig = 1;   // GENERAL VARIABLES FOR THE BUTTONS LAYOUT //

        try {
            for (i = 0; (i < 20); i++, k += 95) {   // 20 BUTTONS -> 5X4 LAYOUT -> 80X80 BUTTONS //
                ButtonsFormat.add(new JButton());
                ButtonsFormat.get(i).setBackground(new Color(130, 130, 130));
                if (i % 4 == 0 && i != 0) {     // => EVERY 4 BUTTONS DROP A LINE //
                    k = 85;
                    j++;
                    ButtonsFormat.get(i).setBounds(k, 90 * j, 80, 80);

                } else
                    ButtonsFormat.get(i).setBounds(k, 90 * j, 80, 80);

                ButtonsFormat.get(i).setBorder(MakeBorder()); // STANDARD FONT , BORDER FROM FRAMELABELS //
                ButtonsFormat.get(i).setFont(new Font("Serif Bold", Font.PLAIN, 15));
                ButtonsFormat.get(i).setFocusable(false);
                ButtonsFormat.get(i).addActionListener(this);

                if ((i >= 4 && i <= 6)) {                // ADDING THE NUMBERS FROM TOP TO BOTTOM //
                    if (i == 4)
                        dig = 7;
                    ButtonsFormat.get(i).setText(Integer.toString(dig));
                    dig++;

                } else if ((i >= 8 && i <= 10)) {
                    if (i == 8)
                        dig = 4;
                    ButtonsFormat.get(i).setText(Integer.toString(dig));
                    dig++;
                } else if ((i >= 12 && i <= 14)) {
                    if (i == 12)
                        dig = 1;
                        ButtonsFormat.get(i).setText(Integer.toString(dig));
                        dig++;
                }

                switch (i) {
                    case 0:
                        ButtonsFormat.get(i).setText("AC");   // i = 0 -> AC (clear) //
                            break;

                    case 1:
                        ButtonsFormat.get(i).setText("DEL");     // i = 1 -> Delete 1 char //
                            break;

                    case 2:
                        ButtonsFormat.get(i).setText(Character.toString('%')); // i = 2 -> mod operator //
                            break;

                    case 3:
                        ButtonsFormat.get(i).setText(Character.toString('/')); // i = 3 -> div operator //
                            break;

                    case 7:
                        ButtonsFormat.get(i).setText(Character.toString('x'));    // i = 7 -> multiply operator //
                            break;

                    case 11:
                        ButtonsFormat.get(i).setText(Character.toString('-'));    // i = 11 -> minus operator //
                            break;

                    case 15:
                        ButtonsFormat.get(i).setText(Character.toString('+'));    // i = 15 -> plus operator //
                            break;

                    case 16:
                        ButtonsFormat.get(i).setText("x ^ y");   // i = 16 -> Power operator //
                            break;

                    case 17:                                // i = 17 -> zero number //
                        ButtonsFormat.get(i).setText(Character.toString('0'));
                            break;

                    case 18:                                // i = 18 -> dot //
                        ButtonsFormat.get(i).setText(Character.toString('.'));
                            break;

                    case 19:                                // i = 19 -> result signal //
                        ButtonsFormat.get(i).setText(Character.toString('='));
                            break;

                }
                   CalculatorAppPage.add(ButtonsFormat.get(i));
            }

        }

        catch(NullPointerException e) {
            System.out.println("NullPointerException");
        }

        catch(Exception e) {
            System.out.println("Exception Found in AddButtons()");
        }
    }

    @Override
   public void actionPerformed(ActionEvent e) {
       for (i = 0 ; (i < ButtonsFormat.size()) ; i++) {   // CHECKING EVERY BUTTTON FOR AN ACTION //

           if (e.getSource() == ButtonsFormat.get(i)) {

                        switch (i) {
                            case 0: {
                                TextDisplay.setText("");
                                Adding_Flag = false;           // RESTART PROCESS //
                                operand_flag = false;
                                num1 = num2 = result = 1;
                                cnt_minus = 0;
                                operand = ' ';
                                    break;          // AC (CLEAR) CASE //
                            }

                            case 1: {
                                Action = TextDisplay.getText().toCharArray();
                                TextDisplay.setText("");              // DELETE CASE <--//

                                if (Action.length <= 1)
                                    return;

                                for (i = 0 ; (i < Action.length - 1) ; i++) {  // REWRITE THE TEXT WITH ONE LESS CHARACTER FROM LEFT -> RIGHT //
                                    TextDisplay.setText(TextDisplay.getText() + Character.toString(Action[i]));

                                    switch (Action[Action.length - 1]) {
                                        case '+':
                                        case '-':
                                        case 'x':
                                        case '/':
                                        case '%':
                                        case '^': {
                                            operand_flag = false;
                                                break;
                                        }
                                    }
                                }
                                    break;
                            }

                            case 4:
                            case 5:
                            case 6:
                            case 8:      //ADDING NUMBERS TO THE TEXT //
                            case 9:
                            case 10:
                            case 12:
                            case 13:
                            case 14:
                            case 17:
                            case 18: {
                                TextDisplay.setText(TextDisplay.getText()
                                        + ButtonsFormat.get(i).getText());
                                    break;
                            }

                            case 2: // ADDING THE OPERATORS ( +,-,*,/,% ,^ (POWER) ) TO THE TEXT //
                            case 3:
                            case 7:
                            case 11:
                            case 15: {
                                if ( !(i == 11) ) {
                                    if ( (!operand_flag) ) { // if ( NOT '-' ) && NOT AN OPERAND WAS ALREADY TYPED //
                                        TextDisplay.setText(TextDisplay.getText() + " " + ButtonsFormat.get(i).getText() + " ");
                                        operand_flag = true;
                                    }
                                }
                                else {  // CHECKING IF => //
                                    if (TextDisplay.getText().equals("")) {   // MINUS WAS PRESSED AT THE START //
                                        TextDisplay.setText(TextDisplay.getText()   // WHICH MEANS THE FIRST NUMBER IS NEGATIVE //
                                                + ButtonsFormat.get(i).getText());
                                    } else { // ELSE CHECKS IF THE MINUS BUTTON WAS PRESSED AFTER THE OPERATOR //
                                        Action = TextDisplay.getText().toCharArray(); // PICK WHICH MEANS THE 2ND NUMBER IS NEGATIVE //
                                        for (int j = 0; (j < TextDisplay.getText().length()); ++j) {
                                            switch (Action[j]) {
                                                case '+':
                                                case 'x':
                                                case '/':
                                                case '%':
                                                case '^': {
                                                    Adding_Flag = true;
                                                    break;
                                                }

                                                case '-': {
                                                    if ( !(j == 0) ) {
                                                        cnt_minus++;
                                                        if (cnt_minus == 1)
                                                        Adding_Flag = true;
                                                        else
                                                        Adding_Flag = false;
                                                    }
                                                                break;
                                                }
                                            }

                                            if (Adding_Flag) {
                                                TextDisplay.setText(TextDisplay.getText()
                                                        + ButtonsFormat.get(i).getText());
                                                Adding_Flag = false;
                                                    return;
                                            }
                                        }
                                        // IF NOT ANY OF THE ABOVE IT CONCLUDES THAT IT IS USED AS AN OPERATOR //
                                        if ( !(operand_flag) ) {
                                            TextDisplay.setText(TextDisplay.getText() + " " + ButtonsFormat.get(i).getText() + " ");
                                            operand_flag = true;
                                        }
                                    }
                                }
                                    break;
                            }

                            case 16: {  // POWER OPERATOR BECAUSE THE BUTTON IS IN THE FORM OF X ^ Y //
                                if ( !(operand_flag)) {
                                    TextDisplay.setText(TextDisplay.getText() + " ^ ");
                                    operand_flag = true;
                                }
                                           break;
                            }

                            case 19: {  // '=' CASE //
                                try {
                                    Action = TextDisplay.getText().toCharArray();
                                    Action_Temp = TextDisplay.getText();

                                    for (int j = 0; (j < Action.length); j++) {
                                        switch (Action[j]) {
                                            case '+':
                                            case '-':
                                            case 'x':
                                            case '/':
                                            case '%':
                                            case '^': {
                                                if ( !(j == 0) ) {  // ONCE THE OPERATOR IS FOUND BUT NOT IN THE BEGINING //
                                                    operand = Action[j];
                                                    num1 = Double.parseDouble(Action_Temp.substring(0, j));    // (START - OPERATOR POSITION - 1 = NUM1 //
                                                    num2 = Double.parseDouble(Action_Temp.substring(j + 1, Action.length)); // (OPERATON + 1 POSITION - END = NUM2 //
                                                            break;
                                                }

                                            }
                                        }

                                            if ( !(operand == ' ') ) { break; } // OPERATOR = ' ' -> DEFAULT SETTING /.
                                    }

                                    switch (operand) {
                                        case '+': {
                                            result = num1 + num2;
                                            break;
                                        }

                                        case '-': {
                                            result = num1 - num2;
                                            break;
                                        }

                                        case 'x': {
                                            result = num1 * num2;
                                            break;
                                        }

                                        case '/': {
                                            if ( !(num2 == 0) )
                                                result = num1 / num2;
                                            else {
                                                TextDisplay.setText(" Can't_Divide_By_0_Use_AC_To_Clear");
                                                return; }

                                            break;
                                        }

                                        case '^': {
                                            result = Math.pow(num1, num2);
                                            break;
                                        }

                                        case '%': {
                                            if ( !(num2 == 0) )
                                                result = num1 % num2;
                                            else {
                                                TextDisplay.setText(" Can't_Divide_By_0_Use_AC_To_Clear ");
                                                return; }

                                            break;
                                        }

                                        default: {
                                            TextDisplay.setText(" Pick_An_Act_Use_AC_To_Clear"); // IF OPERATOR WAS NOT TYPED //
                                            Adding_Flag = false;
                                            num1 = num2 = result = 1;       // RESTART PROCESS //
                                            operand = ' ';
                                            operand_flag = false;
                                            cnt_minus = 0;
                                                break;
                                        }
                                    }

                                    if ( !(TextDisplay.getText().equals(" Pick_An_Act_Use_AC_To_Clear")) ) { // IF OPERATOR WAS NOT TYPED //
                                        if (result == 0)    // JUST IN CASE THE RESULT SHOW -0 //
                                        TextDisplay.setText("0");

                                        else if ( result == ((int) result) )   // IF THERE IS NOT A NEED OF A "DOUBLE" NUMBER FORMAT //
                                        TextDisplay.setText("" + (int) result);

                                        else
                                        TextDisplay.setText("" + result); // GENERAL CASE //
                                    }

                                    Adding_Flag = false;               // RESTART PROCESS //
                                    num1 = num2 = result = 1;
                                    operand = ' ';
                                    operand_flag = false;
                                    cnt_minus = 0;
                                }

                                catch (NumberFormatException q) {   // CATCHING IF THERE IS A NUMBER //
                                    try {                           // BUT NOT AN OPERATOR IT LEAVES JUST THE NUMBER //
                                        result = num1;
                                    }

                                    catch (NumberFormatException g) {
                                        try {
                                            result = num2;
                                        }

                                        catch (NumberFormatException f) {   // ELSE IT CONCLUDES TO NOT A VALID FORMAT //
                                            TextDisplay.setText("Not_Valid_Act");
                                        }
                                    }
                                    if ( result == ((int) result) )
                                        TextDisplay.setText("" + (int) result);
                                    else
                                        TextDisplay.setText("" + result);

                                    Adding_Flag = false;                   // RESTART PROCESS //
                                    num1 = num2 = result = 1;
                                    operand = ' ';
                                    operand_flag = false;
                                    cnt_minus = 0;
                                }

                                catch (Exception q) {
                                    System.out.println("ExceptionFoundIn '='");
                                }
                            }

                        }
                }
          }
    }


}





