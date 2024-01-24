package app.Objects;

public class WorldTemp {
    private int year;
    private double temp;
    private long population;
    
    //intialize methods
    public WorldTemp(){ 
    }
    public WorldTemp(int year){
        this.year = year;
    }
    public WorldTemp(int year, double temp, long population){
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
