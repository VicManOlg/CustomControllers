
package model;

/**
 *
 * @author Victor
 */
public class CategoryDTO {
    private int catId;
    private String catName;

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public CategoryDTO(int catId, String catName) {
        this.catId = catId;
        this.catName = catName;
    }

    @Override
    public String toString() {
        return  catName ;
    }

    public CategoryDTO() {
    }
    
    
}
