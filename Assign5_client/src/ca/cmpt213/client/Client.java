package ca.cmpt213.client;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.Integer.parseInt;

/**
 * @author Tanzil Sarker
 * @version final
 * This class controls all the client side operaton of the application which consists mostly the GUI, The Tokimons are displayed as Json string, Users can get
 * clear info when they choose to view certain tokimons by entering their ID.
 * <p>
 * Known bugs: Display of the tokemons arent exactly the most beautiful, can be better
 */
public class Client extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TokiDEX EXTREME");
        // Create label Control
        Label myLabel = new Label("Welcome, what do you want to do today?");
        // Create button Control
        Button myButton = new Button("Get list of all saved Tokimon and their information");
        Button myButton1 = new Button("Find Tokimon with ID");
        Button myButton2 = new Button("Add a Tokimon to list");
        Button myButton3 = new Button("Change attributes of a Tokimon");
        Button myButton4 = new Button("Remove a Tokimon");


        myButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    URL url = new URL("http://localhost:8080/api/tokimon/all");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    connection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String output;
                    output = br.readLine();
                    System.out.println(output);
                    System.out.println(connection.getResponseCode());
                    connection.disconnect();
                    // Scene scene = new Scene(vbox);

                    Stage subStage = new Stage();
                    subStage.setTitle("Tokimon List");

                    //disable parent window
                    subStage.initModality(Modality.WINDOW_MODAL);
                    subStage.initOwner(primaryStage);
                    Label outputLabel = new Label();

                    outputLabel.setText(output);
                    outputLabel.wrapTextProperty().setValue(true);
                    VBox hb = new VBox(outputLabel);
                    Scene subscene = new Scene(hb, 480, 300);

                    subStage.setScene(subscene);
                    subStage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        myButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Label nameLabel = new Label("ID: ");
                nameLabel.setPrefWidth(100);
                nameLabel.setPadding(new Insets(0, 0, 0, 30));
                TextField nameField = new TextField();
                Button submit = new Button("Search");
                Label outputLabel = new Label();
                GridPane gridpane = new GridPane();
                gridpane.setVgap(20);
                gridpane.setHgap(20);
                gridpane.add(nameLabel, 0, 0);
                gridpane.add(nameField, 1, 0);
                gridpane.add(submit, 1, 2);
                VBox vb = new VBox(20, gridpane, outputLabel);
                Stage subStage = new Stage();
                subStage.setTitle("Search Tokimon");

                //disable parent window
                subStage.initModality(Modality.WINDOW_MODAL);
                subStage.initOwner(primaryStage);
                Scene subscene = new Scene(vb, 300, 300);
                subStage.setScene(subscene);
                subStage.show();
                submit.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        System.out.println(nameField.getText());
                        try {
                            URL url = new URL("http://localhost:8080/api/tokimon/" + nameField.getText());
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");

                            connection.getInputStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                            String output;
                            output = br.readLine();

                            System.out.println(output);
                            System.out.println(connection.getResponseCode());
                            outputLabel.setText(output);
                            connection.disconnect();
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Something Went Wrong");
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });

        myButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Label nameLabel2 = new Label("Name: ");
                nameLabel2.setPrefWidth(100);
                nameLabel2.setPadding(new Insets(0, 0, 0, 30));
                TextField nameField2 = new TextField();
                Label heightLabel = new Label("Height: ");
                heightLabel.setPrefWidth(100);
                heightLabel.setPadding(new Insets(0, 0, 0, 30));
                TextField heightfield = new TextField();
                Label weightLabel = new Label("Weight: ");
                weightLabel.setPrefWidth(100);
                weightLabel.setPadding(new Insets(0, 0, 0, 30));
                TextField weightField = new TextField();
                Label abilityLabel = new Label("Ability: ");
                abilityLabel.setPrefWidth(100);
                abilityLabel.setPadding(new Insets(0, 0, 0, 30));
                TextField abilityField = new TextField();
                Label colorLabel = new Label("Color: ");
                colorLabel.setPrefWidth(100);
                colorLabel.setPadding(new Insets(0, 0, 0, 30));
                TextField colorField = new TextField();
                Label strengthLabel = new Label("Strength: ");
                strengthLabel.setPrefWidth(100);
                strengthLabel.setPadding(new Insets(0, 0, 0, 30));
                TextField strengthField = new TextField();
                Button commit = new Button("commit");
                Label outputLabel2 = new Label();

                GridPane gridpane2 = new GridPane();
                gridpane2.setVgap(20);
                gridpane2.setHgap(20);
                gridpane2.add(nameLabel2, 1, 0);
                gridpane2.add(nameField2, 2, 0);
                gridpane2.add(weightLabel, 1, 1);
                gridpane2.add(weightField, 2, 1);
                gridpane2.add(heightLabel, 1, 2);
                gridpane2.add(heightfield, 2, 2);
                gridpane2.add(abilityLabel, 1, 3);
                gridpane2.add(abilityField, 2, 3);
                gridpane2.add(colorLabel, 1, 4);
                gridpane2.add(colorField, 2, 4);
                gridpane2.add(strengthLabel, 1, 5);
                gridpane2.add(strengthField, 2, 5);
                gridpane2.add(outputLabel2, 1, 6);
                gridpane2.add(commit, 2, 7);
                VBox vb2 = new VBox(20, gridpane2);
                Stage subStage2 = new Stage();
                subStage2.setTitle("Change a Tokimon");
                commit.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            URL url = new URL("http://localhost:8080/api/tokimon/add");
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setDoOutput(true);
                            connection.setRequestMethod("POST");
                            connection.setRequestProperty("Content-Type", "application/json");

                            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
                            wr.write("{\"name\":\"" + nameField2.getText() + "\",\"weight\":" + parseInt(weightField.getText()) + ",\"height\":" + parseInt(heightfield.getText()) + ",\"ability\":\"" + abilityField.getText() + "\",\"color\":\"" + colorField.getText() + "\",\"strength\":" + parseInt(strengthField.getText()) + "}");
                            wr.flush();
                            wr.close();

                            connection.connect();
                            System.out.println(connection.getResponseCode());
                            connection.disconnect();
                            JOptionPane.showMessageDialog(null, "Tokimon added successfully");
                            subStage2.close();
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Something Went Wrong");
                            ex.printStackTrace();
                        }

                    }
                });

                //disable parent window
                subStage2.initModality(Modality.WINDOW_MODAL);
                subStage2.initOwner(primaryStage);
                Scene subscene2 = new Scene(vb2, 600, 400);
                subStage2.setScene(subscene2);
                subStage2.show();
            }
        });


        myButton3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Label nameLabel = new Label("ID: ");
                nameLabel.setPrefWidth(100);
                nameLabel.setPadding(new Insets(0, 0, 0, 30));
                TextField nameField = new TextField();
                Button inquire = new Button("Search");
                Label outputLabel = new Label();
                GridPane gridpane = new GridPane();
                gridpane.setVgap(20);
                gridpane.setHgap(20);
                gridpane.add(nameLabel, 0, 0);
                gridpane.add(nameField, 1, 0);
                gridpane.add(inquire, 1, 2);
                VBox vb = new VBox(20, gridpane, outputLabel);
                Stage subStage = new Stage();
                subStage.setTitle("Change a Tokimon");

                //disable parent window
                subStage.initModality(Modality.WINDOW_MODAL);
                subStage.initOwner(primaryStage);
                Scene subscene = new Scene(vb, 300, 300);
                subStage.setScene(subscene);
                subStage.show();
                inquire.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        System.out.println(nameField.getText());
                        try {
                            URL url = new URL("http://localhost:8080/api/tokimon/" + nameField.getText());
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");

                            connection.getInputStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                            String output;
                            output = br.readLine();
                            System.out.println(output);
                            System.out.println(connection.getResponseCode());
                            connection.disconnect();
                            subStage.close();

                            Label nameLabel2 = new Label("Name: ");
                            nameLabel2.setPrefWidth(100);
                            nameLabel2.setPadding(new Insets(0, 0, 0, 30));
                            TextField nameField2 = new TextField();
                            Label heightLabel = new Label("Height: ");
                            heightLabel.setPrefWidth(100);
                            heightLabel.setPadding(new Insets(0, 0, 0, 30));
                            TextField heightfield = new TextField();
                            Label weightLabel = new Label("Weight: ");
                            weightLabel.setPrefWidth(100);
                            weightLabel.setPadding(new Insets(0, 0, 0, 30));
                            TextField weightField = new TextField();
                            Label abilityLabel = new Label("Ability: ");
                            abilityLabel.setPrefWidth(100);
                            abilityLabel.setPadding(new Insets(0, 0, 0, 30));
                            TextField abilityField = new TextField();
                            Label colorLabel = new Label("Color: ");
                            colorLabel.setPrefWidth(100);
                            colorLabel.setPadding(new Insets(0, 0, 0, 30));
                            TextField colorField = new TextField();
                            Label strengthLabel = new Label("Strength: ");
                            strengthLabel.setPrefWidth(100);
                            strengthLabel.setPadding(new Insets(0, 0, 0, 30));
                            TextField strengthField = new TextField();
                            Button commit = new Button("commit");
                            Label outputLabel2 = new Label();
                            outputLabel.setText(output);

                            GridPane gridpane2 = new GridPane();
                            gridpane2.setVgap(20);
                            gridpane2.setHgap(20);
                            gridpane2.add(nameLabel2, 1, 0);
                            gridpane2.add(nameField2, 2, 0);
                            gridpane2.add(weightLabel, 1, 1);
                            gridpane2.add(weightField, 2, 1);
                            gridpane2.add(heightLabel, 1, 2);
                            gridpane2.add(heightfield, 2, 2);
                            gridpane2.add(abilityLabel, 1, 3);
                            gridpane2.add(abilityField, 2, 3);
                            gridpane2.add(colorLabel, 1, 4);
                            gridpane2.add(colorField, 2, 4);
                            gridpane2.add(strengthLabel, 1, 5);
                            gridpane2.add(strengthField, 2, 5);
                            gridpane2.add(outputLabel2, 1, 6);
                            gridpane2.add(commit, 2, 7);
                            VBox vb2 = new VBox(20, gridpane2, outputLabel);
                            Stage subStage2 = new Stage();
                            subStage2.setTitle("Change a Tokimon");
                            commit.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    try {
                                        URL url = new URL("http://localhost:8080/api/tokimon/change/" + nameField.getText());
                                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                        connection.setDoOutput(true);
                                        connection.setRequestMethod("POST");
                                        connection.setRequestProperty("Content-Type", "application/json");

                                        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
                                        wr.write("{\"name\":\"" + nameField2.getText() + "\",\"weight\":" + parseInt(weightField.getText()) + ",\"height\":" + parseInt(heightfield.getText()) + ",\"ability\":\"" + abilityField.getText() + "\",\"color\":\"" + colorField.getText() + "\",\"strength\":" + parseInt(strengthField.getText()) + "}");
                                        wr.flush();
                                        wr.close();

                                        connection.connect();
                                        System.out.println(connection.getResponseCode());
                                        connection.disconnect();
                                        JOptionPane.showMessageDialog(null, "Tokimon changed successfully");
                                        subStage2.close();
                                    } catch (IOException ex) {
                                        JOptionPane.showMessageDialog(null, "Something Went Wrong");
                                        ex.printStackTrace();
                                    }

                                }
                            });

                            //disable parent window
                            subStage2.initModality(Modality.WINDOW_MODAL);
                            subStage2.initOwner(primaryStage);
                            Scene subscene2 = new Scene(vb2, 600, 400);
                            subStage2.setScene(subscene2);
                            subStage2.show();
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Something Went Wrong");
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });

        myButton4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Label nameLabel = new Label("ID: ");
                nameLabel.setPrefWidth(100);
                nameLabel.setPadding(new Insets(0, 0, 0, 30));
                TextField nameField = new TextField();
                Button submit = new Button("DELETE");
                Label outputLabel = new Label();
                GridPane gridpane = new GridPane();
                gridpane.setVgap(20);
                gridpane.setHgap(20);
                gridpane.add(nameLabel, 0, 0);
                gridpane.add(nameField, 1, 0);
                gridpane.add(submit, 1, 2);
                VBox vb = new VBox(20, gridpane, outputLabel);
                Stage subStage = new Stage();
                subStage.setTitle("DELETE Tokimon");

                //disable parent window
                subStage.initModality(Modality.WINDOW_MODAL);
                subStage.initOwner(primaryStage);
                Scene subscene = new Scene(vb, 300, 300);
                subStage.setScene(subscene);
                subStage.show();
                submit.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        System.out.println(nameField.getText());
                        try {
                            URL url = new URL("http://localhost:8080/api/tokimon/" + nameField.getText());
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("DELETE");
                            connection.getInputStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                            String output;
                            output = br.readLine();

                            System.out.println(output);
                            System.out.println(connection.getResponseCode());
                            outputLabel.setText(output);
                            connection.disconnect();

                            JOptionPane.showMessageDialog(null, "Tokimon changed successfully");
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Something Went Wrong");
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });

        VBox vbox = new VBox(20, myLabel, myButton, myButton1, myButton2, myButton3, myButton4);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 300, 300);

        primaryStage.setScene(scene);

        // Show the window.
        primaryStage.show();

    }
}
