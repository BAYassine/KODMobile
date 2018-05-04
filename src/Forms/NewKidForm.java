package Forms;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

public class NewKidForm extends Form {

    public NewKidForm(){
        super(BoxLayout.y());
        Label space = new Label("Nouveau espace");
        TextField name= new TextField("", "Prenom");
        TextField age = new TextField("","Age");
        Label sexe = new Label("Sexe");
        RadioButton girl = new RadioButton("Fille");
        RadioButton boy = new RadioButton("Garçon");
        new ButtonGroup(girl, boy);
        Container sexec = new Container(BoxLayout.x());
        sexec.addAll(sexe, girl, boy);
        Button photoBtn = new Button("Choisir une photo");
//        photoBtn.addActionListener(e -> {
//            if (FileChooser.isAvailable()) {
//                FileChooser.showOpenDialog(".xls, .csv, text/plain", e2-> {
//                    String file = (String)e2.getSource();
//                    if (file == null) {
//                        this.add("No file was selected");
//                        this.revalidate();
//                    } else {
//                        String extension = null;
//                        if (file.lastIndexOf(".") > 0) {
//                            extension = file.substring(file.lastIndexOf(".")+1);
//                        }
//                        if ("txt".equals(extension)) {
//                            FileSystemStorage fs = FileSystemStorage.getInstance();
//                            try {
//                                InputStream fis = fs.openInputStream(file);
//                                this.addComponent(new SpanLabel(Util.readToString(fis)));
//                            } catch (Exception ex) {
//                                ex.printStackTrace();
//                            }
//                        } else {
//                            this.add("Selected file "+file);
//                        }
//                    }
//                    this.revalidate();
//                });
//            }
//        });
        this.addAll(space, name, age, sexec, photoBtn);

    }

}
