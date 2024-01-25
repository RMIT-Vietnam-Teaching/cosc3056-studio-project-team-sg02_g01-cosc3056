package app.Objects;

public class TemperatureDataCountry {
    public int year1;
    public float avgTemp1;
    public float minTemp1;
    public float maxTemp1;
    public String name;
    public int year2;
    public float avgTemp2;
    public float minTemp2;
    public float maxTemp2;
    public float tempDiff;
    public float tempDiffPercent;

    public TemperatureDataCountry(){
    }

    public TemperatureDataCountry(int year1, float avgTemp1, float minTemp1, float maxTemp1, String name, int year2, float avgTemp2, float minTemp2, float maxTemp2){
        this.year1 = year1;
        this.avgTemp1 = avgTemp1;
        this.minTemp1 = minTemp1;
        this.maxTemp1 = maxTemp1;
        this.name = name;
        this.year2 = year2;
        this.avgTemp2 = avgTemp2;
        this.minTemp2 = minTemp2;
        this.maxTemp2 = maxTemp2;
        tempDiff = this.avgTemp2 - this.avgTemp1;
        tempDiffPercent = (this.avgTemp2 - this.avgTemp1) / this.avgTemp1 * 100;
    }
}
