import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) Menu.showMenu();
    }
}

class Vehicle {
    static List<Vehicle> vehicleList = new ArrayList<>();
    protected String numberPlate;

    protected Vehicle(String numberPlate) {
        this.numberPlate = numberPlate;
        vehicleList.add(this);
    }

    protected String getInformationString() {
        return "Plate number: " + this.numberPlate;
    }

    public static void showAllVehicle() {
        for (int counter = 0; counter < vehicleList.size(); counter++) {
            Vehicle currentVehicle = vehicleList.get(counter);
            System.out.println(counter + ". " + currentVehicle.getInformationString());
        }
    }

    public static void removeById(int id) {
        if(vehicleList.size() >= 0 && id < vehicleList.size()) vehicleList.remove(id);
        else System.out.println("Not found this id = " + id);
    }

    public boolean equals(Vehicle secondVehicle) {
        System.out.println("Equals are " + this.numberPlate.equals(secondVehicle.numberPlate));
        return this.numberPlate.equals(secondVehicle.numberPlate);
    }
}

class Truck extends Vehicle {
    public Truck(String numberPlate) {
        super(numberPlate);
    }

    @Override
    protected String getInformationString() {
        return "Type: Truck, " + super.getInformationString();
    }
}

class Bus extends Vehicle {
    public Bus(String numberPlate) {
        super(numberPlate);
    }

    @Override
    protected String getInformationString() {
        return "Type: Bus, " + super.getInformationString();
    }
}

class Car extends Vehicle {
    public Car(String numberPlate) {
        super(numberPlate);
    }

    @Override
    protected String getInformationString() {
        return "Type: Car, " + super.getInformationString();
    }
}

class Menu {
    static Scanner in = new Scanner(System.in);

    static void menuAction(int command) {
        switch (command) {
            case 1: showSubMenu(); break;
            case 2: Vehicle.showAllVehicle(); break;
            case 3: Vehicle.removeById(in.nextInt()); break;
            case 4: Vehicle.vehicleList.get(in.nextInt()).equals(
                    Vehicle.vehicleList.get(in.nextInt())
            ); break;
            case 5: System.exit(0);
            default: throw new Error("Unexpected selected option");
        }
    }

    static String inputPlate() {
        System.out.println("Enter plate:");
        return in.next();
    }

    static void createAction(int command) {
        switch (command) {
            case 1: new Car(inputPlate()); break;
            case 2: new Truck(inputPlate()); break;
            case 3: new Bus(inputPlate()); break;
            case 4: break;
            default: throw new Error("Unexpected selected option");
        }
    }

    static void showMenu() {
        System.out.print("""
                Select one option:
                1. Register new vehicle
                2. Show vehicles list
                3. Remove vehicle by id
                4. Equal vehicle by ids
                5. Exit\n""");
        menuAction(waitForSelect(5));
    }

    static void showSubMenu() {
        System.out.print("""
                Select one option:
                1. Register new car
                2. Register new truck
                3. Register new bus
                4. Back\n""");
        createAction(waitForSelect(4));
    }

    static int waitForSelect(int maxOptionNum) {
        int temp = 0;

        while (temp == 0) {
            if(!in.hasNextInt()) {
                System.out.println("Use only integer");
                in.next();
                continue;
            };

            temp = in.nextInt();
            if(temp > maxOptionNum || temp < 1) {
                System.out.println("Use value from 1 to " + maxOptionNum);
                temp = 0;
            };
        }

        return temp;
    }
}
