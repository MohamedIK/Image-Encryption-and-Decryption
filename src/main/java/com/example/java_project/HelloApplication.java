package com.example.java_project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.*;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application
{
    GridPane root = new GridPane();

    //Encrypt Objects
    Label labelEncrypt = new Label();
    Button browseEncrypt = new Button();
    Button encrypt = new Button();
    TextField keyEncrypt = new TextField();
    ImageView encryptImage = new ImageView();
    FileChooser chooseEncrypt = new FileChooser();
    File fileEncrypt;
    String f1;
    int keyE;
    Image img1;

    ////////////////////////////////////////////

    //Decrypt Objects
    Label labelDecrypt = new Label();
    Button browseDecrypt = new Button();
    Button decrypt = new Button();
    TextField keyDecrypt = new TextField();
    Button save = new Button();
    ImageView decryptImage = new ImageView();
    FileChooser chooseDecrypt = new FileChooser();
    File fileDecrypt;
    String f2;
    int keyD;

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

        ////////////////////////////////////////////

        //Set The Whole Encrypt Portion
        //Label Properties
        root.add(labelEncrypt, 20, 400);
        labelEncrypt.setText("Encrypt");

        //Image properties
        root.add(encryptImage, 40, 900);

        //TextBox Properties
        root.add(keyEncrypt, 20, 800);

        //Button1 Properties
        root.add(browseEncrypt, 500, 800);
        browseEncrypt.setText("Browse");
        browseEncrypt.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                chooseEncrypt.getExtensionFilters().addAll(new ExtensionFilter("jpg", "*jpg"));
                fileEncrypt = chooseEncrypt.showOpenDialog(stage);
                f1 = fileEncrypt.toString();
                encrypt.setDisable(false);
                img1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(f1)));
                encryptImage.setImage(img1);
            }
        });

        //Button2 Properties
        root.add(encrypt, 500, 900);
        encrypt.setText("Encrypt");
        encrypt.setDisable(true);
        encrypt.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                try
                {
                    if(keyEncrypt.getText().isEmpty() || keyEncrypt.getText().isBlank())
                    {
                        Dialog<String> d = new Dialog<String>();
                        d.setTitle("No Key");
                        d.setContentText("You Must Enter The Key First Before Encryption");
                        d.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    }

                    else
                    {
                        keyE = Integer.parseInt(keyEncrypt.getText());

                        encrypt(keyE);
                    }
                }

                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
            }
        });

        ////////////////////////////////////////////

        //Set The Whole Decrypt Portion
        //Label Properties
        root.add(labelDecrypt, 900, 400);
        labelDecrypt.setText("Decrypt");

        //TextBox Properties
        root.add(keyDecrypt, 800, 800);

        //Button1 Properties
        root.add(browseDecrypt, 600, 800);
        browseDecrypt.setText("Browse");
        browseDecrypt.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                chooseDecrypt.getExtensionFilters().addAll(new ExtensionFilter("jpg", "*jpg"));
                chooseDecrypt.getExtensionFilters().addAll(new ExtensionFilter("png", "*png"));
                fileDecrypt = chooseDecrypt.showOpenDialog(stage);
                f2 = fileDecrypt.toString();
                decrypt.setDisable(false);
            }
        });

        //Button2 Properties
        root.add(decrypt, 600, 900);
        decrypt.setText("Decrypt");
        decrypt.setDisable(true);
        decrypt.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                try
                {
                    if(keyDecrypt.getText().isEmpty() || keyDecrypt.getText().isBlank())
                    {
                        Dialog<String> d = new Dialog<String>();
                        d.setTitle("No Key");
                        d.setContentText("You Must Enter The Key First Before Decryption");
                        d.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    }

                    else
                    {
                        keyD = Integer.parseInt(keyDecrypt.getText());

                        encrypt(keyD);
                    }
                }

                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
            }
        });

    }
    public static void main(String[] args)
    {
        launch();
    }

    public void encrypt(int key) throws IOException
    {
        FileInputStream fis = new FileInputStream(fileEncrypt);

        byte data[] = new byte[fis.available()];

        fis.read(data);
        int i = 0;

        for(byte b : data)
        {
            data[i] = (byte)(b ^ key);
            i++;
        }

        FileOutputStream fos = new FileOutputStream(fileEncrypt);

        fos.write(data);
        fos.close();
        fis.close();

        Dialog<String> d = new Dialog<String>();
        d.setTitle("Success");
        d.setContentText("Encryption is Successful");
        d.getDialogPane().getButtonTypes().add(ButtonType.OK);
    }

    public void decrypt(int key) throws IOException
    {

    }
}