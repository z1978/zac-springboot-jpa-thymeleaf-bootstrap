package com.zac.spring.web.controllers.viewControllers.adminControllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zac.spring.entity.Product;
import com.zac.spring.service.ProductService;
import com.zac.spring.web.dto.UserDto;

/**
 * Created by Zac
 */
@Controller
@RequestMapping("/adminPage")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ModelAndView showProducts() {
        ModelAndView modelAndView = new ModelAndView("adminPage/product/products");
//        List<Product> list = productService.findAll();
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }
    
    @GetMapping("/products/{id}")
    public ModelAndView getEditProductForm(@PathVariable Integer id) {
        Optional<Product> product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("adminPage/product/editProduct");
        modelAndView.addObject("product", product.get());
        return modelAndView;
    }

    @PostMapping("/products/{id}")
    public String updateProduct(Model model, @PathVariable Integer id,
                             @ModelAttribute("oldProduct") @Valid final Product product,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        String formWithErrors = "adminPage/product/editProduct";
        Optional<Product> persistedProduct = productService.findById(id);
        List<Product> allProducts = productService.findAll();

        boolean productNameAlreadyExists = productService.checkIfProductNameIsTaken(allProducts, product, persistedProduct.get());
        boolean hasErrors = false;

        if (productNameAlreadyExists) {
            bindingResult.rejectValue("name", "productNameAlreadyExists", "Oops!  There is already a product registered with the name provided.");
            hasErrors = true;
        }

        if (bindingResult.hasErrors()) hasErrors = true;

        if (hasErrors) {
            model.addAttribute("product", product);
            model.addAttribute("org.springframework.validation.BindingResult.product", bindingResult);
            return formWithErrors;
        } else {
        	Product updateProduct = productService.getProductById(id);
        	updateProduct.setCategoryId(product.getCategoryId());
        	updateProduct.setName(product.getName());
        	updateProduct.setDescriptionDetail(product.getDescriptionDetail());
        	updateProduct.setUpdateDate(new Date());
            productService.updateOne(updateProduct);
            redirectAttributes.addFlashAttribute("productHasBeenUpdated", true);
            return "redirect:/adminPage/products";
        }
    }

    @GetMapping("/products/newProduct")
    public String getAddNewProductForm(Model model) {
    	// TODO
        model.addAttribute("newProduct", new UserDto());
        return "adminPage/product/newProduct";
    }

    @PostMapping("/products/newProduct")
    public String saveNewProduct(Model model, @ModelAttribute("newProduct") @Valid final Product newProduct,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        Product productNameAlreadyExists = productService.findByName(newProduct.getName());
        boolean hasErrors = false;
        String formWithErrors = "adminPage/product/newProduct";

        if (productNameAlreadyExists != null) {
            bindingResult.rejectValue("name", "usernameAlreadyExists", "Oops!  There is already a product registered with the name provided.");
            hasErrors = true;
        }

        if (bindingResult.hasErrors()) hasErrors = true;

        if (hasErrors) return formWithErrors;

        else {
            productService.createOne(newProduct);
            redirectAttributes.addFlashAttribute("productHasBeenSaved", true);
            return "redirect:/adminPage/products";
        }
    }
    
    @RequestMapping(value = "/products/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Integer id, RedirectAttributes attributes, Model model) {
    	productService.deleteById(id);
    	return "redirect:/adminPage/products";
    }
    
//    @DeleteMapping("/products/delete/{id}")
//    public ResponseEntity<Product> deleteProduct(@PathVariable Integer id) {
//        Optional<Product> productToDelete = productService.findById(id);
//
//        if (!productToDelete.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//        else {
//            productService.deleteById(id);
//            return new ResponseEntity<>(productToDelete.get(), HttpStatus.NO_CONTENT);
//        }
//    }
}
