package com.jpablobv.organizationservice.service.impl;

import com.jpablobv.organizationservice.dto.OrganizationDto;
import com.jpablobv.organizationservice.entity.Organization;
import com.jpablobv.organizationservice.mapper.IAutoOrganizationMapper;
import com.jpablobv.organizationservice.repository.IOrganizationRepository;
import com.jpablobv.organizationservice.service.IOrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements IOrganizationService {

    private IOrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {

        Organization organization = IAutoOrganizationMapper.MAPPER.mapToOrganization(organizationDto);

        Organization savedOrganization = organizationRepository.save(organization);

        return IAutoOrganizationMapper.MAPPER.mapToOrganizationDto(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        if ( organization == null ) {
            throw new RuntimeException("Organization not found by code: " + organizationCode);
        }
        return IAutoOrganizationMapper.MAPPER.mapToOrganizationDto(organization);
    }

}
