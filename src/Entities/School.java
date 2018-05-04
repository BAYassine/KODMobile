package Entities;



public class School  {


    private Integer id;

    private String schoolname;

    private String address;

    private int schoolphone;

    private double xschool;

    private double yschool;

    private String schoolmail;

    public School() {
    }

    public School(Integer id) {
        this.id = id;
    }

    public School(Integer id, String schoolname, String address, int schoolphone, double xschool, double yschool, String schoolmail) {
        this.id = id;
        this.schoolname = schoolname;
        this.address = address;
        this.schoolphone = schoolphone;
        this.xschool = xschool;
        this.yschool = yschool;
        this.schoolmail = schoolmail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSchoolphone() {
        return schoolphone;
    }

    public void setSchoolphone(int schoolphone) {
        this.schoolphone = schoolphone;
    }

    public double getXschool() {
        return xschool;
    }


    public void setXschool(double xschool) {
        this.xschool = xschool;
    }

    public double getYschool() {
        return yschool;
    }

    public void setYschool(double yschool) {
        this.yschool = yschool;
    }

    public String getSchoolmail() {
        return schoolmail;
    }

    public void setSchoolmail(String schoolmail) {
        this.schoolmail = schoolmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }




    @Override
    public String toString() {
        return "School{" + "id=" + id + ", schoolname=" + schoolname + ", address=" + address + ", schoolphone=" + schoolphone + ", xschool=" + xschool + ", yschool=" + yschool + ", schoolmail=" + schoolmail + '}';
    }



}
