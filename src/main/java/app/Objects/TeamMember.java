package app.Objects;

public class TeamMember {
    public String id;
    public String name;
    
    public TeamMember(){
    }

    public TeamMember(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // getter
    public String getName() {
        return name;
    }

    public String getID(){
        return id;
    }

    // Setter
    public void setName(String name){
        this.name = name;
    }

    public void setID(String id) {
        this.id = id;
    }

}
