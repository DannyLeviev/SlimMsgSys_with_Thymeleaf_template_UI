package com.mycomp.mymessagesys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycomp.mymessagesys.model.UserDTO;
import com.mycomp.mymessagesys.service.UserService;

@Controller
@RequestMapping("users")
public class UserController {

	private static final String VIEW_TITLE = "title";
	private UserService userSrvc;

	@Autowired
	public void setUserSrvc(UserService userSrvc) {
		this.userSrvc = userSrvc;
	}

	@GetMapping
	public String getUsers(Model model) {
		model.addAttribute(VIEW_TITLE, "All Users View");
		model.addAttribute("usersList", userSrvc.getList());
		return "userViews/allUsers";
	}
	
	@GetMapping("/add")
	public String addUser(Model model) {
		model.addAttribute(VIEW_TITLE, "Add new User");
		return "userViews/addUser";
	}
	
	@PostMapping("/add")
	public String processAddUser(@ModelAttribute UserDTO newUser) {
		userSrvc.create(newUser);
		return "redirect:";
	}
	
	@GetMapping("/update")
	public String updateUser(Model model) {
		model.addAttribute(VIEW_TITLE, "Update existing User");
		return "userViews/updateUser";
	}
	
	@PostMapping("/update")
	public String processupdateUser(@RequestParam String userId, @ModelAttribute UserDTO userUpdate) {
		userSrvc.update(Long.parseLong(userId), userUpdate);
		return "redirect:";
	}
	
	@GetMapping("/delete")
	public String deleteUser(Model model) {
		model.addAttribute(VIEW_TITLE, "Remove existing User");
		return "userViews/deleteUser";
	}
	
	@PostMapping("/delete")
	public String processDeleteUser(@RequestParam String id) {
		userSrvc.delete(Long.parseLong(id));
		return "redirect:";
	}
	
	
}
