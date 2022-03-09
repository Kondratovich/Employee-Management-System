package com.company.springboot.controller;

import com.company.springboot.model.Customer;
import com.company.springboot.model.Project;
import com.company.springboot.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = {"/manager", "/user"})
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/customers/showAddForm")
	public String showAddForm(Model model) {

		Customer customer = new Customer();
		model.addAttribute("customer", customer);

		return "add_customer";
	}

	@PostMapping("/customers/save")
	public String save(@ModelAttribute("employee") Customer customer) {

		customerService.saveCustomer(customer);

		return "redirect:/manager/customers";
	}

	@GetMapping("/customers/showUpdateForm/{id}")
	public String showUpdateForm(@PathVariable(value = "id") long id, Model model) {

		Customer customer = customerService.getCustomerById(id);

		model.addAttribute("customer", customer);

		return "update_customer";
	}

	@GetMapping("/customers/delete/{id}")
	public String delete(@PathVariable(value = "id") long id) {

		customerService.deleteCustomerById(id);

		return "redirect:/manager/customers";
	}

	@GetMapping("/customers")
	public String viewCustomersPage(Model model) {
		return findPaginated(1, "firstName", "asc", model);
	}

	@GetMapping("/customers/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir,
								Model model) {

		int pageSize = 5;

		Page<Customer> page = customerService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Customer> listCustomers = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listCustomers", listCustomers);

		return "managerDashboard_customers";
	}
}
