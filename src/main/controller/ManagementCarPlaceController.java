package main.controller;

import main.MainDispatcher;
import main.model.Car;
import main.model.Carplace;
import main.model.Payment;
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
    private PaymentService paymentService;

    public ManagementCarPlaceController() {
        carPlaceService = new CarPlaceService();
        stopService = new StopService();
        carService = new CarService();
        loginService = new LoginService();
        paymentService = new PaymentService();
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

       Map<Integer, Payment> payments = new HashMap<Integer, Payment>();

       for(Map.Entry<Integer, Stop> entry : stops.entrySet()){

           Stop stop = entry.getValue();
           Car car = null;
           //next call will take values from map if already in it
           car = carService.getCar(stop.getId_car(),false);
           if(car != null)
               cars.put(stop.getId_carplace(), car);
           Payment payment = paymentService.getPayment(stop.getId_stop());
           if (payment != null)
               payments.put(stop.getId_stop(), payment);

       }

        request.put("stops", stops);
        request.put("cars", cars);
       request.put("payments", payments);
        MainDispatcher.getInstance().callView("ManagementCarPlace", request);
    }
}