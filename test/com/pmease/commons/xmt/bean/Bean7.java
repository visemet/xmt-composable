package com.pmease.commons.xmt.bean;

import java.util.Stack;

import org.dom4j.Element;

import com.pmease.commons.xmt.VersionedDocument;

public class Bean7 extends Bean1 {

    private String loginName;
    private String firstName;
    private String lastName;
    private int age;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @SuppressWarnings("unused")
    private void migrate1(VersionedDocument dom, Stack<Integer> versions) {
        Element element = dom.getRootElement().element("fullName");
        if (element != null) {
            String fullName = element.getText();
            int index = fullName.indexOf(' ');
            if (index == -1) {
                dom.getRootElement().addElement("firstName").setText(fullName);
            } else {
                dom.getRootElement().addElement("firstName")
                        .setText(fullName.substring(0, index));
                dom.getRootElement().addElement("lastName")
                        .setText(fullName.substring(index + 1, fullName.length() - 1));
            }
            element.detach();
        }
    }

    @SuppressWarnings("unused")
    private void migrate2(VersionedDocument dom, Stack<Integer> versions) {
        versions.pop();
    }
}
