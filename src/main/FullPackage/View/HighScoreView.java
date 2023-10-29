package main.FullPackage.View;

import lombok.Getter;
import main.FullPackage.Model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

@Getter
public class HighScoreView {
    private final Stage HSstage;
    private final ObservableList<String> items = FXCollections.observableArrayList();

    public HighScoreView() {
        ListView<String> ranking = new ListView<>();
        HBox group = new HBox();
        try {
            Model.LoadHS(items);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        ranking.setItems(items);
        Scene Hscore = new Scene(group, 200, 200);
        group.getChildren().add(ranking);
        HSstage = new Stage();
        HSstage.initModality(Modality.APPLICATION_MODAL);
        HSstage.setScene(Hscore);
    }
}
