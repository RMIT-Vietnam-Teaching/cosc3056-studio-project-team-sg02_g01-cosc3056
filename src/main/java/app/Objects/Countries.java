package app.Objects;

public class Countries {
    String code;
    String name;

    public Countries(String code, String name){
        this.code = code;
        this.name = name;
    }
    public Countries (){

    }
    public void setName(String name){
        this.name = name;
    }
    public void setID(String code){
        this.code = code;
    }

    public String getName(){
        return name;
    }
    public String getCode(){
        return code;
    }
}
