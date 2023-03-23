/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author jvale
 */
public abstract class AbstractConverter<E,D> {
    public abstract E fromDto(D dto);
    public abstract D fromEntity(E entity);
}
