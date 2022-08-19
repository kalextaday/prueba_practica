package com.example.demo.application.port.out;

import com.example.demo.application.dto.ClienteDto;

import java.util.List;

public interface IClientePersistenciaPort {

    ClienteDto agregarCliente(ClienteDto clienteDto);

    ClienteDto editarCliente(ClienteDto clienteDto);

    boolean eliminarCliente(ClienteDto clienteDto);

    ClienteDto obtenerClientePorIdentificacionPersona(Integer idPerson);

    ClienteDto obtenerClientePorId(Integer idCliente);

    List<ClienteDto> obtenerTodos();
}
