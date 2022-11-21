package web.service;

import org.springframework.stereotype.Component;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarServiceImpl implements CarService {
    private List<Car> carList = new ArrayList<>();

    {
        carList.add(new Car(1, "Rolls-Royce", 12345));
        carList.add(new Car(2, "Bentley", 900));
        carList.add(new Car(3, "Волга", 2410));
        carList.add(new Car(4, "ГАЗ", 66));
        carList.add(new Car(5, "Nissan Leaf", 111));
    }

    @Override
    public List<Car> getCars(int count) {
        return (count < 1) ? carList : carList.stream().limit(count).toList();
    }
}
