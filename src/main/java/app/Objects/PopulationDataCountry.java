package app.Objects;

public class PopulationDataCountry {
    public String countryName;
    public int StartYear;
    public Double StartTemp;
    public int StartPop;
    public int EndYear;
    public Double EndTemp;
    public int EndPop;
    public Double TempDifference;
    public int PopDifference;


    public PopulationDataCountry(){
    }

    public PopulationDataCountry(String countryName, int StartYear, Double StartYearTemp, int StartYearPop, int EndYear, Double EndYearTemp, int EndYearPop, Double TempDifference, int PopDifference){
        this.countryName = countryName;
        this.StartYear = StartYear;
        this.StartTemp = StartYearTemp;
        this.StartPop = StartYearPop;
        this.EndYear = EndYear;
        this.EndTemp = EndYearTemp;
        this.EndPop = EndYearPop;
        this.TempDifference = TempDifference;
        this.PopDifference = PopDifference;
    }




}
