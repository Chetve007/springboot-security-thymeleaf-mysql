package com.alex.springBoot.springBootApp;

import com.alex.springBoot.springBootApp.entities.Task;
import com.alex.springBoot.springBootApp.entities.User;
import com.alex.springBoot.springBootApp.services.TaskService;
import com.alex.springBoot.springBootApp.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAppApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private TaskService taskService;

	@Before
	public void initDb() {
		{
			User newUser = new User("userOne@gmail.com", "Chipushila", "123456");
			userService.createUser(newUser);
		}
		{
			User newUser = new User("userAdmin@gmail.com", "Alex", "654321");
			userService.createUser(newUser);
		}

		Task userTask = new Task("13/10/2019", "09:00", "12:00", "This test task description");
		User user = userService.findOne("userOne@gmail.com");
		taskService.addTask(userTask, user);
	}

	@Test
	public void testUser() {
		User user = userService.findOne("userOne@gmail.com");
		assertNotNull(user);
		User admin = userService.findOne("userAdmin@gmail.com");
		assertEquals(admin.getEmail(), "userAdmin@gmail.com");
	}

	@Test
	public void testTask() {
		User user = userService.findOne("userOne@gmail.com");
		List<Task> tasks = taskService.findUserTask(user);
		assertNotNull(tasks);
	}

}
