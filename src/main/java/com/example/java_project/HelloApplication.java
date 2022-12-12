package com.example.java_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application
{
    GridPane root = new GridPane();

    //Encrypt Area
    Label labelEncrypt = new Label();
    Button browseEncrypt = new Button();
    Button encrypt = new Button();
    TextField keyEncrypt = new TextField();
    ImageView encryptImage = new ImageView();

    //Decrypt Area
    Label labelDecrypt = new Label();
    Button browseDecrypt = new Button();
    Button decrypt = new Button();
    TextField keyDecrypt = new TextField();
    Button save = new Button();
    ImageView decryptImage = new ImageView();

    @Override
    public void start(Stage stage) throws IOException
    {
        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/

        //Set The Dimensions of The Whole grid
        Scene scene = new Scene(root, 1000, 800);
        stage.setTitle("Image Encryptor and Decryptor");
        stage.setScene(scene);
        stage.show();

        //Set The Whole Encrypt Portion
        //Label Properties
        root.add(labelEncrypt, 20, 400);
        labelEncrypt.setText("Encrypt");
        //TextBox Properties
        root.add(keyEncrypt, 20, 800);
        //Button1 Properties
        root.add(browseEncrypt, 500, 800);
        //Button2 Properties
        browseEncrypt.setText("Browse");
        encrypt.setText("Encrypt");

        //Set The Whole Encrypt Portion
        //Label Properties
        root.add(labelDecrypt, 900, 400);
        labelDecrypt.setText("Decrypt");
        //TextBox Properties
        root.add(keyDecrypt, 800, 800);
        //Button1 Properties
        root.add(browseDecrypt, 600, 800);
        browseDecrypt.setText("Browse");
        //Button2 Properties
        decrypt.setText("Decrypt");
    }

    public static void main(String[] args)
    {
        launch();
    }
}