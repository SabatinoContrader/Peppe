package main.service;

import main.dao.SlotDAO;
import main.model.Slot;

import java.util.List;

public class SlotService {

    private SlotDAO slotDAO;

    public SlotService() {
        this.slotDAO = new SlotDAO();
    }

    public List<Slot> getAllSlot () {
        return this.slotDAO.getAllSlot();
    }
}
