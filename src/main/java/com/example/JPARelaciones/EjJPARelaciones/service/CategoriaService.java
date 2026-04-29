package com.example.JPARelaciones.EjJPARelaciones.service;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.JPARelaciones.EjJPARelaciones.dto.categoria.CategoriaCreateDto;
import com.example.JPARelaciones.EjJPARelaciones.dto.categoria.CategoriaResponseDto;
import com.example.JPARelaciones.EjJPARelaciones.dto.categoria.CategoriaUpdateDto;
import com.example.JPARelaciones.EjJPARelaciones.mapper.CategoriaMapper;
import com.example.JPARelaciones.EjJPARelaciones.model.Categoria;
import com.example.JPARelaciones.EjJPARelaciones.repository.CategoriaRepository;
import com.example.JPARelaciones.EjJPARelaciones.repository.ProductoRepository;

@Service
public class CategoriaService {
	
	private final CategoriaRepository categoriaRepository;
	private final CategoriaMapper categoriaMapper;
	private final ProductoRepository productoRepository;
	
	public CategoriaService(CategoriaRepository categoriaRepository, 
			CategoriaMapper categoriaMapper, ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
		this.categoriaRepository = categoriaRepository;
		this.categoriaMapper = categoriaMapper;
	}
	
	public List<CategoriaResponseDto> getListCategoria(){
		List<Categoria> list = categoriaRepository.findAll();
		return categoriaMapper.toListDtoCategoria(list);
	}
	
	public CategoriaResponseDto createCategoria(CategoriaCreateDto dto) {
		Categoria categoria = categoriaMapper.toEntity(dto);
		Categoria guardado = categoriaRepository.save(categoria);
		return categoriaMapper.toDto(guardado);
		
	}
	
	public CategoriaResponseDto getCategoriaId(Long id) {
		Categoria categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Categoria no encontrada."));
		return categoriaMapper.toDto(categoria);
	}
	
	public CategoriaResponseDto updateCategoria(Long id, CategoriaUpdateDto dto) {
		Categoria categoriaExistente = categoriaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Categoria no encontrada."));
		categoriaMapper.updateEntity(dto, categoriaExistente);
		Categoria categoria = categoriaRepository.save(categoriaExistente);
		return categoriaMapper.toDto(categoria);
	}
	
	@Modifying
	@Transactional
	public void deleteCategoria(Long id) {
		productoRepository.deleteByCategoriaId(id);
		categoriaRepository.deleteById(id);
	}
}
