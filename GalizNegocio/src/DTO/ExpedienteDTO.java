/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author jvale
 */
public class ExpedienteDTO {
    private String nombreCliente;
    private String antecedentesPatologicos;
    private String antecedentesPersonales;
    private String cosmeticosUso;
    private String diagnosticoPiel;

    public ExpedienteDTO(String nombreCliente, String antecedentesPatologicos, String antecedentesPersonales, String cosmeticosUso, String diagnosticoPiel) {
        this.nombreCliente = nombreCliente;
        this.antecedentesPatologicos = antecedentesPatologicos;
        this.antecedentesPersonales = antecedentesPersonales;
        this.cosmeticosUso = cosmeticosUso;
        this.diagnosticoPiel = diagnosticoPiel;
    }

    

    public ExpedienteDTO() {
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getAntecedentesPatologicos() {
        return antecedentesPatologicos;
    }

    public void setAntecedentesPatologicos(String antecedentesPatologicos) {
        this.antecedentesPatologicos = antecedentesPatologicos;
    }

    public String getAntecedentesPersonales() {
        return antecedentesPersonales;
    }

    public void setAntecedentesPersonales(String antecedentesPersonales) {
        this.antecedentesPersonales = antecedentesPersonales;
    }

    public String getCosmeticosUso() {
        return cosmeticosUso;
    }

    public void setCosmeticosUso(String cosmeticosUso) {
        this.cosmeticosUso = cosmeticosUso;
    }

    public String getDiagnosticoPiel() {
        return diagnosticoPiel;
    }

    public void setDiagnosticoPiel(String diagnosticoPiel) {
        this.diagnosticoPiel = diagnosticoPiel;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.nombreCliente);
        hash = 67 * hash + Objects.hashCode(this.antecedentesPatologicos);
        hash = 67 * hash + Objects.hashCode(this.antecedentesPersonales);
        hash = 67 * hash + Objects.hashCode(this.cosmeticosUso);
        hash = 67 * hash + Objects.hashCode(this.diagnosticoPiel);
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
        final ExpedienteDTO other = (ExpedienteDTO) obj;
        if (!Objects.equals(this.nombreCliente, other.nombreCliente)) {
            return false;
        }
        if (!Objects.equals(this.antecedentesPatologicos, other.antecedentesPatologicos)) {
            return false;
        }
        if (!Objects.equals(this.antecedentesPersonales, other.antecedentesPersonales)) {
            return false;
        }
        if (!Objects.equals(this.cosmeticosUso, other.cosmeticosUso)) {
            return false;
        }
        return Objects.equals(this.diagnosticoPiel, other.diagnosticoPiel);
    }

    @Override
    public String toString() {
        return "ExpedienteDTO{" + "nombreCliente=" + nombreCliente + ", antecedentesPatologicos=" + antecedentesPatologicos + ", antecedentesPersonales=" + antecedentesPersonales + ", cosmeticosUso=" + cosmeticosUso + ", diagnosticoPiel=" + diagnosticoPiel + '}';
    }

    

    

   
    
}
