package ThreadDesignPattern.Chapter17;

public final class Person {
    private final String name;
    private final String address;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person "+name+" "+ address;
    }

    public String getAdress() {
        return address;
    }

    public Person(String name, String adress) {
        this.name = name;
        this.address = adress;
    }
}
