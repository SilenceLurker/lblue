package com.cn.emio.sl.lblue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.jms.Destination;

import com.cn.emio.sl.lblue.test.entity.Mood;
import com.cn.emio.sl.lblue.test.entity.User;
import com.cn.emio.sl.lblue.test.producer.MoodProducer;
import com.cn.emio.sl.lblue.test.service.MoodService;
import com.cn.emio.sl.lblue.test.service.UserService;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.Assert;

@SpringBootTest
class LblueApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("Test");
	}

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Test
	public void mySqlTest() {
		String sql = "select * from usertest";
		List<User> userLIst = jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getString("ID"));
				user.setName(rs.getString("Name"));
				user.setPassword(rs.getString("Password"));
				return user;
			}
		});
		System.out.println("查询到以下数据：");
		for (User u : userLIst) {
			System.out.println("id:" + u.getId() + ", Name:" + u.getName() + ", Password" + u.getPassword());
		}
	}

	@Resource
	private UserService userService;

	@Test
	public void testRepository() {
		List<User> userList = userService.findAll();
		System.out.println("findAll():" + userList.size());

		List<User> userList2 = userService.findByName("Name");
		System.out.println("findByName():" + userList2.size());

		List<User> userList3 = userService.findByNameLike("%No%");
		System.out.println("findByName():" + userList3.size());
		Assert.isTrue(userList3.get(0).getName().equals("NoName"), "Error Data!!");

		List<String> ids = new ArrayList<String>();
		ids.add("1");
		ids.add("2");
		List<User> userList4 = userService.findByIdIn(ids);
		System.out.println("findByIdIn():" + userList4.size());

		PageRequest pageRequest = PageRequest.of(0, 10);
		Page<User> userList5 = userService.findAll(pageRequest);
		System.out.println("page findAll()" + userList5.getTotalPages() + "/" +
				userList5.getSize());

		User user = new User();
		user.setId("3");
		user.setName("test");
		user.setPassword("123");
		userService.save(user);

		userService.delete("3");
	}

	@Test
	public void testTransaction() {
		User user = new User();
		user.setId("5");
		user.setName("阿巴阿巴");
		user.setPassword("123");
		userService.save(user);
	}

	@Resource
	private RedisTemplate redisTemplate;

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void testRedis() {
		// 增 key : name , value: ay
		redisTemplate.opsForValue().set("name", "ay");
		String name = (String) redisTemplate.opsForValue().get("name");
		System.out.println(name);
		// 删除
		redisTemplate.delete("name");
		// 更新
		redisTemplate.opsForValue().set("name", "al");
		// 查询
		name = stringRedisTemplate.opsForValue().get("name");
		System.out.println("name");
	}

	@Test
	public void testMybatis() {
		User user = userService.findByNameAndPassword("Name", "Pass");
		System.out.println(user.getId() + user.getName());
	}

	Logger logger = LogManager.getLogger(this.getClass());

	@Test
	public void testLog4j() {
		userService.delete("2");
		logger.info("delete success!!!");
	}

	@Test
	public void testMax() {
		System.out.println((-2147483647) + (-2147483647));
	}

	@Test
	public void loggerTest() {
		logger.info("new Logger");
	}

	@Resource
	private MoodService moodService;

	@Test
	public void testMood() {
		Mood mood = new Mood();
		mood.setId("1");
		mood.setPraiseNum(0);
		mood.setContent("Text Test");
		mood.setPublishTime(new Date());

		moodService.save(mood);
	}

	@Resource
	private MoodProducer moodProducer;

	@Test
	public void testActiveMQ() {
		Destination destination = new ActiveMQQueue("sl.queue");
		moodProducer.sendMessage(destination, "hello mq");
	}

	@Test
	public void testActiveMQAsynSave() {
		Mood mood = new Mood();

		mood.setId("2");
		mood.setUserId("2");
		mood.setPraiseNum(0);
		mood.setContent("The First Test Mood.");
		mood.setPublishTime(new Date());
		System.out.println("异步发表说说：" + moodService.asynMood(mood));
	}

	@Test
	public void testAsync() {
		long startTime = System.currentTimeMillis();
		System.out.println("First Time:");
		List<User> userList = userService.findAll();
		System.out.println("Second Time:");
		List<User> userList2 = userService.findAll();
		System.out.println("Third Time:");
		List<User> userList3 = userService.findAll();
		long endTime = System.currentTimeMillis();
		System.out.println("Total Time is :" + (endTime - startTime) + "ms");
	}

	@Test
	public void testAsync2() throws Exception {
		long startTime = System.currentTimeMillis();
		System.out.println("First Time:");
		Future<List<User>> userList = userService.findAsynAll();
		System.out.println("Second Time:");
		Future<List<User>> userList2 = userService.findAsynAll();
		System.out.println("Third Time:");
		Future<List<User>> userList3 = userService.findAsynAll();
		while (true) {
			if (userList.isDone() && userList2.isDone() && userList3.isDone()) {
				break;
			} else {
				Thread.sleep(10);
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Total Time is :" + (endTime - startTime) + "ms");

	}
}