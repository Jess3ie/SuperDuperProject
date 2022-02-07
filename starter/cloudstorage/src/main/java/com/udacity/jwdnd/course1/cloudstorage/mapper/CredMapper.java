package com.udacity.jwdnd.course1.cloudstorage.mapper;


import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;


//    private Integer credentialId;
//    private String url;
//    private String username;
//    private String key;
//    private String password;
//    private User userId;


@Mapper
public interface CredMapper {

    @Insert("INSERT INTO CREDENTIALS (url, username, password) VALUES (#{url}, #{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int addCredentials(Credentials credentials);

    @Update("UPDATE CREDENTIALS SET url=#{url}, username=#{username}, password=#{password} WHERE credentialId=#{credentialId}")
    int updateCredentials(Credentials credentials);


    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    Integer deleteCredential(Integer credentialId);

    @Select("Select * from Credentials")
    List<Credentials> getAllCredentials();


}
