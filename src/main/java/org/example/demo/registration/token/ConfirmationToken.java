package org.example.demo.registration.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.demo.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ConfirmationToken {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String token;

	@Column(nullable = false)
	private LocalDateTime createdAt;
	@Column(nullable = false)
	private LocalDateTime expiredAt;
	@Column(nullable = false) 
	private LocalDateTime confirmAt;
	@ManyToOne
	@JoinColumn(
			nullable = false,
			name = "user_seq_id"
	)
	private User user;

	public ConfirmationToken(String token,
	                         LocalDateTime createdAt,
	                         LocalDateTime expiredAt,
	                         User user) {
		this.token = token;
		this.createdAt = createdAt;
		this.expiredAt = expiredAt;
		this.user = user;
	}
}
