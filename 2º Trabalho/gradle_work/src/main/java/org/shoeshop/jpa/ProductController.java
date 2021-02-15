package org.shoeshop.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return "redirect:/home";
    }
    
    /*@GetMapping("/new-product2")
    public String saveProduct(@RequestParam("file") MultipartFile file,
    		@RequestParam("name") String name,
    		@RequestParam("description") String desc,
    		@RequestParam("price") float price)
    {
    	productService.saveProductToDB(file, name, desc, price);
    	return "new-product2-view";
    }*/
    
    @RequestMapping(value="product/{id}", method = RequestMethod.GET)
    public String showProduct(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.get(id));
        return "productshow";
    }

}