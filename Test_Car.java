/**
 * The program simply simulate a car’s fuel gauge and odometer
 * @author Mohammad Seeam
 */

/* FuelGauge class contains all necessary methods for fuel increment and decrement which collaborate with mileage later */
class FuelGauge {
    //To store number of fuel
    private int fuelLevel;
    //Maximum capacity of fuel
    private final int MAX_FUEL_LEVEL = 15;

    /* Constructor which makes fuel level zero at the very first stage */
    public FuelGauge() {
        fuelLevel = 0;
    }

    /* incrementFuel() increase fuel if it is less than maximum capacity */
    public void incrementFuel() {
        if (fuelLevel < MAX_FUEL_LEVEL) {
            fuelLevel++;
        }
    }

    /* We need decrementFuel() to decrease fuel level after running the car for a certain mileage */
    public void decrementFuel() {
        if (fuelLevel > 0) {
            fuelLevel--;
        }
    }

    /* getFuelLevel() is a simply getter function to get fuel level */
    public int getFuelLevel() {
        return fuelLevel;
    }
}

/* Odometer class contains all necessary methods to work with mileage and decrease fuel level after running a certain mileages */

class Odometer {
    //To store milage value
    private int mileage;
    //MAX_MILEAGE is the maximum number of mileage. After 999999, MAX_MILEAGE will be 0
    private final int MAX_MILEAGE = 999999;
    //car burns 1 gallon of gas for 24 miles
    private final int MILES_PER_GALLON = 24;
    //fuelGauge object wo collaborate with mileage and gas level
    private FuelGauge fuelGauge;

    /* Constructor that sets all values to initial stage */
    public Odometer(FuelGauge fuelGauge) {
        mileage = 0;
        this.fuelGauge = fuelGauge;
    }

    /* incrementMileage() increases mileage value. If mileage > 999999 it sets mileage to 0.
    * After each 24 mileages, gas level decrease by 1 */

    public void incrementMileage() {
        mileage++;
        if (mileage > MAX_MILEAGE) {
            mileage = 0;
        }
        if (mileage % MILES_PER_GALLON == 0) {
            fuelGauge.decrementFuel();
        }
    }

    /* getMileage() is a getter function to get current mileages */
    public int getMileage() {
        return mileage;
    }

    /* getFuelLevel() is a getter function to get current fuel level */
    public int getFuelLevel() {
        return fuelGauge.getFuelLevel();
    }
}

/* Test_Car class contains all necessary methods to use and test FuelGauge and Odometer class */
public class Test_Car {
    /* Below is main function */
    public static void main(String[] args) {
        //Made fuelGauge() object to use FuelGauge class.
        FuelGauge fuelGauge = new FuelGauge();
        //Made odometer() object to use Odometer class.
        Odometer odometer = new Odometer(fuelGauge);
        // fill up the car with fuel.
        // We dealt that the car can not take more than 15 gallons of fuel in incrementFuel() function at very above.
        while (fuelGauge.getFuelLevel() < 15) {
            fuelGauge.incrementFuel();
        }
        // run the car until it runs out of fuel
        // fuelGauge.getFuelLevel() will print until fuel is 0 and car stops !
        while (fuelGauge.getFuelLevel() > 0) {
            //Once mileage increases, fuel decreases
            odometer.incrementMileage();
            if(odometer.getMileage()%24==0){
                System.out.println("Mileage: " + odometer.getMileage() + ", Fuel level: " + fuelGauge.getFuelLevel());
            }
        }   
        System.out.println("The car has run out of fuel.");
    }
}


// Output os given below:

/*
Mileage: 24, Fuel level: 14
Mileage: 48, Fuel level: 13
Mileage: 72, Fuel level: 12
Mileage: 96, Fuel level: 11
Mileage: 120, Fuel level: 10
Mileage: 144, Fuel level: 9
Mileage: 168, Fuel level: 8
Mileage: 192, Fuel level: 7
Mileage: 216, Fuel level: 6
Mileage: 240, Fuel level: 5
Mileage: 264, Fuel level: 4
Mileage: 288, Fuel level: 3
Mileage: 312, Fuel level: 2
Mileage: 336, Fuel level: 1
Mileage: 360, Fuel level: 0
The car has run out of fuel.
*/
