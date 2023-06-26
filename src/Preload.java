/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Controller.HomePageFormController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Preload extends javafx.application.Preloader {

    private Stage preloadStage;
    private Scene scene;
    
    public Preload() {
        
    }

    @Override
    public void init() throws IOException {
                                         
    Parent root1 = FXMLLoader.load(getClass().getResource("View/HomePageForm.fxml"));
    scene = new Scene(root1); 
    //scene = new Scene(root1, 700, 400, Color.TRANSPARENT); 
    //root1.setStyle("-fx-background-color: transparent;"); 
                
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
       this.preloadStage = primaryStage;

               
       
       // preloaderStage.initStyle(StageStyle.TRANSPARENT); 
       
        // Set preloader scene and show stage.
        preloadStage.setScene(scene);
        preloadStage.initStyle(StageStyle.UNDECORATED);
        preloadStage.show();
        
        
      
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification info) {
      
          if (info instanceof ProgressNotification) {
            HomePageFormController.label.setText("Loading "+((ProgressNotification) info).getProgress()*100 + "%");

            System.out.println("Value@ :" + ((ProgressNotification) info).getProgress());
              HomePageFormController.startProgressBar.setProgress(((ProgressNotification) info).getProgress());
              System.out.println("end");
        }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
      
        StateChangeNotification.Type type = info.getType();
        switch (type) {
            
            case BEFORE_START:
                // Called after MyApplication#init and before MyApplication#start is called.
                System.out.println("BEFORE_START");
                preloadStage.hide();
                break;
        }
    }
}

