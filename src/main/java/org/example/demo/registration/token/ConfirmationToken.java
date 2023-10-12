package org.example.demo.registration.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ConfirmationToken {
	private Long id;
	private String token;
	private LocalDateTime createdAt;
	private LocalDateTime expiredAt;
	private LocalDateTime confirmAt;

	public ConfirmationToken(String token,
	                         LocalDateTime createdAt,
	                         LocalDateTime expiredAt,
	                         LocalDateTime confirmAt) {
		this.token = token;
		this.createdAt = createdAt;
		this.expiredAt = expiredAt;
		this.confirmAt = confirmAt;
	}
}
