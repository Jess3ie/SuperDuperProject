package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper //All we have to do to use these methods is inject beans for this interface into our services and
// MyBatis will automatically create the code for the JDBC requests!
public interface NoteMapper {

    @Select("Select * from Notes")
    List<Notes> getAllNotes();

    @Insert("INSERT INTO NOTES (noteTitle, noteDescription, userId) VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int addUserNote(Notes notes);

    @Update("UPDATE NOTES SET noteTitle=#{noteTitle}, noteDescription=#{noteDescription} WHERE noteId=#{noteId}")
    void updateUserNote(Integer noteId, String noteTitle, String noteDescription);

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
    Integer deleteNote(Integer noteId);

}
