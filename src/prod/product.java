
package prod;


public class product {
    
    private int id;
    private String name;
    private float price;
    private byte [] picture;
    private String addDate;
    static int pr;
    
    public product( int pid,String pn,float pprice,byte []ppic,String paddDate){
       
       this.id=pid;
       this.name=pn;
       this.addDate=paddDate;
       this.picture=ppic;
       this.price=pprice;
       
       
    }

    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public byte[] getPicture() {
        return picture;
    }

    public String getAddDate() {
        return addDate;
    }
    
    
}
