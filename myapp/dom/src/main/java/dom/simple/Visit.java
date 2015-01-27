package dom.simple;

import java.util.List;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.TypicalLength;
import org.apache.isis.applib.util.ObjectContracts;
import org.apache.isis.applib.value.DateTime;
import org.joda.time.LocalDate;

@SuppressWarnings("deprecation")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Unique(name = "Visit_details", members = { "diagnosis" })

@Bookmarkable
public class Visit implements Comparable<Visit> {

	private String diagnosis;
	

	@javax.jdo.annotations.Column(allowsNull = "false")
	@Title(sequence = "1")
	@MemberOrder(sequence = "1")
	public String getDiagnosis(){
		return diagnosis;
	}

	public void setDiagnosis(final String diagnosis) {
		this.diagnosis = diagnosis;
	}
	
	
	private LocalDate checkIn;
	
	
	@javax.jdo.annotations.Column(allowsNull = "false")
	@MemberOrder(sequence = "2")
	public LocalDate getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	
	private LocalDate checkOut;
	
	@javax.jdo.annotations.Column(allowsNull = "false")
	@MemberOrder(sequence = "3")
	public LocalDate getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}
	
	
	private Pet pet;

	@javax.jdo.annotations.Column(allowsNull = "true")
	@MemberOrder(sequence = "4")
	public Pet getPet() {
		return pet;
	}

	public void setPet(final Pet pet) {
		this.pet = pet;
	}

	public List<Pet> autoCompletePet(String search){
		return pets.findByName(search);
	}

	// region > compareTo
	
	@Override
	public int compareTo(Visit other) {
		return ObjectContracts.compare(this, other, "diagnosis");
	}

	// region > injected services

	@javax.inject.Inject
	@SuppressWarnings("unused")
	private DomainObjectContainer container;
	
	@javax.inject.Inject
	@SuppressWarnings("unused")
	private Pets pets;

	// endregion
	
	
}
