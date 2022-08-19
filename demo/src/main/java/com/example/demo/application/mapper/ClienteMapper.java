package com.example.demo.application.mapper;

import com.example.demo.application.dto.ClienteDto;
import com.example.demo.domain.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public abstract class ClienteMapper {

    public static final ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);


    @Mapping(source = "cliente.idCliente",                    target = "idCliente")
    @Mapping(source = "cliente.idPersona",                    target = "idPersona")
    @Mapping(source = "cliente.contrasena",                    target = "contrasena")
    @Mapping(source = "cliente.estadoCliente",                    target = "estadoCliente")
    public abstract ClienteDto clienteToClienteDto(Cliente cliente);


    @Mapping(source = "clienteDto.idPersona",                    target = "idPersona")
    @Mapping(source = "clienteDto.contrasena",                    target = "contrasena")
    @Mapping(source = "clienteDto.estadoCliente",                    target = "estadoCliente")
    @Mapping(source = "clienteDto.idCliente",                    target = "idCliente")
    public abstract Cliente clienteDtoToCliente(ClienteDto clienteDto);

    public List<ClienteDto> clienteListToClienteDtoList(List<Cliente> data) {
        return data.stream()
                .map(this::clienteToClienteDto)
                .collect(Collectors.toList());
    }
}
