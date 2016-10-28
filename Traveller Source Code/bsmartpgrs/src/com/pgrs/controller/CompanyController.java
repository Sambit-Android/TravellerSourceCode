package com.pgrs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgrs.core.dao.MasterGenericDAO;
import com.pgrs.dao.ICompanyDAO;
import com.pgrs.entity.Company;

/**
 * Controller which includes all the Company business logic concerned to all the modules
 * Module: All
 * 
 * @author Ravi Shankar Reddy
 * @version %I%, %G%
 * @since 0.1
 */

@Controller
public class CompanyController {

	@Autowired
	private MasterGenericDAO genericDAO;
	
	@Autowired
	private ProductAccessController productAccessController;
	
	@Autowired
	private ICompanyDAO companyDAO;
	
	@RequestMapping(value = "/company", method ={ RequestMethod.POST,RequestMethod.GET})
	public String index(Model model, HttpServletRequest request,HttpServletResponse response) throws IOException {
		return "usermanagement/company";
	}
	
	@RequestMapping(value = "/company/read", method ={ RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody List<Company> read(){
		return genericDAO.findAll(Company.class);
	}
	
	@RequestMapping(value = "/company/save", method ={ RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody List<Company> save(@ModelAttribute Company company, BindingResult result,HttpServletRequest request,HttpServletResponse response){
		company.setId(null);
		company.setBranchCode(company.getIfscCode().substring(company.getIfscCode().length() - 5));
		genericDAO.save(company);
		return read();
	}
	@RequestMapping(value = "/company/update", method ={ RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody List<Company> update(@ModelAttribute Company company, BindingResult result,HttpServletRequest request,HttpServletResponse response){
		company.setBranchCode(company.getIfscCode().substring(company.getIfscCode().length() - 5));
		genericDAO.update(company);
		return read();
	}
	
	@RequestMapping(value = "/company/delete", method ={ RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody List<Company> delete(@ModelAttribute Company company, BindingResult result,HttpServletRequest request,HttpServletResponse response){
		genericDAO.deleteById(Company.class, company.getId());
		return read();
	}
	
	@RequestMapping(value = "/company/status/{status}/{id}", method ={ RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody String status(@PathVariable int status,@PathVariable int id,HttpServletRequest request,HttpServletResponse response){
		if(status==0){
			List<Integer> usersList = companyDAO.getActiveUsersBasedOnCompanyId(id);
			if(usersList.isEmpty()){
				companyDAO.companyStatusUpdate(status,id);
				productAccessController.deleteGroup("ou=Company",genericDAO.getById(Company.class, id).getCompanyName());
				return "Deactivated";
			}else{
				return "Cannot be Deactivate,because this company assigned user is active";
			}
		} else{
			companyDAO.companyStatusUpdate(status,id);
			String companyName = genericDAO.getById(Company.class, id).getCompanyName();
			productAccessController.addGroup("ou=Company",companyName, companyName);
			return "Activated";
		}
	}
	
}
