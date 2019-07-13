package com.kaushikam.hibernatejpain100steps.entity.inheritance.mapped_super_class;

import javax.persistence.Entity;
import java.net.URL;

@Entity
public class BlogPost extends Publication {

    private URL url;

    public BlogPost() {}

    public BlogPost(String title, URL url) {
        super(title);
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "BlogPost{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", url=" + url +
                '}';
    }
}
