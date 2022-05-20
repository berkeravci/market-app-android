package com.avci.berkeravciproject1;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private String name;
    private String number;
    private String price;


    public Product(String name, String number, String price) {
        this.name = name;
        this.number = number;
        this.price = price;

    }

    protected Product(Parcel in) {
        name = in.readString();
        number = in.readString();
        price = in.readString();

    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }





    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(number);
        parcel.writeString(price);

    }

    @Override
    public String toString() {
        return "Product" +"\n"+"\n"+
                "Name=" + name + "\n" +
                "Number=" + number + "\n" +
                "Price=" + price +"$"+"\n"
                ;
    }
}
