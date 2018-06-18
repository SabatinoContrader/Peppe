package main.controller;

import main.DebugSingleton;

public class GestoreEccezioni {
    private static GestoreEccezioni ourInstance = new GestoreEccezioni();

    public static GestoreEccezioni getInstance() {
        return ourInstance;
    }

    private GestoreEccezioni() {
    }

    public void gestisciEccezione(Throwable e)
    {
        e.printStackTrace();
        System.out.println("");
        System.out.println("ERRORE! OPERAZIONE NON ESEGUITA");
        System.out.println("");
    }

    public void gestisciEccezione(Throwable e, String typed)
    {
        e.printStackTrace();
        if(e.getMessage().equals("Duplicate entry '"+ typed +"' for key 'PRIMARY'")) System.out.println("Nome utente '" + typed + "' già esistente");
    }
}