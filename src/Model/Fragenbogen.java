package Model;

import java.util.List;
import java.util.Random;

public class Fragenbogen {
    private int ID;
    private int QuestionNr;
    private List<Frage> Questions;
    private int ctRight;
    private int ctWrong;

    public Fragenbogen(int ID, int questionNr, List<Frage> questions) {
        this.ID = ID;
        QuestionNr = questionNr;
        ctRight = ctWrong = 0;
        Questions = questions;
    }

    public void generateQuestions(FrageRepository f) {
        List<Frage> fragen = f.getFragen();
        Random rand = new Random();
        for (int i = 0; i < 26; i++) {
            int randomIndex = rand.nextInt(fragen.size());
            Frage randomFrage = fragen.get(randomIndex);
            Questions.add(randomFrage);
            fragen.remove(randomIndex);
        }
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getQuestionNr() {
        return QuestionNr;
    }

    public void setQuestionNr(int questionNr) {
        QuestionNr = questionNr;
    }

    public List<Frage> getQuestions() {
        return Questions;
    }

    public void setQuestions(List<Frage> questions) {
        Questions = questions;
    }

    public int getCtRight() {
        return ctRight;
    }

    public void setCtRight(int ctRight) {
        this.ctRight = ctRight;
    }

    public int getCtWrong() {
        return ctWrong;
    }

    public void setCtWrong(int ctWrong) {
        this.ctWrong = ctWrong;
    }
}
