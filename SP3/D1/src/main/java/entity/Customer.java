package entity;

import enums.CustomerType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.*;

/**
 *
 * @author Esben
 */
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "first_name")
    private String fName;
    @Column(name = "last_name")
    private String lName;
    
    @Column(name = "customer_type")
    @Enumerated(EnumType.STRING)
    private CustomerType cType;
    
    @ElementCollection()
    private List<String> hobbies = new ArrayList();
    
    @ElementCollection(fetch = FetchType.LAZY) 
    @MapKeyColumn(name = "phoneNumber") 
    @Column(name="phoneDesc")
    private Map<String,String> phoneNum = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }
    

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
    

    public CustomerType getcType() {
        return cType;
    }

    public void setcType(CustomerType cType) {
        this.cType = cType;
    }
    

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }
    
    public void addHobby(String hobby) {
        hobbies.add(hobby);
    }
    

    public Map<String, String> getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Map<String, String> phoneNum) {
        this.phoneNum = phoneNum;
    }
    
    public void addPhone(String num, String desc) {
        phoneNum.put(num, desc);
    }
}
