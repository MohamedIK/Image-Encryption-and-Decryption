package com.example.java_project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.*;
import java.io.IOException;
public class HelloApplication extends Application
{
    Stage window;

    Font fontLabel = Font.font("Arial", FontWeight.BLACK, 18);

    ImageView imgView = new ImageView();

    Image img;

    File file;
    String f;

    int key;

    GridPane grid = new GridPane();

    //Encrypt Objects
    Label labelEncrypt = new Label("Encrypt");
    Button browseEncrypt = new Button();
    Button encrypt = new Button();
    PasswordField keyEncrypt = new PasswordField();
    FileChooser chooseEncrypt = new FileChooser();

    ////////////////////////////////////////////

    //Decrypt Objects
    Label labelDecrypt = new Label("Decrypt");
    Button browseDecrypt = new Button();
    Button decrypt = new Button();
    PasswordField keyDecrypt = new PasswordField();
    FileChooser chooseDecrypt = new FileChooser();

    @Override
    public void start(Stage stage) throws IOException
    {
        window = stage;
        window.setTitle("File Encrypt and Decrypt");

        //Grid layout
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(1);
        grid.setHgap(1);

        //Image properties
        GridPane.setConstraints(imgView, 5, 10);

        ////////////////////////////////////////////

        //Set The Whole Encrypt Portion
        //Label Properties
        GridPane.setConstraints(labelEncrypt, 0, 0);
        labelEncrypt.setFont(fontLabel);

        //TextBox Properties
        GridPane.setConstraints(keyEncrypt, 0, 1);
        keyEncrypt.setPromptText("Enter Key To Encrypt");

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
                //E:\College\Year 3\First Term\Advanced Programming
                img = new Image(f);
                if(img.isError())
                {
                    Dialog<String> x = new Dialog<String>();
                    x.setTitle("Error");
                    x.setContentText("Unsupported Image Format");
                    x.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    x.show();
                }

                else
                {
                    Dialog<String> d = new Dialog<String>();
                    d.setTitle("Success");
                    d.setContentText("Image Is Successfully Imported");
                    d.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    d.show();
                    imgView.setFitWidth(400);
                    imgView.setFitHeight(400);
                    imgView.setImage(img);
                    encrypt.setDisable(false);
                }
            }
        });

        //Button2 Properties
        GridPane.setConstraints(encrypt, 1, 2);
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
                        d.show();
                    }

                    else
                    {
                        if(isInt(keyEncrypt))
                        {
                            key = Integer.parseInt(keyEncrypt.getText().replaceAll("\\s", ""));
                            encrypt(key);
                            imgView.setImage(null);
                        }

                        else
                        {
                            Dialog<String> d = new Dialog<String>();
                            d.setTitle("Invalid Character");
                            d.setContentText("The Key Must Contain Numbers Only");
                            d.getDialogPane().getButtonTypes().add(ButtonType.OK);
                            d.show();
                        }
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
        GridPane.setConstraints(labelDecrypt, 45, 0);
        labelDecrypt.setFont(fontLabel);

        //TextBox Properties
        GridPane.setConstraints(keyDecrypt, 44, 1);

        //Button1 Properties
        GridPane.setConstraints(browseDecrypt, 45, 1);
        browseDecrypt.setText("Browse");
        browseDecrypt.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                chooseDecrypt.getExtensionFilters().addAll(new ExtensionFilter("Image", "*jpg", "*png"));
                file = chooseDecrypt.showOpenDialog(stage);
                f = file.toString();
                img = new Image(f);

                if(img.isError())
                {
                    Dialog<String> d = new Dialog<String>();
                    d.setTitle("Success");
                    d.setContentText("Encrypted Image Is Successfully Imported");
                    d.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    d.show();
                    decrypt.setDisable(false);
                }

                else
                {
                    Dialog<String> x = new Dialog<String>();
                    x.setTitle("Error");
                    x.setContentText("Unsupported Image Format");
                    x.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    x.show();
                }
            }
        });

        //Button2 Properties
        GridPane.setConstraints(decrypt, 44, 2);
        decrypt.setText("Decrypt");
        decrypt.setDisable(true);
        keyDecrypt.setPromptText("Enter Key To Decrypt");
        decrypt.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                if(keyDecrypt.getText().isEmpty() || keyDecrypt.getText().isBlank())
                {
                    Dialog<String> d = new Dialog<String>();
                    d.setTitle("No Key");
                    d.setContentText("You Must Enter The Key First Before Decryption");
                    d.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    d.show();
                }

                else
                {
                    if(isInt(keyDecrypt))
                    {
                        Dialog<ButtonType> x = new Dialog<>();
                        x.setTitle("Check Decrypt Key");
                        x.setContentText("Are You Sure That It Is The Correct Key ?");
                        x.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
                        x.showAndWait().ifPresent(response -> {
                            if(response == ButtonType.YES)
                            {
                                key = Integer.parseInt(keyDecrypt.getText().replaceAll("\\s", ""));

                                try
                                {
                                    decrypt(key);
                                }

                                catch (IOException e)
                                {
                                    throw new RuntimeException(e);
                                }
                            }

                            else
                            {
                                x.close();
                            }
                        });
                    }

                    else
                    {
                        Dialog<String> d = new Dialog<String>();
                        d.setTitle("Invalid Character");
                        d.setContentText("The Key Must Contain Numbers Only");
                        d.getDialogPane().getButtonTypes().add(ButtonType.OK);
                        d.show();
                    }
                }
            }
        });

        grid.getChildren().addAll(labelEncrypt, labelDecrypt, browseEncrypt, browseDecrypt, keyEncrypt, keyDecrypt,
                encrypt, decrypt, imgView);

        Scene scene = new Scene(grid, 900, 600);

        window.setScene(scene);
        window.show();
        window.setResizable(true);
        window.getScene().getWindow().sizeToScene();
        window.setResizable(false);
    }
    public static void main(String[] args)
    {
        launch();
    }

    public boolean isInt(PasswordField pf)
    {
        try
        {
            Integer.parseInt(pf.getText());
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
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

        keyEncrypt.clear();
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

        img = new Image(f);

        if(img.isError())
        {
            Dialog<String> x = new Dialog<String>();
            x.setTitle("Error");
            x.setContentText("Decryption Failed. You May Have Entered Wrong Decryption Key, please Try Again");
            x.getDialogPane().getButtonTypes().add(ButtonType.OK);
            x.show();
            repair_decrypt(key);
            keyDecrypt.clear();
        }

        else
        {
            Dialog<String> d = new Dialog<String>();
            d.setTitle("Success");
            d.setContentText("Decryption is Successful");
            d.getDialogPane().getButtonTypes().add(ButtonType.OK);
            d.show();

            imgView.setFitWidth(400);
            imgView.setFitHeight(400);

            imgView.setImage(img);
            decrypt.setDisable(true);
            keyDecrypt.clear();
        }
    }

    public void repair_decrypt(int key) throws IOException
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
    }
}