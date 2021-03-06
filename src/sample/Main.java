package sample;

import database.MySQL;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;


public class Main extends Application {

    // data inputs
    String election_Name;
    int position;
    String s1, s2;
    String creator_id = "u1001";
    String elect_id;
    static MySQL db = new MySQL();

    public static void main(String[] args) {
        db.connectJDBC();
        db.test();
        launch(args);
    }

    Group group = new Group();

    Group groupV = new Group();
    Group groupR = new Group();


    Group groupE1 = new Group();
    Group groupE = new Group();
    Group groupE0 = new Group();
    Group groupE2 = new Group();


    //function switch sub scene(tabs) on selecting
    public String randomid() {
        // create a string of uppercase and lowercase characters and numbers
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        // combine all strings
        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 10;

        for (int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphaNumeric.length());

            // get character specified by index
            // from the string
            char randomChar = alphaNumeric.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        String randomString = sb.toString();
        return randomString;
    }

    //function switch sub scene(tabs) on selecting
    public void switchScene(SubScene rect1, SubScene rect2, SubScene rect3) {
        rect2.setVisible(false);
        rect3.setVisible(false);
        rect1.setVisible(true);
    }

    //function switch tab focus rectangle colour on selecting
    public void switchtab(Rectangle rect1, Rectangle rect2, Rectangle rect3) {
        rect1.setFill(Color.BLACK);
        rect2.setFill(Color.WHITE);
        rect3.setFill(Color.WHITE);
    }

    //function switch tab text colour on selecting
    public void switchtabtc(Button bt1, Button bt2, Button bt3, Font font1, Font font2) {
        bt1.setTextFill(Color.WHITE);
        bt1.setFont(font1);
        bt2.setTextFill(Color.BLACK);
        bt2.setFont(font2);
        bt3.setTextFill(Color.BLACK);
        bt3.setFont(font2);
    }


    //function switch subwindows in election tab
    public void switchEgroup(Group g1, Group g3, int pos, String elect_id) {


        Font font1 = Font.font("Helvetica", FontWeight.BOLD, 14);

        DropShadow ds = new DropShadow();
        ds.setColor(Color.rgb(230, 230, 230));
        ds.setBlurType(BlurType.GAUSSIAN);
        ds.setRadius(200);
        ds.setSpread(.03);

        //ELECTION TAB2 CONTENT

        Rectangle rectE21 = new Rectangle();
        rectE21.setHeight(500);
        rectE21.setWidth(940);
        rectE21.setX(30);
        rectE21.setY(30);
        rectE21.setFill(Color.rgb(246, 246, 246));
        rectE21.setArcWidth(20);
        rectE21.setArcHeight(20);

        Rectangle rectE22 = new Rectangle();
        rectE22.setHeight(60);
        rectE22.setWidth(870);
        rectE22.setX(60);
        rectE22.setY(55);
        rectE22.setFill(Color.WHITE);
        rectE22.setArcWidth(20);
        rectE22.setArcHeight(20);
        rectE22.setEffect(ds);


        Text tE21 = new Text("Position");
        Text tE22 = new Text("Number Of Candidates");
        Text tE23 = new Text("Verify");

        tE21.setX(120);
        tE21.setY(90);

        tE22.setX(480);
        tE22.setY(90);

        tE23.setX(820);
        tE23.setY(90);


        ListView lsE2 = new ListView();
        lsE2.setBorder(Border.EMPTY);
        lsE2.setStyle("-fx-background-color:#F4F4F4; -fx-control-inner-background:#F4F4F4  ;-fx-control-inner-background-alt:#F4F4F4");
        lsE2.setSelectionModel(null);

        int u = 0;
        while (u != pos) {
            addItem(lsE2, u, elect_id);
            u++;
        }

        //PROCEED button
        Button buttonE1 = new Button();
        buttonE1.setText("Proceed");
        buttonE1.setFont(font1);
        buttonE1.setTextFill(Color.WHITE);
        buttonE1.setLayoutX(520);
        buttonE1.setLayoutY(450);
        buttonE1.setPrefSize(390, 40);
        buttonE1.setStyle(
                "-fx-background-color: #000000;"
        );
        buttonE1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                lsE2.getItems().clear();
                switchE2group(g1, groupE0, pos, Integer.parseInt(s2), elect_id);


            }
        });


        HBox hb = new HBox();
        hb.getChildren().add(buttonE1);
        hb.setAlignment(Pos.CENTER);

        SubScene s1 = new SubScene(hb, 830, 70);

        buttonE1.setOnMouseEntered(mouseEvent -> {
            s1.setCursor(Cursor.HAND);
        });

        buttonE1.setOnMouseExited(mouseEvent -> {
            s1.setCursor(Cursor.DEFAULT);
        });

        lsE2.getItems().add(s1);


        SubScene subE23 = new SubScene(lsE2, 860, 380);
        subE23.setFill(Color.rgb(246, 246, 246));
        subE23.setLayoutX(60);
        subE23.setLayoutY(130);


        groupE0.getChildren().add(rectE21);
        groupE0.getChildren().add(rectE22);
        groupE0.getChildren().add(tE21);
        groupE0.getChildren().add(tE22);
        groupE0.getChildren().add(tE23);
        groupE0.getChildren().add(subE23);


        g1.getChildren().remove(g3);
        g1.getChildren().add(groupE0);

    }


    //function switch subWindows in election tab
    public void switchE2group(Group g1, Group g3, int pos, int num, String elect_id) {

        Font font1 = Font.font("Helvetica", FontWeight.BOLD, 14);

        DropShadow ds = new DropShadow();
        ds.setColor(Color.rgb(230, 230, 230));
        ds.setBlurType(BlurType.GAUSSIAN);
        ds.setRadius(200);
        ds.setSpread(.03);


        //ELECTION TAB2 CONTENT

        Rectangle rectE21 = new Rectangle();
        rectE21.setHeight(500);
        rectE21.setWidth(940);
        rectE21.setX(30);
        rectE21.setY(30);
        rectE21.setFill(Color.rgb(246, 246, 246));
        rectE21.setArcWidth(20);
        rectE21.setArcHeight(20);


        Rectangle rectE22 = new Rectangle();
        rectE22.setHeight(60);
        rectE22.setWidth(870);
        rectE22.setX(60);
        rectE22.setY(55);
        rectE22.setFill(Color.WHITE);
        rectE22.setArcWidth(20);
        rectE22.setArcHeight(20);
        rectE22.setEffect(ds);


        Text tE21 = new Text("Name");
        Text tE22 = new Text("Gender");
        Text tE23 = new Text("Voter ID");
        Text tE24 = new Text("Verify");

        tE21.setX(190);
        tE21.setY(90);

        tE22.setX(425);
        tE22.setY(90);

        tE23.setX(565);
        tE23.setY(90);

        tE24.setX(790);
        tE24.setY(90);

        ListView lsE3 = new ListView();
        lsE3.setBorder(Border.EMPTY);
        lsE3.setBackground(Background.EMPTY);
        lsE3.setStyle("-fx-background-color:#F4F4F4; -fx-control-inner-background:#F4F4F4  ;-fx-control-inner-background-alt:#F4F4F4");
        lsE3.setSelectionModel(null);

        try {
            ResultSet rs = db.getPosts(elect_id);

            while (
                    rs.next()) {

                addItem2(lsE3, rs.getString("id"), rs.getString("name"), rs.getInt("num_candidates"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        //PROCEED button
        Button buttonE1 = new Button();
        buttonE1.setText("Confirm");
        buttonE1.setFont(font1);
        buttonE1.setTextFill(Color.WHITE);
        buttonE1.setLayoutX(520);
        buttonE1.setLayoutY(450);
        buttonE1.setPrefSize(390, 40);
        buttonE1.setStyle(
                "-fx-background-color: #000000; focused:-fx-background-color: #FFFFFF ;"
        );
        buttonE1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                lsE3.getItems().clear();
                switchE3group(g1, groupE2);


            }
        });


        HBox hb = new HBox();
        hb.getChildren().add(buttonE1);
        hb.setAlignment(Pos.CENTER);

        SubScene s1 = new SubScene(hb, 830, 70);

        buttonE1.setOnMouseEntered(mouseEvent -> {
            s1.setCursor(Cursor.HAND);
        });

        buttonE1.setOnMouseExited(mouseEvent -> {
            s1.setCursor(Cursor.DEFAULT);
        });

        lsE3.getItems().add(s1);


        SubScene subE23 = new SubScene(lsE3, 880, 360);
        subE23.setFill(Color.rgb(246, 246, 246));
        subE23.setLayoutX(60);
        subE23.setLayoutY(150);

        groupE2.getChildren().add(rectE21);
        groupE2.getChildren().add(rectE22);
        groupE2.getChildren().add(tE21);
        groupE2.getChildren().add(tE22);
        groupE2.getChildren().add(tE23);
        groupE2.getChildren().add(tE24);
        groupE2.getChildren().add(subE23);

        g1.getChildren().remove(g3);
        g1.getChildren().add(groupE2);

    }

    public void addItem2(ListView l1, String postId, String postLabel, int num) {
        //shadow effect to bottom navbar
        DropShadow ds = new DropShadow();
        ds.setColor(Color.rgb(230, 230, 230));
        ds.setBlurType(BlurType.GAUSSIAN);
        ds.setRadius(200);
        ds.setSpread(.03);


        Rectangle rectE22 = new Rectangle();
        rectE22.setHeight(60);
        rectE22.setWidth(830);
        rectE22.setX(60);
        rectE22.setY(55);
        rectE22.setFill(Color.WHITE);
        rectE22.setArcWidth(20);
        rectE22.setArcHeight(20);
//        rectE22.setEffect(ds);

        Rectangle rectE23 = new Rectangle();
        rectE23.setHeight(60);
        rectE23.setWidth(20);
        rectE23.setFill(Color.TRANSPARENT);

        Rectangle rectE24 = new Rectangle();
        rectE24.setHeight(30);
        rectE24.setWidth(8);
        rectE24.setX(60);
        rectE24.setY(55);
        rectE24.setFill(Color.BLACK);
        rectE24.setArcWidth(5);
        rectE24.setArcHeight(5);
        rectE24.setEffect(ds);


        Text tE21 = new Text(postLabel);

        tE21.setX(120);
        tE21.setY(90);


        HBox hb2 = new HBox();
        hb2.getChildren().add(rectE23);
        hb2.getChildren().add(rectE24);
        hb2.getChildren().add(tE21);
        hb2.setSpacing(40);
        hb2.setAlignment(Pos.CENTER_LEFT);
        hb2.setBackground(Background.EMPTY);

        StackPane sp = new StackPane(rectE22, hb2);

        SubScene sub2 = new SubScene(sp, 850, 70);
        sub2.setFill(Color.rgb(246, 246, 246));
        l1.getItems().add(sub2);


        int n = 0;
        int t;
        while (n != num) {
            t = n + 1;
            //TextField 1
            TextField eName3 = new TextField();
            eName3.setPromptText("Candidate name " + t);
            eName3.setPrefSize(200, 40);
            eName3.setLayoutX(520);
            eName3.setPadding(new Insets(10, 10, 10, 25));
            eName3.setLayoutY(200);
            eName3.setStyle("-fx-focus-color: transparent;-fx-background-color: -fx-control-inner-background;");
            eName3.setEffect(ds);
            eName3.setEditable(false);


            //TextField 1
            TextField eName4 = new TextField();
            eName4.setPromptText("F / M");
            eName4.setPrefSize(100, 40);
            eName4.setLayoutX(520);
            eName4.setPadding(new Insets(10, 10, 10, 25));
            eName4.setLayoutY(200);
            eName4.setStyle("-fx-focus-color: transparent;-fx-background-color: -fx-control-inner-background;");
            eName4.setEffect(ds);
            eName4.setEditable(false);

            //TextField 1
            TextField eName5 = new TextField();
            eName5.setPromptText("190***");
            eName5.setPrefSize(200, 40);
            eName5.setLayoutX(520);
            eName5.setPadding(new Insets(10, 10, 10, 25));
            eName5.setLayoutY(200);
            eName5.setStyle("-fx-focus-color: transparent;-fx-background-color: -fx-control-inner-background;");
            eName5.setEffect(ds);

            //checkbox
            CheckBox ch = new CheckBox();
            ch.setCursor(Cursor.HAND);

            if (eName5.getText().isEmpty()) {
                ch.setDisable(true);
            } else {
                ch.setDisable(false);

            }

            eName5.textProperty().addListener(
                    (observable, oldValue, newValue) -> {
                        if (eName5.getText().isEmpty()) {
                            ch.setDisable(true);
                        } else {
                            ch.setDisable(false);

                        }

                    }
            );


            ch.selectedProperty().addListener(
                    (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                        if (ch.isSelected()) {
                            try {
                                ResultSet rs = db.getCandidate(eName5.getText());
                                rs.next();

                                eName3.setText(rs.getString("name"));
                                eName4.setText(rs.getString("gender"));

                                eName5.setEditable(false);

                                db.addCandidate(randomid(), postId, eName5.getText(), 0);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        if (!ch.isSelected()) {
                            eName5.setEditable(true);
                        }

                        System.out.println(s1);
                        System.out.println(s2);
                    });

            HBox hb = new HBox();
            hb.getChildren().add(eName3);
            hb.getChildren().add(eName4);
            hb.getChildren().add(eName5);
            hb.getChildren().add(ch);
            hb.setSpacing(40);
            hb.setAlignment(Pos.CENTER);
            hb.setBackground(Background.EMPTY);

            SubScene sub1 = new SubScene(hb, 850, 70);
            sub1.setFill(Color.rgb(246, 246, 246));

            l1.getItems().add(sub1);

            n++;
        }

    }


    //add item to list
    public void addItem(ListView l1, int i, String elect_id) {
        String s = Integer.toString(i);
        //shadow effect to bottom navbar
        DropShadow ds = new DropShadow();
        ds.setColor(Color.rgb(230, 230, 230));
        ds.setBlurType(BlurType.GAUSSIAN);
        ds.setRadius(200);
        ds.setSpread(.03);


        //TextField 1
        TextField eName3 = new TextField();
        eName3.setPromptText("Position name " + s);
        eName3.setPrefSize(300, 40);
        eName3.setLayoutX(520);
        eName3.setPadding(new Insets(10, 10, 10, 25));
        eName3.setLayoutY(200);
        eName3.setStyle("-fx-focus-color: transparent;-fx-background-color: -fx-control-inner-background;");
        eName3.setEffect(ds);


        //TextField 1
        TextField eName4 = new TextField();
        eName4.setPromptText("0");
        eName4.setPrefSize(300, 40);
        eName4.setLayoutX(520);
        eName4.setPadding(new Insets(10, 10, 10, 25));
        eName4.setLayoutY(200);
        eName4.setStyle("-fx-focus-color: transparent;-fx-background-color: -fx-control-inner-background;");
        eName4.setEffect(ds);

        //checkbox
        CheckBox ch = new CheckBox();
        ch.setCursor(Cursor.HAND);

        if (eName3.getText().isEmpty() || eName4.getText().isEmpty()) {
            ch.setDisable(true);
        } else {
            ch.setDisable(false);

        }

        eName3.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (eName3.getText().isEmpty() || eName4.getText().isEmpty()) {
                        ch.setDisable(true);
                    } else {
                        ch.setDisable(false);

                    }

                }
        );

        eName4.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (eName3.getText().isEmpty() || eName4.getText().isEmpty()) {
                        ch.setDisable(true);
                    } else {
                        ch.setDisable(false);

                    }

                }
        );

        ch.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {

                    if (ch.isSelected()) {
                        eName3.setEditable(false);
                        eName4.setEditable(false);
                        s1 = eName3.getText();
                        s2 = eName4.getText();

                        db.addPost(randomid(), elect_id, s1, Integer.parseInt(s2));
                    }
                    if (!ch.isSelected()) {
                        eName3.setEditable(true);
                        eName4.setEditable(true);
                    }

                    System.out.println(s1);
                    System.out.println(s2);
                });

        HBox hb = new HBox();
        hb.getChildren().add(eName3);
        hb.getChildren().add(eName4);
        hb.getChildren().add(ch);
        hb.setSpacing(60);
        hb.setAlignment(Pos.CENTER);
        hb.setBackground(Background.EMPTY);

        SubScene s1 = new SubScene(hb, 830, 70);

        l1.setBorder(Border.EMPTY);
        s1.setFill(Color.rgb(246, 246, 246));
        l1.setBackground(Background.EMPTY);

        l1.getItems().add(s1);
    }

    public void switchE3group(Group g1, Group g2) {
        g1.getChildren().remove(g2);
        g1.getChildren().add(groupE);
    }


    @Override
    public void start(Stage primaryStage) {


        Scene scene = new Scene(group, 1000, 650);

        //Sub scene for each tab
        SubScene sceneE = new SubScene(groupE1, 1000, 600);
        SubScene sceneV = new SubScene(groupV, 1000, 600);
        SubScene sceneR = new SubScene(groupR, 1000, 600);

        //Bottom navbar white rounded rectangle
        Rectangle rect = new Rectangle();
        rect.setHeight(60);
        rect.setWidth(800);
        rect.setX(100);
        rect.setY(550);
        rect.setArcWidth(20);
        rect.setArcHeight(20);
        rect.setFill(Color.WHITE);

        //shadow effect to bottom navbar
        DropShadow ds = new DropShadow();
        ds.setColor(Color.rgb(230, 230, 230));
        ds.setBlurType(BlurType.GAUSSIAN);
        ds.setRadius(200);
        ds.setSpread(.03);

        rect.setEffect(ds);

        DropShadow sd = new DropShadow();
        sd.setColor(Color.WHITE);
        sd.setBlurType(BlurType.GAUSSIAN);
        sd.setRadius(200);
        sd.setSpread(.03);


        //tabs
        Button btn2 = new Button("Result");
        Button btn1 = new Button("Vote");
        Button btn = new Button("Election");

        //TAB TEXT FONTS
        Font font1 = Font.font("Helvetica", FontWeight.BOLD, 14);
        Font font2 = Font.font("Helvetica", 14);
        // Font fontE1 = Font.font("Roboto", FontWeight.SEMI_BOLD, 13);


        //Focus Rectangle for tabs
        Rectangle recti1 = new Rectangle();
        recti1.setHeight(40);
        recti1.setWidth(200);
        recti1.setX(120);
        recti1.setY(560);
        recti1.setArcWidth(20);
        recti1.setArcHeight(20);
        recti1.setFill(Color.BLACK);

        Rectangle recti2 = new Rectangle();
        recti2.setHeight(40);
        recti2.setWidth(200);
        recti2.setX(390);
        recti2.setY(560);
        recti2.setArcWidth(20);
        recti2.setArcHeight(20);
        recti2.setFill(Color.WHITE);

        Rectangle recti3 = new Rectangle();
        recti3.setHeight(40);
        recti3.setWidth(200);
        recti3.setX(670);
        recti3.setY(560);
        recti3.setArcWidth(20);
        recti3.setArcHeight(20);
        recti3.setFill(Color.WHITE);


        //Button for election tab
        btn.setPrefSize(200, 60);
        btn.setBorder(Border.EMPTY);
        btn.setTextFill(Color.WHITE);
        btn.setStyle("-fx-background-color: transparent; "
        );
        btn.setLayoutX(120);
        btn.setLayoutY(550);
        btn.setOnAction((MouseClickEvent) -> {
            switchScene(sceneE, sceneV, sceneR);
            switchtab(recti1, recti2, recti3);
            switchtabtc(btn, btn1, btn2, font1, font2);
        });

        btn.setOnMouseEntered(mouseEvent -> {
            scene.setCursor(Cursor.HAND); //Change cursor to hand
        });

        btn.setOnMouseExited(mouseEvent -> {
            scene.setCursor(Cursor.DEFAULT); //Change cursor to hand
        });

        btn.setFont(font1);


        //button for vote tab
        btn1.setPrefSize(200, 60);
        btn1.setBorder(Border.EMPTY);
        btn1.setStyle("-fx-background-color: transparent; "
        );
        btn1.setLayoutX(390);
        btn1.setLayoutY(550);
        btn1.setTextFill(Color.BLACK);
        btn1.setOnAction((MouseClickEvent) -> {

            switchScene(sceneV, sceneE, sceneR);
            switchtab(recti2, recti1, recti3);
            switchtabtc(btn1, btn, btn2, font1, font2);

        });

        btn1.setOnMouseEntered(mouseEvent -> {
            scene.setCursor(Cursor.HAND); //Change cursor to hand
        });

        btn1.setOnMouseExited(mouseEvent -> {
            scene.setCursor(Cursor.DEFAULT); //Change cursor to hand
        });


        btn1.setFont(font2);


        //button for election tab
        btn2.setPrefSize(200, 60);
        btn2.setBorder(Border.EMPTY);
        btn2.setStyle("-fx-background-color: transparent; "
        );
        btn2.setLayoutX(670);
        btn2.setLayoutY(550);
        btn2.setTextFill(Color.BLACK);
        btn2.setOnAction((MouseClickEvent) -> {
            switchScene(sceneR, sceneV, sceneE);
            switchtab(recti3, recti2, recti1);
            switchtabtc(btn2, btn, btn1, font1, font2);
        });
        btn2.setOnMouseEntered(mouseEvent -> {
            scene.setCursor(Cursor.HAND); //Change cursor to hand
        });

        btn2.setOnMouseExited(mouseEvent -> {
            scene.setCursor(Cursor.DEFAULT); //Change cursor to hand
        });

        btn2.setFont(font2);


        //ELECTION TAB CONTENTS
        Rectangle rectE1 = new Rectangle();
        rectE1.setHeight(500);
        rectE1.setWidth(940);
        rectE1.setX(30);
        rectE1.setY(30);
        rectE1.setFill(Color.rgb(246, 246, 246));
        rectE1.setArcWidth(20);
        rectE1.setArcHeight(20);

        Rectangle rectE2 = new Rectangle();
        rectE2.setHeight(450);
        rectE2.setWidth(2);
        rectE2.setX(450);
        rectE2.setY(55);
        rectE2.setFill(Color.WHITE);
        rectE2.setArcWidth(1);
        rectE2.setArcHeight(1);


        Rectangle rectE3 = new Rectangle();
        rectE3.setHeight(10);
        rectE3.setWidth(3);
        rectE3.setX(450);
        rectE3.setY(55);
        rectE3.setFill(Color.BLACK);
        rectE3.setArcWidth(3);
        rectE3.setArcHeight(3);

        Text tE1 = new Text("Election");
//        tE1.setFont(font1);
        tE1.setFill(Color.BLACK);


        HBox hbE1 = new HBox();
        hbE1.getChildren().add(rectE3);
        hbE1.getChildren().add(tE1);
        hbE1.setSpacing(20);

        hbE1.setAlignment(Pos.CENTER_LEFT);

        SubScene subE2 = new SubScene(hbE1, 360, 50);


        ListView lsE1 = new ListView();
        lsE1.setBorder(Border.EMPTY);
        lsE1.setSelectionModel(null);
        lsE1.setBackground(Background.EMPTY);
        lsE1.setStyle("-fx-background-color:#F4F4F4; -fx-control-inner-background:#F4F4F4  ;-fx-control-inner-background-alt:#F4F4F4");
//        lsE1.onScrollToProperty().addListener();

        int n = 4;
        while (n != 0) {
            addOnElection(lsE1);
            n--;
        }


        SubScene subE9 = new SubScene(lsE1, 360, 400);

        VBox vbE1 = new VBox();
        vbE1.getChildren().add(subE2);
        vbE1.getChildren().add(subE9);

        vbE1.setBackground(Background.EMPTY);
        SubScene subE1 = new SubScene(vbE1, 360, 450);
        subE1.setLayoutY(55);
        subE1.setLayoutX(55);

        Text tEh = new Text("Create Election");
        tEh.setFont(font1);


        Image imgh = new Image("sample/election1.png");
        ImageView viewh = new ImageView(imgh);
        viewh.setFitHeight(30);
        viewh.setPreserveRatio(true);
        viewh.setPreserveRatio(true);

        Rectangle rectE9 = new Rectangle();
        rectE9.setHeight(2);
        rectE9.setWidth(230);
        rectE9.setFill(Color.BLACK);
        rectE9.setArcWidth(1);
        rectE9.setArcHeight(1);

        HBox hbh = new HBox();
        hbh.getChildren().add(viewh);
        hbh.getChildren().add(tEh);
        hbh.getChildren().add(rectE9);
        hbh.setAlignment(Pos.CENTER);
        hbh.setSpacing(20);

        SubScene sc = new SubScene(hbh, 460, 30);
        sc.setLayoutY(65);
        sc.setLayoutX(480);


        Text textE1 = new Text("Election Name  : ");
        textE1.setX(520);
        textE1.setY(220);
        textE1.setFill(Color.rgb(108, 108, 108));
        textE1.setFont(font1);

        Text textE2 = new Text("Number Of Posts  : ");
        textE2.setX(520);
        textE2.setY(340);
        textE2.setFill(Color.rgb(108, 108, 108));
        textE2.setFont(font1);


        Image img = new Image("sample/right.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(20);
        view.setPreserveRatio(true);


        //TextField 1
        TextField eName1 = new TextField();
        eName1.setPromptText(" Union  election 2021");
        eName1.setPrefSize(400, 40);
        eName1.setLayoutX(520);
        eName1.setPadding(new Insets(10, 10, 10, 25));
        eName1.setLayoutY(240);
        eName1.setStyle("-fx-background-color: -fx-control-inner-background;");
        eName1.setEffect(ds);

        //TextField2
        TextField eName2 = new TextField();
        eName2.setPromptText("10 or 15...");
        eName2.setPrefSize(400, 40);
        eName2.setLayoutX(520);
        eName2.setLayoutY(365);
        eName2.setPadding(new Insets(10, 10, 10, 25));
        eName2.setStyle("-fx-background-color: -fx-control-inner-background;");
        eName1.setEffect(ds);


        //PROCEED button
        Button buttonE1 = new Button();
        buttonE1.setGraphic(view);
        buttonE1.setGraphicTextGap(10);
        buttonE1.setFont(font1);
        buttonE1.setText("Proceed");
        buttonE1.setTextFill(Color.WHITE);
        buttonE1.setLayoutX(520);
        buttonE1.setLayoutY(450);
        buttonE1.setPrefSize(390, 40);
        buttonE1.setStyle(
                "-fx-background-color: #000000; "
        );
        buttonE1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                election_Name = eName1.getText();
                position = Integer.parseInt(eName2.getText());

                elect_id = randomid();
                db.addElection(elect_id, creator_id, election_Name, false, false);
                switchEgroup(groupE1, groupE, position, elect_id);

            }
        });

        DropShadow dsB = new DropShadow();
        dsB.setColor(Color.rgb(134, 134, 134));
        dsB.setBlurType(BlurType.GAUSSIAN);
        dsB.setRadius(20);
        dsB.setSpread(.01);


        DropShadow dsE = new DropShadow();
        dsE.setColor(Color.rgb(134, 134, 134));
        dsE.setBlurType(BlurType.GAUSSIAN);
        dsE.setRadius(20);
        dsE.setSpread(.01);

        rect.setEffect(ds);

        EventHandler<MouseEvent> mouseEntered = e1 -> {
            sceneE.setCursor(Cursor.HAND);
            buttonE1.setPrefSize(389, 40);
            buttonE1.setLayoutX(520);
            buttonE1.setLayoutY(451);
            buttonE1.setGraphicTextGap(15);
            buttonE1.setStyle("-fx-background-color: #131313; ");
        };

        buttonE1.setOnMouseEntered(mouseEntered);

        EventHandler<MouseEvent> mouseExited = e2 -> {
            sceneE.setCursor(Cursor.DEFAULT);
            buttonE1.setLayoutX(520);
            buttonE1.setLayoutY(450);
            buttonE1.setPrefSize(390, 40);
            buttonE1.setGraphicTextGap(10);
            buttonE1.setStyle(
                    "-fx-background-color: #000000;  ;"
            );
        };

        buttonE1.setOnMouseExited(mouseExited);


        groupE.getChildren().add(rectE1);
        groupE.getChildren().add(rectE2);
        groupE.getChildren().add(textE1);
        groupE.getChildren().add(textE2);
        groupE.getChildren().add(buttonE1);
        groupE.getChildren().add(eName1);
        groupE.getChildren().add(eName2);
        groupE.getChildren().add(subE1);
        groupE.getChildren().add(sc);


        groupE1.getChildren().add(groupE);


        //VOTE TAB CONTENTS


        Image img2 = new Image("sample/election3.png");
        ImageView view2 = new ImageView(img2);
        view2.setFitHeight(100);
        view2.setPreserveRatio(true);
        view2.setPreserveRatio(true);

        VBox vbV1 = new VBox();
        vbV1.setBackground(Background.EMPTY);
        vbV1.setAlignment(Pos.CENTER);
        vbV1.getChildren().add(view2);
        SubScene subV1 = new SubScene(vbV1, 400, 450);
        subV1.setLayoutX(60);
        subV1.setLayoutY(60);


        Rectangle rectV1 = new Rectangle();
        rectV1.setHeight(500);
        rectV1.setWidth(940);
        rectV1.setX(30);
        rectV1.setY(30);
        rectV1.setFill(Color.rgb(246, 246, 246));
        rectV1.setArcWidth(20);
        rectV1.setArcHeight(20);

        Rectangle rectV2 = new Rectangle();
        rectV2.setHeight(450);
        rectV2.setWidth(450);
        rectV2.setX(480);
        rectV2.setY(55);
        rectV2.setFill(Color.WHITE);
        rectV2.setArcWidth(20);
        rectV2.setArcHeight(20);

        rectV2.setEffect(ds);


        Text textV1 = new Text("Voter Id ");
        textV1.setX(520);
        textV1.setY(140);
        textV1.setFill(Color.rgb(0, 0, 0));
        textV1.setFont(font1);

        Text textV2 = new Text("Password ");
        textV2.setX(520);
        textV2.setY(280);
        textV2.setFill(Color.rgb(0, 0, 0));
        textV2.setFont(font1);


        Image img1 = new Image("sample/right.png");
        ImageView viewl = new ImageView(img1);
        viewl.setFitHeight(20);
        viewl.setPreserveRatio(true);
        viewl.setPreserveRatio(true);

        //TextField 1
        TextField vName1 = new TextField();
        vName1.setPrefSize(370, 40);
        vName1.setLayoutX(520);
        vName1.setPadding(new Insets(10, 10, 10, 25));
        vName1.setLayoutY(170);
        vName1.setStyle("-fx-focus-color: transparent;-fx-background-color: #EEEEEE;");
        vName1.setEffect(ds);


        //TextField2
        PasswordField vName2 = new PasswordField();
        vName2.setPrefSize(370, 40);
        vName2.setLayoutX(520);
        vName2.setLayoutY(310);
        vName2.setPadding(new Insets(10, 10, 10, 25));
        vName2.setStyle("-fx-focus-color:transparent;-fx-background-color: #EEEEEE;");
        vName2.setEffect(ds);

        vName2.setVisible(true);


        Button buttonV1 = new Button();
        buttonV1.setGraphic(viewl);
        buttonV1.setGraphicTextGap(10);
        buttonV1.setFont(font1);
        buttonV1.setText("Login");
        buttonV1.setTextFill(Color.WHITE);
        buttonV1.setLayoutX(545);
        buttonV1.setLayoutY(410);
        buttonV1.setPrefSize(320, 40);
        buttonV1.setStyle(
                "-fx-background-color: #000000; "
        );

        buttonV1.setOnAction((actionEvent) -> {
            if (vName1.getText().isEmpty()) {

            }

        });


        rect.setEffect(ds);

        EventHandler<MouseEvent> mouseVEntered = e1 -> {
            sceneV.setCursor(Cursor.HAND);
            buttonV1.setPrefSize(320, 40);
            buttonV1.setLayoutX(545);
            buttonV1.setLayoutY(410);
            buttonV1.setGraphicTextGap(15);
            buttonV1.setStyle("-fx-background-color: #131313; focused:-fx-background-color: #000000 ;");
        };

        buttonV1.setOnMouseEntered(mouseVEntered);

        EventHandler<MouseEvent> mouseVExited = e2 -> {
            sceneV.setCursor(Cursor.DEFAULT);
            buttonV1.setLayoutX(545);
            buttonV1.setLayoutY(410);
            buttonV1.setPrefSize(320, 40);
            buttonV1.setGraphicTextGap(10);
            buttonV1.setStyle(
                    "-fx-background-color: #000000; focused:-fx-background-color: #FFFFFF ;"
            );
        };

        buttonV1.setOnMouseExited(mouseVExited);


        Rectangle rectV3 = new Rectangle();
        rectV3.setHeight(10);
        rectV3.setWidth(3);
        rectV3.setX(450);
        rectV3.setY(55);
        rectV3.setFill(Color.BLACK);
        rectV3.setArcWidth(3);
        rectV3.setArcHeight(3);

        Text tV1 = new Text("Ongoing Election");
//        tE1.setFont(font1);
        tE1.setFill(Color.BLACK);


        HBox hbV1 = new HBox();
        hbV1.getChildren().add(rectV3);
        hbV1.getChildren().add(tV1);
        hbV1.setSpacing(20);

        hbV1.setAlignment(Pos.CENTER_LEFT);

        SubScene subV2 = new SubScene(hbV1, 360, 50);


        ListView lsV1 = new ListView();
        lsV1.setBorder(Border.EMPTY);
        lsV1.setSelectionModel(null);
        lsV1.setBackground(Background.EMPTY);
        lsV1.setStyle("-fx-background-color:#F4F4F4; -fx-control-inner-background:#F4F4F4  ;-fx-control-inner-background-alt:#F4F4F4");
//        lsE1.onScrollToProperty().addListener();

        int m = 4;
        while (m != 0) {
            addOnVote(lsV1);
            m--;
        }


        SubScene subV9 = new SubScene(lsV1, 360, 400);

        VBox vbV2 = new VBox();
        vbV2.getChildren().add(subV2);
        vbV2.getChildren().add(subV9);

        vbV1.setBackground(Background.EMPTY);
        SubScene subV3 = new SubScene(vbV2, 360, 450);
        subV3.setLayoutY(55);
        subV3.setLayoutX(55);


        groupV.getChildren().add(rectV1);
        groupV.getChildren().add(rectV2);
        groupV.getChildren().add(textV1);
        groupV.getChildren().add(textV2);
        groupV.getChildren().add(vName1);
        groupV.getChildren().add(vName2);
        groupV.getChildren().add(buttonV1);
        groupV.getChildren().add(subV1);
        groupV.getChildren().add(subV3);


        //RESULT TAB CONTENTS
        Rectangle rectR1 = new Rectangle();
        rectR1.setHeight(500);
        rectR1.setWidth(940);
        rectR1.setX(30);
        rectR1.setY(30);
        rectR1.setFill(Color.rgb(246, 246, 246));
        rectR1.setArcWidth(20);
        rectR1.setArcHeight(20);

        Rectangle rectR2 = new Rectangle();
        rectR2.setHeight(100);
        rectR2.setWidth(870);
        rectR2.setX(60);
        rectR2.setY(55);
        rectR2.setFill(Color.WHITE);
        rectR2.setArcWidth(20);
        rectR2.setArcHeight(20);
        rectR2.setEffect(ds);


        Rectangle rectR3 = new Rectangle();
        rectR3.setHeight(60);
        rectR3.setWidth(20);
        rectR3.setFill(Color.TRANSPARENT);

        Rectangle rectE24 = new Rectangle();
        rectE24.setHeight(30);
        rectE24.setWidth(8);
        rectE24.setX(60);
        rectE24.setY(55);
        rectE24.setFill(Color.BLACK);
        rectE24.setArcWidth(5);
        rectE24.setArcHeight(5);
        rectE24.setEffect(ds);

        ComboBox cm = new ComboBox();
        cm.setBorder(Border.EMPTY);
        cm.setBackground(Background.EMPTY);
        cm.getSelectionModel().selectFirst();

        cm.getItems().add("Union Election 21");
        cm.getItems().add("College Election 22");
        cm.getItems().add("Teachers Election 23");
        cm.getItems().add("Staff Election 21");
        cm.getItems().add("Management Election 22");
        cm.getItems().add("College Election 23");

        cm.setValue("Union Election 21");
        cm.setOnMouseEntered((MouseEvent) -> {
            sceneR.setCursor(Cursor.HAND);
        });

        cm.setOnMouseExited((MouseEvent) -> {
            sceneR.setCursor(Cursor.DEFAULT);
        });

        HBox hb2 = new HBox();
        hb2.getChildren().add(rectR3);
        hb2.getChildren().add(rectE24);
        hb2.getChildren().add(cm);
        hb2.setSpacing(40);
        hb2.setAlignment(Pos.CENTER_LEFT);

        Rectangle rectE28 = new Rectangle();
        rectE28.setHeight(30);
        rectE28.setWidth(40);
        rectE28.setFill(Color.WHITE);


        Image img5 = new Image("sample/election2.png");
        ImageView view5 = new ImageView(img5);
        view5.setFitHeight(40);
        view5.setPreserveRatio(true);
        view5.setPreserveRatio(true);

        HBox hb8 = new HBox();

        hb8.getChildren().add(view5);
        hb8.getChildren().add(rectE28);
        hb8.setBackground(Background.EMPTY);
        hb8.setAlignment(Pos.CENTER_RIGHT);


        StackPane sp = new StackPane(rectR2, hb8, hb2);

        SubScene sub2 = new SubScene(sp, 870, 100);
        sub2.setLayoutX(60);
        sub2.setLayoutY(55);


        Rectangle rectR4 = new Rectangle();
        rectR4.setHeight(300);
        rectR4.setWidth(870);
        rectR4.setX(60);
        rectR4.setY(200);
        rectR4.setFill(Color.WHITE);
        rectR4.setArcWidth(20);
        rectR4.setArcHeight(20);

        Text tR1 = new Text("Name");
        Text tR2 = new Text("Position");
        Text tR3 = new Text("Result");

        HBox hb3 = new HBox();
        hb3.getChildren().add(tR1);
        hb3.getChildren().add(tR2);
        hb3.getChildren().add(tR3);
        hb3.setSpacing(250);
        hb3.setBackground(Background.EMPTY);
        hb3.setAlignment(Pos.BOTTOM_CENTER);

        SubScene scR1 = new SubScene(hb3, 870, 50);
        scR1.setLayoutX(60);
        scR1.setLayoutY(210);


        ListView lsR1 = new ListView();
        lsR1.setBackground(Background.EMPTY);
        lsR1.setStyle("-fx-background-color:#FFFFFF; -fx-control-inner-background:#FFFFFF  ;-fx-control-inner-background-alt:#FFFFFF");
        lsR1.setSelectionModel(null);
        lsR1.setFocusTraversable(false);
        lsR1.setBorder(Border.EMPTY);

        lsR1.setSelectionModel(null);

        int result = 3;

        while (result != 0) {
            resultList(lsR1, "dinoy raj", "chairman", 1);
            result--;
        }

        Rectangle rectR5 = new Rectangle();
        rectR5.setHeight(2);
        rectR5.setWidth(400);
        rectR5.setArcWidth(5);
        rectR5.setArcHeight(5);
        rectR5.setFill(Color.BLACK);

        HBox hb5 = new HBox();
        hb5.getChildren().add(rectR5);
        hb5.setBackground(Background.EMPTY);
        hb5.setAlignment(Pos.CENTER);

        SubScene scR3 = new SubScene(hb5, 850, 6);


        lsR1.getItems().add(scR3);


        SubScene scR2 = new SubScene(lsR1, 870, 200);
        scR1.setLayoutX(60);
        scR1.setLayoutY(300);

        VBox vb2 = new VBox();
        vb2.setBackground(Background.EMPTY);
        vb2.getChildren().add(scR1);
        vb2.getChildren().add(scR2);
        vb2.setSpacing(20);
        vb2.setAlignment(Pos.TOP_CENTER);

        StackPane sp1 = new StackPane(rectR4, vb2);

        SubScene sub3 = new SubScene(sp1, 870, 300);
        sub3.setLayoutX(60);
        sub3.setLayoutY(200);


        groupR.getChildren().add(rectR1);
        groupR.getChildren().add(sub2);
        groupR.getChildren().add(sub3);


        //Children added to main scene (bottom navbar)
        group.getChildren().add(rect);
        group.getChildren().add(recti1);
        group.getChildren().add(recti2);
        group.getChildren().add(recti3);
        group.getChildren().add(btn);
        group.getChildren().add(btn1);
        group.getChildren().add(btn2);

        //sub scene added to main scene
        group.getChildren().add(sceneR);
        group.getChildren().add(sceneV);
        group.getChildren().add(sceneE);


        //App icon,name and staged the main scene
        primaryStage.getIcons().add(new Image("sample/a.png"));
        primaryStage.setTitle("VotR");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void addOnVote(ListView lsV1) {

        Font font1 = Font.font("Helvetica", FontWeight.BOLD, 10);

        DropShadow ds = new DropShadow();
        ds.setColor(Color.rgb(230, 230, 230));
        ds.setBlurType(BlurType.GAUSSIAN);
        ds.setRadius(200);
        ds.setSpread(.03);

        Rectangle rectR5 = new Rectangle();
        rectR5.setHeight(10);
        rectR5.setWidth(4);
        rectR5.setArcWidth(5);
        rectR5.setArcHeight(5);
        rectR5.setFill(Color.BLACK);

        Rectangle rectR2 = new Rectangle();
        rectR2.setHeight(80);
        rectR2.setWidth(320);
        rectR2.setX(60);
        rectR2.setY(55);
        rectR2.setFill(Color.WHITE);
        rectR2.setArcWidth(20);
        rectR2.setArcHeight(20);
//        rectR2.setEffect(ds);

        Text Ename = new Text("Election 21");


        Button btn1 = new Button();
        btn1.setText("Started");
        btn1.setTextFill(Color.WHITE);
        btn1.setStyle(
                "-fx-background-color: #349632; focused:-fx-background-color: #FFFFFF ;"
        );
        btn1.setFont(font1);


        Button btn11 = new Button();
        btn11.setText("Not Started");
        btn11.setTextFill(Color.WHITE);
        btn11.setStyle(
                "-fx-background-color: #000000; focused:-fx-background-color: #FFFFFF ;"
        );
        btn11.setFont(font1);

        btn11.setOnMouseEntered(
                (MouseEvent) -> {
                    btn11.setStyle(
                            "-fx-background-color: #349632; focused:-fx-background-color: #FFFFFF ;"
                    );
                    btn11.setCursor(Cursor.HAND);
                }
        );

        btn11.setOnMouseExited(
                (MouseEvent) -> {
                    btn11.setStyle(
                            "-fx-background-color: #000000; focused:-fx-background-color: #FFFFFF ;"
                    );
                    btn11.setCursor(Cursor.DEFAULT);
                }
        );


        Rectangle rc = new Rectangle();
        rc.setWidth(150);
        Rectangle rc1 = new Rectangle();
        rc1.setWidth(40);
        Rectangle rc2 = new Rectangle();
        rc2.setWidth(20);


        boolean started = false;
        HBox hb = new HBox();
        hb.getChildren().add(rectR5);
        hb.getChildren().add(rc1);
        hb.getChildren().add(Ename);
        hb.getChildren().add(rc);
        if (started) {
            hb.getChildren().add(btn1);
        } else {
            hb.getChildren().add(btn11);
        }

        hb.getChildren().add(rc2);
        hb.setAlignment(Pos.CENTER_LEFT);
        hb.setBackground(Background.EMPTY);


        StackPane sp = new StackPane(rectR2, hb);
        SubScene sc = new SubScene(sp, 340, 100);
        sc.setFill(Color.rgb(246, 246, 246));
        lsV1.getItems().add(sc);
    }


    private void addOnElection(ListView lsE1) {

        Font font1 = Font.font("Helvetica", FontWeight.BOLD, 10);

        DropShadow ds = new DropShadow();
        ds.setColor(Color.rgb(230, 230, 230));
        ds.setBlurType(BlurType.GAUSSIAN);
        ds.setRadius(200);
        ds.setSpread(.03);

        Rectangle rectR5 = new Rectangle();
        rectR5.setHeight(10);
        rectR5.setWidth(4);
        rectR5.setArcWidth(5);
        rectR5.setArcHeight(5);
        rectR5.setFill(Color.BLACK);

        Rectangle rectR2 = new Rectangle();
        rectR2.setHeight(80);
        rectR2.setWidth(320);
        rectR2.setX(60);
        rectR2.setY(55);
        rectR2.setFill(Color.WHITE);
        rectR2.setArcWidth(20);
        rectR2.setArcHeight(20);
//        rectR2.setEffect(ds);

        Text Ename = new Text("Election 21");


        Button btn1 = new Button();
        btn1.setText("Started");
        btn1.setTextFill(Color.WHITE);
        btn1.setStyle(
                "-fx-background-color: #349632; focused:-fx-background-color: #FFFFFF ;"
        );
        btn1.setFont(font1);


        Button btn11 = new Button();
        btn11.setText("Start");
        btn11.setTextFill(Color.WHITE);
        btn11.setStyle(
                "-fx-background-color: #000000; focused:-fx-background-color: #FFFFFF ;"
        );
        btn11.setFont(font1);

        btn11.setOnMouseEntered(
                (MouseEvent) -> {
                    btn11.setStyle(
                            "-fx-background-color: #349632; focused:-fx-background-color: #FFFFFF ;"
                    );
                    btn11.setCursor(Cursor.HAND);
                }
        );

        btn11.setOnMouseExited(
                (MouseEvent) -> {
                    btn11.setStyle(
                            "-fx-background-color: #000000; focused:-fx-background-color: #FFFFFF ;"
                    );
                    btn11.setCursor(Cursor.DEFAULT);
                }
        );


        Button btn2 = new Button();
        btn2.setText("End");
        btn2.setTextFill(Color.WHITE);
        btn2.setStyle(
                "-fx-background-color: #000000; focused:-fx-background-color: #FFFFFF ;"
        );
        btn2.setFont(font1);
        btn2.setOnMouseEntered(
                (MouseEvent) -> {
                    btn2.setStyle(
                            "-fx-background-color: #EE1320; focused:-fx-background-color: #FFFFFF ;"
                    );
                    btn2.setCursor(Cursor.HAND);
                }
        );

        btn2.setOnMouseExited(
                (MouseEvent) -> {
                    btn2.setStyle(
                            "-fx-background-color: #000000; focused:-fx-background-color: #FFFFFF ;"
                    );
                    btn2.setCursor(Cursor.DEFAULT);
                }
        );

        Rectangle rc = new Rectangle();
        rc.setWidth(110);
        Rectangle rc1 = new Rectangle();
        rc1.setWidth(30);
        Rectangle rc2 = new Rectangle();
        rc2.setWidth(20);


        boolean started = true;
        HBox hb = new HBox();
        hb.getChildren().add(rectR5);
        hb.getChildren().add(rc1);
        hb.getChildren().add(Ename);
        hb.getChildren().add(rc);
        if (started) {
            hb.getChildren().add(btn1);
        } else {
            hb.getChildren().add(btn11);
        }

        hb.getChildren().add(rc2);
        hb.getChildren().add(btn2);
        hb.setAlignment(Pos.CENTER_LEFT);
        hb.setBackground(Background.EMPTY);


        StackPane sp = new StackPane(rectR2, hb);
        SubScene sc = new SubScene(sp, 340, 100);
        sc.setFill(Color.rgb(246, 246, 246));
        lsE1.getItems().add(sc);

    }

    private void resultList(ListView l1, String C_name, String C_position, int i) {

        Font font1 = Font.font("Helvetica", FontWeight.BOLD, 10);

        Text tR1 = new Text(C_name);
        Text tR2 = new Text(C_position);


        Button buttonV1 = new Button();
        buttonV1.setFont(font1);
        buttonV1.setText("Won");
        buttonV1.setTextFill(Color.WHITE);
        buttonV1.setPrefSize(40, 15);
        buttonV1.setStyle(
                "-fx-background-color: #349632; "
        );


        HBox hb3 = new HBox();
        hb3.getChildren().add(tR1);
        hb3.getChildren().add(tR2);
        hb3.getChildren().add(buttonV1);
        hb3.setSpacing(250);
        hb3.setBackground(Background.EMPTY);
        hb3.setAlignment(Pos.BOTTOM_CENTER);

        SubScene scR2 = new SubScene(hb3, 850, 30);


        Rectangle rectR5 = new Rectangle();
        rectR5.setHeight(2);
        rectR5.setWidth(800);
        rectR5.setArcWidth(5);
        rectR5.setArcHeight(5);
        rectR5.setFill(Color.rgb(246, 246, 246));

        HBox hb5 = new HBox();
        hb5.getChildren().add(rectR5);
        hb5.setBackground(Background.EMPTY);
        hb5.setAlignment(Pos.CENTER);

        SubScene scR3 = new SubScene(hb5, 850, 6);


        l1.getItems().add(scR2);
        l1.getItems().add(scR3);


    }

}