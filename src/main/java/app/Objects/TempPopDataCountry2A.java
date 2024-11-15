package app.Objects;

public class TempPopDataCountry2A {
    public String id;
    public String countryName;
    public int StartYear;
    public Double StartTemp;
    public int StartPop;
    public int EndYear;
    public Double EndTemp;
    public int EndPop;
    public Double TempDifference;
    public int PopDifference;
    public Double TempDifferencePercent;
    public Double PopDifferencePercent;


    public TempPopDataCountry2A(){
    }

    public TempPopDataCountry2A(String id, String countryName, int StartYear, Double StartYearTemp, int StartYearPop, int EndYear, Double EndYearTemp, int EndYearPop, Double TempDifference, int PopDifference){
        this.id = id;
        this.countryName = countryName;
        this.StartYear = StartYear;
        this.StartTemp = StartYearTemp;
        this.StartPop = StartYearPop;
        this.EndYear = EndYear;
        this.EndTemp = EndYearTemp;
        this.EndPop = EndYearPop;
        this.TempDifference = TempDifference;
        this.PopDifference = PopDifference;
        this.TempDifferencePercent = this.TempDifference * 100.0 / this.StartTemp;
        this.PopDifferencePercent = this.PopDifference * 100.0 / this.StartPop;
    }
}
