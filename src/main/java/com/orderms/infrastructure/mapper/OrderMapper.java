package com.orderms.infrastructure.mapper;

import com.orderms.adapter.api.dto.OrderDTO;
import com.orderms.adapter.persistence.entity.OrderEntity;
import com.orderms.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toDTO(Order order);
    Order toDomain(OrderDTO orderDTO);
    Order toDomain(OrderEntity orderEntity);
    OrderEntity toEntity(Order order);
}