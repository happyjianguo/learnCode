package cn.yufu.posp.route.domain.model;



/**
 * TranRoute entity. @author MyEclipse Persistence Tools
 */

public class TranRoute  implements java.io.Serializable {


    // Fields    

     private TranRouteId id;


    // Constructors

    /** default constructor */
    public TranRoute() {
    }

    
    /** full constructor */
    public TranRoute(TranRouteId id) {
        this.id = id;
    }

   
    // Property accessors

    public TranRouteId getId() {
        return this.id;
    }
    
    public void setId(TranRouteId id) {
        this.id = id;
    }
   








}