package app.Objects;

public class TempDifference {
    int ID;
    String name;
    double difference;
    double differencePercent;
    boolean up = false;
    public double tFirstYear;
    public double tLastYear;
    public TempDifference(){

    }
    public void setID(int ID){
        this.ID = ID;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setDifference(double tFirstYear, double tLastYear){
        this.tLastYear = tLastYear;
        this.tFirstYear = tFirstYear;
        
        if(tLastYear - tFirstYear < 0){
            up = false;
        } //temp
        else{
            up = true;
        }
        this.difference = Math.abs(tLastYear - tFirstYear);
        this.differencePercent = Math.abs(100-(tLastYear/tFirstYear)*100);

    }


    public double getDifference(){
        return difference;
    }
    public double getDifferencePercent(){
        return differencePercent;
    }
    public int getID(){
        return ID;
    }
    public String getName(){
        return name;
    }
    public boolean getUp(){
        return up;
    }
}
