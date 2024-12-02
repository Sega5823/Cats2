package org.example;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tokens")
public class PasswordResetToken {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    //private int user_Id;
    private LocalDateTime date;
    private String token;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String token_Id;
}
