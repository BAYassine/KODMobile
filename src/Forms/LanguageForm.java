package Forms;

import Core.App;
import Services.KidsService;
import com.codename1.capture.Capture;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.sun.xml.internal.ws.util.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class LanguageForm extends Form{
    private ArrayList<String> words = new ArrayList<>();
    private ArrayList<Integer> doneWords = new ArrayList<>();
    private String current;

    private int getRandom() {
        Random rand = new Random();
        int i;
        do {
            i = rand.nextInt(words.size() + 1);
        } while (doneWords.contains(i));
        doneWords.add(i);
        if (doneWords.size() == words.size())
            doneWords.clear();
        return i;
    }

    public LanguageForm() {
        super(BoxLayout.y());
        this.setToolbar(App.sidemenu);
        try {
            Files.lines(Paths.get("./src/assets/mots.dict"), Charset.forName("UTF-8")).forEach(
                    s -> words.add(s)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        current = words.get(getRandom());
        Container content = new Container(BoxLayout.y());
        Label wordPH = new Label(StringUtils.capitalize(current));
        wordPH.setAlignment(Component.CENTER);
        wordPH.getStyle().setPadding(5,5,0,0);

        Button start = new Button("Parler");
        FontImage.setMaterialIcon(start, FontImage.MATERIAL_MIC);

        Container buttons = new Container(BoxLayout.y());
        Button next = new Button("Mot suivant");
        Button retry = new Button("Réessayer");
        retry.setVisible(false);
        Label message = new Label("Bien");
        message.setAlignment(Component.CENTER);
        message.setVisible(false);
        buttons.addAll(message, retry, next);

        start.addActionListener(e -> {
            String file = Capture.captureAudio();
            if(file != null && new KidsService().spellCheck(current, file)){
            }
            else if (file != null)
                this.add(new Label("Réessayer"));
            this.revalidate();
        });
        next.addActionListener(e -> {
            current = words.get(getRandom());
            wordPH.setText(StringUtils.capitalize(current));
            this.revalidate();
        });
        content.addAll(start, buttons);
        content.getStyle().setPadding(0 ,0 ,20,20);
        this.addAll(wordPH,content);

    }
}
