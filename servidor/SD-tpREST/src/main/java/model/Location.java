package model;

public class Location  {


    Integer posX;
    Integer posY;
    String date;
    Transport transporte;
    public Location(){}

    public Location( Transport transporte ,Integer posX , Integer posY)
    {
        this.transporte = transporte;
        this.posX = posX;
        this.posY = posY;
    }
    public Integer getPosX() {
        return posX;
    }
    public void setPosX(Integer posX) {
        this.posX = posX;
    }
    public Integer getPosY() {
        return posY;
    }
    public void setPosY(Integer posY) {
        this.posY = posY;
    }
    public Transport getTransporte() { return transporte; }
    public void setTransporte(Transport transporte) { this.transporte = transporte; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
