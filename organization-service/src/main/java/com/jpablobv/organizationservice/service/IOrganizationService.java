package com.jpablobv.organizationservice.service;

import com.jpablobv.organizationservice.dto.OrganizationDto;

public interface IOrganizationService {

    OrganizationDto saveOrganization(OrganizationDto organizationDto);

    OrganizationDto getOrganizationByCode(String organizationCode);

}
