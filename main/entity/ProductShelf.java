package main.entity;

public class ProductShelf {

    String shelfId;
    Product product;
    int productCount;

    public String getShelfId() {
        return shelfId;
    }

    public void setShelfId(String shelfId) {
        this.shelfId = shelfId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    Product getProduct(Product product) {
        if (!product.getProductId().isEmpty()) {
            return product;
        }
        else{
            System.out.print("Product " + product.getName() + " not found in shelf");
            return null;
        }
    }

    void addProduct(Product product, int productCount){
        this.product = product;
        this.productCount = productCount;
    }

    void reduceCount(int count){
        if(count > productCount){
            System.out.print("Product " + product.getName() + " not enough in the shelf");
        } else{
            this.productCount -= count;
        }
    }
}