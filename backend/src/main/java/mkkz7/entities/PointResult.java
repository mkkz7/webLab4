package mkkz7.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="point_results")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id = 0;
    private int x = 0;
    private double y = 0.0;
    private int r = 0;
    private boolean hit = false;
    private double execTime = 0.0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
