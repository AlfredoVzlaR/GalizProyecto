/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;
import java.util.Objects;
import org.bson.types.ObjectId;
/**
 *
 * @author Alfredo Valenzuela
 */
public class Expediente {
    private ObjectId idExpediente;
    private String cliente;
    private String antecedentesPatologicos;
    private String diagnosticoPiel;
    private String cosmeticosUso;
    private String antecedentesPersonales;
    private String telefonoCliente;
    

    public Expediente() {
    }

    public Expediente(ObjectId idExpediente, String cliente, String antecedentesPatologicos, String diagnosticoPiel, String cosmeticosUso, String antecedentesPersonales) {
        this.idExpediente = idExpediente;
        this.cliente = cliente;
        this.antecedentesPatologicos = antecedentesPatologicos;
        this.diagnosticoPiel = diagnosticoPiel;
        this.cosmeticosUso = cosmeticosUso;
        this.antecedentesPersonales = antecedentesPersonales;
       
    }

    

    public ObjectId getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(ObjectId idExpediente) {
        this.idExpediente = idExpediente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getAntecedentesPatologicos() {
        return antecedentesPatologicos;
    }

    public void setAntecedentesPatologicos(String antecedentesPatologicos) {
        this.antecedentesPatologicos = antecedentesPatologicos;
    }

    public String getDiagnosticoPiel() {
        return diagnosticoPiel;
    }

    public void setDiagnosticoPiel(String diagnosticoPiel) {
        this.diagnosticoPiel = diagnosticoPiel;
    }

    public String getCosmeticosUso() {
        return cosmeticosUso;
    }

    public void setCosmeticosUso(String cosmeticosUso) {
        this.cosmeticosUso = cosmeticosUso;
    }

    public String getAntecedentesPersonales() {
        return antecedentesPersonales;
    }

    public void setAntecedentesPersonales(String antecedentesPersonales) {
        this.antecedentesPersonales = antecedentesPersonales;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.idExpediente);
        hash = 47 * hash + Objects.hashCode(this.cliente);
        hash = 47 * hash + Objects.hashCode(this.antecedentesPatologicos);
        hash = 47 * hash + Objects.hashCode(this.diagnosticoPiel);
        hash = 47 * hash + Objects.hashCode(this.cosmeticosUso);
        hash = 47 * hash + Objects.hashCode(this.antecedentesPersonales);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Expediente other = (Expediente) obj;
        return Objects.equals(this.idExpediente, other.idExpediente);
    }

    @Override
    public String toString() {
        return "Expediente{" + "idExpediente=" + idExpediente + ", cliente=" + cliente + ", antecedentesPatologicos=" + antecedentesPatologicos + ", diagnosticoPiel=" + diagnosticoPiel + ", cosmeticosUso=" + cosmeticosUso + ", antecedentesPersonales=" + antecedentesPersonales + '}';
    }


    
    
}
