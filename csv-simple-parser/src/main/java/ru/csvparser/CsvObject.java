package ru.csvparser;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="objects")
// Класс экземпляры которого будут сериализованы в БД
public class CsvObject {
	@Id
	@NotNull
    private String ssoid;
	private long times;
	private String grpevent;
	private String typeevent;
	private String subtypeevent;
	private String url;
	private String orgid;
	private String formid;
	private String sessionkey;
	private String serviceresp;
	private long eventdate;
}
