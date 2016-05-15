package smart_unit;

/**
 * Created by Frida on 15-May-16.
 */
public abstract class ASmartUnit {
    private String id = new String();
    private String name = new String();
    private boolean status = false;

    public ASmartUnit(){

    }

    public ASmartUnit(String id, String name){
        this.setId(id);
        this.setName(name);
        this.setStatus(false);
    }

    public String getStringStatus(){
        return status? "ON" : "OFF";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
