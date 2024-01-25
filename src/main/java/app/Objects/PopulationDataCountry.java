package app.Objects;

public class PopulationDataCountry {
    public int year1;
    public int year2;
    public String name;
    public int populationNumber1;
    public int populationNumber2;
    public int populationDiff;
    public int populationDiffPercent;


    public PopulationDataCountry(){
    }

    public PopulationDataCountry(int year1, int populationNumber1, int year2, int populationNumber2, String name){
        this.year1 = year1;
        this.populationNumber1 = populationNumber1;
        this.year2 = year2;
        this.populationNumber2 = populationNumber2;
        this.name = name;
        populationDiff = populationNumber2 - populationNumber1;
        populationDiffPercent = (populationNumber2 - populationNumber1) / populationNumber1 * 100;
    }




}
