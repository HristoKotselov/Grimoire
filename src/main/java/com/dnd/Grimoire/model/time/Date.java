package com.dnd.Grimoire.model.time;

import com.dnd.Grimoire.model.Visibility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "date")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Date {

    @Id
    @Column(name = "date_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dateId;

    @Column(name = "age")
    @Enumerated(EnumType.STRING)
    private Age age;

    @Column(name = "season")
    private Season season;

    @Column(name = "year")
    private int year;

    @Column(name = "month")
    @Enumerated(EnumType.STRING)
    private Month month;

    @Column(name = "day_of_month")
    private int dayOfMonth;

    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private Weekday dayOfWeek;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "date_visibility",
            joinColumns = @JoinColumn(name = "date_id"),
            inverseJoinColumns = @JoinColumn(name = "visibility_id"))
    private List<Visibility> visibilities;
}
