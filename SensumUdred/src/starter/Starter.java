package starter;

import acq.IBusiness;
import acq.IData;
import acq.IPresentation;
import business.BusinessFacade;
import data.DataFacade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.PresentationFacade;

/**
 *
 * @author Søren Bendtsen
 */
public class Starter extends Application {

    /**
     * Glue code for 3-layer architecture
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code applicastiteston logic here
        IData data = new DataFacade();
        IBusiness business = new BusinessFacade();
        // Data is injected into business layer.
        business.injectData(data);
        //System.out.println(business.getUsers());
        IPresentation ui = new PresentationFacade();
        // Business is injecting into UI layer.
        ui.injectBusiness(business);

        // Start GUI.
        ui.openUI();
    }
    
    /**
     * Start GUI
     * @param stage
     * @throws Exception 
     */

    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("FXML_login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Sensum Udred");
    }

}
