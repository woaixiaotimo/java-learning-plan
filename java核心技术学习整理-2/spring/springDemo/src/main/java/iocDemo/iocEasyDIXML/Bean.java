package iocDemo.iocEasyDIXML;


public class Bean {

    private String name;
    private Bean2 bean2;

    public Bean(String name) {
        this.name = name;
    }

    public void method() {
        System.out.println("name = " + name);
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    private String name2;

    public void setBean2(Bean2 bean2) {
        this.bean2 = bean2;
    }

    public Bean2 getBean2() {
        return bean2;
    }

    public String getNameSpeace() {
        return nameSpeace;
    }

    public void setNameSpeace(String nameSpeace) {
        this.nameSpeace = nameSpeace;
    }

    private String nameSpeace;
}
