package ru.job4j.serialization.json;
import com.sun.xml.txw2.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;

@XmlElement(value = "contact")
public class Contact {

    @XmlAttribute
    private String phone;

    public Contact() {

    }

    public Contact(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phone='" + phone + '\''
                + '}';
    }
}