/*
 * Digital.java
 * 
 * All Rights Reserved.
 * Copyright (c) 2020 FPT University
 */
package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Digital class.<br>
 * 
 * <pre>
 * Class thực hiện thao tác với Digital
 * Trong class này sẽ tiến hành các xử lí dưới đây.
 * 
 * ・get id
 * ・get title
 * ・get description
 * ・get image
 * ・get author
 * ・get Date Convert
 * ・get Short Description
 * 
 * ・Set id
 * ・Set title
 * ・Set description
 * ・Set image
 * ・Set author
 * ・Set timePost
 * ・Set shortDes
 *
 * </pre>
 * 
 * @author FU AnhTHPHE130193
 * @version 1.0
 */
public class Digital {
    /** Store id */
    private int id;
    /** Store title */
    private String title;
    /** Store description */
    private String description;
    /** Store image url */
    private String image;
    /** Store author */
    private String author;
    /** Store time post */
    private Date timePost;
    /** Store short description */
    private String shortDes;

    /**
     * Constructor.<br>
     */
    public Digital() {
    }

    
    /**
     * Constructor.<br>
     * 
     * @param id
     * @param title
     * @param description
     * @param image 
     * @param author 
     * @param timePost 
     * @param shortDes 
     * 
     */
    public Digital(int id, String title, String description, String image, String author, Date timePost, String shortDes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.author = author;
        this.timePost = timePost;
        this.shortDes = shortDes;
    }

    /**
     * Xử lí dữ liệu biến TimePost.<br>
     * 
     * <pre>
     * Method sẽ convert kiểu Date sang kiểu String của biến Timepost.
     * 
     * ♦ Trình tự xử lí.
     *   1.Sử dụng class SimpleDateFormat để parse Date TimePost sang String
     *   
     * </pre>
     * 
     */
    public String getDateConvert() {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMM dd yyyy - hh:mm:");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("a");
        String date = dateFormat1.format(this.timePost) + dateFormat2.format(this.timePost).toLowerCase();
        return date; 
    }
    
    /**
     * Get id.<br>
     * 
     * @return the id.
     */
    public int getId() {
        return id;
    }
    /**
     * Set id.<br>
     * 
     * @param id the id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
    * Get title.<br>
    * 
    * @return the title.
    */

    public String getTitle() {
        return title;
    }
    
    /**
     * Set title.<br>
     * 
     * @param title the title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
    * Get description.<br>
    * 
    * @return the description.
    */
    public String getDescription() {
        return description;
    }

    /**
     * Set description.<br>
     * 
     * @param description the description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
    * Get image.<br>
    * 
    * @return the image.
    */
    public String getImage() {
        return image;
    }

    /**
     * Set Image.<br>
     * 
     * @param image the image.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
    * Get author.<br>
    * 
    * @return the author.
    */
    public String getAuthor() {
        return author;
    }

    /**
     * Set author.<br>
     * 
     * @param author the author.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
    * Get time post.<br>
    * 
    * @return the timePost.
    */
    public Date getTimePost() {
        return timePost;
    }

    /**
     * Set time post.<br>
     * 
     * @param timePost the timePost.
     */
    public void setTimePost(Date timePost) {
        this.timePost = timePost;
    }

    /**
    * Get short description.<br>
    * 
    * @return the shortDes.
    */
    public String getShortDes() {
        return shortDes;
    }

    /**
     * Set short description.<br>
     * 
     * @param shortDes the shortDes.
     */
    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }
    
    
    
    
}
