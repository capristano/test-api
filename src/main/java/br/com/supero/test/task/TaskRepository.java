package br.com.supero.test.task;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Comunicações da entidade Task com o banco de dados
 */
public interface TaskRepository extends CrudRepository<Task, Long> {
	
	@Query("SELECT t FROM Task t WHERE t.id = :id AND t.dateExclusion IS null") 
    Task findOne(@Param("id") Long id);
	
	@Query("SELECT t FROM Task t WHERE t.dateExclusion IS null") 
	Iterable<Task> findAll();
	
	@Modifying
	@Transactional
	@Query("UPDATE Task t SET t.dateExclusion = :dateExclusion WHERE t.id = :id")
	void setDeleteDate(@Param("id") Long id, @Param("dateExclusion") Date dateExclusion);

}