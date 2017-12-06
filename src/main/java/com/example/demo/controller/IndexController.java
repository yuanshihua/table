package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import com.example.demo.dao.PersonReposity;
import com.example.demo.entity.Person;

@Controller
public class IndexController {
	
	@Autowired
	private PersonReposity pr;

	/**
	 * 首页先查询数据库中的数据，再跳转
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		List<Person> list = pr.findAll();
		model.addAttribute("persons", list);
		return "add";
	}

	/**
	 * 新增数据,修改数据的方法
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/table")
	public String table(Model model, String id) {
		Person person = new Person();
		if(id!=null) {
			person.setId(id);
//			Person p = pr.findById(id);
			Person p = pr.findOne(person);
			model.addAttribute("person", p);
		}else {
			model.addAttribute("person", person);
		}
		return "table";
	}

	/**
	 * 接收表单传过来的数据，并将数据存在数据库中，再查询所有，显示在表格中
	 * @param person
	 * @param model
	 * @return
	 */
	@RequestMapping("/msg")
	public String msg(Person person, Model model){
		pr.save(person);
		List<Person> all = pr.findAll();
		model.addAttribute("persons", all);
		return "add";
	}
	@RequestMapping("/test")
	public String test() {
		return "index";
	}
	/**
	 * 上传文件方法
	 * @param file
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public String fileUpload(MultipartFile file) throws IllegalStateException, IOException {
		String path = "E://Workspaces//table//src//main//resources//static//"+file.getOriginalFilename();
		File newFile = new File(path);
		file.transferTo(newFile);
		System.out.println(path);
		return path;
	}
}
