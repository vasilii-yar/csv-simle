package ru.csvparser.data;

import org.springframework.data.repository.CrudRepository;

import ru.csvparser.CsvObject;

// Интерфес для работы с базой данных. Не требует явной имплементации. Основные запросы уже реализованы Спрингом.
public interface CsvObjectRepository extends CrudRepository<CsvObject, String>{

}
