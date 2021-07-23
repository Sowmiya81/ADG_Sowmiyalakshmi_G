package com.task5.stages;

public class Stage3_Model {
    String Title,Desc,Followers,Posts,Following;
    int image;

    public Stage3_Model()
    {
        this.Title = "";
        this.Desc = "";
        this.Followers = "";
        this.Posts = "";
        this.Following = "";
        this.image = 0;
    }

    public Stage3_Model(String title, String desc, String followers, String posts, String following,int image) {
        this.Title = title;
        this.Desc = desc;
        this.Followers = followers;
        this.Posts = posts;
        this.Following = following;
        this.image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getFollowers() {
        return Followers;
    }

    public void setFollowers(String followers) {
        Followers = followers;
    }

    public String getPosts() {
        return Posts;
    }

    public void setPosts(String posts) {
        Posts = posts;
    }

    public String getFollowing() {
        return Following;
    }

    public void setFollowing(String following) {
        Following = following;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
