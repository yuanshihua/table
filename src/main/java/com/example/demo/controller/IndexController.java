package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.PersonReposity;
import com.example.demo.entity.Person;

@Controller
public class IndexController {
	
	@Autowired
	private PersonReposity pr;

	@RequestMapping("/index")
	public String index(Model model) {
		List<Person> list = pr.findAll();
		model.addAttribute("persons", list);
		return "add";
	}

	/**
	 * 新增数据,修改数据方法
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/table")
	public String table(Model model, String id) {
		Person person = new Person();
		if(id!=null) {
			person.setId(id);
			Person p = pr.findById(id);
			model.addAttribute("person", p);
		}else {
			model.addAttribute("person", person);
		}
		return "table";
	}

	/**
	 * 接收表单传过来的数据，再将数据存在map中
	 * 
	 * @param person
	 * @param model
	 * @return
	 */
	@RequestMapping("/msg")
	public String msg(Person person, Model model) {
		pr.save(person);
		List<Person> all = pr.findAll();
		model.addAttribute("persons", all);
		return "add";
	}
}
