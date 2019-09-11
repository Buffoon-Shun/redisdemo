package com.hzjd.redisdemo.controller;

import java.util.List;
import java.util.Optional;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzjd.redisdemo.domain.Person;
import com.hzjd.redisdemo.domain.User;
import com.hzjd.redisdemo.service.PersonService;
import com.hzjd.redisdemo.service.UserService;
import com.hzjd.redisdemo.util.ResultUtil;

/**
 * 
 * @author cys
 *
 */
@RestController
public class RedisController {
	private PersonService redisService;
	private UserService userSevice;

	@Autowired
	public void setRedisService(PersonService redisService) {
		this.redisService = redisService;
	}

	@Autowired
	public void setUserSevice(UserService userSevice) {
		this.userSevice = userSevice;
	}

	// 增
	@RequestMapping(value = "/persons", method = RequestMethod.POST)
	public JSONObject addPerson(@Param("name") String name) {
		try {
			Person person = new Person();
			person.setName(name);
			redisService.addPerson(person);
		} catch (Exception e) {
			return ResultUtil.error(e.getMessage());
		}
		return ResultUtil.success();
	}

	// 删
	@RequestMapping(value = "/persons/{id}", method = RequestMethod.DELETE)
	public JSONObject deletePerson(@PathVariable("id") int id) {
		try {
			redisService.delete(id);
		} catch (Exception e) {
			return ResultUtil.error(e.getMessage());
		}
		return ResultUtil.success();
	}

	// 改
	@RequestMapping(value = "/persons/{id}", method = RequestMethod.PUT)
	public JSONObject updatePerson(@PathVariable("id") int id, @Param("name") String name) {
		try {
			Optional<Person> person = redisService.findById(id);
			person.get().setName(name);
			redisService.update(person.get());
		} catch (Exception e) {
			return ResultUtil.error(e.getMessage());
		}
		return ResultUtil.success();
	}

	// 查全部
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public JSONObject getPersonList() {
		try {
			List<Person> personList = redisService.getPersonList();
			return ResultUtil.success(personList);
		} catch (Exception e) {
			return ResultUtil.error(e.getMessage());
		}
	}

	// 查单个
	@RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
	public JSONObject getPerson(@PathVariable("id") int id) {
		try {
			Optional<Person> person = redisService.findById(id);
			return ResultUtil.success(person.get());
		} catch (Exception e) {
			return ResultUtil.error(e.getMessage());
		}
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public JSONObject addUser(@Param("name") String name, @Param("password") String password,
			@Param("phone") String phone) {
		try {
			User user = new User();
			user.setName(name);
			user.setPassword(password);
			user.setPhone(phone);
			userSevice.addUser(user);
			return ResultUtil.success();
		} catch (Exception e) {
			return ResultUtil.error(e.getMessage());
		}
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public JSONObject deleteUser(@PathVariable("id") Integer id) {
		try {
			userSevice.delete(id);
			return ResultUtil.success();
		} catch (Exception e) {
			return ResultUtil.error(e.getMessage());
		}
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public JSONObject updateUser(@PathVariable("id") Integer id, @Param("name") String name,
			@Param("password") String password, @Param("phone") String phone) {
		try {
			User user = userSevice.findUserById(id);
			user.setName(name);
			user.setPassword(password);
			user.setPhone(phone);
			userSevice.update(user);
			return ResultUtil.success();
		} catch (Exception e) {
			return ResultUtil.error(e.getMessage());
		}
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public JSONObject getUser(@PathVariable("id") Integer id) {
		try {
			User user = userSevice.findUserById(id);
			return ResultUtil.success(user);
		} catch (Exception e) {
			return ResultUtil.error(e.getMessage());
		}
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public JSONObject getUserList() {
		try {
			List<User> userList = userSevice.getUserList();
			return ResultUtil.success(userList);
		} catch (Exception e) {
			return ResultUtil.error(e.getMessage());
		}
	}
}
