package ru.csvparser.data;

import org.springframework.data.repository.CrudRepository;

import ru.csvparser.CsvObject;

public interface CsvObjectRepository extends CrudRepository<CsvObject, String>{

}
