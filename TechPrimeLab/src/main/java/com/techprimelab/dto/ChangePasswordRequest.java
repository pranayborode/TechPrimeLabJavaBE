package com.techprimelab.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ChangePasswordRequest {

	private long userId;
	private String email;
    private String currentPassword;
    private String newPassword;
}
