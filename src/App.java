import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class App extends Application {
    static String player = "x";
    static ArrayList<ArrayList<String>> matrix = new ArrayList<>();
    static List<Button> TicTacToe_Buttons = new ArrayList<>();
    static Label game_status;
    static String winner;
    public static void Clear_Matrix() {
        matrix.clear();
        for (int i = 0; i < 5; i++) {
            matrix.add(new ArrayList<>(Arrays.asList("", "", "", "", "")));
        }
        for (Button btn : TicTacToe_Buttons) {
            btn.setText("");
        }


    }
    
    public static boolean Check_Win() {
        Boolean state = false;
        String test_value = "";

        //check rows
        
        for (int row = 0; row < 5; row++) {
             
            for (int column = 0, test_check = 0; column < 5; column++) {
                if ((matrix.get(row).get(column)).equals("")) {
                    state = false;
                    break;
                }
                if (test_check == 0) {
                    test_value = matrix.get(row).get(column);
                    test_check++;
                    continue;
                }

                if ((matrix.get(row).get(column)) != test_value) {
                    state = false;
                    break;
                }
                if (column == 4 && matrix.get(row).get(column).equals(test_value)) {
                    winner = test_value;
                    return true;
                }
            }
        }

        //check columns
        
        for (int column = 0; column < 5; column++) {
             
            for (int row = 0, test_check = 0; row < 5; row++) {
                if ((matrix.get(row).get(column)).equals("")) {
                    state = false;
                    break;
                }
                if (test_check == 0) {
                    test_value = matrix.get(row).get(column);
                    test_check++;
                    continue;
                }

                if ((matrix.get(row).get(column)) != test_value) {
                    state = false;
                    break;
                }
                if (row == 4 && matrix.get(row).get(column).equals(test_value)) {
                    winner = test_value;
                    return true;
                }
            }
        }

        //check diagnol \
        for (int rowcol = 0; rowcol < 5; rowcol++) {
            if ((matrix.get(0).get(0)).equals("")) {
                state = false;
                break;
            }
            if (rowcol == 0) {
                test_value = matrix.get(rowcol).get(rowcol);
                continue;
            }

            if ((matrix.get(rowcol).get(rowcol)) != test_value) {
                state = false;
                break;
            }
            if (rowcol == 4 && matrix.get(rowcol).get(rowcol).equals(test_value)) {
                winner = test_value;
                return true;
            }

        }

        //check diagnol /
        for (int row = 4, col = 0 ; col < 5 ; row--, col++ ) {
            if ((matrix.get(col).get(row)).equals("")) {
                state = false;
                break;
            }
            if (row == 4) {
                test_value = matrix.get(col).get(row);
                continue;
            }

            if ((matrix.get(col).get(row)) != test_value) {
                state = false;
                break;
            }
            if (col == 4 && matrix.get(col).get(row).equals(test_value)) {
                winner = test_value;
                return true;
            }

        }

        return state;
    }
    


    public static void Swap_Player(String local_player) {
        switch(local_player) {
            case "x":
                player = "o";
                game_status.setText(player + "'s turn");
                break;
            case "o":
                player = "x";
                game_status.setText(player + "'s turn");
                break;
        }
    }
    public static Button Make_Button(int x, int y, int row, int column) {
        //preliminaries
        Button button = new Button();
        button.setFont(new Font(40));
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setPrefWidth(106);
        button.setPrefHeight(106);


        

        //functionality
        button.setOnAction(event -> {
            if (button.getText().equals("") && Check_Win() == false) {
                button.setText(player);
                matrix.get(column).set(row, player);

                ///* 
                for (ArrayList<String> currentrow : matrix) {
                    for (String element : currentrow) {
                        System.out.print(element + " ");  
                    }
                    System.out.println();  
                }
                //*/


                if (Check_Win() == (false)) {
                    Swap_Player(player);
                }   
                if (Check_Win() == (true)) {
                    game_status.setText(winner + " has won");
                }
            }
             
        });
        TicTacToe_Buttons.add(button);
        return button;
    }

    @Override
    public void start(Stage primaryStage) {
        for (int i = 0; i < 5; i++) {
            matrix.add(new ArrayList<>(Arrays.asList("", "", "", "", "")));
        }
        game_status = new Label(player + "'s turn");
        game_status.setFont(new Font(50));
        game_status.setLayoutX(20);
        game_status.setLayoutY(610);

        Button restart_button = new Button();
        restart_button.setText("restart");
        restart_button.setFont(new Font(25));
        restart_button.setLayoutX(500);
        restart_button.setLayoutY(620);
        restart_button.setPrefWidth(106);
        restart_button.setPrefHeight(60);
        //functionality
        restart_button.setOnAction(event -> { 
            Clear_Matrix();
            player = "x";
            winner = "";
            game_status.setText(player + "'s turn");

        });



        Button c0r0 = Make_Button(16,0,0,0);
        Button c0r1 = Make_Button(16,120,0,1);
        Button c0r2 = Make_Button(16,240,0,2);
        Button c0r3 = Make_Button(16,360,0,3);
        Button c0r4 = Make_Button(16,480,0,4);

        Button c1r0 = Make_Button(138,0,1,0);
        Button c1r1 = Make_Button(138,120,1,1);
        Button c1r2 = Make_Button(138,240,1,2);
        Button c1r3 = Make_Button(138,360,1,3);
        Button c1r4 = Make_Button(138,480,1,4);

        Button c2r0 = Make_Button(260,0,2,0);
        Button c2r1 = Make_Button(260,120,2,1);
        Button c2r2 = Make_Button(260,240,2,2);
        Button c2r3 = Make_Button(260,360,2,3);
        Button c2r4 = Make_Button(260,480,2,4);

        Button c3r0 = Make_Button(382,0,3,0);
        Button c3r1 = Make_Button(382,120,3,1);
        Button c3r2 = Make_Button(382,240,3,2);
        Button c3r3 = Make_Button(382,360,3,3);
        Button c3r4 = Make_Button(382,480,3,4);

        Button c4r0 = Make_Button(504,0,4,0);
        Button c4r1 = Make_Button(504,120,4,1);
        Button c4r2 = Make_Button(504,240,4,2);
        Button c4r3 = Make_Button(504,360,4,3);
        Button c4r4 = Make_Button(504,480,4,4);


        Pane root = new Pane();
        root.getChildren().addAll(c0r0,c0r1,c0r2,c0r3,c0r4);
        root.getChildren().addAll(c1r0,c1r1,c1r2,c1r3,c1r4);
        root.getChildren().addAll(c2r0,c2r1,c2r2,c2r3,c2r4);
        root.getChildren().addAll(c3r0,c3r1,c3r2,c3r3,c3r4);
        root.getChildren().addAll(c4r0,c4r1,c4r2,c4r3,c4r4);

        root.getChildren().addAll(game_status, restart_button);
        

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, 626, 700); 
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) throws Exception {
        

        launch(args);
    }
}
