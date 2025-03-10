package com.doublev.farmatodo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.doublev.farmatodo.dto.TicketDTO;
import com.doublev.farmatodo.model.Ticket;

@Mapper(componentModel = "spring")
public interface ITicketMapper {
	ITicketMapper INSTANCE = Mappers.getMapper(ITicketMapper.class);

	@Mapping(target = "id", ignore = true)
	Ticket toEntity(TicketDTO dto);

	TicketDTO toDTO(Ticket ticket);
}
