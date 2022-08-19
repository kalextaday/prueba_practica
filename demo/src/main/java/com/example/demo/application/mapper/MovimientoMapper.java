package com.example.demo.application.mapper;

import com.example.demo.application.dto.MovimientoDto;
import com.example.demo.application.dto.PersonaDto;
import com.example.demo.domain.entity.Movimiento;
import com.example.demo.domain.entity.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public abstract class MovimientoMapper {

    public static final MovimientoMapper INSTANCE = Mappers.getMapper(MovimientoMapper.class);


    @Mapping(source = "movimiento.idMovimiento",                    target = "idMovimiento")
    @Mapping(source = "movimiento.idCuenta",                    target = "idCuenta")
    @Mapping(source = "movimiento.fecha",                    target = "fecha")
    @Mapping(source = "movimiento.tipoMovimiento",                    target = "tipoMovimiento")
    @Mapping(source = "movimiento.valor",                    target = "valor")
    @Mapping(source = "movimiento.saldo",                    target = "saldo")
    public abstract MovimientoDto movimientoToMovimientoDto(Movimiento movimiento);


    @Mapping(source = "movimientoDto.idCuenta",                    target = "idCuenta")
    @Mapping(source = "movimientoDto.fecha",                    target = "fecha")
    @Mapping(source = "movimientoDto.tipoMovimiento",                    target = "tipoMovimiento")
    @Mapping(source = "movimientoDto.valor",                    target = "valor")
    @Mapping(source = "movimientoDto.saldo",                    target = "saldo")
    @Mapping(source = "movimientoDto.idMovimiento",                    target = "idMovimiento")
    public abstract Movimiento movimientoDtoToMovimiento(MovimientoDto movimientoDto);

    public List<MovimientoDto> movimientoListToMovimientoDtoList(List<Movimiento> data) {
        return data.stream()
                .map(this::movimientoToMovimientoDto)
                .collect(Collectors.toList());
    }
}
