package frgp.utn.edu.com.parking;

public class Parking {
    public Parking(int idParking, String patente, String tiempo)
    {
        this.IdParking = idParking;
        this.Patente = patente;
        this.Tiempo = tiempo;
    }

    private int IdParking;
    private String Patente;
    private String Tiempo;

    public int getIdParking() {
        return IdParking;
    }

    public void setIdParking(int idParking) {
        IdParking = idParking;
    }

    public String getPatente() {
        return Patente;
    }

    public void setPatente(String patente) {
        Patente = patente;
    }

    public String getTiempo() {
        return Tiempo;
    }

    public void setTiempo(String tiempo) {
        Tiempo = tiempo;
    }
}
