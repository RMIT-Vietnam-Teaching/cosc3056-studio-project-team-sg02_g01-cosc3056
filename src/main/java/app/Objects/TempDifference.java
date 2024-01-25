package app.Objects;

public class TempDifference {
    int ID;
    String name;
    double difference;
    public TempDifference(){

    }
    public void setID(int ID){
        this.ID = ID;
    }
    public void setname(String name){
        this.name = name;
    }
    public void setDifference(double firstYear, double lastYear){
        this.difference = lastYear - firstYear;
    }

}
