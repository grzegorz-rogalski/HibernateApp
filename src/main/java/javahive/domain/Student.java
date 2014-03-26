package javahive.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import javahive.infrastruktura.BaseEntity;
import org.hibernate.annotations.Fetch;

import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Student extends BaseEntity {
	public Student(){};
	private String imie;
	private String nazwisko;
	private boolean wieczny;
	//@OneToMany(mappedBy="student",fetch=FetchType.EAGER)
    @OneToMany
	private List<Ocena> oceny=Lists.newArrayList();
    @OneToOne
    private Indeks indeks;
}
