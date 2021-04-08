package sample;

import Model.Frage;
import Model.Fragenbogen;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ObjectCreator {


    public ObjectCreator() {
    }

    public Button CreateButton(int x, int y, String text){
        Button button = new Button(text);
        button.setPrefSize(65,30);
        button.setLayoutX(x);
        button.setLayoutY(y);

        return button;
    }

    public Label CreateLabel(int x, int y, String text, Font font, Color color){
        Label label = new Label();
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setText(text);
        label.setFont(font);
        label.setTextFill(color);

        return label;
    }

    public CheckBox CreateCheckBox(int x, int y, String text){
        CheckBox checkBox = new CheckBox();
        checkBox.setLayoutX(x);
        checkBox.setLayoutY(y);
        checkBox.setText(text);
        checkBox.setFont(Font.font("Verdana",12));
        checkBox.setTextFill(Color.BLACK);

        return checkBox;
    }

    public void SetUpQuestion(Fragenbogen fragenbogen, CheckBox ans1, CheckBox ans2, CheckBox ans3, Label frage, int index){
        Frage f = fragenbogen.getQuestions().get(index);
        frage.setText((index+1)+". "+f.getText());

        ans1.setText(f.getAnswers().get(0));
        ans2.setText(f.getAnswers().get(1));
        ans3.setText(f.getAnswers().get(2));

        ans1.setSelected(false);
        ans2.setSelected(false);
        ans3.setSelected(false);
    }

}
