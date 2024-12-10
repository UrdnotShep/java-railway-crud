package com.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.apirest.Repositories.ProductRepository;
import com.apirest.apirest.Entities.Producto;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductRepository ProductoRepository;

    @GetMapping
    public List<Producto> getAllProductos(){
        return ProductoRepository.findAll();
    }
       
    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id){
        return ProductoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con el id " + id));
    }
    @PostMapping
    public Producto createProducto(@RequestBody Producto producto){
        return ProductoRepository.save(producto);
    }
    @PutMapping
    public Producto updateProducto (@PathVariable Long id, @RequestBody Producto detallesProducto){
        Producto producto = ProductoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con el id " + id));

        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());

        return ProductoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String borrarProducto(@PathVariable Long id){
        Producto producto = ProductoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con el id " + id));

        ProductoRepository.delete(producto);
        return "El producto con el ID: " + id + " fue eliminado con exito";

    }
    
    }
