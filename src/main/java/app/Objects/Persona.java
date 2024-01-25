package app.Objects;

public class Persona {
    public String name;
    public int age;
    public String location;
    public String background;
    public String quotes;
    public String needs;
    public String goals;
    public String skills;
    public String img_path;

    public Persona(){
    }
    public Persona(String name, int age, String location, String background, String quotes, String needs, String goals, String skills, String img_path){
        this.name = name;
        this.age = age;
        this.location = location;
        this.background = background;
        this.quotes = quotes;
        this.needs = needs;
        this.goals = goals;
        this.skills = skills;
        this.img_path = img_path;
    }

    // Getters
    public String getName () {
        return name;
    }

    public int getAge () {
        return age;
    }

    public String getLocation () {
        return location;
    }

    public String getBackround () {
        return background;
    }

    public String getQuote(){
        return quotes;
    }

    public String getNeed(){
        return needs;
    }

    public String getGoal(){
        return goals;
    }

    public String getSkill(){
        return skills;
    }

    public String getImg(){
        return img_path;
    }
}
