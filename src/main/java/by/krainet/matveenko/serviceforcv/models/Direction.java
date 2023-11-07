package by.krainet.matveenko.serviceforcv.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "direction")
public class Direction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String level;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "applicant_direction",
            joinColumns = @JoinColumn(name = "direction_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "applicant_id", referencedColumnName = "id"))
    private List<ApplicantTest> applicantTests = new ArrayList<>();
}