package app.Objects;

public class TemperatureDataWorld {
    public int year1;
    public float landAvgTemp1;
    public float landMinTemp1;
    public float landMaxTemp1;
    public float landOceanAvgTemp1;
    public float landOceanMinTemp1;
    public float landOceanMaxTemp1;
    public int year2;
    public float landAvgTemp2;
    public float landMinTemp2;
    public float landMaxTemp2;
    public float landOceanAvgTemp2;
    public float landOceanMinTemp2;
    public float landOceanMaxTemp2;
    public float landTempDiff;
    public float landTempDiffPercent;
    public float landOceanTempDiff;
    public float landOceanTempDiffPercent;


    public TemperatureDataWorld(){
    }

    public TemperatureDataWorld(int year1, float landAvgTemp1, float landMinTemp1, float landMaxTemp1, float landOceanAvgTemp1, float landOceanMinTemp1, float landOceanMaxTemp1, int year2, float landAvgTemp2, float landMinTemp2, float landMaxTemp2,  float landOceanAvgTemp2, float landOceanMinTemp2, float landOceanMaxTemp2){
        this.year1 = year1;
        this.landAvgTemp1 = landAvgTemp1;
        this.landMinTemp1 = landMinTemp1;
        this.landMaxTemp1 = landMaxTemp1;
        this.landOceanAvgTemp1 = landOceanAvgTemp1;
        this.landOceanMinTemp1 = landOceanMinTemp1;
        this.landOceanMaxTemp1 = landOceanMaxTemp1;
        this.year2 = year2;
        this.landAvgTemp2 = landAvgTemp2;
        this.landMinTemp2 = landMinTemp2;
        this.landMaxTemp2 = landMaxTemp2;
        this.landOceanAvgTemp2 = landOceanAvgTemp2;
        this.landOceanMinTemp2 = landOceanMinTemp2;
        this.landOceanMaxTemp2 = landOceanMaxTemp2;
        landTempDiff = this.landAvgTemp2 - this.landAvgTemp1;
        landTempDiffPercent = (this.landAvgTemp2 - this.landAvgTemp1) / this.landAvgTemp1 * 100;
        landOceanTempDiff = this.landOceanAvgTemp2 - this.landOceanAvgTemp1;
        landOceanTempDiffPercent = (this.landOceanAvgTemp2 - this.landOceanAvgTemp1) / this.landOceanAvgTemp1 * 100;
    }

}
