
package DataDto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "perros")
public class Perros implements Serializable{
    
    
    
     @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private  int id;
     @Column(name = "name")
    private String name;
    @Column(name = "rasa")
    private String rasa;
      @ManyToOne
    @JoinColumn(name = "Perros", referencedColumnName = "id")
    private Perros perro;
      
      public Perros()
    {
    }

    public Perros(int id)
    {
        this.id = id;
    }

    public Perros(int id, String name, String rasa, Perros perro) {
        this.id = id;
        this.name = name;
        this.rasa = rasa;
        this.perro = perro;
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

    public String getRasa() {
        return rasa;
    }

    public void setRasa(String rasa) {
        this.rasa = rasa;
    }

    public Perros getPerro() {
        return perro;
    }

    public void setPerro(Perros perro) {
        this.perro = perro;
    }

      
}