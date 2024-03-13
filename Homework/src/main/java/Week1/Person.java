package Week1;

import org.springframework.beans.factory.annotation.Autowired;

public class Person {
    private Outfit outfit;
    private HairStyle hairStyle;

    public Person() {
    }
    @Autowired
    public Person(Outfit outfit, HairStyle hairStyle) {
        this.outfit = outfit;
        this.hairStyle = hairStyle;
    }
}
