package app.Objects;

public class WorldTempPop {
    private int year;
    private double temp;
    private long population;
    
    //intialize methods
    public WorldTempPop(){ 
    }

    public WorldTempPop(int year){
        this.year = year;
    }
    public WorldTempPop(int year, double temp, long population){
        this.year = year;
        this.temp = temp;
        this.population = population;
    }

    //getters
    public int getYear(){ 
        return year;
    }
    public double getTemp(){
        return temp;
    }
    public long getPop(){
        return population;
    }
    //setters
    public void setYear(int year){
        this.year = year;
    }
    public void setTemp(double temp){
        this.temp = temp;
    }
    public void setPop(long population){
        this.population = population;
    }
}
