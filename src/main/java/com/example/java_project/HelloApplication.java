package com.example.java_project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class HelloApplication extends Application
{
    Stage window;

    ImageView imgView = new ImageView();

    Image img;

    File file;
    String f;

    int key;

    GridPane grid = new GridPane();

    //Encrypt Objects
    Label labelEncrypt = new Label();
    Button browseEncrypt = new Button();
    Button encrypt = new Button();
    TextField keyncrypt = new TextField();
    FileChooser chooseEncrypt = new FileChooser();
    

    ////////////////////////////////////////////

    //Decrypt Objects
    Label labelDecrypt = new Label();
    Button browseDecrypt = new Button();
    Button decrypt = new Button();
    TextField keycrypt = new TextField();
    Button save = new Button();
    FileChooser chooseDecrypt = new FileChooser();

    @Override
    public void start(Stage stage) throws IOException
    {
        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/

        window = stage;
        window.setTitle("File Encrypt and Decrypt");

        //Grid layout
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(10);
        grid.setVgap(8);

        //Image properties
        GridPane.setConstraints(imgView, 1, 4);

        //Set The Dimensions of The Whole scene
        //Scene scene = new Scene(grid, 1000, 800);
        //stage.setTitle("Image Encryptor and Decryptor");
        //stage.setScene(scene);
        //stage.show();

        ////////////////////////////////////////////

        //Set The Whole Encrypt Portion
        //Label Properties
        GridPane.setConstraints(labelEncrypt, 0, 0);
        labelEncrypt.setText("Encrypt");

        //TextBox Properties
        GridPane.setConstraints(keyncrypt, 0, 1);

        //Button1 Properties
        GridPane.setConstraints(browseEncrypt, 1, 1);
        browseEncrypt.setText("Browse");
        browseEncrypt.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                chooseEncrypt.getExtensionFilters().addAll(new ExtensionFilter("Image", "*jpg", "*png"));
                file = chooseEncrypt.showOpenDialog(stage);
                f = file.toString();
                Dialog<String> d = new Dialog<String>();
                d.setTitle("Success");
                d.setContentText("Image Is Successfully Imported");
                d.getDialogPane().getButtonTypes().add(ButtonType.OK);
                d.show();
                encrypt.setDisable(false);
                //E:\College\Year 3\First Term\Advanced Programming
                imgView.setFitWidth(300);
                imgView.setFitHeight(300);
                img = new Image(f);
                imgView.setImage(img);
            }
        });

        //Button2 Properties
        GridPane.setConstraints(encrypt, 1, 0);
        encrypt.setText("Encrypt");
        encrypt.setDisable(true);
        encrypt.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                try
                {
                    if(keyncrypt.getText().isEmpty() || keyncrypt.getText().isBlank())
                    {
                        Dialog<String> d = new Dialog<String>();
                        d.setTitle("No Key");
                        d.setContentText("You Must Enter The Key First Before Encryption");
                        d.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        d.show();
                    }

                    else
                    {
                        key = Integer.parseInt(keyncrypt.getText());
                        encrypt(key);
                        imgView.setImage(null);
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
        GridPane.setConstraints(labelDecrypt, 10, 0);
        labelDecrypt.setText("Decrypt");

        //TextBox Properties
        GridPane.setConstraints(keycrypt, 7, 1);

        //Button1 Properties
        GridPane.setConstraints(browseDecrypt, 10, 1);
        browseDecrypt.setText("Browse");
        browseDecrypt.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                chooseDecrypt.getExtensionFilters().addAll(new ExtensionFilter("Image", "*jpg", "*png"));
                file = chooseDecrypt.showOpenDialog(stage);
                f = file.toString();
                Dialog<String> d = new Dialog<String>();
                d.setTitle("Success");
                d.setContentText("Encrypted Image Is Successfully Imported");
                d.getDialogPane().getButtonTypes().add(ButtonType.OK);
                d.show();
                decrypt.setDisable(false);
            }
        });

        //Button2 Properties
        GridPane.setConstraints(decrypt, 7, 1);
        decrypt.setText("Decrypt");
        decrypt.setDisable(true);
        decrypt.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                try
                {
                    if(keycrypt.getText().isEmpty() || keycrypt.getText().isBlank())
                    {
                        Dialog<String> d = new Dialog<String>();
                        d.setTitle("No Key");
                        d.setContentText("You Must Enter The Key First Before Decryption");
                        d.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        d.show();
                    }

                    else
                    {
                        key = Integer.parseInt(keycrypt.getText());
                        decrypt(key);
                    }
                }

                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
            }
        });

        grid.getChildren().addAll(labelEncrypt, labelDecrypt, browseEncrypt, browseDecrypt, keyncrypt, keycrypt,
                encrypt, decrypt, imgView);

        Scene scene = new Scene(grid, 800, 800);
        window.setScene(scene);
        window.show();
    }
    public static void main(String[] args)
    {
        launch();
    }

    public void encrypt(int key) throws IOException
    {
        FileInputStream fis = new FileInputStream(file);

        byte data[] = new byte[fis.available()];

        fis.read(data);
        int i = 0;

        for(byte b : data)
        {
            data[i] = (byte)(b ^ key);
            i++;
        }

        FileOutputStream fos = new FileOutputStream(file);

        fos.write(data);
        fos.close();
        fis.close();

        Dialog<String> d = new Dialog<String>();
        d.setTitle("Success");
        d.setContentText("Encryption is Successful");
        d.getDialogPane().getButtonTypes().add(ButtonType.OK);
        d.show();

        encrypt.setDisable(true);

        keyncrypt.clear();
    }

    public void decrypt(int key) throws IOException
    {
        FileInputStream fis = new FileInputStream(file);

        byte data[] = new byte[fis.available()];

        fis.read(data);
        int i = 0;

        for(byte b : data)
        {
            data[i] = (byte)(b ^ key);
            i++;
        }

        FileOutputStream fos = new FileOutputStream(file);

        fos.write(data);
        fos.close();
        fis.close();

        Dialog<String> d = new Dialog<String>();
        d.setTitle("Success");
        d.setContentText("Decryption is Successful");
        d.getDialogPane().getButtonTypes().add(ButtonType.OK);
        d.show();

        imgView.setFitWidth(300);
        imgView.setFitHeight(300);
        img = new Image(f);
        imgView.setImage(img);

        decrypt.setDisable(true);

        keycrypt.clear();
    }
}