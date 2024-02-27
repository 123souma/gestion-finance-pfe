

 package com.example.finance.entites;


 import javax.persistence.*;
 import java.util.Date;

    @Entity
    public class Alert{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String type;

        private Date date;

        private String status;

        private String description;

        @ManyToOne
        @JoinColumn(name = "user_id", referencedColumnName = "cin")
        private User user;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Alert(Long id, String type, Date date, String status, String description, User user) {
            this.id = id;
            this.type = type;
            this.date = date;
            this.status = status;
            this.description = description;
            this.user = user;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Alert() {
        }

        @Override
        public String toString() {
            return "Alert{" +
                    "id=" + id +
                    ", type='" + type + '\'' +
                    ", date=" + date +
                    ", status='" + status + '\'' +
                    ", description='" + description + '\'' +
                    ", user=" + user +
                    '}';
        }
    }
