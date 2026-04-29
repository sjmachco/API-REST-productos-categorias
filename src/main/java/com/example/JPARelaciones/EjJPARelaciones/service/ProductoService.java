package com.example.JPARelaciones.EjJPARelaciones.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.JPARelaciones.EjJPARelaciones.dto.producto.ProductoCreateDto;
import com.example.JPARelaciones.EjJPARelaciones.dto.producto.ProductoResponseDto;
import com.example.JPARelaciones.EjJPARelaciones.dto.producto.ProductoUpdateDto;
import com.example.JPARelaciones.EjJPARelaciones.mapper.ProductoMapper;
import com.example.JPARelaciones.EjJPARelaciones.model.Categoria;
import com.example.JPARelaciones.EjJPARelaciones.model.Producto;
import com.example.JPARelaciones.EjJPARelaciones.repository.CategoriaRepository;
import com.example.JPARelaciones.EjJPARelaciones.repository.ProductoRepository;

@Service
public class ProductoService {
	
	private final ProductoRepository productoRepository;
	private final CategoriaRepository categoriaRepository;
	private final ProductoMapper productoMapper;
	
	public ProductoService(ProductoRepository productoRepository, 
			CategoriaRepository categoriaRepository, ProductoMapper productoMapper) {
		this.productoRepository = productoRepository;
		this.categoriaRepository = categoriaRepository;
		this.productoMapper = productoMapper;
	}
	
	public List<ProductoResponseDto> getListProducto(){
		List<Producto> list = productoRepository.findAll();
		return productoMapper.toListDtoProducto(list);
	}
	
	public ProductoResponseDto getProductoById(Long id) {
		Producto producto = productoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Producto no encontrado"));
		return productoMapper.toDto(producto);
	}
	
	public ProductoResponseDto createProducto(ProductoCreateDto dto) {
		Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
				.orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
		Producto producto = productoMapper.toEntity(dto);
		producto.setCategoria(categoria);
		Producto guardado = productoRepository.save(producto);
		return productoMapper.toDto(guardado);
	}
	
	public ProductoResponseDto updateProducto(Long id, ProductoUpdateDto dto) {
		Producto productoExistente = productoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Producto no encontrado."));
		Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
				.orElseThrow(() -> new RuntimeException("Categoria no encontrada."));
		productoMapper.updateDto(dto, productoExistente);
		productoExistente.setCategoria(categoria);
		Producto actualizado = productoRepository.save(productoExistente);
		return productoMapper.toDto(actualizado);
	}
	
	public void deleteProducto(Long id) {
		productoRepository.deleteById(id);
	}
}
