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
@Table(name = "test")
public class Test {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY)
    private List<Direction> directions = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY)
    private List<ApplicantTest> applicantTests = new ArrayList<>();
}