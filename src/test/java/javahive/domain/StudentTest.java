package javahive.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import javahive.domain.impl.RepozytoriumStudentImpl;
import javahive.infrastruktura.Finder;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
//import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class StudentTest {
    public static final int LICZBA_STUDENTOW_W_YAML = 10;
    public static final String NAZWISKO = "Nowak";
    public static final String FRAGMENT_NAZWISKA = "a";
    
    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    Finder finder;
    @Inject
    RepozytoriumStudentImpl repozytoriumStudentImpl;
    
    @Test
    public void powinienZwrocic10Studentow() {
        //given
        	List<Student> listaStudentow = finder.findAll(Student.class);
        //when
        	int liczbaStudentow = listaStudentow.size();
        //then
        	assertThat(liczbaStudentow, is(LICZBA_STUDENTOW_W_YAML));
    }
    
    @Test
    public void powinienDodacStudenta() {
        //given
        	List<Student> listaStudentow = finder.findAll(Student.class);
        //when
	        int liczbaStudentow = listaStudentow.size();
	        Student s = new Student();
	        s.setImie("Jan");
	        s.setNazwisko("Kwas");
	        s.setWieczny(true);
	        entityManager.persist(s);
        //then
	        assertThat(liczbaStudentow, is(LICZBA_STUDENTOW_W_YAML + 1));
    }

    @Test
    public void sprawdzLiczbeOcen() {
        //given
        	List<Ocena> oc = finder.findAll(Ocena.class);
        //when
        	int rozmiarListyOcen = oc.size();
        //then
        	assertThat(rozmiarListyOcen, Matchers.greaterThan(0));
    }
    
    //Testy porównujące JPQL/HQL/CRITERIA
    @Test
    public void test_LiczStudPoNazwiskuJPQLvsHQL(){
    	//given
	    	List<Student> listaStudentowHQL  = repozytoriumStudentImpl.getStudenciPoNazwisku_HQL(NAZWISKO);
	    	List<Student> listaStudentowJPQL = repozytoriumStudentImpl.getStudenciPoNazwisku_JPQL(NAZWISKO);
	   	//when
	    	int iloscStudHQL = listaStudentowHQL.size();
	    	int iloscStudJPQL= listaStudentowJPQL.size();
    	//then
	    	assertThat(iloscStudHQL, Matchers.is(iloscStudJPQL));
    }
    
    //ZADANIE - wypełnić
    @Ignore
    @Test
    public void sprawdzLiczbeStudentowPoNazwiskuJPQLvsCRITERIA(){
    	//given
    	//when
    	//then
    	
    }   
    @Ignore
    @Test
    public void sprawdzLiczbeStudentowPoNazwiskuCRITERIAvsHQL(){
    	//given
    	//when    		
    	//then    	
    }
    
    //Testy na użycie filtrów Hibernate 
    @Test
    public void powinienZwrocicStudentowZNazwiskiemZawierajcymFragment(){
    	//given
    		List<Student> listaStudZFiltremNaNazwisko =
    				repozytoriumStudentImpl.getStudenciZFiltorwanymNazwiskiem(FRAGMENT_NAZWISKA);
    		List<Student> listaStudentowJPQLZFragmentemNazwiska  = 
    				repozytoriumStudentImpl.getStudenciJPQLPoFragmencieNazwiska(FRAGMENT_NAZWISKA);
    		
    	//when
    		int iloscStudOdfiltrowanych = listaStudZFiltremNaNazwisko.size();
    		int iloscStudZFragmNazwiskaJPQL = listaStudentowJPQLZFragmentemNazwiska.size();
    		
    	//then		    		
    		assertThat(iloscStudOdfiltrowanych, Matchers.is(iloscStudZFragmNazwiskaJPQL));
    }
    
    // Test na użycie projekcji
    @Ignore
    @Test
    public void powinienZwrocicProjekcjaStudentowRosnacoPoNumerzeIndeksu(){
    	//given
    	
    	//when
    	//then    	
    }
    
    @Ignore //skopany test, trzeba dobrze powiazać oceny
    @Test
    public void sprawdzOceny() {
        Student s = finder.findAll(Student.class).get(0);
        System.out.println("***" + s.getImie());
        assertThat(s.getOceny().size(), is(0));
        System.out.println(s.getOceny());
    }
   
    @Test
    public void powinienZwrocicWieleIndeksow() {
        List<Indeks> indeksy = finder.findAll(Indeks.class);
        System.out.println(indeksy.size());
    }
    

}
