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
@Table(name = "applicant")
public class Applicant {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "photo_link")
    private String photo;

    @Column(name = "cv_link")
    private String cv;

    @Column(name = "description")
    private String level;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "applicant_direction",
            joinColumns = @JoinColumn(name = "applicant_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "direction_id", referencedColumnName = "id"))
    private List<Direction> directions = new ArrayList<>();


    @Builder.Default
    @OneToMany(mappedBy = "applicant", fetch = FetchType.LAZY)
    private List<ApplicantTest> applicantTests = new ArrayList<>();
}