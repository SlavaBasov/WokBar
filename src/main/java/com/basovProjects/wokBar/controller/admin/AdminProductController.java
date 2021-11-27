package com.basovProjects.wokBar.controller.admin;

import com.basovProjects.wokBar.exceptions.MyObjectIsAlreadyExistException;
import com.basovProjects.wokBar.exceptions.MyObjectNotFoundException;
import com.basovProjects.wokBar.model.Product;
import com.basovProjects.wokBar.model.category.Category;
import com.basovProjects.wokBar.service.CategoryService;
import com.basovProjects.wokBar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/categories")
public class AdminProductController {

    private final ProductService<Long, Product> productService;
    private final CategoryService<Long, Category> categoryService;

    @Autowired
    public AdminProductController(ProductService<Long, Product> productService, CategoryService<Long, Category> categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/productsOfCategory{id}")
    public String products(@PathVariable("id") Long id, Model model) throws MyObjectNotFoundException {
        model.addAttribute("products", productService.getAllProductsByCategoryId(id));
        model.addAttribute("categoryName", categoryService.findByIdWithLocalization(id).getName());
        return "/admin/products/products";
    }

    @GetMapping("/productsOfCategory{category_id}/newProductForm")
    public String addProduct(@PathVariable("category_id") Long categoryId, Model model) throws MyObjectNotFoundException {
        Product product = new Product();
        product.setCategory(new Category());
        product.getCategory().setId(categoryId);
        model.addAttribute("product", product);
        return "admin/products/newProductForm";
    }

    @PostMapping("/productsOfCategory/newProduct")
    public String addProduct(@ModelAttribute @Valid Product product, BindingResult bindingResult, Model model)
            throws MyObjectIsAlreadyExistException, MyObjectNotFoundException {
        if(bindingResult.hasErrors()){
            return "admin/products/newProductForm";
        }
        if(!productService.saveNewProduct(product)){
            model.addAttribute("errorMessage", "this product already exists");
            return "admin/products/newProductForm";
        };
        return String.format("redirect:/admin/categories/productsOfCategory%d", product.getCategory().getId());
    }



    @GetMapping("/productsOfCategory{category_id}/editProduct{product_id}")
    public String editProduct(@PathVariable("category_id") Long categoryId, @PathVariable("product_id") Long productId,
                              Model model) throws MyObjectNotFoundException {
        model.addAttribute("product", productService.getProductById(productId));
        return "admin/products/editProductForm";
    }

    @PostMapping("/productsOfCategory/editProduct")
    public String editProduct(@ModelAttribute @Valid Product product, BindingResult bindingResult, Model model)
            throws MyObjectIsAlreadyExistException, MyObjectNotFoundException {
        if(bindingResult.hasErrors()){
            return "admin/products/editProductForm";
        }
        if(!productService.updateProduct(product)){
            model.addAttribute("errorMessage", "this product already exists");
            return "admin/products/editProductForm";
        };
        return String.format("redirect:/admin/categories/productsOfCategory%d", product.getCategory().getId());
    }

    @GetMapping("/productsOfCategory{category_id}/deleteProduct{product_id}")
    public String deleteProduct(@PathVariable("category_id") Long categoryId, @PathVariable("product_id") Long productId,
                              Model model) {
        productService.deleteProduct(productId);
        return String.format("redirect:/admin/categories/productsOfCategory%d", categoryId);
    }



}
