package app.Objects;

public class TemperaturePopDataWorld2A {
    public int StartYear;
    public Double StartTempLand;
    public Double StartTempLandOcean;
    public long StartPop;
    public int EndYear;
    public Double EndTempLand;
    public Double EndTempLandOcean;
    public long EndPop;
    public Double tempDifferenceLand;
    public Double tempDifferenceLandPercent;
    public Double tempDifferenceLandOcean;
    public Double tempDifferenceLandOceanPercent;
    public long PopDifference;
    public Double PopDifferencePercent;


    public TemperaturePopDataWorld2A(){
    }


    public TemperaturePopDataWorld2A(int StartYear, Double StartTempLand, Double StartTempLandOcean, long StartPop, int EndYear, Double EndTempLand, Double EndTempLandOcean, long EndPop, Double tempDifferenceLand, Double tempDifferenceLandOcean, long PopDifference){
        this.StartYear = StartYear;
        this.StartTempLand = StartTempLand;
        this.StartTempLandOcean = StartTempLandOcean;
        this.StartPop = StartPop;
        this.EndYear = EndYear;
        this.EndTempLand = EndTempLand;
        this.EndTempLandOcean = EndTempLandOcean;
        this.EndPop = EndPop;
        this.tempDifferenceLand = tempDifferenceLand;
        this.tempDifferenceLandOcean = tempDifferenceLandOcean;
        this.PopDifference = PopDifference;
        this.tempDifferenceLandPercent = this.tempDifferenceLand / this.StartTempLand * 100;
        this.tempDifferenceLandOceanPercent = this.tempDifferenceLandOcean / this.StartTempLandOcean * 100;
        this.PopDifferencePercent = (double) this.PopDifference / this.StartPop * 100;
    }

}
