package Model;

import java.io.IOException;
import java.util.*;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class FrageRepository {
    List<Frage> fragen = new ArrayList<Frage>();

    public List<Frage> getFragen() {
        return fragen;
    }

    public Frage getFrage(int index){
        return fragen.get(index);
    }
    public FrageRepository(String filename) throws IOException, ParseException {

        // parsing file "JSONExample.json"
        JSONParser parser = new JSONParser();
        JSONArray obj = (JSONArray) parser.parse(new FileReader(filename));

        for (Object a : obj) {

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) a;

            String id = (String) jo.get("ID");
            int id2 = Integer.parseInt(id);

            String text = (String) jo.get("frage");

            JSONArray ja = (JSONArray) jo.get("antworten");
            List<String> antworten = new ArrayList<>();

            for (Object value : ja) {
                antworten.add(value.toString());
            }

            JSONArray ja1 = (JSONArray) jo.get("richtigeAntworten");
            List<String> richtigeAntworten = new ArrayList<>();

            for (Object o : ja1) {
                richtigeAntworten.add(o.toString());
            }

            Frage frage = new Frage(id2, text, antworten, richtigeAntworten);
            fragen.add(frage);
        }
    }

    public List<Frage> genereateFragenBogen(){
        List<Frage> FragenFinal = new ArrayList<>();
        Random rand = new Random();
        for(int i=0;i<26;i++){
            int randomIndex = rand.nextInt(fragen.size());
            Frage randomFrage = fragen.get(randomIndex);
            FragenFinal.add(randomFrage);
            fragen.remove(randomIndex);
        }
        return FragenFinal;
    }
    public void printFragen(){
        for(Frage f : fragen) {
            System.out.println(f.getId() + " " + f.getText());
            for(String s:f.getAnswers())
                System.out.println(s);
            System.out.println("Raspunsurile corecte: ");
            for(String s: f.getRightAnswers()){
                System.out.println(s);
            }
        }
    }
}
