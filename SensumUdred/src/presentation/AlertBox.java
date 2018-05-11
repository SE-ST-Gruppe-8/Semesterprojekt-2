package presentation;

import acq.IBusiness;
import acq.ICase;
import acq.ICitizen;
import acq.IInquiry;
import acq.ISocialWorker;
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
    public void displayCaseCreation(String header, IBusiness ib, ICitizen c) {
        Stage window = new Stage();
        window.setTitle(header);
        window.setMinWidth(400);
        window.setMaxWidth(600);
        window.setResizable(false);

        TextField idTextField = new TextField();
        idTextField.setPromptText("Indtast et ID til sagen");
        TextArea desTextArea = new TextArea();
        desTextArea.setPromptText("Indtast en beskrivelse af sagen");
        TextArea processTextArea = new TextArea();
        processTextArea.setPromptText("Indtast forløbet for borgeren.");
        Label idLabel = new Label();
        idLabel.setText("ID");
        Label desLabel = new Label();
        desLabel.setText("Beskrivelse");
        Label processLabel = new Label();
        processLabel.setText("Forløb");

        Button closeButton = new Button("Luk");
        closeButton.setOnAction(e -> window.close());

        Button createButton = new Button("Opret");
        createButton.setOnAction(e -> createAndClose(window, ib, idTextField.getText(), desTextArea.getText(), processTextArea.getText(), (ISocialWorker) ib.getActiveUser(), c));

        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().addAll(closeButton, createButton);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setSpacing(10);
        buttonLayout.setPadding(new Insets(10, 10, 10, 10));
        VBox layout = new VBox();
        layout.getChildren().addAll(idLabel, idTextField, desLabel, desTextArea, processLabel, processTextArea, buttonLayout);
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setPadding(new Insets(10, 10, 0, 10));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public void displayCaseEdit(String header, IBusiness ib, ICase c) {
        Stage window = new Stage();
        window.setTitle(header);
        window.setMinWidth(400);
        window.setMaxWidth(600);
        window.setResizable(false);

        TextField swCaseIdTextField = new TextField();
        swCaseIdTextField.setText(c.getId());

        TextArea swCaseDesTextArea = new TextArea();
        swCaseDesTextArea.setText(c.getDescription());
        TextArea swCaseProcessTextArea = new TextArea();
        swCaseProcessTextArea.setText(c.getProcess());
        Label swCaseIdLabel = new Label();
        swCaseIdLabel.setText("ID");
        Label swCaseDesLabel = new Label();
        swCaseDesLabel.setText("Beskrivelse");
        Label swCaseProcessLabel = new Label();
        swCaseProcessLabel.setText("Forløb");

        Button closeButton = new Button("Luk");
        closeButton.setOnAction(e -> window.close());

        Button createButton = new Button("Gem");
        createButton.setOnAction(e -> editCaseAndClose(window, ib, c, swCaseDesTextArea.getText(), swCaseProcessTextArea.getText()));

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

    public void displayCitizenCreation(String header, IBusiness ib) {
        Stage window = new Stage();
        window.setTitle(header);
        window.setMinWidth(400);
        window.setMaxWidth(600);
        window.setResizable(false);

        TextField idTextField = new TextField();
        idTextField.setPromptText("Indtast navn");
        TextField originTextField = new TextField();
        originTextField.setPromptText("Indtast ID");
        TextArea descTextField = new TextArea();
        descTextField.setPromptText("Indtast beskrivelse");
        Label idLabel = new Label();
        idLabel.setText("ID");
        Label nameLabel = new Label();
        nameLabel.setText("Navn");

        Label needsLabel = new Label();
        needsLabel.setText("Behov");

        Button closeButton = new Button("Luk");
        closeButton.setOnAction(e -> window.close());

        Button createButton = new Button("Opret");
        createButton.setOnAction(e -> createAndClose(window, ib, idTextField.getText(), originTextField.getText(), descTextField.getText()));
//        createButton.setOnAction( e-> window.close());

        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().addAll(closeButton, createButton);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setSpacing(10);
        buttonLayout.setPadding(new Insets(10, 10, 10, 10));
        VBox layout = new VBox();
        layout.getChildren().addAll(nameLabel, idTextField, idLabel, originTextField, needsLabel, descTextField, buttonLayout);
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setPadding(new Insets(10, 10, 0, 10));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public void displayCitizenEdit(String header, IBusiness ib, ICitizen c) {
        Stage window = new Stage();
        window.setTitle(header);
        window.setMinWidth(400);
        window.setMaxWidth(600);
        window.setResizable(false);

        TextField nameTextField = new TextField();
        nameTextField.setText(c.getName());
        TextField idTextField = new TextField();
        idTextField.setText(c.getId());
        TextArea needsTextField = new TextArea();
        needsTextField.setText(c.getNeeds());
        Label nameLabel = new Label();
        nameLabel.setText("Navn");
        Label idLabel = new Label();
        idLabel.setText("ID");
        Label needsLabel = new Label();
        needsLabel.setText("Behov");

        Button closeButton = new Button("Luk");
        closeButton.setOnAction(e -> window.close());

        Button createButton = new Button("Gem");
        createButton.setOnAction(e -> editCitizenAndClose(window, ib, c, needsTextField.getText()));
//        createButton.setOnAction( e-> window.close());

        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().addAll(closeButton, createButton);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setSpacing(10);
        buttonLayout.setPadding(new Insets(10, 10, 10, 10));
        VBox layout = new VBox();
        layout.getChildren().addAll(nameLabel, nameTextField, idLabel, idTextField, needsLabel, needsTextField, buttonLayout);
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setPadding(new Insets(10, 10, 0, 10));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public void displayInquiryCreation(String header, IBusiness ib, ICitizen c) {
        Stage window = new Stage();
        window.setTitle(header);
        window.setMinWidth(400);
        window.setMaxWidth(600);
        window.setResizable(false);

        TextField inquiryIdTextField = new TextField();
        inquiryIdTextField.setPromptText("Indtast ID");
        TextField inquiryOriginTextField = new TextField();
        inquiryOriginTextField.setPromptText("Indtast indsender");
        TextArea inquiryDescTextField = new TextArea();
        inquiryDescTextField.setPromptText("Indtast beskrivelse");
        Label idLabel = new Label();
        idLabel.setText("ID");
        Label originLabel = new Label();
        originLabel.setText("Indsender");
        Label descLabel = new Label();
        descLabel.setText("Beskrivelse");

        Button closeButton = new Button("Luk");
        closeButton.setOnAction(e -> window.close());

        Button createButton = new Button("Opret");
        createButton.setOnAction(e -> createAndClose(window, ib, inquiryIdTextField.getText(), inquiryOriginTextField.getText(), false, c, inquiryDescTextField.getText()));
//        createButton.setOnAction( e-> window.close());

        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().addAll(closeButton, createButton);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setSpacing(10);
        buttonLayout.setPadding(new Insets(10, 10, 10, 10));
        VBox layout = new VBox();
        layout.getChildren().addAll(idLabel, inquiryIdTextField, originLabel, inquiryOriginTextField, descLabel, inquiryDescTextField, buttonLayout);
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setPadding(new Insets(10, 10, 0, 10));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public void displayInquiryEdit(String header, IBusiness ib, IInquiry i) {
        Stage window = new Stage();
        window.setTitle(header);
        window.setMinWidth(400);
        window.setMaxWidth(600);
        window.setResizable(false);

        ICitizen c = i.getCitizen();
        TextField inquiryIdTextField = new TextField();
        inquiryIdTextField.setText(i.getId());
        TextField inquiryOriginTextField = new TextField();
        inquiryOriginTextField.setText(i.getOrigin());
        TextArea inquiryDescTextField = new TextArea();
        inquiryDescTextField.setText(i.getDescription());
        Label swCaseIdLabel = new Label();
        swCaseIdLabel.setText("ID");
        Label swCaseDesLabel = new Label();
        swCaseDesLabel.setText("Indsender");
        Label swCaseProcessLabel = new Label();
        swCaseProcessLabel.setText("Beskrivelse");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        Button createButton = new Button("Save");

        createButton.setOnAction(e -> editInquiryAndClose(window, ib, i, inquiryDescTextField.getText()));
        // createButton.setOnAction( e-> window.close());

        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().addAll(closeButton, createButton);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setSpacing(10);
        buttonLayout.setPadding(new Insets(10, 10, 10, 10));
        VBox layout = new VBox();
        layout.getChildren().addAll(swCaseIdLabel, inquiryIdTextField, swCaseDesLabel, inquiryOriginTextField, swCaseProcessLabel, inquiryDescTextField, buttonLayout);
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setPadding(new Insets(10, 10, 0, 10));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public void createAndClose(Stage window, IBusiness ib, String id, String des, String procs, ISocialWorker sw, ICitizen c) {
        ib.createCase(id, des, procs, sw, c);
        window.close();
    }

    public void createAndClose(Stage window, IBusiness ib, String id, String origin, String description) {
        // if (ib.hasUnqiueCitizenID(id)) {
        if (true) { // brug ovenstående linje når hasUniqueCitizenID(id) er implementeret i DataFacade!!!
            ib.createCitizen(id, origin, description);
            window.close();
        }
    }

    public void createAndClose(Stage window, IBusiness ib, String id, String origin, boolean informed, ICitizen c, String description) {
        ib.createInquiry(id, origin, informed, c, description);
        window.close();
    }

    public void editCaseAndClose(Stage window, IBusiness ib, ICase c, String desc, String proc) {
        ib.editCase(desc, proc, c);
        window.close();
    }

    public void editCitizenAndClose(Stage window, IBusiness ib, ICitizen c, String needs) {
        ib.editCitizen(needs, c);
        window.close();
    }

    public void editInquiryAndClose(Stage window, IBusiness ib, IInquiry i, String desc) {
        ib.editInquiry(desc, i);
        window.close();
    }

}
