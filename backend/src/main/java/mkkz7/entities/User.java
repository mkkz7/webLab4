package mkkz7.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id = 0;
    private String username = "";
    private String password_hash = "";
    private String salt = "";

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PointResult> results = new ArrayList<>();

    public void addResult(PointResult result){
        results.add(result);
        result.setUser(this);
    }

    public void removeResult(PointResult result) {
        results.remove(result);
        result.setUser(null);
    }
}
