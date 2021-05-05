package ru.job4j.serialization.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement
public class Cat {
    @XmlAttribute
    private boolean isMan;
    @XmlAttribute
    private int age;
    @XmlAttribute
    private String name;
    @XmlElementWrapper(name = "eats")
    @XmlElement(name = "eat")
    private String[] eats;
    private Contact contact;

    public Cat() {

    }

    public Cat(boolean isMan, int age, String name, String[] eats, Contact contact) {
        this.isMan = isMan;
        this.age = age;
        this.name = name;
        this.eats = eats;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Cat{"
                + "isMan=" + isMan
                + ", age=" + age
                + ", name='" + name + '\''
                + ", eats=" + Arrays.toString(eats)
                + ", contact=" + contact
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        String[] eats = {"meet", "fish", "water"};
        Cat cat = new Cat(true, 7, "Dr.Feelgood", eats, new Contact("777-77-77-2"));
        String xml = "";

        JAXBContext context = JAXBContext.newInstance(Cat.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(cat, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (StringReader reader = new StringReader(xml)) {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Cat catty = (Cat) unmarshaller.unmarshal(reader);
            System.out.println(catty);
        }
    }
}
