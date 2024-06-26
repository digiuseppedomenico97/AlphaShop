package com.xantrix.webapp.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xantrix.webapp.entity.DettListini;

import javax.transaction.Transactional;


public interface PrezziRepository extends JpaRepository<DettListini, Long>
{

	//Query JPQL
	@Query(value = "SELECT d FROM Listini l JOIN DettListini d ON l.id = d.listino.id WHERE d.codArt = ?1 AND l.id = ?2")
	DettListini SelByCodArtAndList(@Param("codart") String CodArt, @Param("idlist") String Listino);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM DettListini WHERE CodArt = ?1 AND idlist = ?2", nativeQuery = true)
	void DelRowDettList(@Param("codart") String CodArt, @Param("idlist") String idList);

}
