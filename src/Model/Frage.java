package Model;

import java.util.List;

public class Frage {
    private int id;
    private String text;
    private List<String> answers;
    private List<String> rightAnswers;


    public Frage(int id, String text, List<String> answers, List<String> rightAnswers) {
        this.id = id;
        this.text = text;
        this.answers = answers;
        this.rightAnswers = rightAnswers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public List<String> getRightAnswers() { return rightAnswers; }

    public void setRightAnswers(List<String> rightAnswers) { this.rightAnswers = rightAnswers; }
}
