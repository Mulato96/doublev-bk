package com.doublev.farmatodo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.doublev.farmatodo.dto.UsuarioDTO;
import com.doublev.farmatodo.model.Usuario;

@Mapper(componentModel = "spring")
public interface IUsuarioMapper {

	IUsuarioMapper INSTANCE = Mappers.getMapper(IUsuarioMapper.class);

	@Mapping(target = "id", ignore = true)
	Usuario toEntity(UsuarioDTO dto);

	UsuarioDTO toDTO(Usuario usuario);
}
