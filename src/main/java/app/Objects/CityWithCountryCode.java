package app.Objects;

public class CityWithCountryCode {
    public String name;
    public String countryCode;

    public CityWithCountryCode(){};
    public CityWithCountryCode(String name, String code){
        this.name = name;
        this.countryCode = code;
    }
}
