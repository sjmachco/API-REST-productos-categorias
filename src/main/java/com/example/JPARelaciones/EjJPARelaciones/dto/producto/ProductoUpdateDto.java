package com.example.JPARelaciones.EjJPARelaciones.dto.producto;

import java.math.BigDecimal;


public class ProductoUpdateDto {
	
	private String nombre;
	private BigDecimal precio;
	private Long categoriaId;

	public ProductoUpdateDto() {
		
	}
	
	public ProductoUpdateDto(String nombre, BigDecimal precio, Long categoriaId) {
		this.nombre = nombre;
		this.precio = precio;
		this.categoriaId = categoriaId;
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

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
}
