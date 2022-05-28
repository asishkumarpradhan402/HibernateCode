package tech.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@Column(name = "user_id", length = 11, nullable = false)
	@GeneratedValue
	private int userId;
	
	@Column(name = "user_name", length = 50, nullable = false)
	@NotBlank(message = "User_Name is Required")
	@NotNull(message = "User_Name is Required")
	@NotEmpty(message = "User_Name is Required")
	@Size(min = 3, max = 50, message = "User_Name is in between 3 and 50")
	private String userName;
}
