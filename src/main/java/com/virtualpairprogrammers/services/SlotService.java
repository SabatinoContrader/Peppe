package com.virtualpairprogrammers.services;

import com.virtualpairprogrammers.dao.SlotDAO;
import com.virtualpairprogrammers.domain.Slot;

import java.util.List;

public class SlotService {

    private SlotDAO slotDAO;

    public SlotService() {
        this.slotDAO = new SlotDAO();
    }

    public List<Slot> getAllSlot () {
        return this.slotDAO.getAllSlot();
    }

    public Slot getSlot(int id_slot) {
        return this.slotDAO.getSlot(id_slot);
    }
    
    public List<Slot> getNearSlot(double lat, double lng) {
        return this.slotDAO.getNearSlot(lat, lng);
    }
}
