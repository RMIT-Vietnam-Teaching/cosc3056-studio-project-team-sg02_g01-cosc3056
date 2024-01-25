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
    public void setName(String name){
        this.name = name;
    }
    public void setDifference(double tFirstYear, double tLastYear){
        this.difference = tLastYear - tFirstYear; //temp
    }


    public double getDifference(){
        return difference;
    }
    public int getID(){
        return ID;
    }
    public String getName(){
        return name;
    }
}
