package org.apache.dubbo.samples.generic.api;

import java.io.Serializable;

/**
 * Created by ZDZ on 2019/5/20.
 */
public interface IUserService extends IService<IUserService.Params, IUserService.User>{
    public static class Params implements Serializable {
        private static final long serialVersionUID = 1L;

        public Params(String query){

        }
    }

    public static class User implements Serializable {
        private static final long serialVersionUID = 1L;
        private int id;
        private String name;
        public User(int id, String name){
            super();
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User [id=" + id + ", name=" + name + "]";
        }
    }
}
