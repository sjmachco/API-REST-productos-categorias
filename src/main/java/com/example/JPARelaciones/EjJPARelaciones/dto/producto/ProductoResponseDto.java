package com.example.JPARelaciones.EjJPARelaciones.dto.producto;

import java.math.BigDecimal;

import com.example.JPARelaciones.EjJPARelaciones.dto.categoria.CategoriaResponseDto;

public class ProductoResponseDto {
	
	private Long id;
	private String nombre;
	private BigDecimal precio;
	private CategoriaResponseDto categoriaResponseDto;
	
	public ProductoResponseDto() {
		
	}
	
	public ProductoResponseDto(Long id, String nombre, BigDecimal precio, 
			CategoriaResponseDto categoriaResponseDto) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.categoriaResponseDto = categoriaResponseDto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public CategoriaResponseDto getCategoriaResponseDto() {
		return categoriaResponseDto;
	}

	public void setCategoriaResponseDto(CategoriaResponseDto categoriaResponseDto) {
		this.categoriaResponseDto = categoriaResponseDto;
	}
}
