package org.springframework.samples.petclinic.feeding;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "feedings")
public class Feeding extends BaseEntity { // TODO extend baseEntity if not working
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Integer id;

    @NotNull // "The attribute must be required"
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "start_date")
    LocalDate startDate;

    @NotNull
    double weeksDuration;

    // TODO This is N-1 instead of 1-N?
    @NotNull
    @ManyToOne // TODO missing "mappedBy"?
    Pet pet;
}
