package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.*;

import java.util.List;
//    private Integer fileId;
//    private String filename;
//    private String contentType;
//    private String fileSize;
//    private Integer userId;
//    private byte[] fileData;
@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES")
    List<Files> getAllFiles();

    @Select("SELECT * FROM FILES WHERE userid = #{userid} AND fileId = #{fileId}")
    Files getFile(int userid, int fileId);

    @Select("SELECT * FROM FILES WHERE filename = #{filename}")
    Files getFileByName(String filename);

    @Insert("INSERT INTO FILES (filename, contentType, fileSize, userId, fileData) VALUES (#{filename}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(Files files);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    void deleteFile(int fileId);

}
