import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {
    private static  final int COUNT_LIMIT=10;

    public static void main(String[] args) {

        LauncherImpl.launchApplication(AppInitializer.class,Preload.class,args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        /*
        URL resource = this.getClass().getResource("View/HomePageForm.fxml");
        Parent window = FXMLLoader.load(resource);
        Scene scene = new Scene(window);
        primaryStage.setScene(scene);
        primaryStage.setTitle("COMPUTER SHOP MANAGEMENT SYSTEM ");
        primaryStage.centerOnScreen();
        primaryStage.show();
         */

        Parent root = FXMLLoader.load(getClass().getResource("View/LoginForm.fxml"));
        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @Override
    public void init() throws InterruptedException {
        for (int i=1;i<=COUNT_LIMIT;i++){

        double progress=(double) i/10;
            System.out.println("Progress: "+progress);
            LauncherImpl.notifyPreloader(this, new Preload.ProgressNotification(progress));
            Thread.sleep(100);
        }
    }

}
