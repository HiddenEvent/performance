package me.ricky.boardserver.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserDTO {
    public enum Status {
        DEFAULT, DELETED, ADMIN
    }
    private int id;
    private String userID;
    private String password;
    private String nickname;
    private boolean isAdmin;
    private Date createdAt;
    private boolean iswWithDraw;
    private Status status;
    private Date updatedAt;
}
