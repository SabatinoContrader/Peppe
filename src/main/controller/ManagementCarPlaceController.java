package main.controller;

import main.MainDispatcher;
import main.model.Car;
import main.model.Carplace;
import main.model.Stop;
import main.service.CarPlaceService;
import main.service.CarService;
import main.service.StopService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagementCarPlaceController implements Controller {

    private CarPlaceService carPlaceService;
    private StopService stopService;
    private CarService carService;

    public ManagementCarPlaceController() {
        carPlaceService = new CarPlaceService();
        stopService = new StopService();
        carService = new CarService();
    }

   @Override
    public void doControl(Request request) {
        List<Carplace> carplace = carPlaceService.getAllCarPlace();
        request = new Request();
        request.put("carplace", carplace);

        Map<Integer, Stop> stops = new HashMap<Integer, Stop>();
        Map<Integer, Car> cars = new HashMap<Integer, Car>();

       for(Carplace place: carplace){
           Stop stop = stopService.getStop(place.getId_carplace());

           if(stop != null)
               stops.put(stop.getId_carplace(), stop);
       }

       for(Map.Entry<Integer, Stop> entry : stops.entrySet()){

           Stop stop = entry.getValue();
           int id_car = stop.getId_car();
           Car car = null;
           car = carService.getCar(stop.getId_car());
           if(car != null)
               cars.put(car.getId_car(), car);
       }
       /*
        for(Carplace place: carplace){
           Stop stop = stopService.getStop(place.getId_carplace());
           Integer id_car = new Integer(stop.getId_car());
            Car car = null;
            if(id_car != null)
                car = carService.getCar(stop.getId_car());
            if(stop != null)
                stops.put(stop.getId_carplace(), stop);
            if(car != null)
                cars.put(car.getId_car(), car);
        }*/
        request.put("stops", stops);
        request.put("cars", cars);
        MainDispatcher.getInstance().callView("ManagementCarPlace", request);
    }
}