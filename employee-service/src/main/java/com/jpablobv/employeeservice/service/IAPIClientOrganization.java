package com.jpablobv.employeeservice.service;

import com.jpablobv.employeeservice.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8083", value = "ORGANIZATION-SERVICE")
//@FeignClient(name = "DEPARTMENT-SERVICE")
public interface IAPIClientOrganization {

    // Build Get Organization by Code REST API
    @GetMapping("api/organizations/code/{organization-code}")
    public OrganizationDto getOrganizationByCode(@PathVariable("organization-code") String organizationCode);

}
