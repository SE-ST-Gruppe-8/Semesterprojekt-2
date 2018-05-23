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
     * A method to display a popup window. The window uses text writtin in the
     * text areas to create a case.
     *
     * @param header the text in the title bar of the window.
     * @param ib The IBusiness, it is used to call the create method.
     * @param c The selected citizen to create a case on.
     */
    public void displayCaseCreation(String header, IBusiness ib, ICitizen c) {

        // Creates the window pane and sets it parameters.
        Stage window = new Stage();
        window.setTitle(header);
        window.setMinWidth(400);
        window.setMaxWidth(600);
        window.setResizable(false);

        // Creates elements and sets their texts.
        TextArea desTextArea = new TextArea();
        desTextArea.setPromptText("Indtast en beskrivelse af sagen");
        TextArea processTextArea = new TextArea();
        processTextArea.setPromptText("Indtast forløbet for borgeren");
        Label idLabel = new Label();
        idLabel.setText("ID: " + c.getId() + "-2");
        Label desLabel = new Label();
        desLabel.setText("Beskrivelse");
        Label processLabel = new Label();
        processLabel.setText("Forløb");
        Button closeButton = new Button("Luk");
        Button createButton = new Button("Opret");

        // Sets the onActionEvent on Close and Create buttons.
        closeButton.setOnAction(e -> window.close());
        createButton.setOnAction(e -> createAndClose(window, ib, desTextArea.getText(), processTextArea.getText(), (ISocialWorker) ib.getActiveUser(), c));

        // Puts all of the created elements together, and inserts it into the layout, and sets the scene.
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

    /**
     * A method to display a popup window. The window uses text writtin in the
     * text areas to edit a case.
     *
     * @param header the text in the title bar of the window.
     * @param ib The IBusiness to call the edit method.
     * @param c The selected case to be edited.
     */
    public void displayCaseEdit(String header, IBusiness ib, ICase c) {

        // Creates the window pane and sets it parameters.
        Stage window = new Stage();
        window.setTitle(header);
        window.setMinWidth(400);
        window.setMaxWidth(600);
        window.setResizable(false);

        // Creates elements and sets their texts.
        TextArea swCaseDesTextArea = new TextArea();
        swCaseDesTextArea.setText(c.getDescription());
        TextArea swCaseProcessTextArea = new TextArea();
        swCaseProcessTextArea.setText(c.getProcess());
        Label swCaseIdLabel = new Label();
        swCaseIdLabel.setText("ID: " + c.getID());
        Label swCaseDesLabel = new Label();
        swCaseDesLabel.setText("Beskrivelse");
        Label swCaseProcessLabel = new Label();
        swCaseProcessLabel.setText("Forløb");
        Button closeButton = new Button("Luk");
        Button createButton = new Button("Gem");

        // Sets the onActionEvent on Close and Create buttons.
        closeButton.setOnAction(e -> window.close());
        createButton.setOnAction(e -> editCaseAndClose(window, ib, c, swCaseDesTextArea.getText(), swCaseProcessTextArea.getText()));

        // Puts all of the created elements together, and inserts it into the layout, and sets the scene.
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

    /**
     * A method to display a popup window. The window uses text writtin in the
     * text areas to create a citizen.
     *
     * @param header the text in the title bar of the window.
     * @param ib The IBusiness to call the create method.
     */
    public void displayCitizenCreation(String header, IBusiness ib) {

        // Creates the window pane and sets it parameters.
        Stage window = new Stage();
        window.setTitle(header);
        window.setMinWidth(400);
        window.setMaxWidth(600);
        window.setResizable(false);

        // Creates elements and sets their texts.
        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Indtast navn");
        TextField idTextField = new TextField();
        idTextField.setPromptText("Indtast ID");
        TextArea needsTextField = new TextArea();
        needsTextField.setPromptText("Indtast behov");
        Label nameLabel = new Label();
        nameLabel.setText("ID\t*ID'et skal indeholde " + ib.getFinalInts()[1] + " tegn");
        Label idLabel = new Label();
        idLabel.setText("Navn");
        Label needsLabel = new Label();
        needsLabel.setText("Behov");
        Button closeButton = new Button("Luk");
        Button createButton = new Button("Opret");

        // Sets the onActionEvent on Close and Create buttons.
        closeButton.setOnAction(e -> window.close());
        createButton.setOnAction(e -> createAndClose(window, ib, nameTextField.getText(), idTextField.getText(), needsTextField.getText()));

        // Puts all of the created elements together, and inserts it into the layout, and sets the scene.
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

    /**
     * A method to display a popup window. The window uses text writtin in the
     * text areas to edit a citizen.
     *
     * @param header the text in the title bar of the window.
     * @param ib The IBusiness to call the create method.
     * @param c The selected citizen to be edited.
     */
    public void displayCitizenEdit(String header, IBusiness ib, ICitizen c) {

        // Creates the window pane and sets it parameters.
        Stage window = new Stage();
        window.setTitle(header);
        window.setMinWidth(400);
        window.setMaxWidth(600);
        window.setResizable(false);

        // Creates elements and sets their texts.
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
        nameTextField.setDisable(true);
        idTextField.setDisable(true);
        Button closeButton = new Button("Luk");
        Button createButton = new Button("Gem");

        // Sets the onActionEvent on Close and Create buttons.
        closeButton.setOnAction(e -> window.close());
        createButton.setOnAction(e -> editCitizenAndClose(window, ib, c, needsTextField.getText()));

        // Puts all of the created elements together, and inserts it into the layout, and sets the scene.
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

    /**
     * A method to display a popup window. The window uses text writtin in the
     * text areas to create an inquiry.
     *
     * @param header the text in the title bar of the window.
     * @param ib The IBusiness to call the create method.
     * @param c The selected citizen to create an inquiry for.
     */
    public void displayInquiryCreation(String header, IBusiness ib, ICitizen c) {

        // Creates the window pane and sets it parameters.
        Stage window = new Stage();
        window.setTitle(header);
        window.setMinWidth(400);
        window.setMaxWidth(600);
        window.setResizable(false);

        // Creates elements and sets their texts.
        TextField inquiryOriginTextField = new TextField();
        inquiryOriginTextField.setPromptText("Indtast indsender");
        TextArea inquiryDescTextField = new TextArea();
        inquiryDescTextField.setPromptText("Indtast beskrivelse");
        Label idLabel = new Label();
        idLabel.setText("ID: " + c.getId() + "-1");
        Label originLabel = new Label();
        originLabel.setText("Indsender");
        Label descLabel = new Label();
        descLabel.setText("Beskrivelse");
        Label isInformedLabel = new Label();
        isInformedLabel.setText("Er borgeren blevet informeret om henvendelsen?  ");
        RadioButton radiob1 = new RadioButton();
        RadioButton radiob2 = new RadioButton();
        radiob1.setText("Ja");
        radiob2.setText("Nej");
        radiob2.setSelected(true);
        Button closeButton = new Button("Luk");
        Button createButton = new Button("Opret");

        // Sets the onActionEvent on Close and Create buttons.
        closeButton.setOnAction(e -> window.close());
        createButton.setOnAction(e -> createAndClose(window, ib, inquiryOriginTextField.getText(), c, inquiryDescTextField.getText(), radiob1));

        // Puts all of the created elements together, and inserts it into the layout, and sets the scene.
        HBox radioKeepers = new HBox();
        ToggleGroup tg = new ToggleGroup();
        tg.getToggles().addAll(radiob1, radiob2);
        radioKeepers.getChildren().addAll(isInformedLabel, radiob1, radiob2);
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

    /**
     * A method to display a popup window. The window uses text writtin in the
     * text areas to edit an inquiry.
     *
     * @param header the text in the title bar of the window.
     * @param ib The IBusiness to call the create method.
     * @param i The selected inquiry to be edited.
     */
    public void displayInquiryEdit(String header, IBusiness ib, IInquiry i) {

        // Creates the window pane and sets it parameters.
        Stage window = new Stage();
        window.setTitle(header);
        window.setMinWidth(400);
        window.setMaxWidth(600);
        window.setResizable(false);

        // Creates elements and sets their texts.
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
        inquiryOriginTextField.setDisable(true);
        Label isInformedLabel = new Label();
        isInformedLabel.setText("Er borgeren blevet informeret om henvendelse: ");
        RadioButton radiob1 = new RadioButton();
        RadioButton radiob2 = new RadioButton();
        radiob1.setText("Ja  ");
        radiob2.setText("Nej");
        Button closeButton = new Button("Luk");
        Button createButton = new Button("Gem");

        // Sets the onActionEvent on Close and Create buttons.
        closeButton.setOnAction(e -> window.close());
        createButton.setOnAction(e -> editInquiryAndClose(window, ib, i, inquiryDescTextField.getText(), radiob1));

        // Puts all of the created elements together, and inserts it into the layout, and sets the scene.
        HBox radioKeepers = new HBox();
        ToggleGroup tg = new ToggleGroup();
        tg.getToggles().addAll(radiob1, radiob2);
        radioKeepers.getChildren().addAll(isInformedLabel, radiob1, radiob2);
        if (i.isCitizenInformed()) {
            radiob1.setSelected(true);
        } else {
            radiob2.setSelected(true);
        }
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

    /**
     * A method used in the create case window, to create a case.
     *
     * @param window The create case window.
     * @param ib The IBusiness to call the create method.
     * @param des The description for the inquiry, written in the text area of
     * the create case window.
     * @param procs the process for the inquiry, written in the text area of the
     * create case window
     * @param sw The socialworker is the active user.
     * @param c The citizen selected to have a case created for.
     */
    public void createAndClose(Stage window, IBusiness ib, String des, String procs, ISocialWorker sw, ICitizen c) {
        //The case gets the citizens ID with a "-2" added in the end.
        ib.createCase(c.getId() + "-2", des, procs, sw, c);
        window.close();
    }

    /**
     * A method used in the create citizen window, to create a citizen.
     *
     * @param window The create citizen window.
     * @param ib The IBusiness to call the create method.
     * @param name The name for the citizen, written in the text area of the
     * create citizen window.
     * @param id The ID for the citizen, written in the text area of the create
     * citizen window.
     * @param needs The needs for the citizen, written in the text area of the
     * create citizen window.
     */
    public void createAndClose(Stage window, IBusiness ib, String name, String id, String needs) {
        if (ib.hasUnqiueCitizenID(id) && ib.hasAcceptableID(id) && ib.hasAcceptableName(name)) {
            ib.createCitizen(name, id, needs);
            window.close();
        }
    }

    /**
     * A method used in the create inqury window, to create a inquiry for a
     * selected citizen.
     *
     * @param window The create inquiry window.
     * @param ib The IBusiness to call the create method.
     * @param origin The origin for the inquiry, written in the text area of the
     * create inquiry.
     * @param c The citizen selected to create an inquiry for.
     * @param description The description for the inquiry, written in the text
     * area of the inquiry.
     * @param r the radiobutton in the create inquiry window to check whether a
     * citizen is informed or not.
     */
    public void createAndClose(Stage window, IBusiness ib, String origin, ICitizen c, String description, RadioButton r) {
        //The inquiry gets the citizens ID with a "-1" added in the end.
        if (r.isSelected()) {
            ib.createInquiry(c.getId() + "-1", origin, true, c, description);
        } else {
            ib.createInquiry(c.getId() + "-1", origin, false, c, description);
        }
        window.close();
    }

    /**
     * A method used in the edit case window, to edit a selected case.
     *
     * @param window The edit case window.
     * @param ib The IBusiness to call the create method.
     * @param c The case selected to be edited.
     * @param desc The description to be edited.
     * @param proc The process to be edited.
     */
    public void editCaseAndClose(Stage window, IBusiness ib, ICase c, String desc, String proc) {
        ib.editCase(desc, proc, c);
        window.close();
    }

    /**
     * A method used in the edit citizen window, to edit a selected citizen.
     *
     * @param window The edit citizen window.
     * @param ib The IBusiness to call the create method.
     * @param c The citizen selected to be edited.
     * @param needs The needs to be edited.
     */
    public void editCitizenAndClose(Stage window, IBusiness ib, ICitizen c, String needs) {
        ib.editCitizen(needs, c);
        window.close();
    }

    /**
     * A method used in the edit inquiry window, to edit a selected inqury.
     *
     * @param window The edit inquiry window.
     * @param ib The IBusiness to call the create method.
     * @param i The inquiry selected to be edited.
     * @param desc The description to be edited.
     * @param r The radiobutton for whether a citizen is informed, to be edited.
     */
    public void editInquiryAndClose(Stage window, IBusiness ib, IInquiry i, String desc, RadioButton r) {

        if (r.isSelected()) {
            ib.editInquiry(desc, i, true);
        } else {
            ib.editInquiry(desc, i, false);
        }
        window.close();
    }

}
