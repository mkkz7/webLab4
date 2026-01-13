package mkkz7.utils.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointResultDTO {
    private long id;
    private double x;
    private double y;
    private int r;
    private boolean hit;
    private double execTime;
    private long user_id;
    private String username;
}
