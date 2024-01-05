package me.ricky.boardserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.ricky.boardserver.dto.UserDTO;
import me.ricky.boardserver.exception.DuplicateIdException;
import me.ricky.boardserver.mapper.UserProfileMapper;
import me.ricky.boardserver.service.UserService;
import me.ricky.boardserver.utils.SHA256Util;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserProfileMapper userProfileMapper;

    public UserServiceImpl(UserProfileMapper userProfileMapper) {
        this.userProfileMapper = userProfileMapper;
    }

    @Override
    public void register(UserDTO dto) {
        boolean isDuplicatedId = isDuplicatedId(dto.getUserId());
        if (isDuplicatedId) {
            throw new DuplicateIdException("이미 존재하는 아이디입니다.");
        }
        dto.setCreateTime(new Date());
        dto.setPassword(SHA256Util.encryptSHA256(dto.getPassword()));
        int insertCount = userProfileMapper.register(dto);
        if (insertCount != 1) {
            throw new RuntimeException("회원가입에 실패했습니다.");
        }

    }

    @Override
    public UserDTO login(String id, String password) {
        String crpytoPassword = SHA256Util.encryptSHA256(password);
        UserDTO userDTO = userProfileMapper.findByIdAndPassword(id, crpytoPassword);
        return userDTO;
    }

    @Override
    public boolean isDuplicatedId(String id) {
        return userProfileMapper.idCheck(id);
    }

    @Override
    public UserDTO getUserInfo(String id) {
        return null;
    }

    @Override
    public void updatePassword(String id, String beforePassword, String afterPassword) {
        String crpytoPassword = SHA256Util.encryptSHA256(beforePassword);
        UserDTO userDTO = userProfileMapper.findByIdAndPassword(id, crpytoPassword);
        if (userDTO == null) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        userDTO.setPassword(SHA256Util.encryptSHA256(afterPassword));
        int updateCount = userProfileMapper.updatePassword(userDTO);
    }

    @Override
    public void deleteId(String id, String password) {
        String crpytoPassword = SHA256Util.encryptSHA256(password);
        UserDTO userDTO = userProfileMapper.findByIdAndPassword(id, crpytoPassword);
        if (userDTO == null) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        int deleteCount = userProfileMapper.deleteUserProfile(id);
        if (deleteCount != 1) {
            throw new RuntimeException("회원탈퇴에 실패했습니다.");
        }
    }
}
