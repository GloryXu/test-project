package com.redsun.springlock.dto;

/**
 * user
 *
 * @author qiquan
 * @date 2021/07/24 17:57
 */
public class User extends BasePeople implements LockKey {
    private String name;

    private Integer age;

    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String getLockKey() {
        return getName();
    }
}
