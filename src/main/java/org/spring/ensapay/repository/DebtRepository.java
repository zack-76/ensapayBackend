package org.spring.ensapay.repository;

import org.spring.ensapay.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface DebtRepository extends JpaRepository<Debt,String> {


    @Query("select d.nameDebt from Dept d where d.codeDebt=:codeDebt")
    String findDebtNameByCodeDebt(@Param("codeDebt") String codeDebt);
}
