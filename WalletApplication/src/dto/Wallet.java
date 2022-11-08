package dto;

public class Wallet {
    private int id;
    private String name;
    private double balance;
    private String password;

    public Wallet() {
    }

    public Wallet(int id, String name, double balance, String password) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Wallet{" + "id=" + id + ", name='" + name + '\'' + ", balance=" + balance + ", password='" + password + '\'' + '}';
    }
}
