package com.pCarpet.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pCarpet.dao.SlotRepository;
import com.pCarpet.model.Slot;

@Service
public class SlotService {

    private SlotRepository slotRepository;

    @Autowired
    public SlotService(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }
    
    
    public List<Slot> getAllSlot () {
        return slotRepository.findAll();
    }

    
    public Slot getSlot(int id_slot) {
        return slotRepository.findById(id_slot);
    }
    
//    public List<Slot> getNearSlot(double lat, double lng) {
//        return getNearSlot(lat, lng);
//    }
}
