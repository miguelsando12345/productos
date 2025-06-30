package com.example.productos.service;

import com.example.productos.model.Producto;
import com.example.productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repo;

    public List<Producto> obtenerTodos() {
        return repo.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    public Producto guardar(Producto producto) {
        return repo.save(producto);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    public Producto actualizar(Long id, Producto nuevoProducto) {
        return repo.findById(id).map(p -> {
            p.setNombre(nuevoProducto.getNombre());
            p.setPrecio(nuevoProducto.getPrecio());
            p.setStock(nuevoProducto.getStock());
            return repo.save(p);
        }).orElseGet(() -> {
            nuevoProducto.setId(id);
            return repo.save(nuevoProducto);
        });
    }
}
