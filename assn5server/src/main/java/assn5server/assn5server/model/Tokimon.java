package assn5server.assn5server.model;

/**
 * @version : near final
 * @Author Tanzil Sarker
 * <p>
 * This is a POJO that holds all the attributes of tokimons on the server side of the application. Users must not interac with this codes.
 * @knownBugs : None discovered
 */
public class Tokimon {
    private String name;
    private double weight;
    private double height;
    private String ability;
    private int uid;
    private String color;
    private double strength;
    static private int total = 0;

    public Tokimon(String name, double weight, double height, String ability, String color, double strength) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.ability = ability;
        this.color = color;
        this.strength = strength;
        uid = total;
        total++;
    }

    public Tokimon() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public static long getTotal() {
        return total;
    }

    public static void setTotal(int total) {
        Tokimon.total = total;
    }

    @Override
    public String toString() {
        return "Tokimon{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", ability='" + ability + '\'' +
                ", uid=" + uid +
                ", color='" + color + '\'' +
                ", strength=" + strength +
                '}';
    }
}
