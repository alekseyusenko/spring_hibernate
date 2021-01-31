package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    private int series;

    @Column(name = "model")
    private String model;

    @OneToOne(optional = false, mappedBy = "car", cascade = CascadeType.ALL)
    private User user;


    public Car() {}

    public Car(int series, String model) {
        this.series = series;
        this.model = model;
    }

    public int getSeries() { return series; }

    public void setSeries(int series) { this.series = series; }

    public String getModel() { return model; }

    public void setModel(String model) { this.model = model; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    @Override
    public String toString() {
        return "series = " + series +
                ", model = '" + model + '\'' +
                ", user = '" + user.getFirstName() + " " + user.getLastName() + "'";
    }
}
