package com.jpablobv.organizationservice.controller;

import com.jpablobv.organizationservice.dto.OrganizationDto;
import com.jpablobv.organizationservice.service.IOrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organizations")
@AllArgsConstructor
public class OrganizationController {

    private IOrganizationService organizationService;

    // Build Organization REST API
    @PostMapping
    public ResponseEntity<?> createOrganization(@RequestBody OrganizationDto organizationDto) {
        OrganizationDto savedOrganizationDto = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganizationDto, HttpStatus.CREATED);
    }

    // Build Get Organization by Code REST API
    @GetMapping("code/{organization-code}")
    public ResponseEntity<?> getOrganizationByCode(@PathVariable("organization-code") String organizationCode) {
        OrganizationDto organizationDto = organizationService.getOrganizationByCode(organizationCode);
        return ResponseEntity.ok(organizationDto);
    }

}
