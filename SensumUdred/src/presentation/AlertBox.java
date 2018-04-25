package presentation;

import acq.IBusiness;
import business.Citizen;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AlertBox {

    public AlertBox() {

    }

    /**
     * Display a popup window
     *
     * @param header the text in the title bar of the window
     * @param filename the filename of the textfile. Textfile must be placed in
     * Presentation/Textfiles/
     */
    public void display(String header, IBusiness ib) {
        Stage window = new Stage();
        window.setTitle(header);
        window.setMinWidth(400);
        window.setMaxWidth(600);
        window.setResizable(false);

        TextField swCaseIdTextField = new TextField();
        TextArea swCaseDesTextArea = new TextArea();
        TextArea swCaseProcessTextArea = new TextArea();
        Label swCaseIdLabel = new Label();
        swCaseIdLabel.setText("ID");
        Label swCaseDesLabel = new Label();
        swCaseDesLabel.setText("Description");
        Label swCaseProcessLabel = new Label();
        swCaseProcessLabel.setText("Process");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        Button createButton = new Button("Create");
        createButton.setOnAction(e -> ib.createCase(swCaseIdTextField.getText(), swCaseDesTextArea.getText(), swCaseProcessTextArea.getText(), ib.getActiveUser(), new Citizen()));
//        createButton.setOnAction( e-> window.close());


        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().addAll(closeButton, createButton);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setSpacing(10);
        buttonLayout.setPadding(new Insets(10, 10, 10, 10));
        VBox layout = new VBox();
        layout.getChildren().addAll(swCaseIdLabel, swCaseIdTextField, swCaseDesLabel, swCaseDesTextArea, swCaseProcessLabel, swCaseProcessTextArea, buttonLayout);
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setPadding(new Insets(10, 10, 0, 10));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    /**
     * Display a popup window, with a highscore list
     *
     * @param header the text in the title bar of the window
     * @param highScore List containing {@link IHighScore}
     */
    /*    static void display(String header, List<IHighScore> highScore) {
        Stage window = new Stage();
        window.setTitle(header);
        window.setMinWidth(400);
        window.setMaxWidth(600);

        ListView<IHighScore> highscore = new ListView<>();
        ObservableList<IHighScore> highscorelist = highscore.getItems();
        highscorelist.addAll(highScore);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(highscore, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }*/
}
