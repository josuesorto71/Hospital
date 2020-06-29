package modelo;

/**
 *
 * @author Milton Josué Díaz Sorto
 */
public class Paciente {

    public Paciente() {
        
    }
    
    public Paciente(int EdadPaciente, String NombrePaciente, String Padecimiento, String TipoLlamada) {
        this.EdadPaciente = EdadPaciente;
        this.NombrePaciente = NombrePaciente;
        this.Padecimiento = Padecimiento;
        this.TipoLlamada = TipoLlamada;
    }

    public int getEdadPaciente() {
        return EdadPaciente;
    }

    public void setEdadPaciente(int EdadPaciente) {
        this.EdadPaciente = EdadPaciente;
    }

    public String getNombrePaciente() {
        return NombrePaciente;
    }

    public void setNombrePaciente(String NombrePaciente) {
        this.NombrePaciente = NombrePaciente;
    }

    public String getPadecimiento() {
        return Padecimiento;
    }

    public void setPadecimiento(String Padecimiento) {
        this.Padecimiento = Padecimiento;
    }

    public String getTipoLlamada() {
        return TipoLlamada;
    }

    public void setTipoLlamada(String TipoLlamada) {
        this.TipoLlamada = TipoLlamada;
    }
    
    private int EdadPaciente;
    private String NombrePaciente;
    private String Padecimiento;
    private String TipoLlamada;
}
