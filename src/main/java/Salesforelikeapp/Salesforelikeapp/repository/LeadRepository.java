
package Salesforelikeapp.Salesforelikeapp.repository;

import Salesforelikeapp.Salesforelikeapp.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer>, JpaSpecificationExecutor<Lead> {
    // You can add custom queries here if needed
}
