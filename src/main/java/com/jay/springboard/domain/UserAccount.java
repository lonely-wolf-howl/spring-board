package com.jay.springboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
    @Index(columnList = "email", unique = true),
    @Index(columnList = "createdAt"),
    @Index(columnList = "createdBy")
})
@Entity
public class UserAccount extends AuditingFields {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter
  @Column(nullable = false, length = 255)
  private String userId;

  @Setter
  @Column(nullable = false)
  private String userPassword;

  @Setter
  @Column(length = 255)
  private String email;

  @Setter
  @Column(length = 255)
  private String nickname;

  @Setter
  @Column(length = 255)
  private String memo;

  protected UserAccount() {
  }

  private UserAccount(String userId, String userPassword, String email, String nickname, String memo) {
    this.userId = userId;
    this.userPassword = userPassword;
    this.email = email;
    this.nickname = nickname;
    this.memo = memo;
  }

  public static UserAccount of(String userId, String userPassword, String email, String nickname, String memo) {
    return new UserAccount(userId, userPassword, email, nickname, memo);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserAccount that)) return false;
    return this.getUserId() != null && this.getUserId().equals(that.getUserId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getUserId());
  }
}
