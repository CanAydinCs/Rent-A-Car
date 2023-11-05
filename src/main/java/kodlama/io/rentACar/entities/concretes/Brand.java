package kodlama.io.rentACar.entities.concretes;

public class Brand {
    private int id;
    private String name;

    public Brand(){
    }
    public Brand(int _id, String _name){
        id = _id;
        name = _name;
    }

    public String getName(){
        return name;
    }
    public void setName(String _name){
        name = _name;
    }

    public int getId(){
        return id;
    }
    public void setId(int _id){
        id = _id;
    }
}
