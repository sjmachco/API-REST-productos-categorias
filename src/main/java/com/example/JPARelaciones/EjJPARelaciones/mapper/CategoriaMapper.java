package com.example.JPARelaciones.EjJPARelaciones.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.JPARelaciones.EjJPARelaciones.dto.categoria.CategoriaCreateDto;
import com.example.JPARelaciones.EjJPARelaciones.dto.categoria.CategoriaResponseDto;
import com.example.JPARelaciones.EjJPARelaciones.dto.categoria.CategoriaUpdateDto;
import com.example.JPARelaciones.EjJPARelaciones.model.Categoria;

@Component
public class CategoriaMapper {
	
	public Categoria toEntity(CategoriaCreateDto dto) {
		if(dto == null)
			return null;
		Categoria categoria = new Categoria();
		categoria.setDescripcion(dto.getDescripcion());
		return categoria;
	}
	
	public void updateEntity(CategoriaUpdateDto dto, Categoria categoria) {
		categoria.setDescripcion(dto.getDescripcion());
	}
	
	public CategoriaResponseDto toDto(Categoria categoria) {
		if(categoria == null)
			return null;
		CategoriaResponseDto dto = new CategoriaResponseDto();
		dto.setId(categoria.getId());
		dto.setDescripcion(categoria.getDescripcion());
		return dto;
	}
	
	public List<CategoriaResponseDto> toListDtoCategoria(List<Categoria> categorias){
		return categorias.stream()
				.map(this::toDto)
				.collect(Collectors.toList());
	}
}
