package model;

public class Movie {
    private int id;
    private String title;
    private String genre;
    private double price;
    private String showTime;
    private String imagePath; 
    public Movie() {
    	
    }
    public Movie(String title, String genre, double price, String showTime, String imageUrl) {
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.showTime = showTime;
        this.imagePath = imageUrl; // Set imageUrl
    }
    public Movie(int id, String title, String genre, double price, String showTime, String imagePath) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.showTime = showTime;
        this.imagePath = imagePath;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
    public void setImageUrl(String ImagePath) {
        this.imagePath = ImagePath;
    }
    public String getImageUrl() {
        return imagePath;
    }
}
