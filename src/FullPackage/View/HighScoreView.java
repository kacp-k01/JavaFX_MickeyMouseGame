package FullPackage.View;

import FullPackage.Model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


//High score table build class build class


public class HighScoreView {



    Stage HSstage;
    ObservableList<String> items = FXCollections.observableArrayList();


    public HighScoreView(Model model) {

        ListView<String> ranking = new ListView<>();

        HBox group = new HBox();

        try {
            model.LoadHS(items);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ranking.setItems(items);
        Scene Hscore = new Scene(group, 200, 200);

        group.getChildren().add(ranking);

        HSstage = new Stage();
        HSstage.initModality(Modality.APPLICATION_MODAL);
        HSstage.setScene(Hscore);

    }

    public Stage getHSstage() {
        return HSstage;
    }

    public ObservableList<String> getItems() {
        return items;
    }
}
