package app.Objects;

public class StateWithCountryCode {
    public String name;
    public String countryCode;

    public StateWithCountryCode(){};
    public StateWithCountryCode(String name, String code){
        this.name = name;
        this.countryCode = code;
    }
}
