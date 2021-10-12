package tn.esprit.spring.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceImplTest {
    @Autowired
    private IEmployeService employeService;

    @Test
    public void ajouterEmployeTest() {
        int nbEmps = employeService.getNombreEmployeJPQL();
        int idEmp = employeService.ajouterEmploye(new Employe("Majoul", "Khalil", "mohamedkhalil.majoul@esprit.tn", true, Role.INGENIEUR));
        Assert.assertEquals(nbEmps+1, employeService.getNombreEmployeJPQL());
        Employe e = employeService.getAllEmployes().stream().filter(emp -> emp.getId() == idEmp).findFirst().orElse(null);
        Assert.assertNotNull(e);
        Assert.assertEquals("Majoul", e.getNom());
        Assert.assertEquals("Khalil", e.getPrenom());
        Assert.assertEquals("mohamedkhalil.majoul@esprit.tn", e.getEmail());
        Assert.assertTrue(e.isActif());
        Assert.assertEquals(Role.INGENIEUR, e.getRole());
        employeService.deleteEmployeById(idEmp);
    }

    @Test
    public void deleteEmployeByIdTest() {
        int idEmp = employeService.ajouterEmploye(new Employe("Majoul", "Khalil", "mohamedkhalil.majoul@esprit.tn", true, Role.INGENIEUR));
        int nbEmps = employeService.getNombreEmployeJPQL();
        employeService.deleteEmployeById(idEmp);
        Assert.assertEquals(nbEmps-1, employeService.getNombreEmployeJPQL());
        Employe e = employeService.getAllEmployes().stream().filter(emp -> emp.getId() == idEmp).findFirst().orElse(null);
        Assert.assertNull(e);
    }

    @Test
    public void getEmployePrenomByIdTest() {
        int idEmp = employeService.ajouterEmploye(new Employe("Majoul", "Khalil", "mohamedkhalil.majoul@esprit.tn", true, Role.INGENIEUR));
        String prenom = employeService.getEmployePrenomById(idEmp);
        Assert.assertEquals("Khalil", prenom);
        employeService.deleteEmployeById(idEmp);
    }


}
