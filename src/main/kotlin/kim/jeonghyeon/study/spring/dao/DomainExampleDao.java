package kim.jeonghyeon.study.spring.dao;

import kim.jeonghyeon.study.spring.domain.DomainExample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DomainExampleDao extends JpaRepository<DomainExample, String> {
    @Query(value = "select distinct data from domain_example",
            nativeQuery = true
    )
    List<String> getList();
}
