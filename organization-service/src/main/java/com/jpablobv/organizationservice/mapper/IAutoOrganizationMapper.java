package com.jpablobv.organizationservice.mapper;

import com.jpablobv.organizationservice.dto.OrganizationDto;
import com.jpablobv.organizationservice.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IAutoOrganizationMapper {

    IAutoOrganizationMapper MAPPER = Mappers.getMapper(IAutoOrganizationMapper.class);

    Organization mapToOrganization(OrganizationDto organizationDto);

    OrganizationDto mapToOrganizationDto(Organization organization);

}
