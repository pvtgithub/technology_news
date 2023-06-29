package com.technology.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technology.api.output.CategoryOutput;
import com.technology.dto.CategoryDTO;
import com.technology.service.ICategoryService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryAPI {

	@Autowired
	private ICategoryService categoryService;

	@PostMapping("/category")
	public CategoryDTO createcCategory(@RequestBody CategoryDTO categoryDTO) {
		return categoryService.save(categoryDTO);
	}

	@PutMapping("/category/{id}")
	public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable long id) {
		categoryDTO.setId(id);
		return categoryService.save(categoryDTO);
	}

	@DeleteMapping("/category")
	public void deleteCategory(@RequestBody long[] ids) {
		categoryService.delete(ids);
	}

	@GetMapping("/category")
	public CategoryOutput getCategory(@RequestParam("page") int page, @RequestParam("limit") int limit) {
		CategoryOutput result = new CategoryOutput();
		result.setPage(page);
		Pageable pageAble = new PageRequest(page-1, limit);
		result.setcategoryList(categoryService.categoryList(pageAble));
		result.setTotalPage((int) Math.ceil((double) categoryService.countItemCategory() / limit));
		return result;
	}
	
	@GetMapping("/categoryAll")
	public List<CategoryDTO> getAllCategory(){
		return categoryService.categoryAll();
	}
}
