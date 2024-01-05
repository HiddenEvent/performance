package me.ricky.boardserver.mapper;

import me.ricky.boardserver.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserProfileMapper {
    UserDTO getUserProfile(@Param("id") String id);

    int insertUserProfile(@Param("id") String id, @Param("password") String password,
                          @Param("nickname") String nickname,
                          @Param("createTime") String createTime, @Param("updateTime") String updateTime);

    int deleteUserProfile(@Param("id") String id);

    UserDTO findByIdAndPassword(@Param("id") String id, @Param("password") String password);
    boolean idCheck(@Param("id") String id);

    int updatePassword(UserDTO userDTO);

    int updateAddress(UserDTO userDTO);

    int register(UserDTO dto);
}
