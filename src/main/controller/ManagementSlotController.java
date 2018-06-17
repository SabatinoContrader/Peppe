package main.controller;

import main.MainDispatcher;
import main.model.Slot;
import main.service.SlotService;

import java.util.List;

public class ManagementSlotController implements Controller {

    private SlotService slotService;

    public ManagementSlotController() {
        slotService = new SlotService();
    }

    public void doControl(Request request) {
        List<Slot> slots = slotService.getAllSlot();
        request = new Request();
        request.put("slots", slots);
        MainDispatcher.getInstance().callView("ManagementSlot", request);

    }
}
