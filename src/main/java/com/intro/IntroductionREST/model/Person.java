package com.intro.IntroductionREST.model;

import com.intro.IntroductionREST.enumType.PostgreSQLEnumType;
import com.intro.IntroductionREST.enums.Gender;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "persons")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private Gender gender;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "document_id")
    private Document document;
}