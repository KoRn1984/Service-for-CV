package by.krainet.matveenko.serviceforcv.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "applicant_test")
public class ApplicantTest {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "history", joinColumns = @JoinColumn(name = "applicant_test_id"))
    @MapKeyColumn(name = "date")
    @Column(name = "assessment")
    private Map<LocalDate, String> history = new HashMap<>();
}