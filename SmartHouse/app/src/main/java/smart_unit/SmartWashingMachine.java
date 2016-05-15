package smart_unit;

/**
 * Created by Frida on 15-May-16.
 */
public class SmartWashingMachine extends  ASmartUnit {
    private int temperature = 30;
    private int rotations = 700;
    private String program = "silk";

    public SmartWashingMachine(String id, String name){
        super(id, name);
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getRotations() {
        return rotations;
    }

    public void setRotations(int rotations) {
        this.rotations = rotations;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}
