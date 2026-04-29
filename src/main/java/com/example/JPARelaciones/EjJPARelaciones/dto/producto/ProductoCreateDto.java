package com.example.JPARelaciones.EjJPARelaciones.dto.producto;

import java.math.BigDecimal;


public class ProductoCreateDto {

	private Long id;
	private String nombre;
	private BigDecimal precio;
	private Long categoriaId;
	
	public ProductoCreateDto(Long id, String nombre, BigDecimal precio, Long categoriaId) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.categoriaId = categoriaId;
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

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoria(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
}
