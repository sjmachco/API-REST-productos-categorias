package com.example.JPARelaciones.EjJPARelaciones.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.JPARelaciones.EjJPARelaciones.dto.categoria.CategoriaResponseDto;
import com.example.JPARelaciones.EjJPARelaciones.dto.producto.ProductoCreateDto;
import com.example.JPARelaciones.EjJPARelaciones.dto.producto.ProductoResponseDto;
import com.example.JPARelaciones.EjJPARelaciones.dto.producto.ProductoUpdateDto;
import com.example.JPARelaciones.EjJPARelaciones.model.Producto;

@Component
public class ProductoMapper {
	
	
	//La entidad categoria o cualquier entidad diferente no deberia estar dentro de otro mapper
	//public Producto toEntity(ProductoCreateDto dto, Categoria categoria) {
	//Funcion que permite crear una entidad
	public Producto toEntity(ProductoCreateDto dto) {
		if(dto == null)
			return null;
		Producto producto = new Producto();
		producto.setNombre(dto.getNombre());
		producto.setPrecio(dto.getPrecio());
		//producto.setCategoria(categoria);
		return producto;
	}
	
	//Funcion que permite actualizar una entidad ya existente
	public void updateDto(ProductoUpdateDto dto, Producto producto) {
		producto.setNombre(dto.getNombre());
		producto.setPrecio(dto.getPrecio());
	}
	
	//Funcion permite obtener datos de las entidades
	public ProductoResponseDto toDto(Producto producto) {
		if(producto == null)
			return null;
		ProductoResponseDto dto = new ProductoResponseDto();
		dto.setId(producto.getId());
		dto.setNombre(producto.getNombre());
		dto.setPrecio(producto.getPrecio());
		
		if(producto.getCategoria() != null) {
			CategoriaResponseDto categoriaResponseDto = new CategoriaResponseDto();
			categoriaResponseDto.setId(producto.getCategoria().getId());
			categoriaResponseDto.setDescripcion(producto.getCategoria().getDescripcion());
			dto.setCategoriaResponseDto(categoriaResponseDto);
		}
		return dto;
	}
	
	//Funcion que permite obtener una lista de productos
	public List<ProductoResponseDto> toListDtoProducto(List<Producto> productos){
		return productos.stream()
				.map(this::toDto)
				.collect(Collectors.toList());
	}

}
