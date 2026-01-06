package DTO;

public class AutorDTO {
    private int IdAutor;
    private String NombreAutor;
    private String EmailAutor;

    public int getIDAutor() {
        return IdAutor;
    }
    public void setIDAutor(int IdAutor) {
        IdAutor = IdAutor;
    }
    public String getNombreAutor() {
        return NombreAutor;
    }
    public void setNombreAutor(String NombreAutor) {
        NombreAutor = NombreAutor;
    }
    public String getEmailAutor() {
        return EmailAutor;
    }
    public void setEmailAutor(String EmailAutor) {
        EmailAutor = EmailAutor;
    }


@Override
public String toString(){
     return "AutorDTO [ID= " + IdAutor + ", Nombre = "+ NombreAutor + ", Email = "+ EmailAutor + "]";

}
}
