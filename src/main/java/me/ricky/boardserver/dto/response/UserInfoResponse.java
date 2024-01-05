package me.ricky.boardserver.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.ricky.boardserver.dto.UserDTO;

@Getter
@AllArgsConstructor
public class UserInfoResponse {
    private UserDTO userDTO;
}