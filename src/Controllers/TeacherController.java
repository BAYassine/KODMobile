package Controllers;

import Entities.Teacher;
import Forms.TeacherForm;
import Services.TeacherService;
import com.codename1.ui.Container;

import java.util.ArrayList;

public class TeacherController {
    private Container contentPane;
    public void init() {


        ArrayList<Teacher> Teachers = new TeacherService().findAll();
        TeacherForm gf = new TeacherForm(Teachers);
        gf.show();

    }
    public void showid(int id) {
       Teacher teacher = new TeacherService().findteacherid( id );
        TeacherForm gf = new TeacherForm(teacher);
       gf.show();
        }



}
