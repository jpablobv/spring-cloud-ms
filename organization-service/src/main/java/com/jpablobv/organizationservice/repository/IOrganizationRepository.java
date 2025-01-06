package com.jpablobv.organizationservice.repository;

import com.jpablobv.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrganizationRepository extends JpaRepository<Organization, Long> {

    Organization findByOrganizationCode(String organizationCode);

}
