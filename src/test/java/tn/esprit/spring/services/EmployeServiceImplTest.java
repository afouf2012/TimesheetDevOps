package tn.esprit.spring.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger l = LogManager.getLogger(EmployeServiceImpl.class);

    @Test
    public void ajouterEmployeTest() {
        l.info("debut ajouterEmployeTest");
        int nbEmps = employeService.getNombreEmployeJPQL();
        l.info("Nombre employes: "+nbEmps);
        int idEmp = employeService.ajouterEmploye(new Employe("Majoul", "Khalil", "mohamedkhalil.majoul@esprit.tn", true, Role.INGENIEUR));
        l.info("id employe: "+idEmp);
        int newNbEmps = employeService.getNombreEmployeJPQL();
        l.info("Nouveau nombre employes: "+newNbEmps);
        Assert.assertEquals(nbEmps+1, newNbEmps);
        Employe e = employeService.getAllEmployes().stream().filter(emp -> emp.getId() == idEmp).findFirst().orElse(null);
        l.info("employe: "+e);
        Assert.assertNotNull(e);
        Assert.assertEquals("Majoul", e.getNom());
        Assert.assertEquals("Khalil", e.getPrenom());
        Assert.assertEquals("mohamedkhalil.majoul@esprit.tn", e.getEmail());
        Assert.assertTrue(e.isActif());
        Assert.assertEquals(Role.INGENIEUR, e.getRole());
        employeService.deleteEmployeById(idEmp);
        l.info("fin ajouterEmployeTest");
    }

    @Test
    public void deleteEmployeByIdTest() {
        l.info("debut deleteEmployeByIdTest");
        int idEmp = employeService.ajouterEmploye(new Employe("Majoul", "Khalil", "mohamedkhalil.majoul@esprit.tn", true, Role.INGENIEUR));
        l.info("id employe: "+idEmp);
        int nbEmps = employeService.getNombreEmployeJPQL();
        l.info("Nombre employes: "+nbEmps);
        employeService.deleteEmployeById(idEmp);
        int newNbEmps = employeService.getNombreEmployeJPQL();
        l.info("Nouveau nombre employes: "+newNbEmps);
        Assert.assertEquals(nbEmps-1, newNbEmps);
        Employe e = employeService.getAllEmployes().stream().filter(emp -> emp.getId() == idEmp).findFirst().orElse(null);
        l.info("employe: "+e);
        Assert.assertNull(e);
        l.info("fin deleteEmployeByIdTest");
    }

    @Test
    public void getEmployePrenomByIdTest() {
        l.info("debut getEmployePrenomByIdTest");
        int idEmp = employeService.ajouterEmploye(new Employe("Majoul", "Khalil", "mohamedkhalil.majoul@esprit.tn", true, Role.INGENIEUR));
        l.info("id employe: "+idEmp);
        String prenom = employeService.getEmployePrenomById(idEmp);
        l.info("Prenom employe: "+prenom);
        Assert.assertEquals("Khalil", prenom);
        employeService.deleteEmployeById(idEmp);
        l.info("fin getEmployePrenomByIdTest");
    }


}
