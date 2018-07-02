package com.pCarpet.services;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.dao.StopRepository;
import com.pCarpet.dto.ManagementCarPlaceDTO;
import com.pCarpet.dto.ManagementExtensionStopDTO;
import com.pCarpet.model.Car;
import com.pCarpet.model.Payment;
import com.pCarpet.model.Slot;
import com.pCarpet.model.Stop;
import com.pCarpet.model.User;

@Service
public class StopService {

    private StopRepository stopRepository;
    private CarService carService;
    private SlotService slotService;
    private UserService userService;
    private PaymentService paymentService;
    
    @Autowired 
    public  StopService(StopRepository stopRepository, CarService carService, SlotService slotService, UserService userService,PaymentService paymentService) {
        this.stopRepository = stopRepository;
        this.carService = carService;
        this.slotService = slotService;
        this.userService = userService;
        this.paymentService = paymentService;
    }

    public List<ManagementExtensionStopDTO> getAllExtensionStop(User user)
    {
        List<ManagementExtensionStopDTO> managementExtensionStopDTOs = new ArrayList<ManagementExtensionStopDTO>();
        
        List<Car> usercars = carService.getAllCar(user);
        for (Car car : usercars) {
            Stop stop = this.getStop(car);
            if (stop != null) {
                Slot slot = slotService.getSlot(stop.getSlot().getId());
                ManagementExtensionStopDTO managementExtensionStopDTO = new ManagementExtensionStopDTO(stop, slot, car);
                managementExtensionStopDTOs.add(managementExtensionStopDTO);
            }
        }
        return managementExtensionStopDTOs;
    }
    
    public void extensionStop(ManagementExtensionStopDTO managementExtensionStopDTO)
    {
        int id_stop = managementExtensionStopDTO.getId_stop();
        String finish = managementExtensionStopDTO.getFinish();
        this.stopRepository.extensionStop(id_stop,finish);
    }
    
    public List<ManagementCarPlaceDTO> getAllStopDTOByCurrentUser() {
        List<ManagementCarPlaceDTO> managementCarPlaceDTOs = new ArrayList<ManagementCarPlaceDTO>();
      
        updateStopByUser(); 
        
    	User user = userService.getLoggedUser();
        List<Slot> slots = (List<Slot>) slotService.getAllSlotByUser(user);
        
        //Update expire status of user slots here;      
           
        for(Slot slot : slots )
        {
        	List<Stop> stops = getStops(slot);
        	List<Payment> payments = new ArrayList<>();
            for(Stop stop : stops )
            {
            	List<Payment> stopPayments = paymentService.getPaymentForStop(stop);
                for(Payment payment : stopPayments )
                {
                	payments.add(payment);
                }
            }
        	ManagementCarPlaceDTO managementCarPlaceDTO = new ManagementCarPlaceDTO(slot, payments, stops);
        	managementCarPlaceDTOs.add(managementCarPlaceDTO);
        }
        
        return managementCarPlaceDTOs;                          
    }
    
    public void updateStopByUser() 
    {
    	User user = userService.getLoggedUser();
        List<Slot> slots = (List<Slot>) slotService.getAllSlotByUser(user);
        for(Slot slot : slots )
        {
        	List<Stop> stops = getStops(slot);
            for(Stop stop : stops )
            {
            	String finish = stop.getFinish();
            	//LocalDateTime now = LocalDateTime.now();
            	Timestamp now = new Timestamp(System.currentTimeMillis());
            	
            	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
				try {
					Date parsedDate = dateFormat.parse(finish);
					Date parsedDateNow = dateFormat.parse(now.toString());
					
	    			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
	    			Timestamp timestampNow = new java.sql.Timestamp(parsedDateNow.getTime());
	    			
	    			LocalDateTime date = timestamp.toLocalDateTime();
	    			LocalDateTime dateNow = timestampNow.toLocalDateTime();
	            	
	    			System.out.println("date: " + date);
	    			System.out.println("dateNow: " + dateNow);
	    			
	            	//LocalDateTime myfinish = LocalDateTime.parse(finish);
	            	//LocalDateTime myNow = LocalDateTime.parse(finish);
	            
	                boolean isBefore = date.isBefore(dateNow);
	                
	                this.stopRepository.updateExpired(stop.getId_stop(), isBefore);
	            	
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


            	

            }
        }
    }
    
    public List<Stop> getStops(Slot slot) {
        return this.stopRepository.findBySlot(slot);
    }
    
    public void insertStop(Stop stop)
    {
    	this.stopRepository.save(stop);
    }
    
    public Stop getStop(Car car) {
    	return this.stopRepository.findByCar(car);
    }
}
