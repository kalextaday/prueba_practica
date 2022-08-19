package com.example.demo.application.port.impl;

import com.example.demo.application.dto.ClienteDto;
import com.example.demo.application.mapper.ClienteMapper;
import com.example.demo.application.port.out.IClientePersistenciaPort;
import com.example.demo.domain.entity.Cliente;
import com.example.demo.infraestructure.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ClienteService implements IClientePersistenciaPort {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {

        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteDto agregarCliente(ClienteDto personaDto) {

        Cliente clientRecent = ClienteMapper.INSTANCE.clienteDtoToCliente(personaDto);
        Cliente clientSaved = this.clienteRepository.save(clientRecent);

        return ClienteMapper.INSTANCE.clienteToClienteDto(clientSaved);
    }

    @Override
    public ClienteDto editarCliente(ClienteDto clientDto) {
        return this.agregarCliente(clientDto);
    }

    @Override
    public boolean eliminarCliente(ClienteDto personaDto) {
        try{
            Cliente clientRecent = ClienteMapper.INSTANCE.clienteDtoToCliente(personaDto);

            if(Objects.nonNull(clientRecent)){
                this.clienteRepository.delete(clientRecent);
                log.info("Cliente eliminado correctamente");
                return true;
            }
            return false;
        }catch(Exception ex){
            log.error("Error al eliminar cliente: {}", ex.getMessage());
            return false;
        }
    }

    @Override
        public ClienteDto obtenerClientePorIdentificacionPersona(Integer idPerson) {

        Cliente clientRecover = this.clienteRepository.findByIdPerson(idPerson);

        if(clientRecover != null){
            return ClienteMapper.INSTANCE.clienteToClienteDto(clientRecover);
        }

        return null;
    }

    @Override
    public ClienteDto obtenerClientePorId(Integer idCliente) {
        Cliente clientRecover = this.clienteRepository.findById(idCliente).orElse(null);
        if(clientRecover != null){
            return ClienteMapper.INSTANCE.clienteToClienteDto(clientRecover);
        }
        return null;
    }

    @Override
    public List<ClienteDto> obtenerTodos() {
        List<Cliente> clientList = this.clienteRepository.findAll();
        return ClienteMapper.INSTANCE.clienteListToClienteDtoList(clientList);
    }
}
