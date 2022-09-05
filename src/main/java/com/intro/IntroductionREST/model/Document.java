package com.intro.IntroductionREST.model;

import com.intro.IntroductionREST.enumType.PostgreSQLEnumType;
import com.intro.IntroductionREST.enums.DocumentType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "documents")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String series;
    private String number;

    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private DocumentType type;

    private Date issueDate;
}