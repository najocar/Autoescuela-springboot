package com.najocar.autoescuela.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.io.Serializable;
import java.util.*;

@Data
@Entity
@Table(name = "clases")
public class Clase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @ManyToMany
    @JoinTable(name = "clases_alumnoes",
            joinColumns = @JoinColumn(name = "clase_id"),
            inverseJoinColumns = @JoinColumn(name = "alumnoes_dni"))

    private List<Alumno> alumnoes = new ArrayList<>();

    public Clase(int i, String nombre, double v) {
        this.id = i;
        this.nombre = nombre;
        this.precio = v;
        this.alumnoes = new List<Alumno>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Alumno> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Alumno alumno) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Alumno> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Alumno> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Alumno get(int index) {
                return null;
            }

            @Override
            public Alumno set(int index, Alumno element) {
                return null;
            }

            @Override
            public void add(int index, Alumno element) {

            }

            @Override
            public Alumno remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Alumno> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Alumno> listIterator(int index) {
                return null;
            }

            @Override
            public List<Alumno> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
    }

    public Clase() {

    }
}
