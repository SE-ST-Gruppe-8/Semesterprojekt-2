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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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

        TextArea desTextArea = new TextArea();
        desTextArea.setPromptText("Indtast en beskrivelse af sagen");
        TextArea processTextArea = new TextArea();
        processTextArea.setPromptText("Indtast forløbet for borgeren.");
        Label idLabel = new Label();
        idLabel.setText("ID: " + c.getId() + "-1");
        Label desLabel = new Label();
        desLabel.setText("Beskrivelse");
        Label processLabel = new Label();
        processLabel.setText("Forløb");

        Button closeButton = new Button("Luk");
        closeButton.setOnAction(e -> window.close());

        Button createButton = new Button("Opret");
        createButton.setOnAction(e -> createAndClose(window, ib, desTextArea.getText(), processTextArea.getText(), (ISocialWorker) ib.getActiveUser(), c));

        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().addAll(closeButton, createButton);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setSpacing(10);
        buttonLayout.setPadding(new Insets(10, 10, 10, 10));
        VBox layout = new VBox();
        layout.getChildren().addAll(idLabel, desLabel, desTextArea, processLabel, processTextArea, buttonLayout);
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

        TextArea swCaseDesTextArea = new TextArea();
        swCaseDesTextArea.setText(c.getDescription());
        TextArea swCaseProcessTextArea = new TextArea();
        swCaseProcessTextArea.setText(c.getProcess());
        Label swCaseIdLabel = new Label();
        swCaseIdLabel.setText("ID: " + c.getId());
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
        layout.getChildren().addAll(swCaseIdLabel, swCaseDesLabel, swCaseDesTextArea, swCaseProcessLabel, swCaseProcessTextArea, buttonLayout);
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

        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Indtast navn");
        TextField idTextField = new TextField();
        idTextField.setPromptText("Indtast ID");
        TextArea needsTextField = new TextArea();
        needsTextField.setPromptText("Indtast behov");
        Label nameLabel = new Label();
        nameLabel.setText("ID");
        Label idLabel = new Label();
        idLabel.setText("Navn");
        Label needsLabel = new Label();
        needsLabel.setText("Behov");

        Button closeButton = new Button("Luk");
        closeButton.setOnAction(e -> window.close());

        Button createButton = new Button("Opret");
        createButton.setOnAction(e -> createAndClose(window, ib, nameTextField.getText(), idTextField.getText(), needsTextField.getText()));
//        createButton.setOnAction( e-> window.close());

        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().addAll(closeButton, createButton);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setSpacing(10);
        buttonLayout.setPadding(new Insets(10, 10, 10, 10));
        VBox layout = new VBox();
        layout.getChildren().addAll(idLabel, nameTextField, nameLabel, idTextField, needsLabel, needsTextField, buttonLayout);
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

        TextField inquiryOriginTextField = new TextField();
        inquiryOriginTextField.setPromptText("Indtast indsender");
        TextArea inquiryDescTextField = new TextArea();
        inquiryDescTextField.setPromptText("Indtast beskrivelse");
        Label idLabel = new Label();
        idLabel.setText("ID: " + c.getId() + "-2");
        Label originLabel = new Label();
        originLabel.setText("Indsender");
        Label descLabel = new Label();
        descLabel.setText("Beskrivelse");

        Label isInformedLabel = new Label();
        isInformedLabel.setText("Er borgeren blevet informeret om henvendelse: ");

        HBox radioKeepers = new HBox();
        RadioButton radiob1 = new RadioButton();
        RadioButton radiob2 = new RadioButton();
        radiob1.setText("Ja  ");
        radiob2.setText("Nej");
        ToggleGroup tg = new ToggleGroup();
        tg.getToggles().addAll(radiob1, radiob2);
        radioKeepers.getChildren().addAll(isInformedLabel, radiob1, radiob2);

        Button closeButton = new Button("Luk");
        closeButton.setOnAction(e -> window.close());

        Button createButton = new Button("Opret");
        createButton.setOnAction(e -> createAndClose(window, ib, inquiryOriginTextField.getText(), c, inquiryDescTextField.getText(), radiob1));
//        createButton.setOnAction( e-> window.close());

        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().addAll(closeButton, createButton);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setSpacing(10);
        buttonLayout.setPadding(new Insets(10, 10, 10, 10));
        VBox layout = new VBox();
        layout.getChildren().addAll(radioKeepers, idLabel, originLabel, inquiryOriginTextField, descLabel, inquiryDescTextField, buttonLayout);
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

        TextField inquiryOriginTextField = new TextField();
        inquiryOriginTextField.setText(i.getOrigin());
        TextArea inquiryDescTextField = new TextArea();
        inquiryDescTextField.setText(i.getDescription());
        Label swCaseIdLabel = new Label();
        swCaseIdLabel.setText("ID: " + i.getId());
        Label swCaseDesLabel = new Label();
        swCaseDesLabel.setText("Indsender");
        Label swCaseProcessLabel = new Label();
        swCaseProcessLabel.setText("Beskrivelse");

        Label isInformedLabel = new Label();
        isInformedLabel.setText("Er borgeren blevet informeret om henvendelse: ");

        HBox radioKeepers = new HBox();
        RadioButton radiob1 = new RadioButton();
        RadioButton radiob2 = new RadioButton();
        radiob1.setText("Ja  ");
        radiob2.setText("Nej");
        ToggleGroup tg = new ToggleGroup();
        tg.getToggles().addAll(radiob1, radiob2);
        radioKeepers.getChildren().addAll(isInformedLabel, radiob1, radiob2);
        if (i.isCitizenInformed()) {
            radiob1.setSelected(true);
        } else {
            radiob2.setSelected(true);
        }

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        Button createButton = new Button("Save");

        createButton.setOnAction(e -> editInquiryAndClose(window, ib, i, inquiryDescTextField.getText(), radiob1));
        // createButton.setOnAction( e-> window.close());

        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().addAll(closeButton, createButton);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setSpacing(10);
        buttonLayout.setPadding(new Insets(10, 10, 10, 10));
        VBox layout = new VBox();
        layout.getChildren().addAll(radioKeepers, swCaseIdLabel, swCaseDesLabel, inquiryOriginTextField, swCaseProcessLabel, inquiryDescTextField, buttonLayout);
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setPadding(new Insets(10, 10, 0, 10));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    public void createAndClose(Stage window, IBusiness ib, String des, String procs, ISocialWorker sw, ICitizen c) {
        ib.createCase(c.getId() + "-1", des, procs, sw, c);
        window.close();
    }

    public void createAndClose(Stage window, IBusiness ib, String name, String id, String needs) {
        if (ib.hasUnqiueCitizenID(id) && ib.hasAcceptableID(id) && ib.hasAcceptableName(name)) {
            ib.createCitizen(name, id, needs);
            window.close();
        }
    }

    public void createAndClose(Stage window, IBusiness ib, String origin, ICitizen c, String description, RadioButton r) {
        if (r.isSelected()) {
            ib.createInquiry(c.getId() + "-2", origin, true, c, description);
        } else {
            ib.createInquiry(c.getId() + "-2", origin, false, c, description);
        }
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

    public void editInquiryAndClose(Stage window, IBusiness ib, IInquiry i, String desc, RadioButton r) {

        if (r.isSelected()) {
            ib.editInquiry(desc, i, true);
        } else {
            ib.editInquiry(desc, i, false);
        }
        window.close();
    }

}
