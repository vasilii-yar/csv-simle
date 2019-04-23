package ru.csvparser.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ru.csvparser.CsvObject;

// Интерфес для работы с базой данных. Не требует явной имплементации. Основные запросы уже реализованы Спрингом.
public interface CsvObjectRepository extends CrudRepository<CsvObject, String>{
	// top 15 by time
	List<CsvObject> findFirst15ByOrderByTimes();
	
	// all gibdd
	List<CsvObject> findAllByOrgid(String orgid);
	
	// all with type 'answer' ends on step_1
	
	@Query("select o from CsvObject o where o.typeevent = ?1 and o.url like ?2")
	List<CsvObject> findWithTypeAndUrl(String type, String include);
}
