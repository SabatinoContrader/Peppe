package main.controller;

public class GestoreEccezioni {
    private static GestoreEccezioni ourInstance = new GestoreEccezioni();

    private boolean run_on_debug_mode;

    public static GestoreEccezioni getInstance() {
        return ourInstance;
    }

    private GestoreEccezioni() {
        run_on_debug_mode = false;
    }

    public void gestisciEccezione(Throwable e)
    {
        if(run_on_debug_mode) e.printStackTrace();
    }

    public void gestisciEccezione(Throwable e, String typed)
    {
        //if(run_on_debug_mode) e.printStackTrace();

        if(e.getMessage().equals("Duplicate entry '"+ typed +"' for key 'PRIMARY'")) System.out.println("Nome utente " + typed + " già esistente");
    }
}