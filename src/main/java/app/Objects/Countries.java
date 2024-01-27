package app.Objects;

public class Countries {
    String id;
    String name;

    public Countries(String id, String name){
        this.id = id;
        this.name = name;
    }
    public Countries (){

    }
    public void setName(String name){
        this.name = name;
    }
    public void setID(String id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public String getID(){
        return id;
    }
}
