package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Inheritance;

@Entity
public class Movie extends Item{
	
	private String director;
	private String actor;
}
