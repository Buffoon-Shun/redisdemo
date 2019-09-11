package com.hzjd.redisdemo.mapper;

import org.apache.ibatis.annotations.*;

import com.hzjd.redisdemo.domain.User;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
	@Select("SELECT * FROM T_USER WHERE PHONE = #{phone}")
	User findUserByPhone(@Param("phone") String phone);

	@Select("SELECT * FROM T_USER WHERE ID = #{id}")
	User findUserById(@Param("id") Integer id);

	@Insert("INSERT INTO T_USER(NAME, PASSWORD, PHONE) VALUES(#{name}, #{password}, #{phone})")
	int insert(@Param("name") String name, @Param("password") String password, @Param("phone") String phone);

	@Insert("INSERT INTO T_USER(NAME, PASSWORD, PHONE) VALUES("
			+ "#{name, jdbcType=VARCHAR}, #{password, jdbcType=VARCHAR}, #{phone, jdbcType=VARCHAR})")
	int insertByMap(Map<String, Object> map);

	@Insert("INSERT INTO T_USER(NAME, PASSWORD, PHONE) VALUES(#{name}, #{password}, #{phone})")
	int insertByUser(User user);

	@Update("UPDATE T_USER SET NAME = #{name}, PASSWORD = #{password}, PHONE = #{phone} WHERE ID = #{id}")
	void update(User user);

	@Delete("DELETE FROM T_USER WHERE ID = #{id}")
	void delete(Integer id);

	@Results({ @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "password", column = "PASSWORD"), @Result(property = "phone", column = "PHONE") })
	@Select("SELECT ID, NAME, PASSWORD, PHONE FROM T_USER")
	List<User> findAll();

}
