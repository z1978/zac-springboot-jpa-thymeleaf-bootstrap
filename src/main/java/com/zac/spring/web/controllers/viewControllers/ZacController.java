package com.zac.spring.web.controllers.viewControllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zac.spring.entity.Category;
import com.zac.spring.entity.Contact;
import com.zac.spring.entity.Product;
import com.zac.spring.repository.ProductRepository;
import com.zac.spring.service.CategoryService;
import com.zac.spring.service.ContactService;
import com.zac.spring.service.ProductService;

/**
 * Created by Zac
 */

@ComponentScan
@Controller
@RequestMapping("")
public class ZacController {

	final static Logger logger = LoggerFactory.getLogger(ZacController.class);

	private static final String TM_INDEX = "TitanMaster/index";
	private static final String TM_ABOUT = "TitanMaster/about";
	private static final String TM_CATEGORY = "TitanMaster/category";
	private static final String TM_CONTACT = "TitanMaster/contact";

	@Autowired
	private ContactService contactService;
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ProductRepository categoryRepository;

	
	public ZacController(ProductService productService, CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	}

	// eBusiness home
	@GetMapping(value = { "/eb", "/eb/index" })
	public String eBusinessHome(Model model) {
		model.addAttribute("loginError", true);
		return "eBusiness/index";
	}

	// TitanMaster home
	@RequestMapping(value = { "/tm", "/tm/index" }, method = { RequestMethod.GET })
	public ModelAndView titanHome(ModelAndView mav) {
		logger.debug("TM + index");
		mav.addObject("model", "model");
		mav.setViewName(TM_INDEX);
		List<Category> list1 = categoryService.findByParentCategoryId(1);
		mav.addObject("result4category1", list1);
		List<Category> list3 = categoryService.findByParentCategoryId(3);
		mav.addObject("result4category3", list3);
		return mav;
	}
//	@GetMapping(value = { "/tm", "/tm/index" })
//	public ModelAndView titanMasterHome(Model model) {
//		model.addAttribute("loginError", true);
//
//		ModelAndView modelAndView = new ModelAndView("TitanMaster/index");
//		modelAndView.addObject("roles", roleService.findAll());
//		return modelAndView;
//
//		// return "TitanMaster/index";
//	}
	@RequestMapping(value = { "/tm/about" }, method = { RequestMethod.GET })
	public ModelAndView titanAbout(ModelAndView mav) {
		logger.debug("TM + about");
		mav.addObject("model", "model");
		mav.setViewName(TM_ABOUT);
		return mav;
	}

//	@RequestMapping(value = { "/tm/category" }, method = { RequestMethod.GET })
//	public ModelAndView titanItem(ModelAndView mav) {
//		logger.debug("TM + contact");
//		mav.addObject("model", "model");
//		List<Category> list = categoryService.findAll();
//		mav.addObject("result4category", list);
//		mav.setViewName(TM_CATEGORY);
//		return mav;
//	}
	
	@RequestMapping(value = { "/tm/category/{id}" }, method = { RequestMethod.GET })
	    public ModelAndView getCategoryItmeAll(ModelAndView mav, @PathVariable Integer id) {
		logger.debug("TM + contact");
		mav.addObject("model", "model");
	        List<Product> productList = productService.findByCategoryId(id);
	        mav.addObject("result4product", productList);
			mav.setViewName(TM_CATEGORY);
			return mav;
	    }

//	@RequestMapping(value = { "/tm/contact" }, method = { RequestMethod.GET })
//	public ModelAndView titanContact(ModelAndView mav) {
//		logger.debug("TM + contact");
//		mav.addObject("model", "model");
//		mav.setViewName(TM_CONTACT);
//		return mav;
//	}
	
	@GetMapping("/tm/contact")
    public String titanContact(Model model) {
    	// TODO
        model.addAttribute("newContact", new Contact());
        return TM_CONTACT;
    }
	
	@PostMapping("/tm/contact/newContact")
    public String saveNewContact(Model model, @ModelAttribute("newContact") @Valid final Contact newContact,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        boolean hasErrors = false;
        String formWithErrors = "/tm/contact";

        if (bindingResult.hasErrors()) hasErrors = true;

        if (hasErrors) return formWithErrors;

        else {
            contactService.createOne(newContact);
            redirectAttributes.addFlashAttribute("contactHasBeenSaved", true);
            return "TitanMaster/contactConfirm";
        }
        
    }
	
	@GetMapping("/test2")
	public String index2(ModelMap model, @RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "name", required = false) String name) {
		if (StringUtils.hasText(name)) {
			logger.debug(name);
		}
		return TM_INDEX;
	}


	// POST用のパラメータを受け取る
	@RequestMapping(value = { "/formPost" }, method = { RequestMethod.POST })
	public ModelAndView postTest1(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "age", required = true) String age) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName(TM_INDEX);

		// modelに設定して画面に表示するようにする
		mv.addObject("name", name);
		mv.addObject("age", age);

		return mv;
	}

	// http://localhost:8080/formGet?name=a&age=11
	// GET用のパラメータを受け取る
	@RequestMapping(value = { "/formGet" }, method = { RequestMethod.GET })
	public ModelAndView getTest1(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "age", required = false) String age) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName(TM_INDEX);

		// modelに設定して画面に表示するようにする
		mv.addObject("name", name);
		mv.addObject("age", age);

		return mv;
	}

}
