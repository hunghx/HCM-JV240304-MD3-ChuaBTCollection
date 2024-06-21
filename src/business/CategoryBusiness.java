package business;

import entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryBusiness implements ICategoryDesign{
    public static List<Category> categories = new ArrayList<>();
    @Override
    public boolean create(Category category) {
        categories.add(category);
        return true;
    }

    @Override
    public List<Category> findAll() {
        return categories;
    }

    @Override
    public boolean update(Category category) {
        categories.set(categories.indexOf(findById(category.getId())),category);
        return true;
    }

    @Override
    public boolean deleteById(Integer id) {
        categories.remove(findById(id));
        return true;
    }

    @Override
    public Category findById(Integer id) {
        for (Category cat : categories){
            if (cat.getId() == id){
                return cat;
            }
        }
        return null;
    }
}
