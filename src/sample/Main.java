package sample;

import Model.Frage;
import Model.FrageRepository;
import Model.Fragenbogen;
import Model.TimerQuiz;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application implements EventHandler<ActionEvent> {

    Button next;
    Button exitButton;
    Button startButton;
    Label frage;
    Label raspCorecte;
    Label raspGresite;
    Label timeRemained;
    CheckBox ans1;
    CheckBox ans2;
    CheckBox ans3;
    int index=0;
    int idFB = 1;
    FrageRepository frageRepository = new FrageRepository("src/Questions/Questions.json");
    List<Frage> questions =  new ArrayList<>();
    Fragenbogen fb = new Fragenbogen(idFB,1,questions);
    Pane layout = new Pane();
    TimerQuiz tq = new TimerQuiz();



    ObjectCreator objectCreator = new ObjectCreator();
    Font fontMare = Font.font("Verdana", 20);
    Font fontCronometru= Font.font("Arial", 20);


    public Main() throws IOException, ParseException {
    }

    @Override
    public void start(Stage primaryStage){

        primaryStage.setTitle("Quiz Categoria B");


        startButton = objectCreator.CreateButton(400, 300, "Start");
        startButton.setOnAction(this);

        exitButton = objectCreator.CreateButton(400,350, "Exit");
        exitButton.setOnAction(this);

        layout.getChildren().add(exitButton);
        layout.getChildren().add(startButton);
        layout.setStyle("-fx-background-color: LIGHTBLUE");


        Scene scene = new Scene(layout,900,600);
        primaryStage.setScene(scene);
        primaryStage.show();



    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource() == next) {

            //se verifica intrebarea care este pe ecran
            Frage f = fb.getQuestions().get(index);

            int nrRaspCorect = f.getRightAnswers().size();
            int raspDate =0;

            //se verifica numarul de raspunsuri selectate
            if(ans1.isSelected())
                raspDate++;
            if(ans2.isSelected())
                raspDate++;
            if(ans3.isSelected())
                raspDate++;

            //se verifica daca raspunsurile selectate sunt si corecte
            int raspCorectDat=0;
            if(raspDate == nrRaspCorect) {
                if (ans1.isSelected() && f.getRightAnswers().contains("A")) {
                    raspCorectDat++;
                }
                if (ans2.isSelected() && f.getRightAnswers().contains("B")) {
                    raspCorectDat++;
                }
                if(ans3.isSelected() && f.getRightAnswers().contains("C")) {
                    raspCorectDat++;
                }
            }

            //se verifica daca s-au dat toate raspunsurile corecte
            if(raspCorectDat == nrRaspCorect){
                System.out.println("raspuns corect");
                fb.setCtRight(fb.getCtRight()+1);
            }
            else
                fb.setCtWrong(fb.getCtWrong()+1);

            //se modifica textul din labelurile de punctaje
            raspGresite.setText("Raspunsuri gresite:  "+fb.getCtWrong());
            raspCorecte.setText("Raspunsuri corecte: "+fb.getCtRight());

            //daca s-au dat 5 raspunsuri gresite se incheie testul
            if(fb.getCtWrong() == 5)
            {
                ShowEndLayout("Ati raspuns gresit la mai mult de 5 intrebari. Testul se va incheia/reseta.");
            }

            if(fb.getCtRight() == 26-fb.getCtWrong()){
                ShowEndLayout("Testul a fost trecut. Daca doriti sa reluati testul, apasati pe Restart");
            }

            //se afiseasza urmatoarea intrebare
            index++;
            objectCreator.SetUpQuestion(fb, ans1, ans2, ans3, frage, index);

        }

        if(actionEvent.getSource() == exitButton) {
            Platform.exit();
            System.exit(0);
        }

        if(actionEvent.getSource() == startButton) {
            layout.getChildren().clear();
            try {
                frageRepository = new FrageRepository("src/Questions/Questions.json");
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }

            fb.generateQuestions(frageRepository);
            index = 0;
            fb.setCtRight(0);
            fb.setCtWrong(0);

            timeRemained = objectCreator.CreateLabel(100, 40, "", fontCronometru, Color.BLACK);
            tq.createTimer(timeRemained);

            next = objectCreator.CreateButton(300, 500, "Next");
            next.setOnAction(this);

            exitButton = objectCreator.CreateButton(500, 500, "Exit");
            exitButton.setOnAction(this);

            startButton.setText("Restart");
            startButton.setLayoutX(400);
            startButton.setLayoutY(500);

            frage = objectCreator.CreateLabel(50, 150, "", fontMare, Color.BLACK);

            raspCorecte = objectCreator.CreateLabel(500, 20, "Raspunsuri corecte: " + (fb.getCtRight()), fontMare, Color.GREEN);

            raspGresite = objectCreator.CreateLabel(500, 40, "Raspunsuri gresite:  " + (fb.getCtWrong()), fontMare, Color.RED);

            ans1 = objectCreator.CreateCheckBox(50, 200, "");

            ans2 = objectCreator.CreateCheckBox(50, 250, "");

            ans3 = objectCreator.CreateCheckBox(50, 300, "");

            objectCreator.SetUpQuestion(fb, ans1, ans2, ans3, frage, index);

            layout.getChildren().add(next);
            layout.getChildren().add(frage);
            layout.getChildren().add(ans1);
            layout.getChildren().add(ans2);
            layout.getChildren().add(ans3);
            layout.getChildren().add(raspCorecte);
            layout.getChildren().add(raspGresite);
            layout.getChildren().add(exitButton);
            layout.getChildren().add(timeRemained);
            layout.getChildren().add(startButton);

        }
    }

    private void ShowEndLayout(String text){
        layout.getChildren().clear();
        Label message = objectCreator.CreateLabel(50, 300, text, fontMare, Color.BLACK);
        startButton = objectCreator.CreateButton(400, 350, "Restart");
        exitButton = objectCreator.CreateButton(400,400, "Exit");
        startButton.setOnAction(this);
        exitButton.setOnAction(this);
        layout.getChildren().add(exitButton);
        layout.getChildren().add(startButton);
        layout.getChildren().add(message);
    }

    public static void main(String[] args){
        launch(args);
    }
}
