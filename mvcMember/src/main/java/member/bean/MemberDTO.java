package member.bean;

import lombok.Data;

// DTO - Data Transfer Object
// VO - Value Object

// Modifier의 종류
// 1. private
// 2. default
// 3. protected
// 4. public

//@Getter
//@Setter
@Data
public class MemberDTO {
	private String name;
	private String id;
	private String pwd;
	private String gender;
	private String email1;
	private String email2;
	private String tel1;
	private String tel2;
	private String tel3;
	private String zipcode;
	private String addr1;
	private String addr2;
	
}
