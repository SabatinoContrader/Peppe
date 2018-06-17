package main.view;

import main.MainDispatcher;
import main.controller.Request;
import main.model.Slot;

import java.util.List;
import java.util.Scanner;

public class ManagementSlotView implements View {
    private int choice;
    private List<Slot> slots;


    @Override
    public void showResults(Request request) {
        this.slots = (List<Slot>) request.get("slots");

        System.out.println("----- SLOT -----");
        System.out.println("");

        if (!this.slots.isEmpty()) {
            System.out.format("+-----+------------------------------------+------------------+%n");
            System.out.format("|     | INDIRIZZO                          | TARIFFA ORARIA   |%n");
            System.out.format("+-----+------------------------------------+------------------+%n");
            String leftAlignFormat = "| %-3d | %-34s | %-16s |%n";
            int i = 1;
            for (Slot slot : this.slots) {
                String price = slot.getPrice() + "€";
                System.out.format(leftAlignFormat, i, slot.getAddress(), price);
                System.out.format("+-----+------------------------------------+------------------+%n");
                i++;
            }
        } else {
            System.out.println("Non ci sono slot, premere un tasto per tornare indietro");
            getInput();
        }
    }

    @Override
    public void showOptions() {
        if (!this.slots.isEmpty()) {
            System.out.println("SCEGLI SLOT");
            choice = Integer.parseInt(getInput());
        }
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    @Override
    public void submit() {
        if (!this.slots.isEmpty()) {
            Request request = new Request();
            request.put("id_slot", slots.get(choice - 1).getId_slot());
            MainDispatcher.getInstance().callAction("ManagementCarPlace", "doControl", request);
        } else MainDispatcher.getInstance().callAction("Home", "doControl", null);
    }


}
