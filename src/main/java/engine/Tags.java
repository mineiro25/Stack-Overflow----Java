/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.util.Objects;

/**
 *
 * @author Nuno
 */
public class Tags {
    private long id;
    private String tag;
    private long count;

    public Tags(long id, String tag, long count) {
        this.id = id;
        this.tag = tag;
        this.count = count;
    }

    public Tags() {
        this.id=0;
        this.tag="";
        this.count=0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tags other = (Tags) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.count != other.count) {
            return false;
        }
        if (!Objects.equals(this.tag, other.tag)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tags{" + "id=" + id + ", tag=" + tag + ", count=" + count + '}';
    }
    
    
}
