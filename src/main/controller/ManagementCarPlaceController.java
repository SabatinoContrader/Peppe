package main.controller;

import main.MainDispatcher;
import main.model.Car;
import main.model.Carplace;
import main.model.Stop;
import main.service.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagementCarPlaceController implements Controller {

    private CarPlaceService carPlaceService;
    private StopService stopService;
    private CarService carService;
    private LoginService loginService;

    public ManagementCarPlaceController() {
        carPlaceService = new CarPlaceService();
        stopService = new StopService();
        carService = new CarService();
        loginService = new LoginService();
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

       //pre-call all cars to save in map. Do just one database call.
       //carService.getAllCarModel(loginService.getLoggedUser().getUsername(),false);

       for(Map.Entry<Integer, Stop> entry : stops.entrySet()){

           Stop stop = entry.getValue();
           Car car = null;
           //next call will take values from map if already in it
           car = carService.getCar(stop.getId_car(),false);
           if(car != null)
               cars.put(stop.getId_carplace(), car);
       }

        request.put("stops", stops);
        request.put("cars", cars);
        MainDispatcher.getInstance().callView("ManagementCarPlace", request);
    }
}