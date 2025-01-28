import java.util.HashSet;
import java.util.Scanner;

public class Race {
    private static final int timeInHours = 24;
    private final int maxCarsCounter;
    HashSet<Car> cars;

    Leader leader;

    public Race(int maxCarsCounter) {
        cars = null;
        leader = null;
        this.maxCarsCounter = maxCarsCounter;
    }

    public void prepareCars() {
        cars = new HashSet<>();
        leader = null;
        for(int i = 0; i < maxCarsCounter;) {
            Car car = createCar();
            if(!cars.add(car)) {
                System.out.println("Автомобиль '" + car.name + "' уже заявлен. Переменуйте автомобиль.");
                continue;
            } else
                System.out.println("Автомобиль '" + car.name + "' со скоростью " + car.speed + " км/ч добавлен.");
            ++i;
        }
    }
    private Car createCar() {
        Scanner scanner = new Scanner(System.in);
        Car car = new Car();
        while (true) {
            try {
                if (car.name == null) {
                    System.out.println("Введите название автомобиля");
                    car.name = scanner.next();
                }
                if (car.speed == null) {
                    System.out.println("Введите скорость автомобиля " + car.name);
                    if (!car.setSpeed(scanner.nextInt())) {
                        System.out.println("Скорость введена неверно. Введите значение от " + Car.MIN_SPEED + " до " + Car.MAX_SPEED);
                        continue;
                    }
                }

                break;
            } catch (java.util.InputMismatchException e) {
                String rootCause = scanner.next();
                System.out.println("Что-то пошло не так из-за ввода '" + rootCause + "'");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Что-то пошло не так");
            }

            System.out.println("Давайте повторим ввод");
        }

        return car;
    }

    public void performRace() {
        leader = null;
    }

    public void findLeader() throws Exception {
        if(cars == null)
            throw new Exception("Нет машин, которые участвуют в гонке. Найти лидера не получится.");

        for(Car car: cars) {
            final int carDistance = car.speed * timeInHours;
            if(leader == null) {
                leader = new Leader(car.name, carDistance);
            }
            else {
                if (leader.distance < carDistance) {
                    leader = new Leader(car.name, carDistance);
                }
            }
        }
    }
    public Leader getLeader() throws Exception {
        if(leader == null)
            throw new Exception("Лидер гонки не определен. Возможно гонка не была завершена.");
        return leader;
    }
}
