package com.kodilla.tictactoe;

import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TicTacToeApplication extends Application {
    private Image imageback = new Image("file:src/main/resources/tableTicTacToe.png");
    private Image cross = new Image("file:src/main/resources/cross.png");
    private Image circle = new Image("file:src/main/resources/circle.png");
    private Button button1 = new Button();
    private Button button2 = new Button();
    private Button button3 = new Button();
    private Button button4 = new Button();
    private Button button5 = new Button();
    private Button button6 = new Button();
    private Button button7 = new Button();
    private Button button8 = new Button();

    private Button button9 = new Button();

    private Button bRestart = new Button();
    private Button bStart = new Button();

    private Label winnerText = new Label();
    private Label chooseXorO= new Label();
    private RadioButton rbX =new RadioButton();
    private RadioButton rbO =new RadioButton();
private ToggleGroup tgXorO=new ToggleGroup();
    private Label moveFirst= new Label();
    private RadioButton rbPlayer =new RadioButton();
    private RadioButton rbComputer =new RadioButton();
    private ToggleGroup tgMoveFirst=new ToggleGroup();
    private Boolean turnX;
    private boolean gameOver;
    private int numColumn;
    private int numRow;
   private Button[] buttons = {button1,button2,button3,button4,button5,button6,button7,button8,button9};
    ArrayList<Button> buttonsArrayList;
    ArrayList<ImageView> imgArrayCross = new ArrayList<>();
    ArrayList<ImageView> imgArrayCircle = new ArrayList<>();
    private Random random =new Random();
    private int numbers;
    private int numbersInArray;
    GridPane grid = new GridPane();

    public void playXorO() {
     if (rbX.isSelected()) {
         turnX = true;
     } else if (rbO.isSelected()){
         turnX=false;
         }
     }

    public void checkIfGameOver() {
         String line="";
         for (int a=0; a<8;a++) {
             if (a == 0) {
                 line = button1.getText() + button2.getText() + button3.getText();
             } else if (a == 1) {
                 line = button4.getText() + button5.getText() + button6.getText();
             } else if (a == 2) {
                 line = button7.getText() + button8.getText() + button9.getText();
             } else if (a == 3) {
                 line = button1.getText() + button5.getText() + button9.getText();
             } else if (a == 4) {
                 line = button3.getText() + button5.getText() + button7.getText();
             } else if (a == 5) {
                 line = button1.getText() + button4.getText() + button7.getText();
             } else if (a == 6) {
                 line = button2.getText() + button5.getText() + button8.getText();
             } else if (a == 7) {
                 line = button3.getText() + button6.getText() + button9.getText();
             }

             if (line.equals("XXX")) {
                 winnerText.setText("X won!");
                 over();
             } else if (line.equals("OOO")) {
                 winnerText.setText("O won!");
                 over();
             }
         }
    }
    public void over(){
        gameOver=true;
        for (int i=0; i<9;i++){
            buttons[i].setDisable(true);
        }
    }

    public  void draw(){
        List<Integer> checkDraw = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText() != "") {
                checkDraw.add(i);
                }
            }
        if (checkDraw.size()==9 && !gameOver){
            winnerText.setText("Draw!");
        }
    }
    public void restart(){
        gameOver=false;
        turnX=true;
        for (int i=0; i<9;i++){
            buttons[i].setDisable(false);
            buttons[i].setText("");
        }
    }
    public void beginSetings(){
        gameOver=false;
        turnX=true;
        bStart.setDisable(false);
        bRestart.setDisable(true);
        for (int i=0; i<9;i++){
            buttons[i].setDisable(true);
            buttons[i].setText("");
        }
    }
public void adjustNumRowAndColumn(int numbersButton) {
        if (numbersButton<3){
            numRow=1;
            numColumn =numbersButton;
        } else if (numbersButton>=3 &&numbersButton<6 ){
            numRow=2;
            numColumn =numbersButton-3;
        }  else if (numbersButton>=6 &&numbersButton<9 ){
        numRow=3;
        numColumn =numbersButton-6;
        }
    }
public void createButtons() {
    for (int i = 0; i < 9; i++) {
        adjustNumRowAndColumn(i);
        grid.add(buttons[i], numColumn, numRow);
        buttons[i].setPrefSize(200, 200);
        buttons[i].setBackground(Background.fill(Color.WHITE));
    }
}

public void arrayCross(){
    for (int i = 0; i < 9; i++) {
        imgArrayCross.add(new ImageView(cross));
    }
}
public void arrayCircle(){
        for (int i = 0; i < 9; i++) {
            imgArrayCircle.add(new ImageView(circle));
        }
   }
   public void playComputer(){
       List<Integer> numbersComputer = new ArrayList<>();

       for ( int t = 0; t < 9; t++) {
           if (buttons[t].getText() == "") {
               numbersComputer.add(t);
           }
       }
       numbersInArray = random.nextInt(numbersComputer.size());
       numbers = numbersComputer.get(numbersInArray);
       adjustNumRowAndColumn(numbers); //nadanie odpowiednich numerów wierszy i kolumn dla ruchu komputera
       if (turnX && !gameOver) {
           buttons[numbers].setText("X");
           grid.add(imgArrayCross.get(numbers), numColumn, numRow);
       } else if (!turnX && !gameOver) {
           buttons[numbers].setText("O");
           grid.add(imgArrayCircle.get(numbers), numColumn, numRow);
       }
       checkIfGameOver();
       draw();
       turnX = !turnX;
    }
public void move() {
    bStart.setOnMouseClicked(mouseEvent-> {
                if (rbComputer.isSelected()) {
                    turnX=!turnX;
                    playComputer();
                }
         });
    buttonsArrayList = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));
    buttonsArrayList.forEach(button -> {
        button.setOnMouseClicked(mouseEvent-> {
                    for (int n = 0; n < buttonsArrayList.size(); n++) {
                        if (button.equals(buttonsArrayList.get(n))) {
                            Button tempButton = buttonsArrayList.get(n);
                            int tempNumbersButton = n;
                            adjustNumRowAndColumn(tempNumbersButton);
                                    if (turnX && !gameOver) {
                                        tempButton.setText("X");
                                        grid.add(imgArrayCross.get(n), numColumn, numRow);
                                    } else if (!turnX&& !gameOver) {
                                        tempButton.setText("O");
                                        grid.add(imgArrayCircle.get(n), numColumn, numRow);
                                    }
                                    checkIfGameOver();
                                    draw();
                                    turnX = !turnX;
                                    //computer move
                            playComputer();
                        }
                    }

        });
    });
}


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        grid.setAlignment(Pos.TOP_LEFT);
        grid.setPadding(new Insets(10,90,10,90)); //11.5,12.5,13.5,14.5 odstępy
        RowConstraints row0=new RowConstraints(80);
        row0.setValignment(VPos.CENTER);
        grid.getRowConstraints().add(row0);
        for (int i = 1; i <= 3; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(200));
            grid.getRowConstraints().add(new RowConstraints(200));
        }
        RowConstraints row4=new RowConstraints(20);
        row4.setValignment(VPos.BOTTOM);
        grid.getRowConstraints().add(row4);
        RowConstraints row5=new RowConstraints(20);
        row5.setValignment(VPos.BOTTOM);
        grid.getRowConstraints().add(row5);
        RowConstraints row6=new RowConstraints(20);
        row6.setValignment(VPos.BOTTOM);
        grid.getRowConstraints().add(row6);

        grid.setHgap(10);
        grid.setVgap(10);
        //grid.setGridLinesVisible(true);
        grid.setBackground(background);

        winnerText.setTextFill(Color.RED);
        winnerText.setFont(new Font("Arial",24));
        winnerText.setText("TicTacToe");
        grid.add(winnerText,1,0);

        chooseXorO.setTextFill(Color.BLACK);
        chooseXorO.setFont(new Font("Arial",18));
        chooseXorO.setText("You play X or O ?");
        grid.add(chooseXorO,0,4);

        rbX.setFont(new Font("Arial",18));
        rbX.setText("  Cross");
        rbX.setToggleGroup(tgXorO);
        rbX.setSelected(true);
        grid.add(rbX,0,5);

        rbO.setFont(new Font("Arial",18));
        rbO.setText("  Circle");
        rbO.setToggleGroup(tgXorO);
        grid.add(rbO,0,6);

        moveFirst.setTextFill(Color.BLACK);
        moveFirst.setFont(new Font("Arial",18));
        moveFirst.setText("Who move first ?");
        grid.add(moveFirst,1,4);

        rbPlayer.setFont(new Font("Arial",18));
        rbPlayer.setText("  Player");
        rbPlayer.setToggleGroup(tgMoveFirst);
        rbPlayer.setSelected(true);
        grid.add(rbPlayer,1,5);

        rbComputer.setFont(new Font("Arial",18));
        rbComputer.setText("  Computer");
        rbComputer.setToggleGroup(tgMoveFirst);
        grid.add(rbComputer,1,6);

        bStart.setText("Start");
        bStart.setPrefSize(120, 20);
        grid.add(bStart,2,4);

        bRestart.setText("RESTART");
        bRestart.setPrefSize(120, 20);
        grid.add(bRestart,2,6);


        beginSetings();
        createButtons();
        arrayCross();
        arrayCircle();
        move();

       bRestart.setOnAction((e) -> {
            grid.getChildren().removeAll(imgArrayCross);
            grid.getChildren().removeAll(imgArrayCircle);
            rbComputer.setDisable(false);
            rbPlayer.setDisable(false);
            rbO.setDisable(false);
            rbX.setDisable(false);
            bRestart.setDisable(true);
            bStart.setDisable(false);
            winnerText.setText("TicTacToe");
            beginSetings();

       });
        bStart.setOnAction((e) -> {
           rbComputer.setDisable(true);
           rbPlayer.setDisable(true);
           rbO.setDisable(true);
           rbX.setDisable(true);
           bRestart.setDisable(false);
           bStart.setDisable(true);
           restart();
           playXorO();

        });

        Scene scene = new Scene(grid, 800, 900, Color.BLACK);

        primaryStage.setResizable(false);
        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


}





