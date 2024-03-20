package abai.kz.jpa.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Table(name="t_requests")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Requests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", length = 64)
    private String username;
    @Column(name = "course_name", length = 86)
    private String coursename;
    private String commentary;
    private String number;
    private boolean handled;
}
