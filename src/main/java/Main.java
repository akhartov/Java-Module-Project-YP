public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Чтобы начать гонку нужно заявить три автомобиля");
            Race race = new Race(3);
            race.prepareCars();
            race.performRace();
            race.findLeader();
            System.out.println("Самая быстрая машина: " + race.getLeader().name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}