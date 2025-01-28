public class Car {

    public static final int MAX_SPEED = 250;
    public static final int MIN_SPEED = 1;
    public String name;
    public Integer speed;

    public Car() {
        name = null;
        speed = null;
    }

    public boolean setSpeed(int speed) {
        if(speed >= MIN_SPEED && speed <= MAX_SPEED)
        {
            this.speed = speed;
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return name.equals(car.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
