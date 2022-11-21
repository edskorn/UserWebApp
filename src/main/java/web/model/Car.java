package web.model;

public class Car {
    private int carId;
    private String carModel;
    private int carSeries;

    public Car() {
    }

    public Car(int carId, String carModel, int carSeries) {
        this.carId = carId;
        this.carModel = carModel;
        this.carSeries = carSeries;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getCarSeries() {
        return carSeries;
    }

    public void setCarSeries(int carSeries) {
        this.carSeries = carSeries;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", carModel='" + carModel + '\'' +
                ", carSeries=" + carSeries +
                '}';
    }
}
