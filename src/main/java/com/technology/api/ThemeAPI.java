package com.technology.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technology.dto.CategoryDTO;
import com.technology.dto.ThemeDTO;
import com.technology.service.ICategoryService;
import com.technology.service.IThemeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ThemeAPI {
	@Autowired
	private IThemeService themeService;

	@PostMapping("/theme")
	public ThemeDTO createTheme(@RequestBody ThemeDTO themeDTO) {
		return themeService.save(themeDTO);
	}

	@PutMapping("/theme/{id}")
	public ThemeDTO updateTheme(@RequestBody ThemeDTO themeDTO, @PathVariable long id) {
		themeDTO.setId(id);
		return themeService.save(themeDTO);
	}

	@DeleteMapping("/theme")
	public void deleteCategory(@RequestBody long[] ids) {
		themeService.delete(ids);
	}
	
	@GetMapping("/theme")
	public List<ThemeDTO> getThemeByCategoryId(@RequestParam("categoryId") long id){
		return themeService.findThemeByCategory(id);
	}
}
